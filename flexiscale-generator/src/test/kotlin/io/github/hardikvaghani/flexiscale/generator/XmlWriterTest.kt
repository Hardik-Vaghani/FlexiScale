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
import io.github.hardikvaghani.flexiscale.generator.writer.XmlWriter
import io.github.hardikvaghani.flexiscale.runtime.model.ScreenBucket
import java.io.StringWriter
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertTrue

class XmlWriterTest {

    @Test
    fun write_outputsValidResourceXmlWithFormattedDimensions() {

        val stringWriter = StringWriter()

        XmlWriter().write(
            output =
                BucketOutput(
                    bucket = ScreenBucket(360, ""),
                    entries =
                        listOf(
                            DimensionEntry(
                                name = "app_px_1_00_to_dp",
                                value = 1.0,
                                unit = DimensionUnit.DP
                            ),
                            DimensionEntry(
                                name = "app_px_1_00_to_sp_neg",
                                value = -1.0,
                                unit = DimensionUnit.SP
                            )
                        )
                ),
            writer = stringWriter
        )

        val xml = stringWriter.toString()

        assertTrue(xml.startsWith("<?xml version=\"1.0\" encoding=\"utf-8\"?>"))
        assertContains(xml, "<resources>")
        assertContains(xml, "<dimen name=\"app_px_1_00_to_dp\">1.00000dp</dimen>")
        assertContains(xml, "<dimen name=\"app_px_1_00_to_sp_neg\">-1.00000sp</dimen>")
        assertTrue(xml.trimEnd().endsWith("</resources>"))
    }
}
