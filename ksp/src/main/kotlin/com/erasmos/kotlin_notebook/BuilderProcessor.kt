package com.erasmos.kotlin_notebook

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.*
import com.google.devtools.ksp.validate

class BuilderProcessor(private val generator: CodeGenerator) : SymbolProcessor {
    override fun process(resolver: Resolver): List<KSAnnotated> {

        val classesWhichCannotBeProcessed = mutableListOf<KSAnnotated>()
        val eligibleClasses = resolver
            .getSymbolsWithAnnotation("com.erasmos.kotlin_notebook.Builder")
            .filterIsInstance<KSClassDeclaration>()
        eligibleClasses.forEach { clazz ->
            if (clazz.validate())
                generateBuilder(clazz)
        }
        return classesWhichCannotBeProcessed
    }

    private fun generateBuilder(clazz: KSClassDeclaration) {
        val className: String = clazz.simpleName.asString()
        val packageName: String = clazz.packageName.asString()
        val parameters: List<KSValueParameter> = clazz.primaryConstructor!!.parameters
        val classFiles: Array<KSFile> = listOfNotNull(clazz.containingFile).toTypedArray()
        val builderClassName = "${className}Builder"
        val builderClassFile = generator.createNewFile(
            Dependencies(aggregating = false, *classFiles),
            packageName,
            builderClassName
        )
        builderClassFile.bufferedWriter().use { writer ->
            writer.write("package $packageName\n\n")
            writer.write("import $packageName.$className\n")
            writer.write("class $builderClassName {\n")
            parameters.forEach { parameter ->
                val parameterName = parameter.name!!.asString()
                val parameterType = parameter.type.resolve()
                writer.write("\n")
                writer.write("  private var $parameterName: $parameterType? = null\n")
                writer.write(" fun $parameterName(value: $parameterType) = apply { $parameterName = value}\n")
            }
            writer.write(" fun build(): $className {\n")
            writer.write("     return $className(\n")
            parameters.forEachIndexed { index, parameter ->
                val parameterName = parameter.name!!.asString()
                writer.write("    $parameterName = $parameterName ?: throw IllegalArgumentException(\"$parameterName must be provided!\")")
                if (index < parameters.size - 1) writer.write(",")
                writer.write("\n")
            }
            writer.write("    )")
            writer.write("  }")
            writer.write("}")
        }
    }

}
