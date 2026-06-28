package io.github.hardikvaghani.flexiscale.tokens.motion

import io.github.hardikvaghani.flexiscale.tokens.model.DurationToken

object FlexiDelayTokens {

    val None = DurationToken(0)

    val Tiny = DurationToken(25)

    val Small = DurationToken(50)

    val Medium = DurationToken(100)

    val Large = DurationToken(200)

    val ExtraLarge = DurationToken(300)
}