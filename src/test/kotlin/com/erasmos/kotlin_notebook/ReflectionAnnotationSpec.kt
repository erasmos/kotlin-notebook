package com.erasmos.kotlin_notebook

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDate
import java.util.UUID
import kotlin.reflect.KClass
import kotlin.reflect.KProperty
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.findAnnotation

/*
    This example is closely based on the one found in the advanced course, from Rock The JVM.
 */
class ReflectionAnnotationSpec : FreeSpec({

    "Annotations can be read at runtime; example:" - {
        "one" {
            val result = generateTableStatement(Customer::class)

            result shouldBe "CREATE TABLE customers (email_address String, id String, name String)"
        }
    }

}) {

    companion object {

        @Target(AnnotationTarget.CLASS)
        @Retention(AnnotationRetention.RUNTIME)
        annotation class Table(val name: String)

        @Target(AnnotationTarget.PROPERTY)
        @Retention(AnnotationRetention.RUNTIME)
        annotation class Column(val name: String)

        @Table("customers")
        class Customer(
            @Column("id") val id: UUID,
            @Column("name") val name: String,
            @Column("email_address") val emailAddress: String
        )

        fun generateTableStatement(clazz: KClass<*>): String? {
            fun getColumnType(property: KProperty<*>): String? =
                when (property.returnType.classifier) {
                    Int::class -> "Integer"
                    String::class -> "String"
                    UUID::class -> "String"
                    else -> null
                }

            val tableAnnotation: Table = clazz.findAnnotation<Table>() ?: return null
            val tableName = tableAnnotation.name

            val columns = clazz.declaredMemberProperties.mapNotNull { property: KProperty<*> ->
                val columnAnnotation = property.findAnnotation<Column>()
                if (columnAnnotation != null) {
                    val columnType = getColumnType(property)
                    if (columnType != null) "${columnAnnotation.name} $columnType" else null
                } else null
            }.sorted().joinToString(", ", "(", ")")

            return "CREATE TABLE $tableName $columns"
        }


    }
}