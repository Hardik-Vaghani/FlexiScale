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

package io.github.hardikvaghani.flexiscale.generator

import io.github.hardikvaghani.flexiscale.generator.model.BucketOutput
import io.github.hardikvaghani.flexiscale.generator.model.DimensionEntry
import io.github.hardikvaghani.flexiscale.generator.model.DimensionUnit
import io.github.hardikvaghani.flexiscale.generator.writer.ResourceFolderWriter
import io.github.hardikvaghani.flexiscale.runtime.model.ScreenBucket
import java.nio.file.Files
import kotlin.io.path.exists
import kotlin.io.path.readText
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertTrue

class ResourceFolderWriterTest {

    @Test
    fun write_createsAndroidResourceFolderAndDimensionsFile() {

        val outputDirectory =
            Files.createTempDirectory("flexiscale-resource-writer-test")

        ResourceFolderWriter(outputDirectory)
            .write(
                listOf(
                    BucketOutput(
                        bucket = ScreenBucket(411, ""),
                        entries =
                            listOf(
                                DimensionEntry(
                                    name = "app_px_16_00_to_dp",
                                    value = 17.6,
                                    unit = DimensionUnit.DP
                                )
                            )
                    )
                )
            )

        val xmlFile =
            outputDirectory
                .resolve("values-sw411dp")
                .resolve("dimensions.xml")

        assertTrue(xmlFile.exists())
        assertContains(
            charSequence = xmlFile.readText(),
            other = "<dimen name=\"app_px_16_00_to_dp\">17.60000dp</dimen>"
        )
    }
}
