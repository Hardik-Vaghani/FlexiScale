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

package io.github.hardikvaghani.flexiscale.tokens.motion

import io.github.hardikvaghani.flexiscale.tokens.model.DurationToken

object FlexiDelayTokens {

    val None = DurationToken(0)

    val Tiny = DurationToken(25)

    val Small = DurationToken(50)

    val Medium = DurationToken(100)

    val Large = DurationToken(200)

    val ExtraLarge = DurationToken(300)
}