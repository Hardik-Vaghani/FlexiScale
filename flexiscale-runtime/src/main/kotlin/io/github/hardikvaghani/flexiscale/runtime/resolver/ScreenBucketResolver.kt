package io.github.hardikvaghani.flexiscale.runtime.resolver

import io.github.hardikvaghani.flexiscale.runtime.model.ScreenBucket

object ScreenBucketResolver {

    fun resolve(
        smallestWidthDp: Int
    ): ScreenBucket {

        return ScreenBucket
            .valuesSorted
            .lastOrNull {
                smallestWidthDp >= it.minWidthDp
            }
            ?: ScreenBucket.SW192
    }
}