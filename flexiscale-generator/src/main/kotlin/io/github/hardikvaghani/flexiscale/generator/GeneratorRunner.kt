package io.github.hardikvaghani.flexiscale.generator

import io.github.hardikvaghani.flexiscale.generator.generator.FlexiScaleGenerator
import io.github.hardikvaghani.flexiscale.generator.model.GeneratorConfig
import io.github.hardikvaghani.flexiscale.generator.writer.ResourceFolderWriter
import java.nio.file.Path
import java.nio.file.Paths

fun main() {
    /**
    11 -     * Generated files are stored under build/generated/flexiscale.
    12 -     * This follows Gradle conventions and keeps generated artifacts
    13 -     * separate from source code.
    14 -     *
    15 -     * Paths.get(System.getProperty("user.dir"), "generated")
    16 -     * Paths.get("generated")
    17 -     * Paths.get("build/generated/flexiscale")
    18 -     * Paths.get("build", "generated", "flexiscale")
    19 -     * Paths.get("flexiscale-generator", "build", "generated", "flexiscale")
    20 -     * Paths.get(System.getProperty("user.dir"),"flexiscale-generator", "build", "generated", "flexiscale")
    21 -     * Paths.get(System.getProperty("user.dir"),"flexiscale-resources", "src", "main", "res")
    22 -     */
    //val outputDirectory = Paths.get(System.getProperty("user.dir"),"flexiscale-resources", "src", "main", "res") 
    val outputDirectory =
        repositoryRoot()
            .resolve("flexiscale-resources")
            .resolve("src")
            .resolve("main")
            .resolve("res")

    val generator =
        FlexiScaleGenerator(
            config = GeneratorConfig.DEFAULT,

            writer = ResourceFolderWriter(outputDirectory = outputDirectory)
        )


    generator.generateAll()

    println(
        "FlexiScale XML generation completed."
    )
}

// Run those commands and create your generator folder with variation files:-
// ./gradlew :flexiscale-generator:run --stacktrace
    // OR
// ./gradlew :flexiscale-generator:run
// find generated -type f | wc -l

private fun repositoryRoot(): Path {

    val currentDirectory =
        Paths
            .get(System.getProperty("user.dir"))
            .toAbsolutePath()

    return if (currentDirectory.fileName.toString() == "flexiscale-generator") {
        currentDirectory.parent
    } else {
        currentDirectory
    }
}
