package com.erasmos.kotlin_notebook

import com.erasmos.kotlin_notebook.Data.Concerts.borisGiltburgEveningOf31stOctober2024
import com.erasmos.kotlin_notebook.Data.Performers
import com.erasmos.kotlin_notebook.Data.Performers.borisGiltburg
import com.erasmos.kotlin_notebook.Data.Performers.ireneDuval
import com.erasmos.kotlin_notebook.Data.Performers.jeremyDenk
import com.erasmos.kotlin_notebook.Data.Performers.joshuaBell
import com.erasmos.kotlin_notebook.Data.Performers.stevenIsserlis
import com.erasmos.kotlin_notebook.Data.Seats
import com.erasmos.kotlin_notebook.Instrument.Piano
import com.erasmos.kotlin_notebook.Instrument.Violin
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class HigherOrderFunctionForCollectionsSpec : FreeSpec({

    "there are a number of collection functions which take functions; for" - {
        "lists; for example" - {
            "map" - {
                "one" {
                    fun normaliseGrade(seat: Seat, grade: Grade): Seat = seat.copy(grade = grade)

                    val allUniqueGradesBefore: Set<Grade> = Seats.all.map { it.grade }.toSet()
                    allUniqueGradesBefore shouldBe Grade.entries.toSet()

                    val updatedSeats: List<Seat> = Seats.all.map { normaliseGrade(it, Grade.E) }

                    val allUniqueGradesAfter: Set<Grade> = updatedSeats.map { it.grade }.toSet()
                    allUniqueGradesAfter shouldBe setOf(Grade.E)
                }
                "two" {
                    val result =
                        Instrument.entries.map { instrument -> Performers.all.filter { it.instrument == instrument } }
                    result shouldBe listOf(
                        listOf(stevenIsserlis),
                        listOf(borisGiltburg, jeremyDenk, Performers.connieShih),
                        listOf(joshuaBell, ireneDuval)
                    )
                }
            }
            "flatMap" {
                val result =
                    Instrument.entries.flatMap { instrument -> Performers.all.filter { it.instrument == instrument } }
                result shouldBe listOf(
                    stevenIsserlis,
                    borisGiltburg,
                    jeremyDenk,
                    Performers.connieShih,
                    joshuaBell,
                    ireneDuval
                )

            }
            "fold" {
                val sumOfPrices =
                    borisGiltburgEveningOf31stOctober2024.tickets.mapNotNull { it.price }.fold(0.0, priceSummer)
                sumOfPrices shouldBe 3640.0
            }
            "reduce" {
                val sumOfPrices =
                    borisGiltburgEveningOf31stOctober2024.tickets.mapNotNull { it.price }.reduce(priceSummer)
                sumOfPrices shouldBe 3640.0
            }
            "filter" {
                val performersWhoPlayPiano = Performers.all.filter(playsPiano)
                performersWhoPlayPiano shouldBe listOf(borisGiltburg, jeremyDenk, Performers.connieShih)
            }
            "count" {
                val numberOfPerformersWhoPlayPiano = Performers.all.count(playsPiano)
                numberOfPerformersWhoPlayPiano shouldBe 3
            }
            "find, when it" - {
                "exists" {
                    val firstPerformerWhoPlaysPiano = Performers.all.find(playsPiano)
                    firstPerformerWhoPlaysPiano shouldBe borisGiltburg
                }
                "doesn't exist" {
                    Performers.all.find { it.instrument == Violin && it.name == "Boris Giltburg" } shouldBe null
                }
            }
            "joinToString" {
                Performers.all.map { it.name }
                    .joinToString { x -> "$x!" } shouldBe "Boris Giltburg!, Steven Isserlis!, Joshua Bell!, Irène Duval!, Jeremy Denk!, Connie Shih!"
            }
            "zip" {
                val pianists = Performers.all.filter(playsPiano)
                val violinists = Performers.all.filter(violinOnly)

                pianists.zip(violinists) { x, y -> Pair(x, y) } shouldBe listOf(
                    Pair(borisGiltburg, joshuaBell),
                    Pair(jeremyDenk, ireneDuval)
                )
            }
        }
        "sets; for example" - {
            "all" - {
                setOf(borisGiltburg, jeremyDenk).all(playsPiano) shouldBe true
                setOf(borisGiltburg, jeremyDenk, ireneDuval).all(playsPiano) shouldBe false
            }
            "none" - {
                setOf(joshuaBell, stevenIsserlis).none(playsPiano) shouldBe true
                setOf(borisGiltburg, jeremyDenk).none(playsPiano) shouldBe false
            }
        }
        "maps; for example" - {
            "filter keys" {
                performerNationality.filterKeys(playsPiano) shouldBe mapOf(
                    borisGiltburg to "Israeli",
                    jeremyDenk to "American"
                )
            }
            "map values" {
                performerNationality.mapValues { pair -> "${pair.key.name} is ${pair.value}." } shouldBe mapOf(
                    borisGiltburg to "Boris Giltburg is Israeli.",
                    stevenIsserlis to "Steven Isserlis is British.",
                    joshuaBell to "Joshua Bell is American.",
                    jeremyDenk to "Jeremy Denk is American."
                )
            }
        }
    }

}) {

    companion object {

        val priceSummer: (Double, Double) -> Double = { x: Double, y: Double -> x + y }
        val playsPiano: (Performer) -> Boolean = { it.instrument == Piano }
        val violinOnly: (Performer) -> Boolean = { it.instrument == Violin }

        val performerNationality = mapOf(
            borisGiltburg to "Israeli",
            stevenIsserlis to "British",
            joshuaBell to "American",
            jeremyDenk to "American"
        )
    }
}