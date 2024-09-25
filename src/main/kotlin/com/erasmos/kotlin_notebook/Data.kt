package com.erasmos.kotlin_notebook

import com.erasmos.kotlin_notebook.Data.Performers.borisGiltburg
import com.erasmos.kotlin_notebook.Data.Performers.connieShih
import com.erasmos.kotlin_notebook.Data.Performers.ireneDuval
import com.erasmos.kotlin_notebook.Data.Performers.jeremyDenk
import com.erasmos.kotlin_notebook.Data.Performers.joshuaBell
import com.erasmos.kotlin_notebook.Data.Performers.stevenIsserlis
import com.erasmos.kotlin_notebook.Grade.*
import com.erasmos.kotlin_notebook.Section.*
import java.time.LocalDate
import java.time.LocalTime
import java.time.Month
import java.time.Month.*

object Data {

    object Seats {
        val all = listOf(
            Seat("AA1", FrontStalls, D),
            Seat("AA2", FrontStalls, D),
            Seat("AA3", FrontStalls, D),
            Seat("AA17", FrontStalls, D),
            Seat("AA18", FrontStalls, D),
            Seat("BB1", FrontStalls, C),
            Seat("BB2", FrontStalls, C),
            Seat("BB18", FrontStalls, C),
            Seat("BB19", FrontStalls, C),
            Seat("CC1", FrontStalls, C),
            Seat("CC2", FrontStalls, C),
            Seat("CC3", FrontStalls, C),
            Seat("CC17", FrontStalls, C),
            Seat("CC18", FrontStalls, C),
            Seat("CC19", FrontStalls, C),
            Seat("A1", FrontStalls, B),
            Seat("A2", FrontStalls, B),
            Seat("A3", FrontStalls, B),
            Seat("A4", FrontStalls, B),
            Seat("A5", FrontStalls, B),
            Seat("A6", FrontStalls, B),
            Seat("A7", FrontStalls, B),
            Seat("A8", FrontStalls, B),
            Seat("A9", FrontStalls, B),
            Seat("A10", FrontStalls, B),
            Seat("A11", FrontStalls, B),
            Seat("A12", FrontStalls, B),
            Seat("A13", FrontStalls, B),
            Seat("A14", FrontStalls, B),
            Seat("A15", FrontStalls, B),
            Seat("A16", FrontStalls, B),
            Seat("A17", FrontStalls, B),
            Seat("A18", FrontStalls, B),
            Seat("A19", FrontStalls, B),
            Seat("B2", FrontStalls, B),
            Seat("B3", FrontStalls, B),
            Seat("B4", FrontStalls, B),
            Seat("B5", FrontStalls, B),
            Seat("B6", FrontStalls, B),
            Seat("B7", FrontStalls, B),
            Seat("B8", FrontStalls, B),
            Seat("B9", FrontStalls, B),
            Seat("B10", FrontStalls, B),
            Seat("B11", FrontStalls, B),
            Seat("B12", FrontStalls, B),
            Seat("B13", FrontStalls, B),
            Seat("B14", FrontStalls, B),
            Seat("B16", FrontStalls, B),
            Seat("B17", FrontStalls, B),
            Seat("B18", FrontStalls, B),
            Seat("B19", FrontStalls, B),
            Seat("C1", FrontStalls, A),
            Seat("C2", FrontStalls, A),
            Seat("C3", FrontStalls, A),
            Seat("C4", FrontStalls, A),
            Seat("C5", FrontStalls, A),
            Seat("C6", FrontStalls, A),
            Seat("C7", FrontStalls, A),
            Seat("C8", FrontStalls, A),
            Seat("C9", FrontStalls, A),
            Seat("C10", FrontStalls, A),
            Seat("C11", FrontStalls, A),
            Seat("C12", FrontStalls, A),
            Seat("C13", FrontStalls, A),
            Seat("C14", FrontStalls, A),
            Seat("C15", FrontStalls, A),
            Seat("C16", FrontStalls, A),
            Seat("C17", FrontStalls, A),
            Seat("C18", FrontStalls, A),
            Seat("C19", FrontStalls, A),
            Seat("W1", RearStalls, E),
            Seat("W2", RearStalls, E),
            Seat("W3", RearStalls, E),
            Seat("W4", RearStalls, E),
            Seat("W5", RearStalls, E),
            Seat("W6", RearStalls, E),
            Seat("W7", RearStalls, E),
            Seat("W8", RearStalls, E),
            Seat("W9", RearStalls, E),
            Seat("W10", RearStalls, E),
            Seat("W11", RearStalls, E),
            Seat("W12", RearStalls, E),
            Seat("W13", RearStalls, E),
            Seat("W14", RearStalls, E),
            Seat("W15", RearStalls, E),
            Seat("W16", RearStalls, E),
            Seat("W17", RearStalls, E),
            Seat("W18", RearStalls, E),
            Seat("W19", RearStalls, E),
            Seat("A1", Balcony, B),
            Seat("A2", Balcony, B),
            Seat("A3", Balcony, B),
            Seat("A4", Balcony, B),
            Seat("A5", Balcony, B),
            Seat("A6", Balcony, B),
            Seat("A7", Balcony, B),
            Seat("A8", Balcony, B),
            Seat("A9", Balcony, B),
            Seat("A10", Balcony, B),
            Seat("A11", Balcony, B),
            Seat("A12", Balcony, B),
            Seat("A13", Balcony, B),
            Seat("A14", Balcony, B),
            Seat("A15", Balcony, B),
            Seat("A16", Balcony, B),
            Seat("A17", Balcony, B),
            Seat("A18", Balcony, B),
            Seat("A19", Balcony, B),
            Seat("A20", Balcony, B),
            Seat("A21", Balcony, B),
            Seat("A22", Balcony, B),
        )
    }

    object Performers {

        val borisGiltburg = Pianist("Boris Giltburg", LocalDate.of(1984, JUNE, 21))
        val stevenIsserlis = Cellist("Steven Isserlis", LocalDate.of(1958, DECEMBER, 19))
        val joshuaBell = Violinist("Joshua Bell", LocalDate.of(1967, DECEMBER, 9))
        val ireneDuval = Violinist("Irène Duval")
        val jeremyDenk = Pianist("Jeremy Denk", LocalDate.of(1970, MAY, 16))
        val connieShih = Pianist("Connie Shih")

        val all = listOf(
            borisGiltburg,
            stevenIsserlis,
            joshuaBell,
            ireneDuval,
            jeremyDenk,
            connieShih
        )
    }

    object Concerts {

        val borisGiltburgEveningOf31stOctober2024 = Concert(
            "Boris Giltburg, piano",
            listOf(borisGiltburg),
            LocalDate.of(2024, OCTOBER, 31),
            StartTime(LocalTime.of(19, 30)),
            EndTime(LocalTime.of(21 , 30)),
            mapOf(
                A to 40.00,
                B to 36.00,
                C to 32.00,
                D to 26.00,
                E to 18.00
            )
        )

        val stevenIsserlisAndOthersEveningOf4thNovember2024 =
            Concert(
                "Steven Isserlis cello; Joshua Bell violin; Irène Duval violin; Jeremy Denk piano; Connie Shih piano; Quatuor Agate",
                listOf(
                    stevenIsserlis,
                    joshuaBell,
                    ireneDuval,
                    jeremyDenk,
                    connieShih
                ),
                LocalDate.of(2024, NOVEMBER, 4),
                StartTime(LocalTime.of(19, 30)),
                EndTime(LocalTime.of(21, 30)),
                mapOf(
                    A to 50.00,
                    B to 42.00,
                    C to 32.00,
                    D to 26.00,
                    E to 18.00
                )
            )

        val all = listOf(
            borisGiltburgEveningOf31stOctober2024,
            stevenIsserlisAndOthersEveningOf4thNovember2024
        )

    }




}