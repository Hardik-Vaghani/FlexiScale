package io.github.hardikvaghani.flexiscale.compose.extensions

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import io.github.hardikvaghani.flexiscale.compose.resolver.ComposeScreenInfoResolver
import io.github.hardikvaghani.flexiscale.runtime.FlexiScale

@Composable
fun Int.fsp(): TextUnit {

    return FlexiScale
        .sp(
            smallestWidthDp =
                ComposeScreenInfoResolver.smallestWidthDp(),
            value = this
        )
        .sp
}

@Composable
fun Float.fsp(): TextUnit {

    return FlexiScale
        .sp(
            smallestWidthDp =
                ComposeScreenInfoResolver.smallestWidthDp(),
            value = this
        )
        .sp
}

@Composable
fun Double.fsp(): TextUnit {

    return FlexiScale
        .sp(
            smallestWidthDp =
                ComposeScreenInfoResolver.smallestWidthDp(),
            value = this
        )
        .sp
}