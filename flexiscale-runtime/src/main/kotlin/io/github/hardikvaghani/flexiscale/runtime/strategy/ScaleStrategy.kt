package io.github.hardikvaghani.flexiscale.runtime.strategy

import io.github.hardikvaghani.flexiscale.runtime.model.ScaleProfile
import io.github.hardikvaghani.flexiscale.runtime.model.ScreenBucket

interface ScaleStrategy {

    fun profile(
        bucket: ScreenBucket
    ): ScaleProfile
}