package com.erasmos.kotlin_notebook

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class GenericsSpec: FreeSpec({
    "Generics are relevant to both the class and function definitions; for example:" - {
        "one" {
            val performers =
                PerformerList(listOf(Data.Performers.joshuaBell, Data.Performers.borisGiltburg, Data.Performers.ireneDuval))

            val youngest = performers.getYoungest()

            youngest shouldBe Data.Performers.borisGiltburg
        }
        "two" {
            val performers =
                PerformerList(listOf(Data.Performers.joshuaBell, Data.Performers.borisGiltburg, Data.Performers.ireneDuval))

            val oldest = performers.getOldest()

            oldest shouldBe Data.Performers.joshuaBell
        }
    }
})