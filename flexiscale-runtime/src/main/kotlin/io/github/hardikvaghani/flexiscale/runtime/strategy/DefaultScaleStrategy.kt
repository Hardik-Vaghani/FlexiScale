package io.github.hardikvaghani.flexiscale.runtime.strategy

import io.github.hardikvaghani.flexiscale.runtime.model.*

object DefaultScaleStrategy : ScaleStrategy {

    override fun profile(
        bucket: ScreenBucket
    ): ScaleProfile {

        return when (bucket) {

            ScreenBucket.SW192 ->
                ScaleProfile(
                    0.55,
                    0.82
                )

            ScreenBucket.SW240 ->
                ScaleProfile(
                    0.70,
                    0.88
                )

            ScreenBucket.SW320 ->
                ScaleProfile(
                    0.90,
                    0.95
                )

            ScreenBucket.SW360 ->
                ScaleProfile(
                    1.00,
                    1.00
                )

            ScreenBucket.SW411 ->
                ScaleProfile(
                    1.10,
                    1.05
                )

            ScreenBucket.SW480 ->
                ScaleProfile(
                    1.20,
                    1.08
                )

            ScreenBucket.SW600 ->
                ScaleProfile(
                    1.35,
                    1.12
                )

            ScreenBucket.SW720 ->
                ScaleProfile(
                    1.50,
                    1.18
                )

            ScreenBucket.SW840 ->
                ScaleProfile(
                    1.65,
                    1.24
                )

            ScreenBucket.SW960 ->
                ScaleProfile(
                    1.80,
                    1.30
                )

            ScreenBucket.SW1280 ->
                ScaleProfile(
                    2.00,
                    1.38
                )

            ScreenBucket.SW1440 ->
                ScaleProfile(
                    2.20,
                    1.45
                )
        }
    }
}