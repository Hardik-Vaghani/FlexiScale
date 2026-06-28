package io.github.hardikvaghani.flexiscale.tokens.duration

import io.github.hardikvaghani.flexiscale.tokens.model.DurationToken

object FlexiDurationTokens {

    val Instant = DurationToken(0)

    val Fast = DurationToken(150)

    val Normal = DurationToken(300)

    val Slow = DurationToken(500)

    val VerySlow = DurationToken(1000)
}