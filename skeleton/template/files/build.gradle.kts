{{~def 'prjId' (asJavaId ghProjectId)~}}
import com.github.jrubygradle.JRubyPrepare
import org.asciidoctor.gradle.AsciidoctorTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.gradle.api.Task
import org.gradle.jvm.tasks.Jar
import org.ysb33r.groovy.dsl.vfs.VFS

buildscript {
    dependencies {
        classpath("org.ysb33r.gradle:vfs-gradle-plugin:1.0")
        classpath("commons-httpclient:commons-httpclient:3.1")
    }
}

val {{prjId}}Version: String by project
val deckjsVersion: String by project
val asciidoctorBackendVersion: String by project
val downloadDir = File(buildDir,"download")
val templateDir = File(downloadDir,"templates")
val deckjsDir = File(downloadDir,"deck.js")
val deckjsVersionDir = File(downloadDir,"deck.js-master")

plugins {
    kotlin("jvm") version "1.2.51"
    id("org.jetbrains.dokka") version "0.9.17"
    id("org.asciidoctor.convert") version "1.5.8.1"
    id("com.github.jruby-gradle.base") version "1.5.0"
}

repositories {
    jcenter()
}

apply {
    plugin("org.jetbrains.dokka")
    plugin("com.github.jruby-gradle.base")
    plugin("org.ysb33r.vfs")
    plugin("org.asciidoctor.convert")
}
java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

dependencies {
    compile(kotlin("reflect"))
    compile(kotlin("stdlib"))
    gems("rubygems:haml:5.0.4")
}

tasks {
    "download" {
        doLast {
            mkdir(downloadDir!!)
            with(VFS()) {
                cp(mapOf("recursive" to true, "overwrite" to true, "smash" to true),
                        "zip:https://github.com/asciidoctor/asciidoctor-deck.js/archive/${asciidoctorBackendVersion}.zip!asciidoctor-deck.js-${asciidoctorBackendVersion}/templates",
                        templateDir)
                cp(mapOf("recursive" to true, "overwrite" to true, "smash" to true),
                        "zip:https://github.com/imakewebthings/deck.js/archive/${deckjsVersion}.zip!deck.js-${deckjsVersion}",
                        deckjsDir)
            }
            deckjsVersionDir.renameTo(deckjsDir)
        }
    }
}
val downloadTask = tasks.get("download")
val jrubyPrepare = tasks.get("jrubyPrepare")

with(downloadTask) {
    description = "Download extra deckjs resources"
    outputs.dirs(templateDir, deckjsDir)
}

tasks.withType<AsciidoctorTask> {
    dependsOn(jrubyPrepare, downloadTask)

    sources(delegateClosureOf<PatternSet> {
        include("index.adoc")
    })

    resources(delegateClosureOf<CopySpec> {
        from (sourceDir) { include("img/**", "deck.js/**") }
        from (downloadDir) { include("deck.js/**") }
    })

    backends("html5")

    attributes.putAll(mapOf(
            "prjdir" to "${project.rootDir}",
            "sourcedir" to "${project.rootDir}/src/main",
            "imagesdir" to "./img"
    ))

    options(mapOf("template_dirs" to listOf(File(templateDir,"haml").absolutePath)))
}
