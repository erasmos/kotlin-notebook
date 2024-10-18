package com.erasmos.kotlin_notebook

import com.erasmos.kotlin_notebook.Grade.D
import com.erasmos.kotlin_notebook.Section.FrontStalls
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class DoWhileSpec : FreeSpec({
    "'do / while' is an instruction, not an expression, which takes a predicate" {
        val seats = mutableListOf<Seat>()
        var index = 0
        do {
            seats.add(Data.Seats.all[index])
            index++
        } while (index < 4)
        seats shouldBe listOf(
            Seat("AA1", FrontStalls, D),
            Seat("AA2", FrontStalls, D),
            Seat("AA3", FrontStalls, D),
            Seat("AA17", FrontStalls, D)
        )
    }

})