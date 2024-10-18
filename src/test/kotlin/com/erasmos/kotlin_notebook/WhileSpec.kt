package com.erasmos.kotlin_notebook

import com.erasmos.kotlin_notebook.Data.Seats
import com.erasmos.kotlin_notebook.Grade.D
import com.erasmos.kotlin_notebook.Section.FrontStalls
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class WhileSpec: FreeSpec({
    "'while' is an instruction, not an expression, which takes a predicate"  {
            val resultList = mutableListOf<Seat>()
            var index = 0
            while(index < 3) {
                resultList.add(Seats.all[index])
                index++
            }
            resultList shouldBe listOf(
                Seat("AA1", FrontStalls, D),
                Seat("AA2", FrontStalls, D),
                Seat("AA3", FrontStalls, D)
            )
    }

})