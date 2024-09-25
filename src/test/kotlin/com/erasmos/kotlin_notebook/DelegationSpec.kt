package com.erasmos.kotlin_notebook

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDate
import java.time.Month
import java.time.Month.JUNE

class DelegationSpec : FreeSpec({

    "Delegation can be easily added to a wrapper class, where" - {
        "where any delegate property or method may be overridden" {
            val performer = Data.Performers.borisGiltburg
            val artistInResidence = ArtistInResidence(performer)

            performer.name shouldBe "Boris Giltburg"
            performer.dateOfBirth shouldBe LocalDate.of(1984, JUNE, 21)
            performer.instrument shouldBe Instrument.Piano
            performer.getDescription() shouldBe "Boris Giltburg, Piano"

            artistInResidence.name shouldBe "Boris Giltburg"
            artistInResidence.dateOfBirth shouldBe LocalDate.of(1984, JUNE, 21)
            artistInResidence.instrument shouldBe Instrument.Piano
            artistInResidence.getDescription() shouldBe "[Artist In Residence] Boris Giltburg, Piano"
        }
    }

})