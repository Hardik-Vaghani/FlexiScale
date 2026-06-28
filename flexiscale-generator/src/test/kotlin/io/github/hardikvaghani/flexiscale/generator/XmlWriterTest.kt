package io.github.hardikvaghani.flexiscale.generator

import io.github.hardikvaghani.flexiscale.generator.model.BucketOutput
import io.github.hardikvaghani.flexiscale.generator.model.DimensionEntry
import io.github.hardikvaghani.flexiscale.generator.model.DimensionUnit
import io.github.hardikvaghani.flexiscale.generator.writer.XmlWriter
import io.github.hardikvaghani.flexiscale.runtime.model.ScreenBucket
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertTrue

class XmlWriterTest {

    @Test
    fun write_outputsValidResourceXmlWithFormattedDimensions() {

        val xml =
            XmlWriter().write(
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
                )
            )

        assertTrue(xml.startsWith("<?xml version=\"1.0\" encoding=\"utf-8\"?>"))
        assertContains(xml, "<resources>")
        assertContains(xml, "<dimen name=\"app_px_1_00_to_dp\">1.00000dp</dimen>")
        assertContains(xml, "<dimen name=\"app_px_1_00_to_sp_neg\">-1.00000sp</dimen>")
        assertTrue(xml.endsWith("</resources>"))
    }
}
