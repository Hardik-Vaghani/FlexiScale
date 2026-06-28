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

import io.github.hardikvaghani.flexiscale.runtime.model.ScaleProfile
import io.github.hardikvaghani.flexiscale.runtime.model.ScreenBucket
import io.github.hardikvaghani.flexiscale.runtime.scaler.ResponsiveScaler
import io.github.hardikvaghani.flexiscale.runtime.strategy.ScaleStrategy
import kotlin.test.Test
import kotlin.test.assertEquals

class ResponsiveScalerTest {

    @Test
    fun dp_usesDpScaleFromStrategy() {

        val scaler =
            ResponsiveScaler(
                strategy =
                    fixedStrategy(
                        dpScale = 2.5,
                        spScale = 9.0
                    )
            )

        assertEquals(
            expected = 40.0,
            actual = scaler.dp(
                bucket = ScreenBucket(360, ""),
                value = 16.0
            ),
            absoluteTolerance = 0.0001
        )
    }

    @Test
    fun sp_usesSpScaleFromStrategy() {

        val scaler =
            ResponsiveScaler(
                strategy =
                    fixedStrategy(
                        dpScale = 9.0,
                        spScale = 1.25
                    )
            )

        assertEquals(
            expected = 20.0,
            actual = scaler.sp(
                bucket = ScreenBucket(411, ""),
                value = 16.0
            ),
            absoluteTolerance = 0.0001
        )
    }

    @Test
    fun scaler_preservesZeroAndNegativeValues() {

        val scaler =
            ResponsiveScaler(
                strategy =
                    fixedStrategy(
                        dpScale = 1.5,
                        spScale = 2.0
                    )
            )

        assertEquals(
            expected = 0.0,
            actual = scaler.dp(
                bucket = ScreenBucket(360, ""),
                value = 0.0
            ),
            absoluteTolerance = 0.0001
        )

        assertEquals(
            expected = -24.0,
            actual = scaler.sp(
                bucket = ScreenBucket(360, ""),
                value = -12.0
            ),
            absoluteTolerance = 0.0001
        )
    }

    private fun fixedStrategy(
        dpScale: Double,
        spScale: Double
    ): ScaleStrategy =
        object : ScaleStrategy {
            override fun profile(
                bucket: ScreenBucket
            ): ScaleProfile =
                ScaleProfile(
                    dpScale = dpScale,
                    spScale = spScale
                )
        }
}
