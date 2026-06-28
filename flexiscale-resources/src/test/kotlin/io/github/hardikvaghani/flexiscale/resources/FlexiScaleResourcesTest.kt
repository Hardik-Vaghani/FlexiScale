package io.github.hardikvaghani.flexiscale.resources

import java.nio.file.Path
import javax.xml.parsers.DocumentBuilderFactory
import kotlin.io.path.exists
import kotlin.io.path.isDirectory
import kotlin.io.path.readText
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FlexiScaleResourcesTest {

    @Test
    fun allExpectedBucketFoldersContainDimensionsXml() {

        expectedBuckets.forEach { bucket ->
            val file =
                resourcesDirectory
                    .resolve("values-sw${bucket}dp")
                    .resolve("dimensions.xml")

            assertTrue(
                actual = file.exists(),
                message = "Missing dimensions.xml for values-sw${bucket}dp"
            )
        }
    }

    @Test
    fun allDimensionsFilesAreValidAndroidResourceXml() {

        expectedBuckets.forEach { bucket ->
            val file =
                resourcesDirectory
                    .resolve("values-sw${bucket}dp")
                    .resolve("dimensions.xml")

            val document =
                DocumentBuilderFactory
                    .newInstance()
                    .newDocumentBuilder()
                    .parse(file.toFile())

            assertEquals(
                expected = "resources",
                actual = document.documentElement.nodeName,
                message = "Invalid root tag for values-sw${bucket}dp"
            )
        }
    }

    @Test
    fun generatedDimensionsContainCompleteConfiguredRange() {

        expectedBuckets.forEach { bucket ->
            val xml =
                resourcesDirectory
                    .resolve("values-sw${bucket}dp")
                    .resolve("dimensions.xml")
                    .readText()

            assertEquals(
                expected = expectedDimensionCountPerBucket,
                actual = Regex("<dimen ").findAll(xml).count(),
                message = "Unexpected dimension count for values-sw${bucket}dp"
            )

            assertTrue(xml.contains("name=\"app_px_0_10_to_dp\""))
            assertTrue(xml.contains("name=\"app_px_0_10_to_dp_neg\""))
            assertTrue(xml.contains("name=\"app_px_0_10_to_sp\""))
            assertTrue(xml.contains("name=\"app_px_0_10_to_sp_neg\""))
            assertTrue(xml.contains("name=\"app_px_500_00_to_dp\""))
            assertTrue(xml.contains("name=\"app_px_500_00_to_dp_neg\""))
            assertTrue(xml.contains("name=\"app_px_500_00_to_sp\""))
            assertTrue(xml.contains("name=\"app_px_500_00_to_sp_neg\""))
        }
    }

    @Test
    fun generatedDimensionsUseOnlyDpAndSpUnits() {

        expectedBuckets.forEach { bucket ->
            val xml =
                resourcesDirectory
                    .resolve("values-sw${bucket}dp")
                    .resolve("dimensions.xml")
                    .readText()

            Regex("<dimen name=\"[^\"]+\">-?[0-9]+\\.[0-9]{5}(dp|sp)</dimen>")
                .findAll(xml)
                .count()
                .also { validDimensionCount ->
                    assertEquals(
                        expected = expectedDimensionCountPerBucket,
                        actual = validDimensionCount,
                        message = "Malformed dimension entries in values-sw${bucket}dp"
                    )
                }
        }
    }

    private val resourcesDirectory: Path
        get() =
            findModuleDirectory()
                .resolve("src")
                .resolve("main")
                .resolve("res")

    private fun findModuleDirectory(): Path {

        var current =
            Path.of("").toAbsolutePath()

        while (true) {
            val candidate =
                current.resolve("flexiscale-resources")

            if (candidate.exists() && candidate.isDirectory()) {
                return candidate
            }

            if (current.fileName?.toString() == "flexiscale-resources") {
                return current
            }

            current =
                current.parent
                    ?: error("Unable to locate flexiscale-resources module")
        }
    }

    private companion object {

        private const val expectedDimensionCountPerBucket = 20_000

        private val expectedBuckets =
            listOf(
                192,
                240,
                280,
                300,
                320,
                330,
                360,
                390,
                411,
                420,
                450,
                480,
                510,
                540,
                570,
                600,
                640,
                680,
                720,
                760,
                800,
                840,
                900,
                960,
                1024,
                1080,
                1200,
                1280,
                1366,
                1440,
                1600,
                1920,
                2560
            )
    }
}
