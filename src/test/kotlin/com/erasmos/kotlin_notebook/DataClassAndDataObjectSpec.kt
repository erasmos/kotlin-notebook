package com.erasmos.kotlin_notebook

import com.erasmos.kotlin_notebook.Grade.D
import com.erasmos.kotlin_notebook.Section.Balcony
import com.erasmos.kotlin_notebook.Section.FrontStalls
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.string.shouldNotContain

class DataClassAndDataObjectSpec : FreeSpec({
    "a data class offers automatic" - {
        "implementations of several functions" - {
            "equals" - {
                "with only constructor values" - {
                    val seatOne = Seat("AA1", FrontStalls, D)
                    val seatTwo = Seat("AA1", FrontStalls, D)
                    val seatThree = Seat("AA2", FrontStalls, D)
                    val seatFour = Seat("AA1", Balcony, D)

                    seatOne shouldBe seatTwo
                    seatOne shouldNotBe seatThree
                    seatOne shouldNotBe seatFour
                }
            }
            "hashCode" - {
                val seatOne = Seat("AA1", FrontStalls, D)
                val seatTwo = Seat("AA1", FrontStalls, D)
                val seatThree = Seat("AA2", FrontStalls, D)
                val seatFour = Seat("AA1", Balcony, D)

                seatOne.hashCode() shouldBe seatTwo.hashCode()
                seatOne.hashCode() shouldNotBe seatThree.hashCode()
                seatOne.hashCode() shouldNotBe seatFour.hashCode()
            }
            "toString" - {
                val seat = Seat("AA1", FrontStalls, D)

                seat.toString() shouldBe "Seat(id=AA1, section=FrontStalls, grade=D)"
                seat.toString() shouldNotContain("stairs")
            }
        }
        "destructuring" - {
            val seat = Seat("AA1", FrontStalls, D)

            val (id, section, grade) = seat
            id shouldBe "AA1"
            section shouldBe FrontStalls
            grade shouldBe D
        }
    }
    "a data object is a singleton, which" - {
        "offers toString" - {
            ConcertHall.toString() shouldBe "ConcertHall"
        }
    }
}
)