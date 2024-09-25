package com.erasmos.kotlin_notebook

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import java.util.function.Predicate

class SingleAbstractMethodSpec : FreeSpec({

    "a single abstract method (SAM) is any interface with only one method. This interface can be" - {
        "an existing Java interface (like Predicate)" {
            val result = sumUsingStandardPredicate(4) { it % 2 == 0 }

            result shouldBe 6
        }
        "or a Kotlin interface" {
            val result = sumUsingCustomPredicate(4) { it % 2 == 0 }

            result shouldBe 6
        }
    }


}) {

    companion object {

        // 'fun' is optional, but it does enforce that there is one and only one method
        fun interface CustomPredicate<T> {
            fun test(value: T): Boolean
        }

        fun sumUsingStandardPredicate(maxNumber: Int, standardPredicate: Predicate<Int>): Int =
            (1..maxNumber).filter { standardPredicate.test(it) }.sum()

        fun sumUsingCustomPredicate(maxNumber: Int, customPredicate: CustomPredicate<Int>): Int =
            (1..maxNumber).filter { customPredicate.test(it) }.sum()


    }
}