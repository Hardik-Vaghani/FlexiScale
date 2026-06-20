package io.github.hardikvaghani.flexiscale.generator

import io.github.hardikvaghani.flexiscale.generator.generator.FlexiScaleGenerator
import io.github.hardikvaghani.flexiscale.generator.model.GeneratorConfig
import io.github.hardikvaghani.flexiscale.generator.writer.ResourceFolderWriter
import java.nio.file.Paths

fun main() {

    /**
     * Generated files are stored under build/generated/flexiscale.
     * This follows Gradle conventions and keeps generated artifacts
     * separate from source code.
     *
     * Paths.get(System.getProperty("user.dir"), "generated")
     * Paths.get("generated")
     * Paths.get("build/generated/flexiscale")
     * Paths.get("build", "generated", "flexiscale")
     * Paths.get("flexiscale-generator", "build", "generated", "flexiscale")
     * Paths.get(System.getProperty("user.dir"),"flexiscale-generator", "build", "generated", "flexiscale")
     */
    val outputDirectory = Paths.get(System.getProperty("user.dir"),"flexiscale-generator", "build", "generated", "flexiscale")

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