import io.github.hardikvaghani.flexiscale.runtime.model.ScaleProfile
import io.github.hardikvaghani.flexiscale.runtime.model.ScreenBucket
import io.github.hardikvaghani.flexiscale.runtime.scaler.ResponsiveScaler
import io.github.hardikvaghani.flexiscale.runtime.strategy.ScaleStrategy
import kotlin.test.Test
import kotlin.test.assertEquals

class ResponsiveScalerTest {

    @Test
    fun dp_usesDpScaleFromStrategy() {

        val scaler =
            ResponsiveScaler(
                strategy =
                    fixedStrategy(
                        dpScale = 2.5,
                        spScale = 9.0
                    )
            )

        assertEquals(
            expected = 40.0,
            actual = scaler.dp(
                bucket = ScreenBucket(360, ""),
                value = 16.0
            ),
            absoluteTolerance = 0.0001
        )
    }

    @Test
    fun sp_usesSpScaleFromStrategy() {

        val scaler =
            ResponsiveScaler(
                strategy =
                    fixedStrategy(
                        dpScale = 9.0,
                        spScale = 1.25
                    )
            )

        assertEquals(
            expected = 20.0,
            actual = scaler.sp(
                bucket = ScreenBucket(411, ""),
                value = 16.0
            ),
            absoluteTolerance = 0.0001
        )
    }

    @Test
    fun scaler_preservesZeroAndNegativeValues() {

        val scaler =
            ResponsiveScaler(
                strategy =
                    fixedStrategy(
                        dpScale = 1.5,
                        spScale = 2.0
                    )
            )

        assertEquals(
            expected = 0.0,
            actual = scaler.dp(
                bucket = ScreenBucket(360, ""),
                value = 0.0
            ),
            absoluteTolerance = 0.0001
        )

        assertEquals(
            expected = -24.0,
            actual = scaler.sp(
                bucket = ScreenBucket(360, ""),
                value = -12.0
            ),
            absoluteTolerance = 0.0001
        )
    }

    private fun fixedStrategy(
        dpScale: Double,
        spScale: Double
    ): ScaleStrategy =
        object : ScaleStrategy {
            override fun profile(
                bucket: ScreenBucket
            ): ScaleProfile =
                ScaleProfile(
                    dpScale = dpScale,
                    spScale = spScale
                )
        }
}
