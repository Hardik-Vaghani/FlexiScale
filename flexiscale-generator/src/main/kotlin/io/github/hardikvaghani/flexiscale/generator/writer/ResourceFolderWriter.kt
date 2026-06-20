package io.github.hardikvaghani.flexiscale.generator.writer

import io.github.hardikvaghani.flexiscale.generator.model.BucketOutput
import java.nio.file.Path

class ResourceFolderWriter(
    private val outputDirectory: Path
) {

    private val xmlWriter =
        XmlWriter()

    fun write(
        outputs: List<BucketOutput>
    ) {

        outputs.forEach {

            val folder =
                outputDirectory
                    .resolve(it.folderName)
                    .toFile()

            if (!folder.exists()) {
                folder.mkdirs()
            }

            val xmlFile =
                folder.resolve(
                    it.fileName
                )

            xmlFile.writeText(
                xmlWriter.write(it)
            )
        }
    }
}