package com.teamlab.smartphone.codegen

import io.swagger.codegen.*
import io.swagger.codegen.languages.JavaClientCodegen
import io.swagger.models.Model

class KotlinCodegen : JavaClientCodegen(), CodegenConfig {
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
        apiTestTemplateFiles.clear()
        modelDocTemplateFiles.clear()
        apiDocTemplateFiles.clear()
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

    override fun processOpts() {
        super.processOpts()
        supportingFiles.clear()
    }

    override fun fromModel(name: String, model: Model, allDefinitions: MutableMap<String, Model>): CodegenModel {
        return super.fromModel(name, model, allDefinitions).apply {
            imports.remove("ApiModelProperty")
            imports.remove("ApiModel")
        }
    }

    override fun postProcessOperations(objs: MutableMap<String, Any>): MutableMap<String, Any> {
        super.postProcessOperations(objs)
        @Suppress("UNCHECKED_CAST")
        (objs["operations"] as? Map<String, Any>)?.let {
            (it["operation"] as? List<CodegenOperation>)?.forEach {
                it.path = it.path.removePrefix("/")
            }
        }
        return objs
    }
}

fun main(vararg args: String) = SwaggerCodegen.main(args)
