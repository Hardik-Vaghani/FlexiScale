/*
 * Copyright 2025 Hardik Vaghani
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
