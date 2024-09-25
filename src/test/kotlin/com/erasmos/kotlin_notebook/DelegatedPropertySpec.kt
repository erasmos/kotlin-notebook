package com.erasmos.kotlin_notebook

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class DelegatedPropertySpec : FreeSpec({

    "delegate properties give us common functionality; for example:" - {
        "one" {
            val performance = Performance(Data.Concerts.borisGiltburgEveningOf31stOctober2024)
            Logger.clear()

            performance.status shouldBe PerformanceStatus.Unknown
            Logger.messages shouldBe listOf("[performance-Boris Giltburg, piano] reading var [status] which is currently [Unknown]")

            performance.status = PerformanceStatus.Before
            Logger.messages shouldBe listOf(
                "[performance-Boris Giltburg, piano] reading var [status] which is currently [Unknown]",
                "[performance-Boris Giltburg, piano] writing var [status] which was [Before] but will now be [Before]"
            )

        }

    }

})