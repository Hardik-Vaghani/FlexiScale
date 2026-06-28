# Changelog

All notable changes to FlexiScale will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

### Added

- Responsive scaling engine with `FlexiScale.dp()` and `FlexiScale.sp()`
- 2369 screen width buckets (192dp–2560dp) with descriptive labels
- `DefaultScaleStrategy` with linear interpolation formula
- `ScaleStrategy` interface for custom scaling logic
- `ScreenBucketResolver` for bucket resolution
- Pre-generated XML `@dimen/` resources for all screen buckets
- Design system tokens: spacing, typography, radius, elevation, size, icon, motion, opacity, stroke, accessibility
- Jetpack Compose `.fdp()` and `.fsp()` composable extensions
- XML dimension resource generator (internal tool)
- Apache 2.0 license
- Maven Central publishing configuration
