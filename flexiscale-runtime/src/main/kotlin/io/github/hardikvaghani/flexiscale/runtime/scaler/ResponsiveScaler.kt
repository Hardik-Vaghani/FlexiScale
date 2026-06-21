package io.github.hardikvaghani.flexiscale.runtime.scaler

import io.github.hardikvaghani.flexiscale.runtime.model.ScreenBucket
import io.github.hardikvaghani.flexiscale.runtime.strategy.DefaultScaleStrategy
import io.github.hardikvaghani.flexiscale.runtime.strategy.ScaleStrategy

class ResponsiveScaler(
    private val strategy: ScaleStrategy =
        DefaultScaleStrategy
) {

    fun dp(
        bucket: ScreenBucket,
        value: Double
    ): Double {

        return value *
                strategy
                    .profile(bucket)
                    .dpScale
    }

    fun sp(
        bucket: ScreenBucket,
        value: Double
    ): Double {

        return value *
                strategy
                    .profile(bucket)
                    .spScale
    }
}