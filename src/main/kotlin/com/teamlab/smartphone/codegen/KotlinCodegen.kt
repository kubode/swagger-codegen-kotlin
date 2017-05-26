package com.teamlab.smartphone.codegen

import io.swagger.codegen.CodegenConfig
import io.swagger.codegen.CodegenType
import io.swagger.codegen.DefaultCodegen
import io.swagger.codegen.SwaggerCodegen

class KotlinCodegen : DefaultCodegen(), CodegenConfig {
    override fun getTag() = CodegenType.CLIENT
    override fun getName() = "kotlin"
    override fun getHelp() = "Generate a Kotlin client."
    override fun toApiName(name: String?) = "Api"

    init {
        supportsInheritance = false
        templateDir = "kotlin"
        embeddedTemplateDir = "kotlin"
        modelTemplateFiles["model.mustache"] = ".kt"
        apiTemplateFiles["api.mustache"] = ".kt"
        // https://github.com/JetBrains/kotlin/blob/master/core/descriptors/src/org/jetbrains/kotlin/renderer/KeywordStringsGenerated.java
        (reservedWords as MutableSet) += setOf(
                "package",
                "as",
                "typealias",
                "class",
                "this",
                "super",
                "val",
                "var",
                "fun",
                "for",
                "null",
                "true",
                "false",
                "is",
                "in",
                "throw",
                "return",
                "break",
                "continue",
                "object",
                "if",
                "try",
                "else",
                "while",
                "do",
                "when",
                "interface",
                "typeof")
        (defaultIncludes as MutableSet) += setOf(
                "integer",
                "array",
                "string",
                "List",
                "Map")
        (languageSpecificPrimitives as MutableSet) += setOf(
                "Boolean",
                "Double",
                "Float",
                "Long",
                "Int",
                "Short",
                "Byte")
        (typeMapping as MutableMap) += mapOf(
                "integer" to "Int",
                "long" to "Long",
                "float" to "Float",
                "double" to "Double",
                "string" to "String",
                "byte" to "Byte",
                "binary" to "ByteArray",
                "boolean" to "Boolean",
                "date" to "Date",
                "dateTime" to "Date",
                "password" to "String",
                "array" to "List",
                "map" to "Map",
                "uuid" to "UUID")
        (importMapping as MutableMap) += mapOf(
                "Date" to "java.util.Date",
                "UUID" to "java.util.UUID")
    }
}

fun main(vararg args: String) = SwaggerCodegen.main(args)
