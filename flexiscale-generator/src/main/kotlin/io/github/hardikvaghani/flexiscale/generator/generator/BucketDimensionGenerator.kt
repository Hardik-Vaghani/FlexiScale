package io.github.hardikvaghani.flexiscale.generator.generator

import io.github.hardikvaghani.flexiscale.generator.model.*
import io.github.hardikvaghani.flexiscale.runtime.model.ScreenBucket
import io.github.hardikvaghani.flexiscale.runtime.strategy.DefaultScaleStrategy
import java.util.Locale

class BucketDimensionGenerator(
    private val generator: DimensionGenerator
) {

    fun generate(
        bucket: ScreenBucket
    ): BucketOutput {

        val profile =
            DefaultScaleStrategy.profile(bucket)

        val entries =
            generator.generate().map {

                val scale =
                    when (it.unit) {

                        DimensionUnit.SP ->
                            profile.spScale

                        DimensionUnit.DP ->
                            profile.dpScale
                    }

                it.copy(
                    value = String.format(
                        Locale.US,
                        "%.5f",
                        it.value * scale
                    ).toDouble()
                )
            }

        return BucketOutput(
            bucket = bucket,
            entries = entries
        )
    }
}