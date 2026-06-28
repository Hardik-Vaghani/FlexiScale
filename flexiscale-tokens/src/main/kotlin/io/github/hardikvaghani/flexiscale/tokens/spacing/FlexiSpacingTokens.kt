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

package io.github.hardikvaghani.flexiscale.tokens.spacing

import io.github.hardikvaghani.flexiscale.tokens.model.DimensionToken

object FlexiSpacingTokens {

    val None = DimensionToken(0.0)

    val Xxxs = DimensionToken(1.0)
    val Xxs = DimensionToken(2.0)
    val Xs = DimensionToken(4.0)

    val Sm = DimensionToken(8.0)
    val Md = DimensionToken(12.0)
    val Lg = DimensionToken(16.0)

    val Xl = DimensionToken(20.0)
    val Xxl = DimensionToken(24.0)
    val Xxxl = DimensionToken(32.0)

    val Huge = DimensionToken(40.0)
    val Massive = DimensionToken(48.0)
    val Giant = DimensionToken(56.0)
    val Colossal = DimensionToken(64.0)

    val ScreenPadding = DimensionToken(16.0)
    val CardPadding = DimensionToken(16.0)
    val DialogPadding = DimensionToken(24.0)

    val SectionSpacing = DimensionToken(32.0)
}