package com.erasmos.kotlin_notebook

import com.erasmos.kotlin_notebook.Data.Concerts
import com.erasmos.kotlin_notebook.Data.Concerts.borisGiltburgEveningOf31stOctober2024
import com.erasmos.kotlin_notebook.Data.Concerts.stevenIsserlisAndOthersEveningOf4thNovember2024
import com.erasmos.kotlin_notebook.Data.Performers
import com.erasmos.kotlin_notebook.Data.Performers.borisGiltburg
import com.erasmos.kotlin_notebook.Instrument.Cello
import com.erasmos.kotlin_notebook.Instrument.Piano
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class IfSpec : FreeSpec({

    "'If' is an expression, so always returns a value" - {
        "Here are a few examples:" - {
            "All on one line, when" - {
                "True" {
                    val whichConcert = if (borisGiltburg.instrument == Piano) borisGiltburgEveningOf31stOctober2024 else stevenIsserlisAndOthersEveningOf4thNovember2024
                    whichConcert shouldBe borisGiltburgEveningOf31stOctober2024
                }
                "False" {
                    val whichConcert = if (borisGiltburg.instrument == Cello) borisGiltburgEveningOf31stOctober2024 else stevenIsserlisAndOthersEveningOf4thNovember2024
                    whichConcert shouldBe stevenIsserlisAndOthersEveningOf4thNovember2024
                }
            }
            "On separate lines, when" - {
                "True" {
                    val whichConcert = if (borisGiltburg.instrument == Piano) {
                        borisGiltburgEveningOf31stOctober2024
                    } else {
                        stevenIsserlisAndOthersEveningOf4thNovember2024
                    }
                    whichConcert shouldBe borisGiltburgEveningOf31stOctober2024
                }
                "False" {
                    val whichConcert = if (borisGiltburg.instrument == Cello){
                        borisGiltburgEveningOf31stOctober2024
                    } else {
                        stevenIsserlisAndOthersEveningOf4thNovember2024
                    }
                    whichConcert shouldBe stevenIsserlisAndOthersEveningOf4thNovember2024
                }
            }
        }
    }


})
