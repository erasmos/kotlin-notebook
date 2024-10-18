package com.erasmos.kotlin_notebook

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class NullableSpec : FreeSpec({

    "any nullable value" - {
        "must be marked as such" {
            // val performer: Performer = null : won't compile
            val performer: Performer? = null
            performer shouldBe null
        }
        "offers a safe way to" - {
            "access" - {
                "properties" {
                    val performer: Performer? = null
                    performer?.name shouldBe null
                }
                "methods" {
                    val performer: Performer? = null
                    performer?.getDescription() shouldBe null
                }
            }
            "cast" {
                val value: Any? = null
                val performer: Performer? = value as? Performer
                performer shouldBe null
            }
        }

        "offers an unsafe way to" - {
            "access" - {
                "properties" {
                    val performer: Performer? = null
                    shouldThrow<NullPointerException> {
                        performer!!.name
                    }
                }
                "methods" {
                    val performer: Performer? = null
                    shouldThrow<NullPointerException> {
                        performer!!.getDescription()
                    }
                }
            }
            "cast" {
                val performer: Any? = null
                shouldThrow<NullPointerException> {
                    performer as Performer
                }
            }
        }

    }

})
