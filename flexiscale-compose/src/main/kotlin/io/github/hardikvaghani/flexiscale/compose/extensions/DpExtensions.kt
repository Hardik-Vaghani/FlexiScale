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

package io.github.hardikvaghani.flexiscale.compose.extensions

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.github.hardikvaghani.flexiscale.compose.resolver.ComposeScreenInfoResolver
import io.github.hardikvaghani.flexiscale.runtime.FlexiScale

@Composable
fun Int.fdp(): Dp {

    return FlexiScale
        .dp(
            smallestWidthDp =
                ComposeScreenInfoResolver.smallestWidthDp(),
            value = this
        )
        .dp
}

@Composable
fun Float.fdp(): Dp {

    return FlexiScale
        .dp(
            smallestWidthDp =
                ComposeScreenInfoResolver.smallestWidthDp(),
            value = this
        )
        .dp
}

@Composable
fun Double.fdp(): Dp {

    return FlexiScale
        .dp(
            smallestWidthDp =
                ComposeScreenInfoResolver.smallestWidthDp(),
            value = this
        )
        .dp
}