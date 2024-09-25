package com.erasmos.kotlin_notebook

import com.erasmos.kotlin_notebook.Data.Performers.borisGiltburg
import com.erasmos.kotlin_notebook.Data.Performers.ireneDuval
import com.erasmos.kotlin_notebook.Data.Performers.jeremyDenk
import com.erasmos.kotlin_notebook.Data.Performers.joshuaBell
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class ReifiedTypeSpec : FreeSpec({

    "Reified types allow us to workaround the usual Java erasure; example" - {

        val performers = listOf(
            borisGiltburg,
            joshuaBell,
            jeremyDenk,
            ireneDuval
        )

        "one" {
            performers.filterByType<Pianist>() shouldBe listOf(
                borisGiltburg,
                jeremyDenk
            )
        }
        "two" {
            performers.filterByType<Violinist>() shouldBe listOf(joshuaBell, ireneDuval)
        }
    }

}) {

    companion object {
        inline fun <reified T> List<Any>.filterByType(): List<T> =
            this.filter { it is T }.map { it as T }
    }
}