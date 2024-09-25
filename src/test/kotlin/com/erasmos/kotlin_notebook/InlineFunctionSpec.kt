package com.erasmos.kotlin_notebook

import com.erasmos.kotlin_notebook.Grade.D
import com.erasmos.kotlin_notebook.Section.FrontStalls
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class InlineFunctionSpec : FreeSpec({

    "an inline function - which has an other function as an argument - " - {
        "can sometimes increase performance time" {
            val seat = Seat("AA1", FrontStalls, D)
            val checks: (Seat) -> Unit = { _: Seat ->
                Thread.sleep(200)
            }

            seat.inspect(checks)

            seat.lastInspected shouldNotBe null
        }
        "but shouldn't be used when when we want to keep a reference to that passed function" {
            var wasStored = false
            var wasExecuted = false

            performOperation(
                storedOperation = { wasStored  = true },
                unstoredOperation = { wasExecuted = true}
            )

            wasStored shouldBe false
            wasExecuted shouldBe true

            StoredOperationWrapper.executeOperation()

            wasStored shouldBe true
        }

    }


}) {

    companion object {

        object StoredOperationWrapper {

            private var operation : (() -> Unit)? = null

            fun store(operation: () -> Unit) {
                this.operation = operation
            }

            fun executeOperation() {
                operation?.invoke()
            }
        }

        inline fun performOperation(
            noinline storedOperation: () -> Unit,
            unstoredOperation: () -> Unit
        ) {
            StoredOperationWrapper.store(storedOperation)
            unstoredOperation()
        }


    }
}