package io.github.hardikvaghani.flexiscale.generator.generator

import io.github.hardikvaghani.flexiscale.generator.model.*

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

        return value
            .toString()
            .replace(".", "_")
    }
}