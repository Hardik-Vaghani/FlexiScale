package io.github.hardikvaghani.flexiscale.generator.writer

import io.github.hardikvaghani.flexiscale.generator.model.*
import java.util.Locale

class XmlWriter {

    fun write(
        output: BucketOutput
    ): String {

        val body =
            output.entries.joinToString("\n") {

                val unit =
                    when (it.unit) {
                        DimensionUnit.DP -> "dp"
                        DimensionUnit.SP -> "sp"
                    }

                String.format(
                    Locale.US,
                    "<dimen name=\"%s\">%.5f%s</dimen>",
                    it.name,
                    it.value,
                    unit
                )
            }

        return """
<?xml version="1.0" encoding="utf-8"?>
<resources>

$body

</resources>
""".trimIndent()
    }
}