package com.erasmos.kotlin_notebook

import com.erasmos.kotlin_notebook.Instrument.*
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldBeIn
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf

class EnumSpec : FreeSpec({
    "an enum" - {
        "can consist of members which" - {
            "have no properties" - {
                val value = Piano
                value shouldBeIn Instrument.entries
            }
            "have one or more properties" - {
                "one" {
                    val value = Piano
                    value.providedByConcertHall shouldBe true
                }
                "two" {
                    val value = Violin
                    value.providedByConcertHall shouldBe false
                }

            }
            "provide automatic implementations of " - {
                "toString" {
                    val value = Cello
                    value.toString() shouldBe "Cello"
                }
            }
        }
        "offers the ability to" - {
            "list all members" {
                val value = Instrument.entries.toSet()
                value shouldBe setOf(Cello, Piano, Violin)
            }
            "find a members by String, when" - {
                "it exists" {
                    val value = Instrument.valueOf("Cello")
                    value shouldBe Cello
                }
                "doesn't exist" {
                    val exception = shouldThrow<IllegalArgumentException> {
                        Instrument.valueOf("Kazoo")
                    }
                    exception.shouldBeTypeOf<IllegalArgumentException>()
                }
            }
        }
    }
})

