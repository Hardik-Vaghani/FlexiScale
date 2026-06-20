package io.github.hardikvaghani.flexiscale.runtime.strategy

import io.github.hardikvaghani.flexiscale.runtime.model.ScaleProfile
import io.github.hardikvaghani.flexiscale.runtime.model.ScreenBucket

object DefaultScaleStrategy : ScaleStrategy {

    private val profiles = mapOf(

        ScreenBucket.SW192 to ScaleProfile(0.55, 0.82),
        ScreenBucket.SW240 to ScaleProfile(0.70, 0.88),

        ScreenBucket.SW280 to ScaleProfile(0.80, 0.92),
        ScreenBucket.SW300 to ScaleProfile(0.85, 0.94),
        ScreenBucket.SW320 to ScaleProfile(0.90, 0.95),
        ScreenBucket.SW330 to ScaleProfile(0.95, 0.97),

        ScreenBucket.SW360 to ScaleProfile(1.00, 1.00),
        ScreenBucket.SW390 to ScaleProfile(1.05, 1.03),
        ScreenBucket.SW411 to ScaleProfile(1.10, 1.05),
        ScreenBucket.SW420 to ScaleProfile(1.12, 1.06),
        ScreenBucket.SW450 to ScaleProfile(1.16, 1.07),
        ScreenBucket.SW480 to ScaleProfile(1.20, 1.08),

        ScreenBucket.SW510 to ScaleProfile(1.24, 1.09),
        ScreenBucket.SW540 to ScaleProfile(1.28, 1.10),
        ScreenBucket.SW570 to ScaleProfile(1.32, 1.11),
        ScreenBucket.SW600 to ScaleProfile(1.35, 1.12),

        ScreenBucket.SW640 to ScaleProfile(1.40, 1.14),
        ScreenBucket.SW680 to ScaleProfile(1.45, 1.16),
        ScreenBucket.SW720 to ScaleProfile(1.50, 1.18),
        ScreenBucket.SW760 to ScaleProfile(1.55, 1.20),
        ScreenBucket.SW800 to ScaleProfile(1.60, 1.22),
        ScreenBucket.SW840 to ScaleProfile(1.65, 1.24),

        ScreenBucket.SW900 to ScaleProfile(1.72, 1.27),
        ScreenBucket.SW960 to ScaleProfile(1.80, 1.30),
        ScreenBucket.SW1024 to ScaleProfile(1.86, 1.32),
        ScreenBucket.SW1080 to ScaleProfile(1.92, 1.34),

        ScreenBucket.SW1200 to ScaleProfile(1.98, 1.36),
        ScreenBucket.SW1280 to ScaleProfile(2.00, 1.38),
        ScreenBucket.SW1366 to ScaleProfile(2.10, 1.42),
        ScreenBucket.SW1440 to ScaleProfile(2.20, 1.45),

        ScreenBucket.SW1600 to ScaleProfile(2.40, 1.50),
        ScreenBucket.SW1920 to ScaleProfile(2.80, 1.60),
        ScreenBucket.SW2560 to ScaleProfile(3.20, 1.70),
    )

    override fun profile(
        bucket: ScreenBucket
    ): ScaleProfile {

        return profiles[bucket]
            ?: error(
                "No ScaleProfile found for bucket: $bucket"
            )
    }
}