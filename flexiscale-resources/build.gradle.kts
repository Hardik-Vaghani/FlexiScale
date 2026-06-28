plugins {
    id("com.android.library")
    kotlin("android")
    id("publishing-conventions")
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

kotlin {
    jvmToolchain(21)
}
