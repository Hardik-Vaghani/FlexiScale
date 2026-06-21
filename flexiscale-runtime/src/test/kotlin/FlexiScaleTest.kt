import io.github.hardikvaghani.flexiscale.runtime.FlexiScale
import kotlin.test.Test
import kotlin.test.assertEquals

class FlexiScaleTest {

    @Test
    fun testDpScaling() {

        val result =
            FlexiScale.dp(
                smallestWidthDp = 411,
                value = 16
            )

        assertEquals(17.6, result)
    }

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
            17.6,
            dp,
            0.0001
        )

        assertEquals(
            14.7,
            sp,
            0.0001
        )
    }
}