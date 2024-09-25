package com.erasmos.kotlin_notebook

import com.erasmos.kotlin_notebook.Data.Concerts.borisGiltburgEveningOf31stOctober2024
import com.erasmos.kotlin_notebook.Data.Concerts.stevenIsserlisAndOthersEveningOf4thNovember2024
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.equals.shouldNotBeEqual
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import java.time.LocalDate
import java.time.Month
import java.time.Month.JUNE
import java.time.Month.MAY

class ClassSpec : FreeSpec({

    "a class" - {
        "can have multiple constructors; examples:" - {
            "one" {
                val pianist = Pianist("Boris Giltburg", LocalDate.of(1984, JUNE, 21))

                pianist.name shouldBe "Boris Giltburg"
                pianist.dateOfBirth shouldBe LocalDate.of(1984, JUNE, 21)
            }
            "two" {
                val pianist = Pianist("Boris Giltburg")

                pianist.name shouldBe "Boris Giltburg"
                pianist.dateOfBirth shouldBe null
            }
            "three" {
                val pianist = Pianist()

                pianist.name shouldBe "Unknown"
                pianist.dateOfBirth shouldBe null
            }
        }
        "can have non-constructor properties, whether a" - {
            "val" - {
                val pianist = Pianist("Jeremy Denk", LocalDate.of(1970, MAY, 16))

                pianist.instrument shouldBe Instrument.Piano
            }
            "var" - {

                val performance = Performance(borisGiltburgEveningOf31stOctober2024)

                performance.status shouldBe PerformanceStatus.Unknown
                performance.status = PerformanceStatus.Before
                performance.status shouldBe PerformanceStatus.Before
            }
            "var which isn't initialised during construction, which we" - {
                "attempt to read" - {
                    "before initialisation" {
                        val performance = Performance(borisGiltburgEveningOf31stOctober2024)
                        val exception = shouldThrow<UninitializedPropertyAccessException> {
                            performance.review
                        }
                        exception.message shouldBe "lateinit property review has not been initialized"
                    }
                    "after initialisation" {
                        val performance = Performance(borisGiltburgEveningOf31stOctober2024)

                        performance.review = "Quite good."
                        performance.review shouldBe "Quite good."
                    }

                }


            }
        }
        "requires the implementation of" - {
            "equals" {
                val performanceOne = Performance(borisGiltburgEveningOf31stOctober2024)
                val performanceTwo = Performance(borisGiltburgEveningOf31stOctober2024)
                val performanceThree = Performance(stevenIsserlisAndOthersEveningOf4thNovember2024)

                performanceOne shouldBeEqual performanceTwo
                performanceOne shouldNotBeEqual performanceThree
                performanceTwo shouldNotBeEqual performanceThree

            }
            "hashCode" {
                val performanceOne = Performance(borisGiltburgEveningOf31stOctober2024)
                val performanceTwo = Performance(borisGiltburgEveningOf31stOctober2024)
                val performanceThree = Performance(stevenIsserlisAndOthersEveningOf4thNovember2024)

                performanceOne.hashCode() shouldBeEqual performanceTwo.hashCode()
                performanceOne.hashCode() shouldNotBeEqual performanceThree.hashCode()
                performanceTwo.hashCode() shouldNotBeEqual performanceThree.hashCode()
            }
            "toString" {
                val performance = Performance(borisGiltburgEveningOf31stOctober2024)

                performance.toString() shouldBe "Performance(concert=Boris Giltburg, piano, status=Unknown)"
            }
        }
    }

})