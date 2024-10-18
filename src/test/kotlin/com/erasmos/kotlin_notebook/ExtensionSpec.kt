package com.erasmos.kotlin_notebook

import com.erasmos.kotlin_notebook.Data.Concerts.borisGiltburgEveningOf31stOctober2024
import com.erasmos.kotlin_notebook.Data.Concerts.stevenIsserlisAndOthersEveningOf4thNovember2024
import com.erasmos.kotlin_notebook.PerformanceStatus.After
import com.erasmos.kotlin_notebook.PerformanceStatus.Unknown
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import java.time.temporal.ChronoUnit

class ExtensionSpec : FreeSpec({

    "an extension" - {
        "method can be added to any class, when" - {
            "one of our own, when" - {
                "such a method doesn't already exist" {
                    val performance = Performance(stevenIsserlisAndOthersEveningOf4thNovember2024)

                    performance.status shouldBe Unknown
                    performance.evacuate()
                    performance.status shouldBe After
                }
                "the same method already exists (though it will be ignored)" {
                    val performance = Performance(stevenIsserlisAndOthersEveningOf4thNovember2024)

                    performance.toString() shouldBe "Performance(concert=Steven Isserlis cello; Joshua Bell violin; Irène Duval violin; Jeremy Denk piano; Connie Shih piano; Quatuor Agate, status=Unknown)"
                }

            }
            "an existing one, when" - {
                "such a method doesn't already exist" {
                    val result = 10.repeat("*")

                    result shouldBe "**********"
                }
                "the same method already exists (though it will be ignored)" {

                    10.toString() shouldBe "10"
                }
            }
        }
        "property can be added to any class - but only a var - when" - {
            "one of our own, when" - {
                "such a property doesn't already exist" {
                    val concert = borisGiltburgEveningOf31stOctober2024

                    concert.durationInMinutes shouldBe 120
                }
                "the same property already exists (though it will be ignored)" - {
                    val concert = borisGiltburgEveningOf31stOctober2024

                    concert.name shouldBe "Boris Giltburg, piano"
                }
            }
            "an existing one, when" - {
                "such a property doesn't already exist" {
                    val value = 2024

                    value.reversed shouldBe "4202"

                }
                "the same property already exists (though it will be ignored)" {
                    "Hello!".length shouldBe 6
                }
            }
        }
    }

}) {
    companion object {

        fun Performance.evacuate() {
            this.status = After
        }

        fun Performance.toString() = "A performance."

        var Concert.durationInMinutes: Int
            get() = ChronoUnit.MINUTES.between(startTime.value, endTime.value).toInt()
            set(_) {}


        fun Int.repeat(string: String): String = string.repeat(this)

        fun Int.toString(): String =
            "Int = $this"

        var Int.reversed: String
            get() = this.toString().reversed()
            set(_) {}

        var String.length: Int
            get() = this.length * 10
            set(_) {}


    }
}