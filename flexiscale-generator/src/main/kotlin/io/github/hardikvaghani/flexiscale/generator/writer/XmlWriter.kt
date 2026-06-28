package io.github.hardikvaghani.flexiscale.generator.writer

import io.github.hardikvaghani.flexiscale.generator.model.BucketOutput
import io.github.hardikvaghani.flexiscale.generator.model.DimensionUnit
import java.io.Writer
import java.util.Locale

class XmlWriter {

    fun write(
        output: BucketOutput,
        writer: Writer
    ) {
        writer.appendLine("<?xml version=\"1.0\" encoding=\"utf-8\"?>")
        writer.appendLine("<resources>")
        writer.appendLine()

        output.entries.forEach { entry ->
            val unit = when (entry.unit) {
                DimensionUnit.DP -> "dp"
                DimensionUnit.SP -> "sp"
            }
            writer.appendLine(
                "<dimen name=\"${entry.name}\">${
                    "%.5f".format(
                        Locale.US,
                        entry.value
                    )
                }$unit</dimen>"
            )
        }

        writer.appendLine()
        writer.appendLine("</resources>")
    }
}
