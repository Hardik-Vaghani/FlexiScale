package io.github.hardikvaghani.flexiscale.runtime.model

enum class ScreenBucket(
    val minWidthDp: Int,
    val description: String
) {

    SW192(
        192,
        "Small Wear OS"
    ),

    SW240(
        240,
        "Large Wear OS"
    ),

    SW320(
        320,
        "Small Phone"
    ),

    SW360(
        360,
        "Standard Phone"
    ),

    SW411(
        411,
        "Large Phone"
    ),

    SW480(
        480,
        "Foldable Folded"
    ),

    SW600(
        600,
        "Tablet"
    ),

    SW720(
        720,
        "Large Tablet"
    ),

    SW840(
        840,
        "XL Tablet / Chromebook"
    ),

    SW960(
        960,
        "Desktop / TV"
    ),

    SW1280(
        1280,
        "Large Desktop"
    ),

    SW1440(
        1440,
        "Ultra Wide Desktop"
    )
}