package io.github.hardikvaghani.flexiscale.compose

import io.github.hardikvaghani.flexiscale.runtime.FlexiScale
import kotlin.test.Test
import kotlin.test.assertEquals

class FlexiScaleComposeTest {

    @Test
    fun composeModule_canUseRuntimeScalingContract() {

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

        // dpScale for SW411 = 1.0 + (411-360)/(2560-360)*2.20 = 1.051
        // 16 * 1.051 = 16.816
        assertEquals(
            expected = 16.816,
            actual = dp,
            absoluteTolerance = 0.0001
        )

        // spScale for SW411 = 1.0 + (411-360)/(2560-360)*0.70 = 1.01623
        // 14 * 1.01623 = 14.2272
        assertEquals(
            expected = 14.2272,
            actual = sp,
            absoluteTolerance = 0.0001
        )
    }

    @Test
    fun composeModule_preservesRuntimeEdgeBehavior() {

        assertEquals(
            expected = 0.0,
            actual =
                FlexiScale.dp(
                    smallestWidthDp = 360,
                    value = 0
                ),
            absoluteTolerance = 0.0001
        )

        // spScale for SW600 = 1.0 + (600-360)/(2560-360)*0.70 = 1.07636
        // -10 * 1.07636 = -10.7636
        assertEquals(
            expected = -10.7636,
            actual =
                FlexiScale.sp(
                    smallestWidthDp = 600,
                    value = -10
                ),
            absoluteTolerance = 0.0001
        )
    }
}
