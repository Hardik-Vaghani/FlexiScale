/*
 * Copyright 2025 Hardik Vaghani
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.hardikvaghani.flexiscale.generator

import io.github.hardikvaghani.flexiscale.generator.generator.FlexiScaleGenerator
import io.github.hardikvaghani.flexiscale.generator.model.GeneratorConfig
import io.github.hardikvaghani.flexiscale.generator.writer.ResourceFolderWriter
import java.nio.file.Path
import java.nio.file.Paths

fun main() {
    /**
    11 -     * Generated files are stored under build/generated/flexiscale.
    12 -     * This follows Gradle conventions and keeps generated artifacts
    13 -     * separate from source code.
    14 -     *
    15 -     * Paths.get(System.getProperty("user.dir"), "generated")
    16 -     * Paths.get("generated")
    17 -     * Paths.get("build/generated/flexiscale")
    18 -     * Paths.get("build", "generated", "flexiscale")
    19 -     * Paths.get("flexiscale-generator", "build", "generated", "flexiscale")
    20 -     * Paths.get(System.getProperty("user.dir"),"flexiscale-generator", "build", "generated", "flexiscale")
    21 -     * Paths.get(System.getProperty("user.dir"),"flexiscale-resources", "src", "main", "res")
    22 -     */
    //val outputDirectory = Paths.get(System.getProperty("user.dir"),"flexiscale-resources", "src", "main", "res") 
    val outputDirectory =
        repositoryRoot()
            .resolve("flexiscale-resources")
            .resolve("src")
            .resolve("main")
            .resolve("res")

    val generator =
        FlexiScaleGenerator(
            config = GeneratorConfig.DEFAULT,

            writer = ResourceFolderWriter(outputDirectory = outputDirectory)
        )


    generator.generateAll()

    println(
        "FlexiScale XML generation completed."
    )
}

// Run those commands and create your generator folder with variation files:-
// ./gradlew :flexiscale-generator:run --stacktrace
    // OR
// ./gradlew :flexiscale-generator:run
// find generated -type f | wc -l

private fun repositoryRoot(): Path {

    val currentDirectory =
        Paths
            .get(System.getProperty("user.dir"))
            .toAbsolutePath()

    return if (currentDirectory.fileName.toString() == "flexiscale-generator") {
        currentDirectory.parent
    } else {
        currentDirectory
    }
}
