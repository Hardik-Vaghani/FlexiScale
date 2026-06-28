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

package io.github.hardikvaghani.flexiscale.runtime.scaler

import io.github.hardikvaghani.flexiscale.runtime.model.ScreenBucket
import io.github.hardikvaghani.flexiscale.runtime.strategy.DefaultScaleStrategy
import io.github.hardikvaghani.flexiscale.runtime.strategy.ScaleStrategy

class ResponsiveScaler(
    private val strategy: ScaleStrategy =
        DefaultScaleStrategy
) {

    fun dp(
        bucket: ScreenBucket,
        value: Double
    ): Double {

        return value *
                strategy
                    .profile(bucket)
                    .dpScale
    }

    fun sp(
        bucket: ScreenBucket,
        value: Double
    ): Double {

        return value *
                strategy
                    .profile(bucket)
                    .spScale
    }
}