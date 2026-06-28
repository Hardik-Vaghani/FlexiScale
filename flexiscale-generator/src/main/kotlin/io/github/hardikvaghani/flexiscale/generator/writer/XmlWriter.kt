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
