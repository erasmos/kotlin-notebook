package com.erasmos.kotlin_notebook

import com.erasmos.kotlin_notebook.Data.Performers.jeremyDenk
import com.erasmos.kotlin_notebook.Data.Performers.joshuaBell
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.types.shouldBeSameInstanceAs
import java.time.LocalDate
import java.time.Month.DECEMBER
import java.time.Month.MAY

typealias PerformerBirthdays = Map<Performer, LocalDate>
typealias PerformerFact<A> = Map<Performer, A>

class TypeAliasSpec : FreeSpec({

    "Type aliases are useful for promoting the domain model; example" - {
        "one" {
            val performerBirthdays: PerformerBirthdays = mapOf(
                jeremyDenk to LocalDate.of(1970, MAY, 16),
                joshuaBell to LocalDate.of(1967, DECEMBER, 9)
            )

            val performerBirthdaysAsMap: Map<Performer, LocalDate> = performerBirthdays
            val performerBirthdaysAsFacts: PerformerFact<LocalDate> = performerBirthdays

            performerBirthdays shouldBeSameInstanceAs performerBirthdaysAsMap
            performerBirthdays shouldBeSameInstanceAs performerBirthdaysAsFacts
        }
    }

})