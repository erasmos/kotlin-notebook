package com.erasmos.kotlin_notebook

import io.kotest.core.spec.style.FreeSpec

class ContractSpec : FreeSpec({

    "A contract is code that gives hints to the compiler about the function's return value; for example" - {
        "one" {
            val performer: Performer = Data.Performers.borisGiltburg

            if (Performer.isPianist(performer)) {
                performer.openLid()
            }

        }
    }

})
