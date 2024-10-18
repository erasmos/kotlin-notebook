package com.erasmos.kotlin_notebook

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class FunctionSpec : FreeSpec({
    "functions can have" - {
        "default argument values" - {
            val cellist = Cellist("Steven Isserlis")

            cellist.dateOfBirth shouldBe null
        }
        "nested functions" {
            val instrumentsToBeProvided = getInstrumentsToBeProvidedByConcertHall(Data.Concerts.stevenIsserlisAndOthersEveningOf4thNovember2024)

            instrumentsToBeProvided shouldBe setOf(Instrument.Piano)
        }
    }
}) {
    companion object {
        fun getInstrumentsToBeProvidedByConcertHall(concert: Concert): Set<Instrument> {
            fun mustBeProvided(instrument: Instrument): Boolean = instrument.providedByConcertHall
            return concert.performers.map { it.instrument }.filter { mustBeProvided(it) }.toSet()
        }
    }
}

