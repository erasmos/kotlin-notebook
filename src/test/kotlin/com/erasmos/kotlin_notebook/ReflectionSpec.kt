package com.erasmos.kotlin_notebook

import com.erasmos.kotlin_notebook.Data.Performers.borisGiltburg
import com.erasmos.kotlin_notebook.Grade.*
import com.erasmos.kotlin_notebook.Section.FrontStalls
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.property.shouldBeImmutable
import io.kotest.matchers.property.shouldBeMutable
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeSameInstanceAs
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.Month
import java.time.Month.JUNE
import java.time.Month.OCTOBER
import kotlin.reflect.KClass
import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.*

class ReflectionSpec : FreeSpec({
    "reflection allows us to" - {
        "get and set properties" {
            val properties = seatClass.declaredMemberProperties

            val seat = Seat("A16", FrontStalls, B)

            properties.size shouldBe 4
            properties.find { it.name == "id" }!!.apply {
                returnType.classifier shouldBe String::class
                returnType.isMarkedNullable shouldBe false
                shouldBeImmutable()
                call(seat) shouldBe "A16"
            }
            properties.find { it.name == "section" }!!.apply {
                returnType.classifier shouldBe Section::class
                returnType.isMarkedNullable shouldBe false
                shouldBeImmutable()
                call(seat) shouldBe FrontStalls
            }
            properties.find { it.name == "grade" }!!.apply {
                returnType.classifier shouldBe Grade::class
                returnType.isMarkedNullable shouldBe false
                shouldBeImmutable()
                call(seat) shouldBe B
            }
            properties.find { it.name == "lastInspected" }!!.apply {
                returnType.classifier shouldBe LocalDateTime::class
                returnType.isMarkedNullable shouldBe true
                shouldBeMutable()
                call(seat) shouldBe null
                (this as KMutableProperty<*>).setter.call(seat, LocalDateTime.of(2024, OCTOBER, 31, 23, 30, 0))
                call(seat) shouldBe LocalDateTime.of(2024, OCTOBER, 31, 23, 30, 0)
                seat.lastInspected shouldBe LocalDateTime.of(2024, OCTOBER, 31, 23, 30, 0)
            }
        }
        "call functions" {

            val declaredFunctions = concertClass.declaredFunctions

            val concert = Concert(
                "Boris Giltburg, piano",
                listOf(borisGiltburg),
                LocalDate.of(2024, OCTOBER, 31),
                StartTime(LocalTime.of(19, 30)),
                EndTime(LocalTime.of(21, 30)),
                mapOf(
                    A to 40.00,
                    B to 36.00,
                    C to 32.00,
                    D to 26.00,
                    E to 18.00
                )
            )

            declaredFunctions.size shouldBe 11
            declaredFunctions.map { it.name } shouldBe listOf(
                "component1",
                "component2",
                "component3",
                "component4",
                "component5",
                "component6",
                "copy",
                "equals",
                "hashCode",
                "price",
                "toString"
            )

            declaredFunctions.find { it.name == "price" }!!.apply {
                parameters.size shouldBe 2
                parameters.map { it.name } shouldBe listOf(null, "grade")
                parameters.find { it.name == "grade" }!!.apply {

                    call(concert, D) shouldBe 26.0
                    concert.price(D) shouldBe 26.0
                }
                returnType.classifier shouldBe Double::class
                returnType.isMarkedNullable shouldBe true
            }
        }
        "call constructors" {

            pianistClass.constructors.size shouldBe 3
            val primaryConstructor = pianistClass.primaryConstructor!!

            primaryConstructor.apply {
                shouldBeSameInstanceAs(pianistClass.constructors.toList()[2])
                parameters.size shouldBe 2
                val nameParameter = parameters.find { it.name == "name" }
                nameParameter!!.apply {
                    type.classifier shouldBe String::class
                }
                val dateOfBirthParameter = parameters.find { it.name == "dateOfBirth" }
                dateOfBirthParameter!!.apply {
                    type.classifier shouldBe LocalDate::class
                }
                val pianist = callBy(
                    mapOf(
                        nameParameter to "Boris Giltburg",
                        dateOfBirthParameter to LocalDate.of(1984, JUNE, 21)
                    )
                )
                pianist shouldBe Pianist("Boris Giltburg", LocalDate.of(1984, JUNE, 21))
            }

            pianistClass.constructors.toList()[1].apply {
                parameters.size shouldBe 0
                val pianist = callBy(
                    mapOf()
                )
                pianist shouldBe Pianist("Unknown", null)
            }

            pianistClass.constructors.toList()[0].apply {
                parameters.size shouldBe 1
                val nameParameter = parameters.find { it.name == "name" }
                nameParameter!!.apply {
                    type.classifier shouldBe String::class
                }
                val pianist = callBy(
                    mapOf(
                        nameParameter to "Boris Giltburg"
                    )
                )
                pianist shouldBe Pianist("Boris Giltburg", null)
            }

        }
        "examine the companion object" {
            val companionObjectType = performerInterface.companionObject!!
            companionObjectType.apply {
                declaredMemberProperties.size shouldBe 1
                val nameUnknownProperty = declaredMemberProperties.find { it.name == "NAME_UNKNOWN" }!!
                nameUnknownProperty.apply {
                    returnType.classifier shouldBe String::class
                    shouldBeImmutable()
                    call() shouldBe "Unknown"
                }

                declaredMemberFunctions.size shouldBe 1
                declaredMemberFunctions.find { it.name == "isPianist" }!!.apply {
                    parameters.size shouldBe 2
                    parameters[0].apply {
                        name shouldBe null
                        returnType.classifier shouldBe Boolean::class
                    }
                    parameters[1].apply {
                        name shouldBe "performer"
                        type.classifier shouldBe Performer::class
                        returnType.classifier shouldBe Boolean::class
                    }

                    Performer.isPianist(borisGiltburg) shouldBe true
                    this.call(performerInterface.companionObjectInstance, borisGiltburg) shouldBe true
                }
            }
            performerInterface.companionObjectInstance shouldBeSameInstanceAs (Performer)
        }
    }
}) {
    companion object {
        val seatClass: KClass<Seat> = Seat::class
        val concertClass: KClass<Concert> = Concert::class
        val pianistClass: KClass<Pianist> = Pianist::class
        val performerInterface: KClass<Performer> = Performer::class
    }
}