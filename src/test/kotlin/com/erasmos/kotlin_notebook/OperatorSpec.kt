package com.erasmos.kotlin_notebook

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.assertions.throwables.shouldThrowUnit
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import kotlin.math.abs

class OperatorSpec : FreeSpec({

    "we can implement operators, such as" - {
        "+ (add)" {
            Fraction(1, 4) + Fraction(2, 4) shouldBe Fraction(3, 4)
            Fraction(1, 8) + Fraction(2, 8) shouldBe Fraction(3, 8)
            Fraction(1, 4) + Fraction(3, 8) shouldBe Fraction(5, 8)
            Fraction(2, 9) + Fraction(1, 9) shouldBe Fraction(1, 3)
        }
        "- (subtract)" {
            Fraction(3, 4) - Fraction(1, 4) shouldBe Fraction(1, 2)
            Fraction(6, 8) - Fraction(4, 16) shouldBe Fraction(1, 2)
        }
        "- (negate)" {
            -Fraction(3, 4) shouldBe Fraction(-3, 4)
            -Fraction(4, 8) shouldBe Fraction(-1, 2)
        }
        "* (multiply)" {
            Fraction(1, 2) * Fraction(1, 2) shouldBe Fraction(1, 4)
            Fraction(1, 4) * Fraction(4, 2) shouldBe Fraction(1, 2)
            Fraction(-1, 4) * Fraction(4, 2) shouldBe Fraction(-1, 2)
            Fraction(-1, 4) * Fraction(-4, 2) shouldBe Fraction(1, 2)
            Fraction(3, 4) * Fraction(1, 3) shouldBe Fraction(1, 4)
        }
        "/ (divide)" {
            Fraction(1, 2) / Fraction(1, 2) shouldBe Fraction(1, 1)
            Fraction(4, 2) / Fraction(4, 2) shouldBe Fraction(1, 1)
            Fraction(3, 4) / Fraction(3, 1) shouldBe Fraction(1, 4)
        }
        "++ (increment)" - {
            "prefix" {
                var original = Fraction(1, 2)
                val incremented = ++original

                original shouldBe Fraction(3, 2)
                incremented shouldBe Fraction(3, 2)
            }
            "postfix" {
                var original = Fraction(2, 16)
                val result = original++

                original shouldBe Fraction(9, 8)
                result shouldBe Fraction(2, 16)
            }

        }
        "-- (decrement)" - {
            "prefix" {
                var original = Fraction(3, 2)
                val incremented = --original

                original shouldBe Fraction(1, 2)
                incremented shouldBe Fraction(1, 2)
            }
            "postfix" {
                var original = Fraction(2, 4)
                val returnedOriginal = original--

                original shouldBe Fraction(-1, 2)
                returnedOriginal shouldBe Fraction(2, 4)
            }
        }
        "[] (get)" - {
            "numerator" {
                Fraction(3, 2)[0] shouldBe 3
            }
            "denominator" {
                Fraction(3, 2)[1] shouldBe 2
            }
            "other" {
                val exception = shouldThrow<IllegalArgumentException> {
                    Fraction(3, 2)[6]
                }
                exception.message shouldBe "Unsupported index [6]"
            }
        }
        "[] (set)" - {
            "numerator" {
                val value = Fraction(3, 2)
                value[0] = 1

                value shouldBe Fraction(1, 2)
            }
            "denominator" {
                val value = Fraction(3, 2)
                value[1] = 4

                value shouldBe Fraction(3, 4)
            }
            "other" {
                val exception = shouldThrowUnit<IllegalArgumentException> {
                    val value = Fraction(3, 2)
                    value[4] = 4
                }
                exception.message shouldBe "Unsupported index [4]"
            }
        }
        "< (less than)" {
            (Fraction(1, 2) < Fraction(3, 4)) shouldBe true
            (Fraction(1, 1) < Fraction(1, 1)) shouldBe false
            (Fraction(6, 8) < Fraction(2, 4)) shouldBe false
            (Fraction(3, 4) < Fraction(3, 4)) shouldBe false
        }
        "<= (less than or equal to)" {
            (Fraction(1, 2) <= Fraction(3, 4)) shouldBe true
            (Fraction(1, 1) <= Fraction(1, 1)) shouldBe true
        }
        "> (greater than)" {
            (Fraction(1, 2) > Fraction(3, 4)) shouldBe false
            (Fraction(1, 1) > Fraction(1, 1)) shouldBe false
            (Fraction(6, 8) > Fraction(2, 4)) shouldBe true
            (Fraction(3, 4) > Fraction(3, 4)) shouldBe false
        }
        ">= (greater than or equal to)" {
            (Fraction(1, 2) >= Fraction(3, 4)) shouldBe false
            (Fraction(1, 1) >= Fraction(1, 1)) shouldBe true
            (Fraction(6, 8) >= Fraction(3, 4)) shouldBe true
            (Fraction(3, 4) >= Fraction(3, 4)) shouldBe true
        }
        "destructuring" {
            val (numerator, denominator) = Fraction(1, 2)

            numerator shouldBe 1
            denominator shouldBe 2
        }
    }

}) {

    class Fraction(var numerator: Int, var denominator: Int) {

        operator fun plus(other: Fraction): Fraction {
            if (denominator == other.denominator) return simplify(Fraction(numerator + other.numerator, denominator))
            val (firstNormalised, secondNormalised) = normaliseDenominator(this, other)
            return simplify(firstNormalised + secondNormalised)
        }

        operator fun minus(other: Fraction): Fraction = simplify(this + (-other))

        operator fun unaryMinus(): Fraction =
            simplify(Fraction(-numerator, denominator))

        operator fun times(other: Fraction): Fraction =
            simplify(Fraction(numerator * other.numerator, denominator * other.denominator))

        operator fun div(other: Fraction): Fraction =
            times(Fraction(other.denominator, other.numerator))

        operator fun inc(): Fraction = plus(Fraction(denominator, denominator))

        operator fun dec(): Fraction = minus(Fraction(denominator, denominator))

        operator fun get(index: Int): Int =
            when (index) {
                0 -> numerator
                1 -> denominator
                else -> throw IllegalArgumentException("Unsupported index [$index]")
            }

        operator fun set(index: Int, value: Int) {
            when (index) {
                0 -> numerator = value
                1 -> denominator = value
                else -> throw IllegalArgumentException("Unsupported index [$index]")
            }

        }

        operator fun compareTo(other: Fraction): Int {
            if (denominator == other.denominator) return numerator - other.numerator
            val (firstNormalised, secondNormalised) = normaliseDenominator(this, other)
            return firstNormalised.numerator - secondNormalised.numerator
        }

        operator fun component1() = numerator
        operator fun component2() = denominator

        override fun toString(): String {
            return "$numerator/$denominator"
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Fraction

            if (numerator != other.numerator) return false
            if (denominator != other.denominator) return false

            return true
        }

        override fun hashCode(): Int {
            var result = numerator
            result = 31 * result + denominator
            return result
        }


        companion object {

            private fun simplify(fraction: Fraction): Fraction {
                val greatestCommonDivisor = greatestCommonDivisor(fraction.numerator, fraction.denominator)
                return Fraction(fraction.numerator / greatestCommonDivisor, fraction.denominator / greatestCommonDivisor)
            }

            private tailrec fun greatestCommonDivisor(first: Int, second: Int): Int {
                val (smallest, largest) = listOf(abs(first), abs(second)).sorted()
                val remainder = largest % smallest
                return if (remainder == 0) smallest else greatestCommonDivisor(smallest, remainder)
            }

            private fun normaliseDenominator(first: Fraction, second: Fraction): Pair<Fraction, Fraction> {
                val commonDenominator = first.denominator * second.denominator
                return Pair(
                    Fraction(first.numerator * second.denominator, commonDenominator),
                    Fraction(second.numerator * first.denominator, commonDenominator)
                )
            }
        }
    }
}