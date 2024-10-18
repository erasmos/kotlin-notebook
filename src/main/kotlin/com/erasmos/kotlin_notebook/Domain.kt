package com.erasmos.kotlin_notebook

import com.erasmos.kotlin_notebook.Performer.Companion.NAME_UNKNOWN
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.Period
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract
import kotlin.reflect.KProperty

data object ConcertHall

@Builder
data class Seat(val id: String, val section: Section, val grade: Grade) {
    var lastInspected: LocalDateTime? = null

    inline fun inspect(checks: (Seat) -> Unit) {
        checks(this)
        lastInspected = LocalDateTime.now()
    }
}

enum class Section {
    Balcony, FrontStalls, RearStalls
}


enum class Grade {
    A, B, C, D, E
}

@JvmInline
value class StartTime(val value: LocalTime)

@JvmInline
value class EndTime(val value: LocalTime)

data class Concert(
    val name: String,
    val performers: List<Performer>,
    val date: LocalDate,
    val startTime: StartTime,
    val endTime: EndTime,
    val prices: Map<Grade, Double>
) {
    val tickets =
        Data.Seats.all.filter { price(it.grade) != null }.map { Ticket(this, it) }

    fun price(grade: Grade): Double? = prices[grade]
}

@Builder
data class Ticket(val concert: Concert, val seat: Seat) {
    val price: Double? = concert.price(seat.grade)
}


interface Performer {

    val instrument: Instrument
    val name: String
    // val name: String = "Unknown" : Property initializers are not allowed in interfaces
    val dateOfBirth: LocalDate?

    fun getDescription(): String
    fun getCurrentAge(): Int? = dateOfBirth?.let { Period.between(dateOfBirth, LocalDate.now()).years }

    companion object {
        const val NAME_UNKNOWN: String = "Unknown"

        @OptIn(ExperimentalContracts::class)
        fun isPianist(performer: Performer): Boolean {
            contract {
                returns(true) implies (performer is Pianist)
            }
            return performer.instrument == Instrument.Piano
        }
    }

}

class ArtistInResidence(private val performer: Performer) : Performer by performer {
    override fun getDescription(): String =
        "[Artist In Residence] " + performer.getDescription()
}

data class Pianist(override val name: String, override val dateOfBirth: LocalDate?) : Performer {
    constructor(name: String) : this(name, null)
    constructor() : this(NAME_UNKNOWN, null)

    override val instrument: Instrument = Instrument.Piano
    override fun getDescription(): String = "$name, $instrument"

    fun openLid() {}
}

data class Cellist(override val name: String, override val dateOfBirth: LocalDate? = null) : Performer {
    override val instrument: Instrument = Instrument.Cello
    override fun getDescription(): String = "$name, $instrument"
}

data class Violinist(override val name: String, override val dateOfBirth: LocalDate?) : Performer {
    constructor(name: String) : this(name, null)

    override val instrument: Instrument = Instrument.Violin
    override fun getDescription(): String = "$name, $instrument"
}

enum class Instrument(val providedByConcertHall: Boolean) {
    Cello(false), Piano(true), Violin(false);
}

enum class PerformanceStatus {
    Unknown, Before, During, After
}

class Performance(val concert: Concert) {
    var status: PerformanceStatus by LoggedVar("performance-${concert.name}", PerformanceStatus.Unknown)
    lateinit var review: String
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Performance

        return concert == other.concert
    }

    override fun hashCode(): Int {
        return concert.hashCode()
    }

    override fun toString(): String {
        return "Performance(concert=${concert.name}, status=$status)"
    }
}

object Logger {
    val messages = mutableListOf<String>()
    fun clear() {
        messages.clear()
    }
}

class LoggedVar<A>(private val logName: String, defaultValue: A) {
    private var currentValue: A = defaultValue
    operator fun getValue(thisRef: Any?, property: KProperty<*>): A {
        Logger.messages.add("[$logName] reading var [${property.name}] which is currently [${currentValue}]")
        return currentValue
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: A) {
        Logger.messages.add("[$logName] writing var [${property.name}] which was [${value}] but will now be [$value]")
        currentValue = value
    }
}

data class PerformerList<out T : Performer>(val performers: List<T>) {
    fun getYoungest(): T = performers.filter { it.dateOfBirth != null }.sortedByDescending { it.dateOfBirth }.first()
    fun getOldest(): T = performers.filter { it.dateOfBirth != null }.sortedBy { it.dateOfBirth }.first()

}

abstract class Membership(open val name: String, val priorityBookingOrder: PriorityBookingOrder) {
    abstract fun requestAcknowledgementOnWebsite(): Boolean
    abstract fun requestToAttendOpenRehearsal(): Boolean
    abstract fun requestToAttendSpecialAnnualEvent(): Boolean
}

class Friend(override val name: String) : Membership(name, PriorityBookingOrder.Fourth) {
    override fun requestAcknowledgementOnWebsite(): Boolean = throw MembershipPrivilegeAbsentException()
    override fun requestToAttendOpenRehearsal(): Boolean = throw MembershipPrivilegeAbsentException()
    override fun requestToAttendSpecialAnnualEvent(): Boolean = throw MembershipPrivilegeAbsentException()
}

class Supporter(override val name: String) : Membership(name, PriorityBookingOrder.Third) {
    override fun requestAcknowledgementOnWebsite(): Boolean = throw MembershipPrivilegeAbsentException()
    override fun requestToAttendOpenRehearsal(): Boolean = throw MembershipPrivilegeAbsentException()
    override fun requestToAttendSpecialAnnualEvent(): Boolean = throw MembershipPrivilegeAbsentException()
}

class Benefactor(override val name: String) : Membership(name, PriorityBookingOrder.Second) {
    override fun requestAcknowledgementOnWebsite(): Boolean = throw MembershipPrivilegeAbsentException()
    override fun requestToAttendOpenRehearsal() = true
    override fun requestToAttendSpecialAnnualEvent(): Boolean = throw MembershipPrivilegeAbsentException()
}

class Patron(override val name: String) : Membership(name, PriorityBookingOrder.First) {
    override fun requestAcknowledgementOnWebsite(): Boolean = true
    override fun requestToAttendOpenRehearsal() = true
    override fun requestToAttendSpecialAnnualEvent(): Boolean = true
}

class MembershipPrivilegeAbsentException : RuntimeException()

enum class PriorityBookingOrder {
    First, Second, Third, Fourth
}

