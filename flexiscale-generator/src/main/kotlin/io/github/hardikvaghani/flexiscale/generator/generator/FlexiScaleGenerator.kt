package io.github.hardikvaghani.flexiscale.generator.generator

import io.github.hardikvaghani.flexiscale.generator.model.BucketOutput
import io.github.hardikvaghani.flexiscale.generator.model.GeneratorConfig
import io.github.hardikvaghani.flexiscale.generator.writer.ResourceFolderWriter
import io.github.hardikvaghani.flexiscale.runtime.model.ScreenBucket

class FlexiScaleGenerator(
    private val config: GeneratorConfig,
    private val writer: ResourceFolderWriter
) {

    fun generateAll(): List<BucketOutput> {

        val outputs = mutableListOf<BucketOutput>()

        ScreenBucket.allBuckets.forEach { bucket ->

            val output =
                BucketDimensionGenerator(
                    generator =
                        XmlDimensionGenerator(config)
                ).generate(bucket)

            writer.writeSingle(output)

            outputs.add(output)

            if (bucket.minWidthDp % 100 == 0) {
                println(
                    "Generated dimensions for " +
                        "values-sw${bucket.minWidthDp}dp " +
                        "(${bucket.minWidthDp}/2560)"
                )
            }
        }

        return outputs
    }
}
