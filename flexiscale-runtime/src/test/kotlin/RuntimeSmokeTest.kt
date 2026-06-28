import io.github.hardikvaghani.flexiscale.runtime.FlexiScale
import kotlin.test.Test
import kotlin.test.assertTrue

class RuntimeSmokeTest {

    @Test
    fun runtimeProducesFiniteValues() {

        assertTrue(
            FlexiScale.dp(
                smallestWidthDp = 411,
                value = 16
            ).isFinite()
        )

        assertTrue(
            FlexiScale.sp(
                smallestWidthDp = 411,
                value = 14
            ).isFinite()
        )
    }
}
