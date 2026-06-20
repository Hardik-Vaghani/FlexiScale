package io.github.hardikvaghani.flexiscale.generator.model

data class GeneratorConfig(
    val start: Double = 0.1,//0.5,
    val end: Double = 500.0,
    val step: Double = 0.1,//0.5,
    val generateDp: Boolean = true,
    val generateSp: Boolean = true,
    val includeNegative: Boolean = true,
    val decimalPlaces: Int = 5
) {

    companion object {
        val DEFAULT = GeneratorConfig()
    }
}