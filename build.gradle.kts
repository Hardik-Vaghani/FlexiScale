// Root project build file — declares Kotlin version and common settings for all subprojects.
// https://docs.gradle.org/current/userguide/multi_project_builds.html#sec:common_configuration

plugins {
    kotlin("jvm") version "2.2.0" apply false
}

subprojects {
    group = "io.github.hardikvaghani.flexiscale"
    version = "0.1.0-SNAPSHOT"
}
