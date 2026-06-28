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

package io.github.hardikvaghani.flexiscale.generator.generator

import io.github.hardikvaghani.flexiscale.generator.model.*
import io.github.hardikvaghani.flexiscale.runtime.model.ScreenBucket
import io.github.hardikvaghani.flexiscale.runtime.strategy.DefaultScaleStrategy
import java.util.Locale

class BucketDimensionGenerator(
    private val generator: DimensionGenerator
) {

    fun generate(
        bucket: ScreenBucket
    ): BucketOutput {

        val profile =
            DefaultScaleStrategy.profile(bucket)

        val entries =
            generator.generate().map {

                val scale =
                    when (it.unit) {

                        DimensionUnit.SP ->
                            profile.spScale

                        DimensionUnit.DP ->
                            profile.dpScale
                    }

                it.copy(
                    value = String.format(
                        Locale.US,
                        "%.5f",
                        it.value * scale
                    ).toDouble()
                )
            }

        return BucketOutput(
            bucket = bucket,
            entries = entries
        )
    }
}