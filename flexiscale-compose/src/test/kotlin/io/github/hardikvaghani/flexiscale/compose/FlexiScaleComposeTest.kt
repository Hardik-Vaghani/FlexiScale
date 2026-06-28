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

package io.github.hardikvaghani.flexiscale.compose

import io.github.hardikvaghani.flexiscale.runtime.FlexiScale
import kotlin.test.Test
import kotlin.test.assertEquals

class FlexiScaleComposeTest {

    @Test
    fun composeModule_canUseRuntimeScalingContract() {

        val dp =
            FlexiScale.dp(
                smallestWidthDp = 411,
                value = 16
            )

        val sp =
            FlexiScale.sp(
                smallestWidthDp = 411,
                value = 14
            )

        // dpScale for SW411 = 1.0 + (411-360)/(2560-360)*2.20 = 1.051
        // 16 * 1.051 = 16.816
        assertEquals(
            expected = 16.816,
            actual = dp,
            absoluteTolerance = 0.0001
        )

        // spScale for SW411 = 1.0 + (411-360)/(2560-360)*0.70 = 1.01623
        // 14 * 1.01623 = 14.2272
        assertEquals(
            expected = 14.2272,
            actual = sp,
            absoluteTolerance = 0.0001
        )
    }

    @Test
    fun composeModule_preservesRuntimeEdgeBehavior() {

        assertEquals(
            expected = 0.0,
            actual =
                FlexiScale.dp(
                    smallestWidthDp = 360,
                    value = 0
                ),
            absoluteTolerance = 0.0001
        )

        // spScale for SW600 = 1.0 + (600-360)/(2560-360)*0.70 = 1.07636
        // -10 * 1.07636 = -10.7636
        assertEquals(
            expected = -10.7636,
            actual =
                FlexiScale.sp(
                    smallestWidthDp = 600,
                    value = -10
                ),
            absoluteTolerance = 0.0001
        )
    }
}
