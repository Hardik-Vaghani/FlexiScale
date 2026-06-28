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

package io.github.hardikvaghani.flexiscale.generator.generator

import io.github.hardikvaghani.flexiscale.generator.model.*
import java.util.Locale

class XmlDimensionGenerator(
    private val config: GeneratorConfig
) : DimensionGenerator {

    override fun generate(): List<DimensionEntry> {

        val result = mutableListOf<DimensionEntry>()

        val values =
            io.github.hardikvaghani.flexiscale.generator.util
                .NumberSequenceGenerator
                .generate(
                    config.start,
                    config.end,
                    config.step
                )

        values.forEach { value ->

            if (config.generateDp) {

                result.add(
                    createEntry(
                        value,
                        DimensionUnit.DP,
                        false
                    )
                )

                if (config.includeNegative) {
                    result.add(
                        createEntry(
                            value,
                            DimensionUnit.DP,
                            true
                        )
                    )
                }
            }

            if (config.generateSp) {

                result.add(
                    createEntry(
                        value,
                        DimensionUnit.SP,
                        false
                    )
                )

                if (config.includeNegative) {
                    result.add(
                        createEntry(
                            value,
                            DimensionUnit.SP,
                            true
                        )
                    )
                }
            }
        }

        return result
    }

    private fun createEntry(
        value: Double,
        unit: DimensionUnit,
        negative: Boolean
    ): DimensionEntry {

        val suffix =
            if (negative) "_neg"
            else ""

        val name =
            "app_px_${format(value)}_to_${unit.name.lowercase()}$suffix"

        return DimensionEntry(
            name = name,
            value = if (negative) -value else value,
            unit = unit
        )
    }

    private fun format(
        value: Double
    ): String {

        return String.format(
            Locale.US,
            "%.2f",
            value
        ).replace(".", "_")
    }
}