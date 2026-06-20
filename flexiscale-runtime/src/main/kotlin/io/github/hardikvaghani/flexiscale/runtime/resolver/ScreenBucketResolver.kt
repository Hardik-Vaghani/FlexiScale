package io.github.hardikvaghani.flexiscale.runtime.resolver

import io.github.hardikvaghani.flexiscale.runtime.model.*

object ScreenBucketResolver {

    fun resolve(
        smallestWidthDp: Int
    ): ScreenBucket {

        return when {

            smallestWidthDp >= 1440 -> ScreenBucket.SW1440
            smallestWidthDp >= 1280 -> ScreenBucket.SW1280
            smallestWidthDp >= 960 -> ScreenBucket.SW960
            smallestWidthDp >= 840 -> ScreenBucket.SW840
            smallestWidthDp >= 720 -> ScreenBucket.SW720
            smallestWidthDp >= 600 -> ScreenBucket.SW600
            smallestWidthDp >= 480 -> ScreenBucket.SW480
            smallestWidthDp >= 411 -> ScreenBucket.SW411
            smallestWidthDp >= 360 -> ScreenBucket.SW360
            smallestWidthDp >= 320 -> ScreenBucket.SW320
            smallestWidthDp >= 240 -> ScreenBucket.SW240

            else -> ScreenBucket.SW192
        }
    }
}