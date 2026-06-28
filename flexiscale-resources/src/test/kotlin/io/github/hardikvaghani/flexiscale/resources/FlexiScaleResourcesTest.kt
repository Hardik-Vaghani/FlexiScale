/*
 * Copyright 2025 Hardik Vaghani
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.hardikvaghani.flexiscale.resources

import java.nio.file.Files
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
    fun allDiscoveredBucketFoldersContainDimensionsXml() {

        discoveredBuckets.forEach { (folderName, absolutePath) ->
            val file = absolutePath.resolve("dimensions.xml")

            assertTrue(
                actual = file.exists(),
                message = "Missing dimensions.xml in $folderName"
            )
        }
    }

    @Test
    fun allDimensionsFilesAreValidAndroidResourceXml() {

        discoveredBuckets.forEach { (folderName, absolutePath) ->
            val file = absolutePath.resolve("dimensions.xml")

            val document =
                DocumentBuilderFactory
                    .newInstance()
                    .newDocumentBuilder()
                    .parse(file.toFile())

            assertEquals(
                expected = "resources",
                actual = document.documentElement.nodeName,
                message = "Invalid root tag in $folderName/dimensions.xml"
            )
        }
    }

    @Test
    fun generatedDimensionsContainCompleteConfiguredRange() {

        discoveredBuckets.forEach { (folderName, absolutePath) ->
            val xml =
                absolutePath
                    .resolve("dimensions.xml")
                    .readText()

            assertEquals(
                expected = expectedDimensionCountPerBucket,
                actual = Regex("<dimen ").findAll(xml).count(),
                message = "Unexpected dimension count in $folderName"
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

        discoveredBuckets.forEach { (folderName, absolutePath) ->
            val xml =
                absolutePath
                    .resolve("dimensions.xml")
                    .readText()

            Regex("<dimen name=\"[^\"]+\">-?[0-9]+\\.[0-9]{5}(dp|sp)</dimen>")
                .findAll(xml)
                .count()
                .also { validDimensionCount ->
                    assertEquals(
                        expected = expectedDimensionCountPerBucket,
                        actual = validDimensionCount,
                        message = "Malformed dimension entries in $folderName"
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

    /**
     * Discovers all values-swXXXdp bucket folders in the resources directory
     * by scanning the filesystem. This keeps the test in sync with the
     * generator output automatically.
     *
     * The result is cached so directory traversal happens only once.
     */
    private val discoveredBuckets: List<Pair<String, Path>> by lazy {
        val resDir = resourcesDirectory

        Files
            .newDirectoryStream(resDir) { path ->
                path.fileName
                    .toString()
                    .startsWith("values-sw") &&
                    path.isDirectory()
            }
            .use { stream ->
                stream
                    .map { path ->
                        path.fileName.toString() to path
                    }
                    .sortedBy { it.first }
                    .toList()
            }
    }

    private companion object {

        /**
         * GeneratorConfig.DEFAULT produces:
         * - start=0.1, end=500.0, step=0.1 → 5000 values
         * - generateDp=true, generateSp=true, includeNegative=true
         * - 5000 × 4 = 20,000 entries per bucket
         */
        private const val expectedDimensionCountPerBucket = 20_000
    }
}
