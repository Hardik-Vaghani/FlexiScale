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

import io.github.hardikvaghani.flexiscale.generator.model.GeneratorConfig
import io.github.hardikvaghani.flexiscale.generator.writer.ResourceFolderWriter
import io.github.hardikvaghani.flexiscale.runtime.model.ScreenBucket

class FlexiScaleGenerator(
    private val config: GeneratorConfig,
    private val writer: ResourceFolderWriter
) {

    fun generateAll(): Int {

        var generatedCount = 0

        ScreenBucket.allBuckets.forEach { bucket ->

            val output =
                BucketDimensionGenerator(
                    generator =
                        XmlDimensionGenerator(config)
                ).generate(bucket)

            writer.writeSingle(output)

            generatedCount++

            if (bucket.minWidthDp % 100 == 0) {
                println(
                    "Generated dimensions for " +
                        "values-sw${bucket.minWidthDp}dp " +
                        "(${bucket.minWidthDp}/2560)"
                )
            }
        }

        return generatedCount
    }
}
