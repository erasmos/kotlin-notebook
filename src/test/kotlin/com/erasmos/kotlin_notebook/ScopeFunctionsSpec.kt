package com.erasmos.kotlin_notebook

import com.erasmos.kotlin_notebook.Grade.B
import com.erasmos.kotlin_notebook.Grade.D
import com.erasmos.kotlin_notebook.Section.FrontStalls
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.doubles.plusOrMinus
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.types.shouldBeSameInstanceAs

class ScopeFunctionsSpec : FreeSpec({

    "there are several types of 'scope' functions:" - {
        "let" {
            val result: String =
                Data.Seats.all
                    .filter { it.id.startsWith("AA")}
                    .sortedBy { it.id }
                    .let { seats -> seats.joinToString(",") { it.id } }

            result shouldBe "AA1,AA17,AA18,AA2,AA3"
        }
        "run" {
            val violinist = Data.Performers.ireneDuval

            val result = violinist.run { "$name plays the ${instrument.toString().lowercase()}." }

            result shouldBe "Irène Duval plays the violin."
        }
        "with" {
            val violinist = Data.Performers.ireneDuval

            val result = with(violinist) { "$name plays the ${instrument.toString().lowercase()}." }

            result shouldBe "Irène Duval plays the violin."
        }
        "apply" {
            val seat =     Seat("W10", Section.RearStalls, Grade.E)
            seat.lastInspected shouldBe null

            val modifiedSeat = seat.apply { inspect {  } }

            modifiedSeat shouldBeSameInstanceAs seat
            modifiedSeat.lastInspected shouldNotBe null
        }
        "also" {
            val seatOne = Seat("AA1", FrontStalls, D)
            val seatTwo = Seat("AA2", FrontStalls, D)
            val seatThree = Seat("A2", FrontStalls, B)
            val seatFour = Seat("A3", FrontStalls, B)
            val seats = listOf(
                seatOne,
                seatTwo,
                seatThree,
                seatFour
            )
            seats.all { it.lastInspected == null } shouldBe true

            val result: List<Seat> = seats
                .filter { it.grade == B }
                .also { it.forEach{seat -> seat.inspect {  }} }


            result shouldBe listOf(seatThree, seatFour)
            seatOne.lastInspected shouldBe null
            seatTwo.lastInspected shouldBe null
            seatThree.lastInspected shouldNotBe null
            seatFour.lastInspected shouldNotBe null
        }
    }


})