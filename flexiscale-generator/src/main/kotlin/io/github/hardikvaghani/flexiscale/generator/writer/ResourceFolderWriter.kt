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

        outputs.forEach { output ->
            writeSingle(output)
        }
    }

    fun writeSingle(
        output: BucketOutput
    ) {

        val folder =
            outputDirectory
                .resolve(output.folderName)
                .toFile()

        if (!folder.exists()) {
            folder.mkdirs()
        }

        val xmlFile =
            folder.resolve(
                output.fileName
            )

        xmlFile.bufferedWriter().use { writer ->
            xmlWriter.write(output, writer)
        }
    }
}
