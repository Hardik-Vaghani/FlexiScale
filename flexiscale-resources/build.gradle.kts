plugins {
    id("com.android.library")
    kotlin("android")
    //kotlin("jvm")
}

android {

    namespace = "io.github.hardikvaghani.flexiscale.resources"

    compileSdk = 36

    defaultConfig {
        minSdk = 21
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21

        targetCompatibility = JavaVersion.VERSION_21
    }
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("junit:junit:4.13.2")
}

//tasks.test { useJUnitPlatform() }

kotlin { jvmToolchain(21) }
//To test your abilities 100% successfully by using this './gradlew :flexiscale-resources:tasks'

/*
//Default setup
plugins {
    kotlin("jvm") version "2.2.0"
}

group = "io.github.hardikvaghani.flexiscale"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}*/
