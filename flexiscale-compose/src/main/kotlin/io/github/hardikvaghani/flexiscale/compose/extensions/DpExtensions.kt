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