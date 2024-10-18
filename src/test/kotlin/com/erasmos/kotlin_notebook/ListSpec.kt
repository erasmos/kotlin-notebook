package com.erasmos.kotlin_notebook

import com.erasmos.kotlin_notebook.Data.Performers.borisGiltburg
import com.erasmos.kotlin_notebook.Data.Performers.connieShih
import com.erasmos.kotlin_notebook.Data.Performers.ireneDuval
import com.erasmos.kotlin_notebook.Data.Performers.jeremyDenk
import com.erasmos.kotlin_notebook.Data.Performers.joshuaBell
import com.erasmos.kotlin_notebook.Data.Performers.stevenIsserlis
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class ListSpec : FreeSpec({

    beforeTest {
        aMutableList = mutableListOf(borisGiltburg, stevenIsserlis, joshuaBell, borisGiltburg)
    }

    afterTest {
        anImmutableList shouldBe listOf(borisGiltburg, stevenIsserlis, joshuaBell, borisGiltburg)
    }

    "a list can be either" - {
        "immutable; we can" - {
            "request the element at an index, when it" - {
                "exists" - {
                    "first style" {
                        anImmutableList.get(3) shouldBe borisGiltburg
                    }
                    "second style (more idiomatic)" {
                        anImmutableList[3] shouldBe borisGiltburg
                    }
                }
                "doesn't exist" {
                    shouldThrow<ArrayIndexOutOfBoundsException> {
                        anImmutableList[100]
                    }
                }
            }
            "get the size" {
                anImmutableList.size shouldBe 4
            }
            "request the elements in range, when" - {
                "valid" {
                    anImmutableList.subList(1, 4) shouldBe listOf(stevenIsserlis, joshuaBell, borisGiltburg)
                }
                "invalid" {
                    shouldThrow<IndexOutOfBoundsException> {
                        anImmutableList.subList(1, 42)
                    }
                }
            }
            "create a new list, by" - {
                "adding a new element, using style" - {
                    "one" {
                        anImmutableList.plus(ireneDuval) shouldBe listOf(borisGiltburg, stevenIsserlis, joshuaBell, borisGiltburg, ireneDuval)
                    }
                    "two" {
                        anImmutableList + ireneDuval shouldBe listOf(borisGiltburg, stevenIsserlis, joshuaBell, borisGiltburg, ireneDuval)
                    }
                }
                "removing the first element matching the value, using style" - {
                    "one" {
                        anImmutableList.minus(stevenIsserlis) shouldBe listOf(borisGiltburg, joshuaBell, borisGiltburg)
                    }
                    "two" {
                        anImmutableList - stevenIsserlis shouldBe listOf(borisGiltburg, joshuaBell, borisGiltburg)
                    }
                }
            }
            "find the index of a particular value, when" - {
                "it exists, but only once" {
                    anImmutableList.indexOf(stevenIsserlis) shouldBe 1
                }
                "it exists, multiples times" {
                    anImmutableList.indexOf(borisGiltburg) shouldBe 0
                }
                "it doesn't exist" {
                    anImmutableList.indexOf(jeremyDenk) shouldBe -1
                }
            }
            "see if it contains a particular value, when" - {
                "it exists, but only once" {
                    anImmutableList.contains(joshuaBell) shouldBe true
                }
                "it exists, multiples times" {
                    anImmutableList.contains(borisGiltburg) shouldBe true
                }
                "it doesn't exist" {
                    anImmutableList.contains(jeremyDenk) shouldBe false
                }
            }
            "convert it to a set, removing any duplicates" {
                anImmutableList.toSet() shouldBe setOf(borisGiltburg, stevenIsserlis, joshuaBell)
            }
        }
        "or mutable; we can" - {
            "request the element at an index, when it" - {
                "exists" - {
                    "first style" {
                        aMutableList.get(3) shouldBe borisGiltburg
                    }
                    "second style (more idiomatic)" {
                        aMutableList[3] shouldBe borisGiltburg
                    }
                }
                "doesn't exist" {
                    shouldThrow<IndexOutOfBoundsException> {
                        aMutableList[100]
                    }
                }
            }
            "get the size" {
                aMutableList.size shouldBe 4
            }
            "request the elements in range, when" - {
                "valid" {
                    aMutableList.subList(1, 4) shouldBe mutableListOf(stevenIsserlis, joshuaBell, borisGiltburg)
                }
                "invalid" {
                    shouldThrow<IndexOutOfBoundsException> {
                        aMutableList.subList(1, 42)
                    }
                }
            }
            "update the list, by" - {
                "adding a new element to the end" - {
                    aMutableList.add(ireneDuval) shouldBe true
                    aMutableList shouldBe listOf(borisGiltburg, stevenIsserlis, joshuaBell, borisGiltburg, ireneDuval)
                }
                "adding a new element at an existing index" - {
                    aMutableList.add(3, connieShih) shouldBe Unit
                    aMutableList shouldBe listOf(borisGiltburg, stevenIsserlis, joshuaBell, connieShih, borisGiltburg)
                }
                "replacing an existing element, using style" - {
                    "one" {
                        val replacedValue = aMutableList.set(3, connieShih)
                        replacedValue shouldBe borisGiltburg
                        aMutableList shouldBe listOf(borisGiltburg, stevenIsserlis, joshuaBell, connieShih)
                    }
                    "two" {
                        aMutableList[3] = connieShih
                        aMutableList shouldBe listOf(borisGiltburg, stevenIsserlis, joshuaBell, connieShih)
                    }
                }
                "removing an element at the specified index" {
                    val removedValue = aMutableList.removeAt(3)

                    removedValue shouldBe borisGiltburg
                    aMutableList shouldBe listOf(borisGiltburg, stevenIsserlis, joshuaBell)
                }
            }
            "find the index of a particular value, when" - {
                "it exists, but only once" {
                    aMutableList.indexOf(stevenIsserlis) shouldBe 1
                }
                "it exists, multiples times" {
                    aMutableList.indexOf(borisGiltburg) shouldBe 0
                }
                "it doesn't exist" {
                    aMutableList.indexOf(ireneDuval) shouldBe -1
                }
            }
            "see if it contains a particular value, when" - {
                "it exists, but only once" {
                    aMutableList.contains(stevenIsserlis) shouldBe true
                }
                "it exists, multiples times" {
                    aMutableList.contains(borisGiltburg) shouldBe true
                }
                "it doesn't exist" {
                    aMutableList.contains(ireneDuval) shouldBe false
                }
            }
            "convert it to a set, removing any duplicates" {
                aMutableList.toSet() shouldBe mutableSetOf(borisGiltburg, stevenIsserlis, joshuaBell)
            }
        }

    }

}) {

    companion object {
        val anImmutableList = listOf(borisGiltburg, stevenIsserlis, joshuaBell, borisGiltburg)
        lateinit var aMutableList: MutableList<Performer>
    }
}