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

package io.github.hardikvaghani.flexiscale.runtime

import io.github.hardikvaghani.flexiscale.runtime.model.ScreenInfo
import io.github.hardikvaghani.flexiscale.runtime.resolver.ScreenBucketResolver
import io.github.hardikvaghani.flexiscale.runtime.scaler.ResponsiveScaler

object FlexiScale {

    private val scaler =
        ResponsiveScaler()

    // --------------------------------------------------
    // Double
    // --------------------------------------------------

    fun dp(
        smallestWidthDp: Int,
        value: Double
    ): Double {

        return scaler.dp(
            bucket =
                ScreenBucketResolver.resolve(
                    smallestWidthDp
                ),
            value = value
        )
    }

    fun sp(
        smallestWidthDp: Int,
        value: Double
    ): Double {

        return scaler.sp(
            bucket =
                ScreenBucketResolver.resolve(
                    smallestWidthDp
                ),
            value = value
        )
    }

    // --------------------------------------------------
    // Float
    // --------------------------------------------------

    fun dp(
        smallestWidthDp: Int,
        value: Float
    ): Double =
        dp(
            smallestWidthDp,
            value.toDouble()
        )

    fun sp(
        smallestWidthDp: Int,
        value: Float
    ): Double =
        sp(
            smallestWidthDp,
            value.toDouble()
        )

    // --------------------------------------------------
    // Int
    // --------------------------------------------------

    fun dp(
        smallestWidthDp: Int,
        value: Int
    ): Double =
        dp(
            smallestWidthDp,
            value.toDouble()
        )

    fun sp(
        smallestWidthDp: Int,
        value: Int
    ): Double =
        sp(
            smallestWidthDp,
            value.toDouble()
        )

    // --------------------------------------------------
    // ScreenInfo
    // --------------------------------------------------

    fun dp(
        screenInfo: ScreenInfo,
        value: Double
    ): Double =
        dp(
            smallestWidthDp =
                screenInfo.smallestWidthDp,
            value = value
        )

    fun sp(
        screenInfo: ScreenInfo,
        value: Double
    ): Double =
        sp(
            smallestWidthDp =
                screenInfo.smallestWidthDp,
            value = value
        )
}