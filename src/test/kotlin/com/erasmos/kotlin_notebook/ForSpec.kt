package com.erasmos.kotlin_notebook

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class ForSpec : FreeSpec({

    "'for' is an instruction, not an expression, which can operate over" - {
        "a collection" - {
            val list = listOf(Data.Performers.borisGiltburg, Data.Performers.connieShih, Data.Performers.jeremyDenk)
            val resultList = mutableListOf<Performer>()
            for (element in list) {
                resultList.add(element)
            }
            resultList shouldBe listOf(Data.Performers.borisGiltburg, Data.Performers.connieShih, Data.Performers.jeremyDenk)
        }
        "a range, which is" - {
            "ascending" - {
                "inclusive" - {
                    "without stepping" {
                        val resultList = mutableListOf<Int>()
                        for (element in 1..4) {
                            resultList.add(element)
                        }

                        resultList shouldBe listOf(1, 2, 3, 4)
                    }
                    "with stepping" {
                        val resultList = mutableListOf<Int>()
                        for (element in 1..4 step 2) {
                            resultList.add(element)
                        }

                        resultList shouldBe listOf(1, 3)
                    }
                }
                "exclusive" - {
                    "without stepping" {
                        val resultList = mutableListOf<Int>()
                        for (element in 1 until 4) {
                            resultList.add(element)
                        }

                        resultList shouldBe listOf(1, 2, 3)
                    }
                    "with stepping" {
                        val resultList = mutableListOf<Int>()
                        for (element in 1 until 5 step 2) {
                            resultList.add(element)
                        }

                        resultList shouldBe listOf(1, 3)
                    }
                }
            }
            "descending" - {
                "inclusive" - {
                    "without stepping" {
                        val resultList = mutableListOf<Int>()
                        for (element in 4 downTo 1) {
                            resultList.add(element)
                        }

                        resultList shouldBe listOf(4, 3, 2, 1)
                    }
                    "with stepping" {
                        val resultList = mutableListOf<Int>()
                        for (element in 4 downTo 1 step 2) {
                            resultList.add(element)
                        }

                        resultList shouldBe listOf(4, 2)
                    }
                }
            }
        }
    }
})