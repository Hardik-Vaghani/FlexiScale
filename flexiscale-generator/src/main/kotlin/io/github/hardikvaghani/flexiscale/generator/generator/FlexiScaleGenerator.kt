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

        val outputs =
            ScreenBucket.entries.map { bucket ->

                BucketDimensionGenerator(
                    generator =
                        XmlDimensionGenerator(config)
                ).generate(bucket)
            }

        writer.write(outputs)

        return outputs
    }
}