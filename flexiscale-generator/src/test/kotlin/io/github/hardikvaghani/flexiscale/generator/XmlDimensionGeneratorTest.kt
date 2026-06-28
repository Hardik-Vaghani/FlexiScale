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

import io.github.hardikvaghani.flexiscale.generator.generator.XmlDimensionGenerator
import io.github.hardikvaghani.flexiscale.generator.model.DimensionUnit
import io.github.hardikvaghani.flexiscale.generator.model.GeneratorConfig
import kotlin.test.Test
import kotlin.test.assertEquals

class XmlDimensionGeneratorTest {

    @Test
    fun generate_createsDpAndSpEntriesWithNegativeVariants() {

        val entries =
            XmlDimensionGenerator(
                config =
                    GeneratorConfig(
                        start = 1.0,
                        end = 1.0,
                        step = 1.0,
                        generateDp = true,
                        generateSp = true,
                        includeNegative = true
                    )
            ).generate()

        assertEquals(
            expected = listOf(
                "app_px_1_00_to_dp",
                "app_px_1_00_to_dp_neg",
                "app_px_1_00_to_sp",
                "app_px_1_00_to_sp_neg"
            ),
            actual = entries.map { it.name }
        )

        assertEquals(
            expected = listOf(1.0, -1.0, 1.0, -1.0),
            actual = entries.map { it.value }
        )

        assertEquals(
            expected = listOf(
                DimensionUnit.DP,
                DimensionUnit.DP,
                DimensionUnit.SP,
                DimensionUnit.SP
            ),
            actual = entries.map { it.unit }
        )
    }

    @Test
    fun generate_respectsUnitFlagsAndNegativeFlag() {

        val entries =
            XmlDimensionGenerator(
                config =
                    GeneratorConfig(
                        start = 1.0,
                        end = 2.0,
                        step = 1.0,
                        generateDp = false,
                        generateSp = true,
                        includeNegative = false
                    )
            ).generate()

        assertEquals(
            expected = listOf(
                "app_px_1_00_to_sp",
                "app_px_2_00_to_sp"
            ),
            actual = entries.map { it.name }
        )

        assertEquals(
            expected = listOf(DimensionUnit.SP, DimensionUnit.SP),
            actual = entries.map { it.unit }
        )
    }
}
