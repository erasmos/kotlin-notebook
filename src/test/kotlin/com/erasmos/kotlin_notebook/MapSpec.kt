package com.erasmos.kotlin_notebook

import com.erasmos.kotlin_notebook.Grade.*
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class MapSpec : FreeSpec({

    beforeTest {
        aMutableMap = mutableMapOf(
            A to 40.00,
            B to 36.00,
            C to 32.00,
            E to 18.00
        )
    }

    afterTest {
        anImmutableMap shouldBe mapOf(
            A to 40.00,
            B to 36.00,
            C to 32.00,
            E to 18.00
        )
    }

    "a map can either be" - {
        "immutable; we can" - {
            "see if a particular key is present, if it" - {
                "exists" {
                    anImmutableMap.contains(A) shouldBe true
                }
                "doesn't exist" {
                    anImmutableMap.contains(D) shouldBe false
                }
            }
            "get the value for a particular key, if it" - {
                "exists, using style" - {
                    "one" {
                        anImmutableMap.get(A) shouldBe 40.00
                    }
                    "two" {
                        anImmutableMap[A] shouldBe 40.00
                    }
                }
                "doesn't exist, using style" - {
                    "one" {
                        anImmutableMap.get(D) shouldBe null
                    }
                    "two" {
                        anImmutableMap[D] shouldBe null
                    }
                }
            }
            "create a new map by" - {
                "adding a new pair, using style" - {
                    "one" {
                        anImmutableMap.plus(D to 26.00) shouldBe mapOf(
                            A to 40.00,
                            B to 36.00,
                            C to 32.00,
                            D to 26.00,
                            E to 18.00
                        )
                    }
                    "two" {
                        anImmutableMap + (D to 26.00) shouldBe mapOf(
                            A to 40.00,
                            B to 36.00,
                            C to 32.00,
                            D to 26.00,
                            E to 18.00
                        )
                    }
                }
                "removing an existing pair, using style" - {
                    "one" {
                        anImmutableMap.minus(B) shouldBe mapOf(
                            A to 40.00,
                            C to 32.00,
                            E to 18.00
                        )
                    }
                    "two" {
                        anImmutableMap - B shouldBe mapOf(
                            A to 40.00,
                            C to 32.00,
                            E to 18.00
                        )
                    }
                }
            }
            "convert to a list of pairs" {
                anImmutableMap.toList() shouldBe listOf(
                    Pair(A, 40.00),
                    Pair(B, 36.00),
                    Pair(C, 32.00),
                    Pair(E, 18.00)
                )
            }
        }
        "mutable; we can" - {
            "see if a particular key is present, if it" - {
                "exists" {
                    aMutableMap.contains(A) shouldBe true
                }
                "doesn't exist" {
                    aMutableMap.contains(D) shouldBe false
                }
            }
            "update it by" - {
                "adding a new pair, if it" - {
                    "didn't already exist" {
                        val replacedValue = aMutableMap.put(D, 26.00)

                        replacedValue shouldBe null
                        aMutableMap shouldBe mutableMapOf(
                            A to 40.00,
                            B to 36.00,
                            C to 32.00,
                            D to 26.00,
                            E to 18.00
                        )
                    }
                    "already existed" {
                        val replacedValue = aMutableMap.put(A, 42.00)

                        replacedValue shouldBe 40.00
                        aMutableMap shouldBe mutableMapOf(
                            A to 42.00,
                            B to 36.00,
                            C to 32.00,
                            E to 18.00
                        )
                    }
                }
                "removing an existing pair, if it" - {
                    "already existed" {
                        val removedValue = aMutableMap.remove(A)

                        removedValue shouldBe 40.00
                        aMutableMap shouldBe mutableMapOf(
                            B to 36.00,
                            C to 32.00,
                            E to 18.00
                        )
                    }
                    "didn't already exist" {
                        val removedValue = aMutableMap.remove(D)

                        removedValue shouldBe null
                        aMutableMap shouldBe mutableMapOf(
                            A to 40.00,
                            B to 36.00,
                            C to 32.00,
                            E to 18.00
                        )
                    }
                }
                "updating the value for an existing key, when it" - {
                    "exists, using style" - {
                        "one" {
                            val replacedValue = aMutableMap.put(A, 42.00)

                            replacedValue shouldBe 40.00
                            aMutableMap shouldBe mutableMapOf(
                                A to 42.00,
                                B to 36.00,
                                C to 32.00,
                                E to 18.00
                            )
                        }
                        "two" {
                            aMutableMap[A] = 42.00

                            aMutableMap shouldBe mutableMapOf(
                                A to 42.00,
                                B to 36.00,
                                C to 32.00,
                                E to 18.00
                            )
                        }
                    }
                    "doesn't exist, using style" - {
                        "one" {
                            val replacedValue = aMutableMap.put(D, 26.00)

                            replacedValue shouldBe null
                            aMutableMap shouldBe mutableMapOf(
                                A to 40.00,
                                B to 36.00,
                                C to 32.00,
                                D to 26.00,
                                E to 18.00
                            )
                        }
                        "two" {
                            aMutableMap[D] = 26.00

                            aMutableMap shouldBe mutableMapOf(
                                A to 40.00,
                                B to 36.00,
                                C to 32.00,
                                D to 26.00,
                                E to 18.00
                            )
                        }
                    }
                }
            }
        }
    }

}) {
    companion object {
        val anImmutableMap = mapOf(
            A to 40.00,
            B to 36.00,
            C to 32.00,
            E to 18.00
        )

        lateinit var aMutableMap: MutableMap<Grade, Double>
    }
}