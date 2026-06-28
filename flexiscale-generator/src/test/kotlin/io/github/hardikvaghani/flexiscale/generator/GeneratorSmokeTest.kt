package io.github.hardikvaghani.flexiscale.generator

import io.github.hardikvaghani.flexiscale.generator.generator.FlexiScaleGenerator
import io.github.hardikvaghani.flexiscale.generator.model.GeneratorConfig
import io.github.hardikvaghani.flexiscale.generator.writer.ResourceFolderWriter
import io.github.hardikvaghani.flexiscale.runtime.model.ScreenBucket
import java.nio.file.Files
import kotlin.io.path.exists
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GeneratorSmokeTest {

    @Test
    fun generateAll_writesOneDimensionsFilePerScreenBucket() {

        val outputDirectory =
            Files.createTempDirectory("flexiscale-generator-test")

        val generatedCount =
            FlexiScaleGenerator(
                config =
                    GeneratorConfig(
                        start = 1.0,
                        end = 1.0,
                        step = 1.0,
                        generateDp = true,
                        generateSp = false,
                        includeNegative = false
                    ),
                writer =
                    ResourceFolderWriter(outputDirectory)
            ).generateAll()

        assertEquals(
            expected = ScreenBucket.allBuckets.size,
            actual = generatedCount
        )

        ScreenBucket.allBuckets.forEach { bucket ->
            assertTrue(
                actual =
                    outputDirectory
                        .resolve("values-sw${bucket.minWidthDp}dp")
                        .resolve("dimensions.xml")
                        .exists(),
                message = "Missing generated dimensions.xml for sw${bucket.minWidthDp}"
            )
        }
    }
}
