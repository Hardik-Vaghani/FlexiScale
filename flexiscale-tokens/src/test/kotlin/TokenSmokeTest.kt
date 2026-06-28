import io.github.hardikvaghani.flexiscale.tokens.accessibility.FlexiAccessibilityTokens
import io.github.hardikvaghani.flexiscale.tokens.accessibility.FlexiTouchTargetTokens
import io.github.hardikvaghani.flexiscale.tokens.duration.FlexiDurationTokens
import io.github.hardikvaghani.flexiscale.tokens.elevation.FlexiElevationTokens
import io.github.hardikvaghani.flexiscale.tokens.icon.FlexiIconTokens
import io.github.hardikvaghani.flexiscale.tokens.layout.FlexiBreakpointTokens
import io.github.hardikvaghani.flexiscale.tokens.layout.FlexiContentWidthTokens
import io.github.hardikvaghani.flexiscale.tokens.layout.FlexiGridTokens
import io.github.hardikvaghani.flexiscale.tokens.model.DimensionToken
import io.github.hardikvaghani.flexiscale.tokens.model.DurationToken
import io.github.hardikvaghani.flexiscale.tokens.model.FloatToken
import io.github.hardikvaghani.flexiscale.tokens.model.OpacityToken
import io.github.hardikvaghani.flexiscale.tokens.model.PercentageToken
import io.github.hardikvaghani.flexiscale.tokens.motion.FlexiDelayTokens
import io.github.hardikvaghani.flexiscale.tokens.motion.FlexiEasingTokens
import io.github.hardikvaghani.flexiscale.tokens.motion.FlexiSpringTokens
import io.github.hardikvaghani.flexiscale.tokens.opacity.FlexiOpacityTokens
import io.github.hardikvaghani.flexiscale.tokens.radius.FlexiRadiusTokens
import io.github.hardikvaghani.flexiscale.tokens.size.FlexiSizeTokens
import io.github.hardikvaghani.flexiscale.tokens.spacing.FlexiSpacing
import io.github.hardikvaghani.flexiscale.tokens.spacing.FlexiSpacingTokens
import io.github.hardikvaghani.flexiscale.tokens.stroke.FlexiStrokeTokens
import io.github.hardikvaghani.flexiscale.tokens.typography.FlexiTypographyTokens
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull

class TokenSmokeTest {

    @Test
    fun valueClassesExposeWrappedValues() {

        assertEquals(12.0, DimensionToken(12.0).value)
        assertEquals(150, DurationToken(150).millis)
        assertEquals(0.75, FloatToken(0.75).value)
        assertEquals(0.38, OpacityToken(0.38).value)
        assertEquals(50.0, PercentageToken(50.0).value)
    }

    @Test
    fun percentageTokenRejectsOutOfRangeValues() {

        assertFailsWith<IllegalArgumentException> {
            PercentageToken(-0.1)
        }

        assertFailsWith<IllegalArgumentException> {
            PercentageToken(100.1)
        }
    }

    @Test
    fun accessibilityTokensExposeExpectedValues() {

        assertEquals(4.5, FlexiAccessibilityTokens.MINIMUM_CONTRAST)
        assertEquals(7.0, FlexiAccessibilityTokens.ENHANCED_CONTRAST)
        assertEquals(48.0, FlexiTouchTargetTokens.Minimum.value)
        assertEquals(56.0, FlexiTouchTargetTokens.Comfortable.value)
        assertEquals(64.0, FlexiTouchTargetTokens.Large.value)
    }

    @Test
    fun durationTokensExposeExpectedValues() {

        assertEquals(0, FlexiDurationTokens.Instant.millis)
        assertEquals(150, FlexiDurationTokens.Fast.millis)
        assertEquals(300, FlexiDurationTokens.Normal.millis)
        assertEquals(500, FlexiDurationTokens.Slow.millis)
        assertEquals(1000, FlexiDurationTokens.VerySlow.millis)

        assertEquals(0, io.github.hardikvaghani.flexiscale.tokens.motion.FlexiDurationTokens.Instant.millis)
        assertEquals(150, io.github.hardikvaghani.flexiscale.tokens.motion.FlexiDurationTokens.Fast.millis)
        assertEquals(300, io.github.hardikvaghani.flexiscale.tokens.motion.FlexiDurationTokens.Normal.millis)
        assertEquals(500, io.github.hardikvaghani.flexiscale.tokens.motion.FlexiDurationTokens.Slow.millis)
        assertEquals(1000, io.github.hardikvaghani.flexiscale.tokens.motion.FlexiDurationTokens.VerySlow.millis)
    }

    @Test
    fun motionTokensExposeExpectedValues() {

        assertEquals(0, FlexiDelayTokens.None.millis)
        assertEquals(25, FlexiDelayTokens.Tiny.millis)
        assertEquals(50, FlexiDelayTokens.Small.millis)
        assertEquals(100, FlexiDelayTokens.Medium.millis)
        assertEquals(200, FlexiDelayTokens.Large.millis)
        assertEquals(300, FlexiDelayTokens.ExtraLarge.millis)

        assertEquals("linear", FlexiEasingTokens.LINEAR)
        assertEquals("ease_in", FlexiEasingTokens.EASE_IN)
        assertEquals("ease_out", FlexiEasingTokens.EASE_OUT)
        assertEquals("ease_in_out", FlexiEasingTokens.EASE_IN_OUT)
        assertEquals("emphasized", FlexiEasingTokens.EMPHASIZED)

        assertEquals(0.25, FlexiSpringTokens.VeryLow.value)
        assertEquals(0.50, FlexiSpringTokens.Low.value)
        assertEquals(0.75, FlexiSpringTokens.Medium.value)
        assertEquals(1.00, FlexiSpringTokens.High.value)
        assertEquals(1.25, FlexiSpringTokens.VeryHigh.value)
    }

    @Test
    fun dimensionTokenGroupsExposeExpectedValues() {

        assertEquals(0.0, FlexiElevationTokens.None.value)
        assertEquals(1.0, FlexiElevationTokens.Level1.value)
        assertEquals(3.0, FlexiElevationTokens.Level2.value)
        assertEquals(6.0, FlexiElevationTokens.Level3.value)
        assertEquals(8.0, FlexiElevationTokens.Level4.value)
        assertEquals(12.0, FlexiElevationTokens.Level5.value)
        assertEquals(16.0, FlexiElevationTokens.Level6.value)

        assertEquals(12.0, FlexiIconTokens.Tiny.value)
        assertEquals(16.0, FlexiIconTokens.Small.value)
        assertEquals(24.0, FlexiIconTokens.Medium.value)
        assertEquals(32.0, FlexiIconTokens.Large.value)
        assertEquals(48.0, FlexiIconTokens.ExtraLarge.value)
        assertEquals(64.0, FlexiIconTokens.Hero.value)

        assertEquals(0.0, FlexiRadiusTokens.None.value)
        assertEquals(2.0, FlexiRadiusTokens.Tiny.value)
        assertEquals(4.0, FlexiRadiusTokens.Small.value)
        assertEquals(8.0, FlexiRadiusTokens.Medium.value)
        assertEquals(12.0, FlexiRadiusTokens.Large.value)
        assertEquals(16.0, FlexiRadiusTokens.ExtraLarge.value)
        assertEquals(24.0, FlexiRadiusTokens.Huge.value)
        assertEquals(999.0, FlexiRadiusTokens.Pill.value)

        assertEquals(0.5, FlexiStrokeTokens.Hairline.value)
        assertEquals(1.0, FlexiStrokeTokens.Thin.value)
        assertEquals(2.0, FlexiStrokeTokens.Medium.value)
        assertEquals(4.0, FlexiStrokeTokens.Thick.value)
        assertEquals(8.0, FlexiStrokeTokens.Heavy.value)
    }

    @Test
    fun layoutAndSpacingTokensExposeExpectedValues() {

        assertNotNull(FlexiBreakpointTokens())
        assertNotNull(FlexiGridTokens())

        assertEquals(600.0, FlexiContentWidthTokens.Compact.value)
        assertEquals(840.0, FlexiContentWidthTokens.Medium.value)
        assertEquals(1200.0, FlexiContentWidthTokens.Expanded.value)
        assertEquals(1440.0, FlexiContentWidthTokens.Large.value)

        assertEquals(0.0, FlexiSpacingTokens.None.value)
        assertEquals(1.0, FlexiSpacingTokens.Xxxs.value)
        assertEquals(2.0, FlexiSpacingTokens.Xxs.value)
        assertEquals(4.0, FlexiSpacingTokens.Xs.value)
        assertEquals(8.0, FlexiSpacingTokens.Sm.value)
        assertEquals(12.0, FlexiSpacingTokens.Md.value)
        assertEquals(16.0, FlexiSpacingTokens.Lg.value)
        assertEquals(20.0, FlexiSpacingTokens.Xl.value)
        assertEquals(24.0, FlexiSpacingTokens.Xxl.value)
        assertEquals(32.0, FlexiSpacingTokens.Xxxl.value)
        assertEquals(40.0, FlexiSpacingTokens.Huge.value)
        assertEquals(48.0, FlexiSpacingTokens.Massive.value)
        assertEquals(56.0, FlexiSpacingTokens.Giant.value)
        assertEquals(64.0, FlexiSpacingTokens.Colossal.value)
        assertEquals(16.0, FlexiSpacingTokens.ScreenPadding.value)
        assertEquals(16.0, FlexiSpacingTokens.CardPadding.value)
        assertEquals(24.0, FlexiSpacingTokens.DialogPadding.value)
        assertEquals(32.0, FlexiSpacingTokens.SectionSpacing.value)

        assertEquals(0, FlexiSpacing.NONE)
        assertEquals(2, FlexiSpacing.XXXS)
        assertEquals(4, FlexiSpacing.XXS)
        assertEquals(8, FlexiSpacing.XS)
        assertEquals(12, FlexiSpacing.SM)
        assertEquals(16, FlexiSpacing.MD)
        assertEquals(24, FlexiSpacing.LG)
        assertEquals(32, FlexiSpacing.XL)
        assertEquals(48, FlexiSpacing.XXL)
        assertEquals(64, FlexiSpacing.XXXL)
        assertEquals(96, FlexiSpacing.HUGE)
    }

    @Test
    fun sizeOpacityAndTypographyTokensExposeExpectedValues() {

        assertEquals(32.0, FlexiSizeTokens.ChipHeight.value)
        assertEquals(36.0, FlexiSizeTokens.ButtonSmall.value)
        assertEquals(48.0, FlexiSizeTokens.ButtonMedium.value)
        assertEquals(56.0, FlexiSizeTokens.ButtonLarge.value)
        assertEquals(56.0, FlexiSizeTokens.ToolbarHeight.value)
        assertEquals(56.0, FlexiSizeTokens.TextFieldHeight.value)
        assertEquals(80.0, FlexiSizeTokens.BottomBarHeight.value)
        assertEquals(56.0, FlexiSizeTokens.FabSize.value)

        assertEquals(0.38, FlexiOpacityTokens.Disabled.value)
        assertEquals(0.60, FlexiOpacityTokens.Low.value)
        assertEquals(0.75, FlexiOpacityTokens.Medium.value)
        assertEquals(0.90, FlexiOpacityTokens.High.value)
        assertEquals(1.00, FlexiOpacityTokens.Full.value)

        assertEquals(10.0, FlexiTypographyTokens.LabelTiny.value)
        assertEquals(11.0, FlexiTypographyTokens.LabelSmall.value)
        assertEquals(12.0, FlexiTypographyTokens.LabelMedium.value)
        assertEquals(14.0, FlexiTypographyTokens.BodySmall.value)
        assertEquals(16.0, FlexiTypographyTokens.BodyMedium.value)
        assertEquals(18.0, FlexiTypographyTokens.BodyLarge.value)
        assertEquals(20.0, FlexiTypographyTokens.TitleSmall.value)
        assertEquals(24.0, FlexiTypographyTokens.TitleMedium.value)
        assertEquals(28.0, FlexiTypographyTokens.TitleLarge.value)
        assertEquals(32.0, FlexiTypographyTokens.HeadlineSmall.value)
        assertEquals(40.0, FlexiTypographyTokens.HeadlineMedium.value)
        assertEquals(48.0, FlexiTypographyTokens.HeadlineLarge.value)
        assertEquals(57.0, FlexiTypographyTokens.DisplaySmall.value)
        assertEquals(64.0, FlexiTypographyTokens.DisplayMedium.value)
        assertEquals(72.0, FlexiTypographyTokens.DisplayLarge.value)
    }
}
