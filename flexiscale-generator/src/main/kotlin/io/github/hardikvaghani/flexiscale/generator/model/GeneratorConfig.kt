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

package io.github.hardikvaghani.flexiscale.generator.model

data class GeneratorConfig(
    val start: Double = 0.1,//0.5,
    val end: Double = 500.0,
    val step: Double = 0.1,//0.5,
    val generateDp: Boolean = true,
    val generateSp: Boolean = true,
    val includeNegative: Boolean = true,
    val decimalPlaces: Int = 5
) {

    companion object {
        val DEFAULT = GeneratorConfig()
    }
}