plugins {
    id("com.android.library")
    kotlin("android")
    id("org.jetbrains.kotlin.plugin.compose")
    id("publishing-conventions")
}

android {

    namespace = "io.github.hardikvaghani.flexiscale.compose"

    compileSdk = 36

    defaultConfig {
        minSdk = 21
    }

    testOptions {
        unitTests.isReturnDefaultValues = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(project(":flexiscale-runtime"))
    implementation(project(":flexiscale-resources"))
    testImplementation(kotlin("test"))
    testImplementation("junit:junit:4.13.2")

    implementation(platform("androidx.compose:compose-bom:2025.06.01"))

    implementation("androidx.compose.ui:ui")
}

//tasks.test { useJUnitPlatform() }

kotlin { jvmToolchain(21) }
