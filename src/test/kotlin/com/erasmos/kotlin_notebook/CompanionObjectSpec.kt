package com.erasmos.kotlin_notebook

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class CompanionObjectSpec : FreeSpec({

    "a companion object is a singleton which belongs to a class, which can have" - {
        "values" {
            Performer.NAME_UNKNOWN shouldBe "Unknown"
        }
        "functions" {
            Performer.isPianist(Data.Performers.borisGiltburg) shouldBe true
        }
    }

})