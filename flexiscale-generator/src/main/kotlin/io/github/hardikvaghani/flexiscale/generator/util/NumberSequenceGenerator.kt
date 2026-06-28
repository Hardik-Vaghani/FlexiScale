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

package io.github.hardikvaghani.flexiscale.generator.util

import java.math.BigDecimal

object NumberSequenceGenerator {

    fun generate(
        start: Double,
        end: Double,
        step: Double
    ): Sequence<Double> {

        require(start > 0.0) { "Start must be positive: $start" }
        require(end >= start) { "End ($end) must be >= start ($start)" }
        require(step > 0.0) { "Step must be positive: $step" }

        return sequence {
            var current =
                BigDecimal.valueOf(start)

            val endValue =
                BigDecimal.valueOf(end)

            val stepValue =
                BigDecimal.valueOf(step)

            while (current <= endValue) {
                yield(current.toDouble())
                current = current.add(stepValue)
            }
        }
    }
}
