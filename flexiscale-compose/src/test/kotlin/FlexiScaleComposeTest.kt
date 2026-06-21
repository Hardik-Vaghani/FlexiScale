package io.github.hardikvaghani.flexiscale.compose

import io.github.hardikvaghani.flexiscale.runtime.FlexiScale
import kotlin.test.Test
import kotlin.test.assertEquals

class FlexiScaleComposeTest {

    @Test
    fun runtimeDependency_shouldWork() {

        val dp =
            FlexiScale.dp(
                smallestWidthDp = 411,
                value = 16
            )

        val sp =
            FlexiScale.sp(
                smallestWidthDp = 411,
                value = 14
            )

        assertEquals(
            expected = 17.6,
            actual = dp
        )

        assertEquals(
            expected = 14.7,
            actual = sp
        )
    }
}