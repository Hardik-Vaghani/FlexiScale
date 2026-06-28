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

import io.github.hardikvaghani.flexiscale.generator.util.NumberSequenceGenerator
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class NumberSequenceGeneratorTest {

    @Test
    fun generate_includesStartIntermediateAndEndValues() {

        assertEquals(
            expected = listOf(0.1, 0.2, 0.3, 0.4, 0.5),
            actual =
                NumberSequenceGenerator
                    .generate(
                        start = 0.1,
                        end = 0.5,
                        step = 0.1
                    )
                    .toList()
        )
    }

    @Test
    fun generate_includesConfiguredEndForDefaultRange() {

        val values =
            NumberSequenceGenerator
                .generate(
                    start = 0.1,
                    end = 500.0,
                    step = 0.1
                )
                .toList()

        assertEquals(
            expected = 500.0,
            actual = values.last(),
            absoluteTolerance = 0.0001
        )

        assertEquals(
            expected = 5000,
            actual = values.size
        )
    }

    @Test
    fun generate_returnsSingleValueWhenStartEqualsEnd() {

        assertEquals(
            expected = listOf(2.0),
            actual =
                NumberSequenceGenerator
                    .generate(
                        start = 2.0,
                        end = 2.0,
                        step = 0.5
                    )
                    .toList()
        )
    }

    @Test
    fun generate_rejectsInvalidRanges() {

        assertFailsWith<IllegalArgumentException> {
            NumberSequenceGenerator.generate(0.0, 1.0, 0.1).toList()
        }

        assertFailsWith<IllegalArgumentException> {
            NumberSequenceGenerator.generate(2.0, 1.0, 0.1).toList()
        }

        assertFailsWith<IllegalArgumentException> {
            NumberSequenceGenerator.generate(1.0, 2.0, 0.0).toList()
        }
    }
}
