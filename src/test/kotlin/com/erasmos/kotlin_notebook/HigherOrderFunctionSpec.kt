package com.erasmos.kotlin_notebook

import com.erasmos.kotlin_notebook.Data.Performers
import com.erasmos.kotlin_notebook.Grade.B
import com.erasmos.kotlin_notebook.Section.Balcony
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class HigherOrderFunctionSpec : FreeSpec({

    "a function can be" - {
        "passed as an argument to another function" {
            val performerAges = Performers.all.mapNotNull { it.getCurrentAge() }
            val sumOfPerformerAges = performerAges.reduce { x: Int, y: Int -> x + y }

            sumOfPerformerAges shouldBe 215
        }
        "returned from another function; example" - {
            "one" {
                val makeSeatLowGrade = makeGradeChanger(Grade.E)

                val updatedSeat = makeSeatLowGrade(Seat("A1", Balcony, B))

                updatedSeat shouldBe Seat("A1", Balcony, Grade.E)
            }
        }
    }

}) {
    companion object {
        fun makeGradeChanger(grade: Grade): (Seat) -> Seat = { seat: Seat -> seat.copy(grade = grade) }
    }
}