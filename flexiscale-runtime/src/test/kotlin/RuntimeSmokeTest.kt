import io.github.hardikvaghani.flexiscale.runtime.FlexiScale
import kotlin.test.Test

class RuntimeSmokeTest {

    @Test
    fun printValues() {

        println(
            FlexiScale.dp(
                smallestWidthDp = 411,
                value = 16
            )
        )

        println(
            FlexiScale.sp(
                smallestWidthDp = 411,
                value = 14
            )
        )
    }
}