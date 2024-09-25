package com.erasmos.kotlin_notebook

import com.erasmos.kotlin_notebook.Data.Performers.borisGiltburg
import com.erasmos.kotlin_notebook.Data.Performers.connieShih
import com.erasmos.kotlin_notebook.Data.Performers.ireneDuval
import com.erasmos.kotlin_notebook.Data.Performers.jeremyDenk
import com.erasmos.kotlin_notebook.Data.Performers.joshuaBell
import com.erasmos.kotlin_notebook.Data.Performers.stevenIsserlis
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class SetSpec : FreeSpec({

    beforeTest {
        aMutableSet = mutableSetOf(joshuaBell, ireneDuval, jeremyDenk, connieShih)
    }

    afterTest {
        anImmutableSet shouldBe setOf(joshuaBell, ireneDuval, jeremyDenk, connieShih)
    }

    "a set can only contain unique values, and can be either" - {
        "immutable; we can" - {
            "get the size" {
                anImmutableSet.size shouldBe 4
            }
            "create a new list, by" - {
                "adding a new element, using style" - {
                    "one" {
                        anImmutableSet.plus(stevenIsserlis) shouldBe setOf(joshuaBell, ireneDuval, jeremyDenk, connieShih,stevenIsserlis )
                    }
                    "two" {
                        anImmutableSet + stevenIsserlis shouldBe setOf(joshuaBell, ireneDuval, jeremyDenk, connieShih,stevenIsserlis )
                    }
                }
                "removing the specified value when it" - {
                    "exists, using style" - {
                        "one" {
                            anImmutableSet.minus(connieShih) shouldBe setOf(joshuaBell, ireneDuval, jeremyDenk)
                        }
                        "two" {
                            anImmutableSet - connieShih shouldBe setOf(joshuaBell, ireneDuval, jeremyDenk)
                        }
                    }
                    "doesn't exist" {
                        anImmutableSet - borisGiltburg shouldBe setOf(joshuaBell, ireneDuval, jeremyDenk, connieShih)
                    }
                }
            }
            "see if it contains a particular value, when it" - {
                "exists, using style" - {
                    "one" {
                        anImmutableSet.contains(joshuaBell) shouldBe true
                    }
                    "two" {
                        (joshuaBell in anImmutableSet) shouldBe true
                    }
                }
                "doesn't exist" {
                    anImmutableSet.contains(borisGiltburg) shouldBe false
                }
            }
            "to get an intersection with another set" {
                anImmutableSet.intersect(setOf(borisGiltburg, joshuaBell, ireneDuval)) shouldBe setOf(joshuaBell, ireneDuval)
            }
            "to get a union with another set" {
                anImmutableSet.union(setOf(borisGiltburg, stevenIsserlis, joshuaBell)) shouldBe setOf(borisGiltburg, stevenIsserlis, joshuaBell,ireneDuval,jeremyDenk, connieShih)
            }
            "see the differing elements with another set (those in the first set, but missing from the second)" {
                anImmutableSet.minus(setOf(borisGiltburg, stevenIsserlis, joshuaBell)) shouldBe setOf(ireneDuval, jeremyDenk, connieShih)
            }
        }
        "or mutable; we can" - {
            "update the list, by" - {
                "adding an element, which" - {
                    "doesn't already exist" {
                        aMutableSet.add(borisGiltburg) shouldBe true
                        aMutableSet shouldBe mutableSetOf(borisGiltburg, joshuaBell, ireneDuval, jeremyDenk, connieShih)
                    }
                    "exists" {
                        aMutableSet.add(joshuaBell) shouldBe false
                        aMutableSet shouldBe mutableSetOf(joshuaBell, ireneDuval, jeremyDenk, connieShih)
                    }
                }
                "removing an element, which" - {
                    "already exists" {
                        aMutableSet.remove(joshuaBell) shouldBe true
                        aMutableSet shouldBe mutableSetOf(ireneDuval, jeremyDenk, connieShih)
                    }
                    "doesn't exist" {
                        aMutableSet.remove(stevenIsserlis) shouldBe false
                        aMutableSet shouldBe mutableSetOf(joshuaBell, ireneDuval, jeremyDenk, connieShih)
                    }
                }
            }
            "see if it contains a particular value, when" - {
                "it exists" {
                    aMutableSet.contains(jeremyDenk) shouldBe true
                }
                "it doesn't exist" {
                    aMutableSet.contains(stevenIsserlis) shouldBe false
                }
            }
        }
    }

}) {


    companion object {
        val anImmutableSet = setOf(joshuaBell, ireneDuval, jeremyDenk, connieShih)
        lateinit var aMutableSet: MutableSet<Performer>
    }

}