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

import io.github.hardikvaghani.flexiscale.runtime.FlexiScale
import io.github.hardikvaghani.flexiscale.runtime.model.ScreenInfo
import kotlin.test.Test
import kotlin.test.assertEquals

class FlexiScaleTest {

    @Test
    fun dp_scalesDoubleFloatAndIntValues() {

        // dpScale for SW411 = 1.0 + (411-360)/(2560-360)*2.20 = 1.051
        // 16 * 1.051 = 16.816
        assertEquals(
            expected = 16.816,
            actual =
                FlexiScale.dp(
                    smallestWidthDp = 411,
                    value = 16.0
                ),
            absoluteTolerance = 0.0001
        )

        assertEquals(
            expected = 16.816,
            actual =
                FlexiScale.dp(
                    smallestWidthDp = 411,
                    value = 16f
                ),
            absoluteTolerance = 0.0001
        )

        assertEquals(
            expected = 16.816,
            actual =
                FlexiScale.dp(
                    smallestWidthDp = 411,
                    value = 16
                ),
            absoluteTolerance = 0.0001
        )
    }

    @Test
    fun sp_scalesDoubleFloatAndIntValues() {

        // spScale for SW411 = 1.0 + (411-360)/(2560-360)*0.70 = 1.01623
        // 14 * 1.01623 = 14.2272
        assertEquals(
            expected = 14.2272,
            actual =
                FlexiScale.sp(
                    smallestWidthDp = 411,
                    value = 14.0
                ),
            absoluteTolerance = 0.0001
        )

        assertEquals(
            expected = 14.2272,
            actual =
                FlexiScale.sp(
                    smallestWidthDp = 411,
                    value = 14f
                ),
            absoluteTolerance = 0.0001
        )

        assertEquals(
            expected = 14.2272,
            actual =
                FlexiScale.sp(
                    smallestWidthDp = 411,
                    value = 14
                ),
            absoluteTolerance = 0.0001
        )
    }

    @Test
    fun screenInfoOverloads_useSmallestWidthDp() {

        val screenInfo =
            ScreenInfo(
                smallestWidthDp = 600,
                widthDp = 1280,
                heightDp = 800
            )

        // dpScale for SW600 = 1.0 + (600-360)/(2560-360)*2.20 = 1.24
        // 16 * 1.24 = 19.84
        assertEquals(
            expected = 19.84,
            actual =
                FlexiScale.dp(
                    screenInfo = screenInfo,
                    value = 16.0
                ),
            absoluteTolerance = 0.0001
        )

        // spScale for SW600 = 1.0 + (600-360)/(2560-360)*0.70 = 1.07636
        // 14 * 1.07636 = 15.0691
        assertEquals(
            expected = 15.0691,
            actual =
                FlexiScale.sp(
                    screenInfo = screenInfo,
                    value = 14.0
                ),
            absoluteTolerance = 0.0001
        )
    }

    @Test
    fun scaling_preservesZeroAndNegativeValues() {

        assertEquals(
            expected = 0.0,
            actual =
                FlexiScale.dp(
                    smallestWidthDp = 411,
                    value = 0
                ),
            absoluteTolerance = 0.0001
        )

        // dpScale for SW411 = 1.051, -16 * 1.051 = -16.816
        assertEquals(
            expected = -16.816,
            actual =
                FlexiScale.dp(
                    smallestWidthDp = 411,
                    value = -16
                ),
            absoluteTolerance = 0.0001
        )
    }

    @Test
    fun runtimeDependency_shouldWork() {

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

        assertEquals(
            16.816,
            dp,
            0.0001
        )

        assertEquals(
            14.2272,
            sp,
            0.0001
        )
    }
}
