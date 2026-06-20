package io.github.hardikvaghani.flexiscale.generator

import io.github.hardikvaghani.flexiscale.generator.generator.FlexiScaleGenerator
import io.github.hardikvaghani.flexiscale.generator.model.GeneratorConfig
import io.github.hardikvaghani.flexiscale.generator.writer.ResourceFolderWriter
import java.nio.file.Paths

fun main() {

    val generator =
        FlexiScaleGenerator(
            config =
                GeneratorConfig.DEFAULT,

            writer =
                ResourceFolderWriter(
                    outputDirectory =
                        Paths.get("generated")
                )
        )

    generator.generateAll()

    println(
        "FlexiScale XML generation completed."
    )
}