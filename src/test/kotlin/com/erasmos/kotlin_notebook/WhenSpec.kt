package com.erasmos.kotlin_notebook

import com.erasmos.kotlin_notebook.Grade.*
import com.erasmos.kotlin_notebook.Instrument.*
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class WhenSpec : FreeSpec({

    "'when' is an expression, so always returns a value; when" - {
        "there is a subject" - {
            "which matches against literals" - {
                "but without all options are covered," - {
                    "without grouping" - {
                        "one" {
                            val value = Cello
                            val hasStrings =
                                when (value) {
                                    Cello -> true
                                    Violin -> true
                                    else -> false
                                }
                            hasStrings shouldBe true
                        }
                        "two" {
                            val value = Violin
                            val hasStrings =
                                when (value) {
                                    Cello -> true
                                    Violin -> true
                                    else -> false
                                }
                            hasStrings shouldBe true
                        }
                        "three" {
                            val value = Piano
                            val hasStrings =
                                when (value) {
                                    Cello -> true
                                    Violin -> true
                                    else -> false
                                }
                            hasStrings shouldBe false
                        }
                    }
                    "with grouping" - {
                        "one" {
                            val value = Cello
                            val hasStrings =
                                when (value) {
                                    Cello, Violin -> true
                                    else -> false
                                }
                            hasStrings shouldBe true
                        }
                        "two" {
                            val value = Violin
                            val hasStrings =
                                when (value) {
                                    Cello, Violin -> true
                                    else -> false
                                }
                            hasStrings shouldBe true
                        }
                        "three" {
                            val value = Piano
                            val hasStrings =
                                when (value) {
                                    Cello, Violin -> true
                                    else -> false
                                }
                            hasStrings shouldBe false
                        }
                    }
                }

                "where all options are covered" - {
                    "with grouping" - {
                        "one" {
                            val value = Cello
                            val hasStrings =
                                when (value) {
                                    Cello, Violin -> true
                                    Piano -> false
                                }
                            hasStrings shouldBe true
                        }
                        "two" {
                            val value = Violin
                            val hasStrings =
                                when (value) {
                                    Cello, Violin -> true
                                    Piano -> false
                                }
                            hasStrings shouldBe true
                        }
                        "three" {
                            val value = Piano
                            val hasStrings =
                                when (value) {
                                    Cello, Violin -> true
                                    Piano -> false
                                }
                            hasStrings shouldBe false
                        }
                    }
                    "without grouping" - {
                        "one" {
                            val value = Cello
                            val hasStrings =
                                when (value) {
                                    Cello -> true
                                    Violin -> true
                                    Piano -> false
                                }
                            hasStrings shouldBe true
                        }
                        "two" {
                            val value = Violin
                            val hasStrings =
                                when (value) {
                                    Cello -> true
                                    Violin -> true
                                    Piano -> false
                                }
                            hasStrings shouldBe true
                        }
                        "three" {
                            val value = Piano
                            val hasStrings =
                                when (value) {
                                    Cello -> true
                                    Violin -> true
                                    Piano -> false
                                }
                            hasStrings shouldBe false
                        }

                    }
                }

            }
            "matching on predicates" - {
                "one" {
                    val budget = 15.00
                    val response: Grade? = when (budget) {
                        in 0.00..17.99 -> null
                        in 18.00..25.99 -> E
                        in 26.00..31.99 -> Grade.D
                        in 32.00..35.99 -> Grade.C
                        in 36.00..41.99 -> Grade.B
                        else -> Grade.A
                    }
                    response shouldBe null
                }
                "two" {
                    val budget = 20.00
                    val response: Grade? = when (budget) {
                        in 0.00..17.99 -> null
                        in 18.00..25.99 -> E
                        in 26.00..31.99 -> Grade.D
                        in 32.00..35.99 -> Grade.C
                        in 36.00..41.99 -> Grade.B
                        else -> Grade.A
                    }
                    response shouldBe E
                }
                "three" {
                    val budget = 26.00
                    val response: Grade? = when (budget) {
                        in 0.00..17.99 -> null
                        in 18.00..25.99 -> E
                        in 26.00..31.99 -> Grade.D
                        in 32.00..35.99 -> Grade.C
                        in 36.00..41.99 -> Grade.B
                        else -> Grade.A
                    }
                    response shouldBe Grade.D
                }
                "four" {
                    val budget = 32.00
                    val response: Grade? = when (budget) {
                        in 0.00..17.99 -> null
                        in 18.00..25.99 -> E
                        in 26.00..31.99 -> Grade.D
                        in 32.00..35.99 -> Grade.C
                        in 36.00..41.99 -> Grade.B
                        else -> Grade.A
                    }
                    response shouldBe Grade.C
                }
                "five" {
                    val budget = 40.00
                    val response: Grade? = when (budget) {
                        in 0.00..17.99 -> null
                        in 18.00..25.99 -> E
                        in 26.00..31.99 -> Grade.D
                        in 32.00..35.99 -> Grade.C
                        in 36.00..41.99 -> Grade.B
                        else -> Grade.A
                    }
                    response shouldBe Grade.B
                }
                "six" {
                    val budget = 100.00
                    val response: Grade? = when (budget) {
                        in 0.00..17.99 -> null
                        in 18.00..25.99 -> E
                        in 26.00..31.99 -> Grade.D
                        in 32.00..35.99 -> Grade.C
                        in 36.00..41.99 -> Grade.B
                        else -> Grade.A
                    }
                    response shouldBe Grade.A
                }

            }
        }
        "there is no subject" - {
            "one" {
                val under25YearsOld = true
                val budget = 0.00
                val response: Grade? = when {
                    under25YearsOld && budget >= figureDiscountedPrice(A) -> A
                    under25YearsOld && budget >= figureDiscountedPrice(B) -> B
                    !under25YearsOld && budget >= figureFullPrice(A) -> A
                    !under25YearsOld && budget >= figureFullPrice(B) -> B
                    else -> null
                }

                response shouldBe null
            }
            "two" {
                val under25YearsOld = true
                val budget = 34.00
                val response: Grade? = when {
                    under25YearsOld && budget >= figureDiscountedPrice(A) -> A
                    under25YearsOld && budget >= figureDiscountedPrice(B) -> B
                    !under25YearsOld && budget >= figureFullPrice(A) -> A
                    !under25YearsOld && budget >= figureFullPrice(B) -> B
                    else -> null
                }

                response shouldBe B
            }
            "three" {
                val under25YearsOld = true
                val budget = 40.00
                val response: Grade? = when {
                    under25YearsOld && budget >= figureDiscountedPrice(A) -> A
                    under25YearsOld && budget >= figureDiscountedPrice(B) -> B
                    !under25YearsOld && budget >= figureFullPrice(A) -> A
                    !under25YearsOld && budget >= figureFullPrice(B) -> B
                    else -> null
                }

                response shouldBe A
            }
            "four" {
                val under25YearsOld = false
                val budget = 34.99
                val response: Grade? = when {
                    under25YearsOld && budget >= figureDiscountedPrice(A) -> A
                    under25YearsOld && budget >= figureDiscountedPrice(B) -> B
                    !under25YearsOld && budget >= figureFullPrice(A) -> A
                    !under25YearsOld && budget >= figureFullPrice(B) -> B
                    else -> null
                }

                response shouldBe null
            }
            "five" {
                val under25YearsOld = false
                val budget = 38.00
                val response: Grade? = when {
                    under25YearsOld && budget >= figureDiscountedPrice(A) -> A
                    under25YearsOld && budget >= figureDiscountedPrice(B) -> B
                    !under25YearsOld && budget >= figureFullPrice(A) -> A
                    !under25YearsOld && budget >= figureFullPrice(B) -> B
                    else -> null
                }

                response shouldBe B
            }
            "six" {
                val under25YearsOld = false
                val budget = 44.00
                val response: Grade? = when {
                    under25YearsOld && budget >= figureDiscountedPrice(A) -> A
                    under25YearsOld && budget >= figureDiscountedPrice(B) -> B
                    !under25YearsOld && budget >= figureFullPrice(A) -> A
                    !under25YearsOld && budget >= figureFullPrice(B) -> B
                    else -> null
                }

                response shouldBe A
            }

        }
    }
}) {
    companion object {

        private var basePrices = mapOf(
            A to 40.00,
            B to 36.00,
            C to 32.00,
            D to 26.00,
            E to 18.00
        )

        fun figureFullPrice(grade: Grade) = basePrices[grade]!!

        fun figureDiscountedPrice(grade: Grade) = basePrices[grade]!! * 0.9

    }
}

