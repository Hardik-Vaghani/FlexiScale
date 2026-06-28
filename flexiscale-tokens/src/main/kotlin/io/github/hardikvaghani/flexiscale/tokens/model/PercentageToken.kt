package io.github.hardikvaghani.flexiscale.tokens.model

@JvmInline
value class PercentageToken(
    val value: Double
) {
    init {
        require(value in 0.0..100.0) {
            "Percentage must be between 0 and 100"
        }
    }
}