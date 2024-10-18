package com.erasmos.kotlin_notebook

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.ints.shouldBeGreaterThanOrEqual
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import java.time.LocalDate
import java.time.Month
import java.time.Month.DECEMBER

class AbstractClassSpec : FreeSpec({

    "An abstract class cannot be instantiated, but can have implemented methods; examples:" - {
        "one" {
            val cellist = Cellist("Steven Isserlis", LocalDate.of(1958, DECEMBER, 19))

            cellist.name shouldBe "Steven Isserlis"
            cellist.dateOfBirth shouldBe LocalDate.of(1958, DECEMBER, 19)
            cellist.getCurrentAge()?.shouldBeGreaterThanOrEqual(58)
            cellist.getDescription() shouldBe "Steven Isserlis, Cello"
        }
        "two" {
            val connieShih = Pianist("Connie Shih")

            connieShih.name shouldBe "Connie Shih"
            connieShih.dateOfBirth shouldBe null
            connieShih.getCurrentAge() shouldBe null
            connieShih.getDescription() shouldBe "Connie Shih, Piano"
        }
    }

})
