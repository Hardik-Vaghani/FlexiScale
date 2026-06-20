package io.github.hardikvaghani.flexiscale.generator.util

object NumberSequenceGenerator {

    fun generate(
        start: Double,
        end: Double,
        step: Double
    ): Sequence<Double> {

        require(start > 0)
        require(end >= start)
        require(step > 0)
//        require(start > 0.0) { "Start must be positive: $start" }
//        require(end >= start) { "End ($end) must be >= start ($start)" }
//        require(step > 0.0) { "Step must be positive: $step" }
        return generateSequence(start) {

            val next = it + step

            if (next > end) null
            else next
        }
    }
}