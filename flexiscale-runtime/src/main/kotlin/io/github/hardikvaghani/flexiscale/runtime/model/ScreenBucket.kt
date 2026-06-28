package io.github.hardikvaghani.flexiscale.runtime.model

data class ScreenBucket(
    val minWidthDp: Int,
    val description: String
) {

    companion object {

        val allBuckets: List<ScreenBucket> by lazy {
            (192..2560).map { sw ->
                ScreenBucket(
                    minWidthDp = sw,
                    description = descriptionFor(sw)
                )
            }
        }

        private fun descriptionFor(
            sw: Int
        ): String =

            when (sw) {
                in 192..239 -> "Small Wear OS"
                in 240..279 -> "Large Wear OS"

                in 280..299 -> "Compact Wear / Tiny Phone"
                in 300..319 -> "Legacy Small Phone"
                in 320..359 -> "Small Phone"

                in 360..410 -> "Standard Phone"
                in 411..479 -> "Large Phone"
                in 480..599 -> "Foldable"

                in 600..719 -> "Tablet"
                in 720..839 -> "Large Tablet"

                in 840..959 -> "Chromebook"
                in 960..1199 -> "Desktop"

                in 1200..1439 -> "Desktop Large"
                in 1440..1599 -> "Ultra Wide"

                in 1600..1919 -> "Large Desktop"
                in 1920..2559 -> "TV"

                else -> "Ultra Wide Monitor"
            }
    }
}
