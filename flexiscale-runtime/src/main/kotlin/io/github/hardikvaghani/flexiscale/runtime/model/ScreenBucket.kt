package io.github.hardikvaghani.flexiscale.runtime.model

enum class ScreenBucket(
    val minWidthDp: Int,
    val description: String
) {

    SW192(192, "Small Wear OS"),
    SW240(240, "Large Wear OS"),

    SW280(280, "Compact Wear / Tiny Phone"),
    SW300(300, "Legacy Small Phone"),
    SW320(320, "Small Phone"),
    SW330(330, "Compact Phone"),

    SW360(360, "Standard Phone"),
    SW390(390, "Modern Compact Phone"),
    SW411(411, "Large Phone"),
    SW420(420, "Modern Large Phone"),
    SW450(450, "XL Phone"),
    SW480(480, "Foldable Folded"),

    SW510(510, "Foldable Compact"),
    SW540(540, "Foldable Expanded"),
    SW570(570, "Large Foldable"),
    SW600(600, "Tablet"),

    SW640(640, "Compact Tablet"),
    SW680(680, "Medium Tablet"),
    SW720(720, "Large Tablet"),
    SW760(760, "Tablet Landscape"),
    SW800(800, "XL Tablet"),
    SW840(840, "Chromebook"),

    SW900(900, "Desktop Compact"),
    SW960(960, "Desktop"),
    SW1024(1024, "Desktop Small"),
    SW1080(1080, "Desktop Medium"),

    SW1200(1200, "Desktop Large"),
    SW1280(1280, "Desktop XL"),
    SW1366(1366, "Laptop Wide"),
    SW1440(1440, "Ultra Wide"),

    SW1600(1600, "Large Desktop"),
    SW1920(1920, "TV"),
    SW2560(2560, "Ultra Wide Monitor");

    companion object {
        val valuesSorted =
            entries.sortedBy { it.minWidthDp }
    }
}