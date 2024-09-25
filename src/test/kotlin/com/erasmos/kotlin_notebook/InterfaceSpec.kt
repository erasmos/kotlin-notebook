package com.erasmos.kotlin_notebook

import com.erasmos.kotlin_notebook.Data.Performers.joshuaBell
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class InterfaceSpec : FreeSpec({

    "an interface can have" - {
        "properties" - {
            "without a value" {
                joshuaBell.name shouldBe "Joshua Bell"
            }
        }
        "methods" - {
            "without an implementation" {
                joshuaBell.getDescription() shouldBe "Joshua Bell, Violin"
            }
            "with an implementation" {
                joshuaBell.getCurrentAge() shouldBe 56
            }
        }
    }
}

)
