import io.github.hardikvaghani.flexiscale.runtime.model.ScreenBucket
import io.github.hardikvaghani.flexiscale.runtime.resolver.ScreenBucketResolver
import kotlin.test.Test
import kotlin.test.assertEquals

class ScreenBucketResolverTest {

    @Test
    fun resolve_returnsExactBucketAtKnownBoundaries() {

        assertEquals(
            expected = 192,
            actual = ScreenBucketResolver.resolve(192).minWidthDp
        )

        assertEquals(
            expected = 411,
            actual = ScreenBucketResolver.resolve(411).minWidthDp
        )

        assertEquals(
            expected = 2560,
            actual = ScreenBucketResolver.resolve(2560).minWidthDp
        )
    }

    @Test
    fun resolve_returnsExactMatchForEveryBucket() {

        // With all 2369 buckets available, every value maps to itself
        for (sw in 192..2560 step 100) {
            assertEquals(
                expected = sw,
                actual = ScreenBucketResolver.resolve(sw).minWidthDp,
                message = "Failed for smallestWidthDp=$sw"
            )
        }
    }

    @Test
    fun resolve_clampsBelowMinimumToSmallestBucket() {

        assertEquals(
            expected = 192,
            actual = ScreenBucketResolver.resolve(0).minWidthDp
        )

        assertEquals(
            expected = 192,
            actual = ScreenBucketResolver.resolve(-1).minWidthDp
        )
    }

    @Test
    fun resolve_clampsAboveMaximumToLargestBucket() {

        assertEquals(
            expected = 2560,
            actual = ScreenBucketResolver.resolve(4000).minWidthDp
        )
    }
}
