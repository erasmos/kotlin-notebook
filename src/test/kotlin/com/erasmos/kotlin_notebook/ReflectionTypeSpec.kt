package com.erasmos.kotlin_notebook

import com.erasmos.kotlin_notebook.Grade.*
import com.erasmos.kotlin_notebook.Section.*
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import kotlin.reflect.KParameter
import kotlin.reflect.KType
import kotlin.reflect.full.primaryConstructor

class ReflectionTypeSpec : FreeSpec({

    "Usually types are erased by the compiler, but there are some workarounds; example" - {
        "one" {
            val rawSeat =
                """
                    id = A16
                    section = Balcony
                    grade = B
                """.trimIndent()

            val seat = SeatReader(rawSeat).readAs<Seat>()

            seat shouldBe Seat("A16", Balcony, B)
        }
    }

}) {

    companion object {

        class SeatReader(private val content: String) {

            inline fun <reified T : Any> readAs(): T =
                parse<T>(parse())


            inline fun <reified T : Any> parse(properties: Map<String, String>): T {
                val kClass = T::class
                val constructor = kClass.primaryConstructor
                    ?: throw IllegalArgumentException("No primary constructor found for [$kClass]")
                val constructorParameters = constructor.parameters.associateWith { parameter: KParameter ->
                    val key = parameter.name
                        ?: throw IllegalArgumentException("Missing name for constructor parameter [$parameter]")
                    val value = properties[key]
                        ?: throw IllegalArgumentException("Missing value for constructor parameter [$key]")
                    parse(value, parameter.type)
                }
                return constructor.callBy(constructorParameters)
            }

            fun parse(): Map<String, String> {
                val configMap = mutableMapOf<String, String>()
                content.split("\n").map { line ->
                    val trimmedLine = line.trim()
                    if (trimmedLine.isNotEmpty() && !trimmedLine.startsWith("#")) {
                        val (key, value) = trimmedLine.split("=").map { it.trim() }
                        configMap[key] = value
                    }
                }
                return configMap
            }

            fun parse(value: String, type: KType): Any =
                when (type.classifier) {
                    String::class -> value
                    Grade::class -> Grade.valueOf(value)
                    Section::class -> Section.valueOf(value)
                    else -> throw IllegalArgumentException("Unsupported type [$type]")
                }


        }

    }

}