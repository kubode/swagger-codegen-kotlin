import groovy.util.Eval

group = "com.teamlab.smartphone"
version = "1.0-SNAPSHOT"

buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath(kotlin("gradle-plugin"))
    }
}

plugins {
    kotlin("jvm")
    application
}

application {
    mainClassName = "io.swagger.codegen.Codegen"
}

repositories {
    jcenter()
}

dependencies {
    compile(kotlin("stdlib-jre8"))
    compile("io.swagger:swagger-codegen-cli:2.2.2")
}

tasks {
    // https://stackoverflow.com/a/29382636
    "run"(JavaExec::class) {
        project.properties["appArgs"]?.let {
            @Suppress("UNCHECKED_CAST")
            args = Eval.me(it.toString()) as List<String>
        }
    }
}
