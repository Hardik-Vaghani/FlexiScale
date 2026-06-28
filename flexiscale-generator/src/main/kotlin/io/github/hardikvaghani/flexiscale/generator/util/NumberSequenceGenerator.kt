package io.github.hardikvaghani.flexiscale.generator.util

import java.math.BigDecimal

object NumberSequenceGenerator {

    fun generate(
        start: Double,
        end: Double,
        step: Double
    ): Sequence<Double> {

        require(start > 0.0) { "Start must be positive: $start" }
        require(end >= start) { "End ($end) must be >= start ($start)" }
        require(step > 0.0) { "Step must be positive: $step" }

        return sequence {
            var current =
                BigDecimal.valueOf(start)

            val endValue =
                BigDecimal.valueOf(end)

            val stepValue =
                BigDecimal.valueOf(step)

            while (current <= endValue) {
                yield(current.toDouble())
                current = current.add(stepValue)
            }
        }
    }
}
