package com.erasmos.kotlin_notebook

import com.erasmos.kotlin_notebook.Data.Concerts.borisGiltburgEveningOf31stOctober2024
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import java.time.LocalTime

class ValueClassSpec : FreeSpec({
    "a value class is merely a low-memory, 'disposable' wrapper around a single primitive type; for example" {
        val concert = borisGiltburgEveningOf31stOctober2024

        concert.startTime shouldBe StartTime(LocalTime.of(19, 30))
        concert.startTime shouldNotBe LocalTime.of(19, 30)
        concert.endTime shouldBe EndTime(LocalTime.of(21, 30))
        concert.endTime shouldNotBe LocalTime.of(21, 30)
    }
}
)
