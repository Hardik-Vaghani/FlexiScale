package io.github.hardikvaghani.flexiscale.compose.resolver

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration

object ComposeScreenInfoResolver {

    @Composable
    fun smallestWidthDp(): Int {
        return LocalConfiguration
            .current
            .smallestScreenWidthDp
    }
}