package com.erasmos.kotlin_notebook

import com.erasmos.kotlin_notebook.Data.Concerts.stevenIsserlisAndOthersEveningOf4thNovember2024
import com.erasmos.kotlin_notebook.Data.Seats
import com.erasmos.kotlin_notebook.Grade.A
import com.erasmos.kotlin_notebook.Section.Balcony
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class KotlinSymbolProcessingSpec : FreeSpec({

    "KSP allows us to use Kotlin to write boilerplate code; example:" - {
        "one" {
            val seatBuilder = SeatBuilder()

            val seat = seatBuilder
                .id("ZZ0")
                .section(Balcony)
                .grade(A)
                .build()

            seat shouldBe Seat("ZZ0", Balcony, A)
        }
        "two" {
            val ticketBuilder = TicketBuilder()
            val ticket = ticketBuilder
                .concert(stevenIsserlisAndOthersEveningOf4thNovember2024)
                .seat(Seats.all[10])
                .build()

            ticket shouldBe Ticket(stevenIsserlisAndOthersEveningOf4thNovember2024, Seats.all[10])
        }

    }
})