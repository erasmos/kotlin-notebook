package com.erasmos.kotlin_notebook

import com.erasmos.kotlin_notebook.Data.Performers.borisGiltburg
import com.erasmos.kotlin_notebook.Data.Performers.connieShih
import com.erasmos.kotlin_notebook.Data.Performers.ireneDuval
import com.erasmos.kotlin_notebook.Data.Performers.jeremyDenk
import com.erasmos.kotlin_notebook.Data.Performers.joshuaBell
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeSameInstanceAs

class VarianceSpec : FreeSpec({

    "Variance refers to the relation to types and wrappers around that type (e.g. lists); there are three options" - {
        "covariant - when elements are being outputted / produced / got" - {
            "one" {
                val pianists: List<Pianist> = listOf(borisGiltburg, jeremyDenk)
                val pianistsAsPerformers: List<Performer> = pianists

                pianists shouldBeSameInstanceAs pianistsAsPerformers
            }
            "two" {
                val pianists: PerformerList<Pianist> = PerformerList(listOf(borisGiltburg, jeremyDenk))
                val pianistsAsPerformers: PerformerList<Performer> = pianists

                pianists shouldBeSameInstanceAs pianistsAsPerformers
            }
            "three" {
                val pianistsAsPerformers: PerformerList<Performer> =
                    PerformerList(listOf(borisGiltburg, jeremyDenk)).add(connieShih)

                pianistsAsPerformers shouldBe PerformerList<Performer>(listOf(borisGiltburg, jeremyDenk, connieShih))
            }
        }
        "contravariant - when elements are being consumed / acted upon" - {
            "one" - {
                val pianoTeacher: Teacher<Pianist> = Teacher<Performer>()

                pianoTeacher.teach(connieShih) shouldBe true
            }
            "two" - {
                val violinTeacher: Teacher<Violinist> = Teacher<Performer>()

                violinTeacher.teach(joshuaBell) shouldBe true
            }
            "two" - {
                val generalTeacher: Teacher<Performer> = Teacher()

                generalTeacher.teach(connieShih) shouldBe true
                generalTeacher.teach(joshuaBell) shouldBe true
            }
        }
        "covariant - otherwise" {
            val violinists: ArrayList<Violinist> = ArrayList()
            violinists.add(joshuaBell)
            violinists.add(ireneDuval)

            violinists shouldBe listOf(joshuaBell, ireneDuval)
        }
    }


}) {

    companion object {

        fun <B : Performer, A : B> PerformerList<A>.add(performer: B): PerformerList<B> =
            PerformerList(this.performers.toList<B>() + performer)

        class Teacher<in A> {
            fun teach(performer: A): Boolean = true
        }
    }
}