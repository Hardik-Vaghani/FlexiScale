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

import io.github.hardikvaghani.flexiscale.generator.generator.BucketDimensionGenerator
import io.github.hardikvaghani.flexiscale.generator.generator.DimensionGenerator
import io.github.hardikvaghani.flexiscale.generator.model.DimensionEntry
import io.github.hardikvaghani.flexiscale.generator.model.DimensionUnit
import io.github.hardikvaghani.flexiscale.runtime.model.ScreenBucket
import kotlin.test.Test
import kotlin.test.assertEquals

class BucketDimensionGeneratorTest {

    @Test
    fun generate_appliesRuntimeScaleProfileForBucket() {

        val output =
            BucketDimensionGenerator(
                generator =
                    DimensionGenerator {
                        listOf(
                            DimensionEntry(
                                name = "base_dp",
                                value = 16.0,
                                unit = DimensionUnit.DP
                            ),
                            DimensionEntry(
                                name = "base_sp",
                                value = 14.0,
                                unit = DimensionUnit.SP
                            )
                        )
                    }
            ).generate(ScreenBucket(411, ""))

        assertEquals(
            expected = 411,
            actual = output.bucket.minWidthDp
        )

        // dpScale for SW411 = 1.0 + (411-360)/(2560-360)*2.20 = 1.051
        // 16 * 1.051 = 16.816
        assertEquals(
            expected = 16.816,
            actual = output.entries[0].value,
            absoluteTolerance = 0.0001
        )

        // spScale for SW411 = 1.0 + (411-360)/(2560-360)*0.70 = 1.01623
        // 14 * 1.01623 = 14.2272
        assertEquals(
            expected = 14.2272,
            actual = output.entries[1].value,
            absoluteTolerance = 0.0001
        )
    }
}
