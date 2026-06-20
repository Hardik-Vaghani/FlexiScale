plugins {
    kotlin("jvm")
    application //if you want to run the main function, then enable it.
}


dependencies {
    implementation(project(":flexiscale-runtime"))
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}

//if you want to run the main function, then enable it.
application {
    mainClass.set(
        "io.github.hardikvaghani.flexiscale.generator.GeneratorRunnerKt"
    )
}
