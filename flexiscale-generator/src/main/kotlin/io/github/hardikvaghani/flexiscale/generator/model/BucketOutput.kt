package io.github.hardikvaghani.flexiscale.generator.model

import io.github.hardikvaghani.flexiscale.runtime.model.ScreenBucket

data class BucketOutput(
    val bucket: ScreenBucket,
    val entries: List<DimensionEntry>
) {

    val folderName: String
        get() = "values-sw${bucket.minWidthDp}dp"

    val fileName: String
        get() = "dimensions.xml"
}