package com.erasmos.kotlin_notebook

import com.erasmos.kotlin_notebook.Data.Performers.borisGiltburg
import com.erasmos.kotlin_notebook.Data.Performers.connieShih
import com.erasmos.kotlin_notebook.Data.Performers.ireneDuval
import com.erasmos.kotlin_notebook.Data.Performers.joshuaBell
import com.erasmos.kotlin_notebook.Data.Performers.stevenIsserlis
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class ArraySpec : FreeSpec({

    beforeTest {
        anArray = arrayOf(borisGiltburg, connieShih, joshuaBell, ireneDuval)
    }

    "an array is always mutable; we can" - {
        "request the element at a specified index, when that index is" - {
            "valid, using style" - {
                "one" {
                    anArray.get(0) shouldBe borisGiltburg
                }
                "two" {
                    anArray[0] shouldBe borisGiltburg
                }
            }
            "invalid" - {
                "one" {
                    shouldThrow<ArrayIndexOutOfBoundsException> {
                        anArray.get(10)
                    }
                }
                "two" {
                    shouldThrow<ArrayIndexOutOfBoundsException> {
                        anArray[10]
                    }
                }
            }
        }
        "set the element at a specified index, when that index is" - {
            "valid, using style" - {
                "one" {
                    anArray.set(1, stevenIsserlis) shouldBe Unit
                    anArray shouldBe arrayOf(borisGiltburg, stevenIsserlis, joshuaBell, ireneDuval)
                }
                "two" {
                    anArray[1] = stevenIsserlis
                    anArray shouldBe arrayOf(borisGiltburg, stevenIsserlis, joshuaBell, ireneDuval)
                }
            }
            "invalid" - {
                "one" {
                    shouldThrow<ArrayIndexOutOfBoundsException> {
                        anArray.set(8, stevenIsserlis)
                    }
                }
                "two" {
                    shouldThrow<ArrayIndexOutOfBoundsException> {
                        anArray[8] = stevenIsserlis
                    }
                }
            }
        }
        "get the size of it" {
            anArray.size shouldBe 4
        }
    }

}) {

    companion object {
        lateinit var anArray: Array<Performer>
    }
}