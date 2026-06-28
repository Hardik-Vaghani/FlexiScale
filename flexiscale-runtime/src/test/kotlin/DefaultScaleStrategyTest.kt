import io.github.hardikvaghani.flexiscale.runtime.model.ScreenBucket
import io.github.hardikvaghani.flexiscale.runtime.strategy.DefaultScaleStrategy
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class DefaultScaleStrategyTest {

    @Test
    fun allBucketsHavePositiveProfile() {

        ScreenBucket.allBuckets.forEach {
            val profile =
                DefaultScaleStrategy.profile(it)

            assertTrue(
                actual = profile.dpScale > 0.0,
                message = "dpScale must be positive for ${it.minWidthDp}"
            )

            assertTrue(
                actual = profile.spScale > 0.0,
                message = "spScale must be positive for ${it.minWidthDp}"
            )
        }
    }

    @Test
    fun profiles_matchAnchorBuckets() {

        // SW192 — minimum
        assertEquals(
            expected = 0.5333,
            actual = DefaultScaleStrategy.profile(ScreenBucket(192, "")).dpScale,
            absoluteTolerance = 0.0001
        )

        assertEquals(
            expected = 0.82,
            actual = DefaultScaleStrategy.profile(ScreenBucket(192, "")).spScale,
            absoluteTolerance = 0.0001
        )

        // SW360 — anchor (1.0)
        assertEquals(
            expected = 1.0,
            actual = DefaultScaleStrategy.profile(ScreenBucket(360, "")).dpScale,
            absoluteTolerance = 0.0001
        )

        assertEquals(
            expected = 1.0,
            actual = DefaultScaleStrategy.profile(ScreenBucket(360, "")).spScale,
            absoluteTolerance = 0.0001
        )

        // SW411 — standard phone (sw > 360, uses second formula)
        assertEquals(
            expected = 1.051,
            actual = DefaultScaleStrategy.profile(ScreenBucket(411, "")).dpScale,
            absoluteTolerance = 0.0001
        )

        // SW600 — tablet (sw > 360, uses second formula)
        assertEquals(
            expected = 1.24,
            actual = DefaultScaleStrategy.profile(ScreenBucket(600, "")).dpScale,
            absoluteTolerance = 0.0001
        )

        // SW2560 — maximum
        assertEquals(
            expected = 3.20,
            actual = DefaultScaleStrategy.profile(ScreenBucket(2560, "")).dpScale,
            absoluteTolerance = 0.0001
        )

        assertEquals(
            expected = 1.70,
            actual = DefaultScaleStrategy.profile(ScreenBucket(2560, "")).spScale,
            absoluteTolerance = 0.0001
        )
    }

    @Test
    fun profiles_areMonotonicAcrossBuckets() {

        val profiles =
            ScreenBucket.allBuckets.map {
                DefaultScaleStrategy.profile(it)
            }

        profiles
            .zipWithNext()
            .forEach { (previous, next) ->
                assertTrue(
                    actual = next.dpScale >= previous.dpScale,
                    message = "dpScale decreased between buckets"
                )
                assertTrue(
                    actual = next.spScale >= previous.spScale,
                    message = "spScale decreased between buckets"
                )
            }
    }
}
