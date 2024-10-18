package com.erasmos.kotlin_notebook

import com.erasmos.kotlin_notebook.Data.Concerts.borisGiltburgEveningOf31stOctober2024
import com.erasmos.kotlin_notebook.Grade.*
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDate
import java.time.LocalTime
import java.time.Month
import java.time.Month.JUNE
import java.time.Month.OCTOBER

class LambdaWithReceiverSpec : FreeSpec({

    "Lambdas with receivers can be used for creating DSLs; example:" - {
        "one" {
            val concert = concert {
                name("Boris Giltburg, piano")
                on(2024, OCTOBER, 31)
                starts(19, 30)
                ends(21, 30)
                forA(40.00)
                forB(36.00)
                forC(32.00)
                forD(26.00)
                forE(18.00)
                performer {
                    name("Boris Giltburg")
                    birthday(1984, JUNE, 21)
                }
            }

            concert shouldBe borisGiltburgEveningOf31stOctober2024
        }
    }

}) {

    companion object {

        class ConcertScope {

            var name: String? = null
            private var date: LocalDate? = null
            private var startTime: StartTime? = null
            private var endTime: EndTime? = null
            private val prices: MutableMap<Grade, Double> = mutableMapOf()
            private val performers: MutableList<Performer> = mutableListOf()

            fun name(value: String) {
                name = value
            }

            fun on(year: Int, month: Month, dayOfMonth: Int) {
                date = LocalDate.of(year, month, dayOfMonth)
            }

            fun starts(hourOf24: Int, minute: Int) {
                startTime = StartTime(LocalTime.of(hourOf24, minute, 0))
            }

            fun ends(hourOf24: Int, minute: Int) {
                endTime = EndTime(LocalTime.of(hourOf24, minute, 0))
            }

            fun forA(price: Double) {
                prices[A] = price
            }

            fun forB(price: Double) {
                prices[B] = price
            }

            fun forC(price: Double) {
                prices[C] = price
            }

            fun forD(price: Double) {
                prices[D] = price
            }

            fun forE(price: Double) {
                prices[E] = price
            }

            fun performer(init: PerformerScope.() -> Unit) {
                val scope = PerformerScope()
                scope.init()
                val performer = scope.asPerformer()
                performers.add(performer)
            }


            fun asConcert(): Concert =
                Concert(
                    name!!,
                    performers,
                    date!!,
                    startTime!!,
                    endTime!!,
                    prices
                )

            companion object {

                class PerformerScope {
                    var name: String? = null
                    var birthday: LocalDate? = null

                    fun name(value: String) {
                        name = value
                    }

                    fun birthday(year: Int, month: Month, dayOfMonth: Int) {
                        birthday = LocalDate.of(year, month, dayOfMonth)
                    }


                    fun asPerformer(): Performer =
                        Pianist(name!!, birthday)

                }


            }
        }

        fun concert(init: ConcertScope.() -> Unit): Concert {
            val scope = ConcertScope()
            scope.init()
            return scope.asConcert()
        }
    }
}