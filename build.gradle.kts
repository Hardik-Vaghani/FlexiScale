// Root project build file — declares Kotlin version and common settings for all subprojects.
// https://docs.gradle.org/current/userguide/multi_project_builds.html#sec:common_configuration

plugins {
    id("com.android.library") version "8.13.0" apply false
    kotlin("android") version "2.2.0" apply false
    kotlin("jvm") version "2.2.0" apply false
    id("org.jetbrains.kotlin.plugin.compose") version "2.2.0" apply false
}

subprojects {
    group = "io.github.hardikvaghani.flexiscale"
    version = "0.1.0-SNAPSHOT"
}

//runtime      -> kotlin-jvm
//generator    -> kotlin-jvm
//tokens       -> kotlin-jvm
//
//resources    -> android-library
//compose      -> android-library