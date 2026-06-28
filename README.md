# FlexiScale

[![CI](https://github.com/hardikvaghani/flexiscale/actions/workflows/ci.yml/badge.svg)](https://github.com/hardikvaghani/flexiscale/actions/workflows/ci.yml)

A **responsive UI scaling library for Android** — providing consistent scaling across all form factors from Wear OS (192dp) to Ultra Wide Monitors (2560dp).

Supports XML Developers, Jetpack Compose Developers, and Kotlin/Java Developers.

---

## Project Structure

```
FlexiScale/
├── build.gradle.kts                  # Root build — declares plugins + group/version
├── settings.gradle.kts               # Project name, repository config, module declarations
├── gradle.properties                 # Build cache & configuration cache
├── gradle/
│   ├── libs.versions.toml            # Version catalog (Kotlin, kotlinx libraries)
│   └── wrapper/
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── .github/workflows/
│   └── ci.yml                        # GitHub Actions CI with Android SDK
│
├── flexiscale-runtime/               # Core scaling engine (JVM, published)
├── flexiscale-generator/             # XML resource generator (JVM, internal tool)
├── flexiscale-tokens/                # Design system tokens (JVM, published)
├── flexiscale-resources/             # Android resources module (generated XML)
└── flexiscale-compose/               # Compose integration layer (Android, published)
```

## Modules

| Module | Type | Published | Description |
|--------|------|-----------|-------------|
| `:flexiscale-runtime` | JVM | ✅ Planned | Core scaling engine — `ScreenBucket`, `ScaleProfile`, `ResponsiveScaler`, `DefaultScaleStrategy` (formula-based) |
| `:flexiscale-generator` | JVM | ❌ No | Internal tool — generates `dimensions.xml` for all 2369 screen buckets |
| `:flexiscale-tokens` | JVM | ✅ Planned | Design system tokens — spacing, typography, radius, elevation, size, icon, motion, opacity, stroke, accessibility |
| `:flexiscale-resources` | Android | ❌ No | Generated XML dimension resources — 33 bucket folders, ~660K dimension entries |
| `:flexiscale-compose` | Android | ✅ Planned | Compose integration — `.fdp()`, `.fsp()` composable extensions |

## Features

- **2369 screen width buckets** — every integer from 192dp to 2560dp with formula-based scale profiles
- **dpScale & spScale** — separate linear interpolation formulas for density-independent and scale-independent pixels
- **Extensible strategy pattern** — `ScaleStrategy` interface for custom scaling logic
- **Design tokens** — complete design system with 9 token categories
- **XML resources** — pre-generated `@dimen/` resources for all screen buckets
- **Compose extensions** — `.fdp()`, `.fsp()` for Jetpack Compose

## Test Status

| Module | Tests | Status |
|--------|-------|--------|
| flexiscale-runtime | 16 | ✅ All passing |
| flexiscale-generator | 10 | ✅ All passing |
| flexiscale-tokens | 8 | ✅ All passing |
| flexiscale-compose | 2 | ✅ (runtime-only, requires Android SDK) |
| flexiscale-resources | 4 | ✅ (requires Android SDK) |
| **Total** | **40** | **✅ All passing** |

## Prerequisites

- **JDK 21+** (automatically resolved via the Foojay Toolchains plugin if not installed locally)
- **Android SDK** (for `:flexiscale-compose` and `:flexiscale-resources` modules)
- No manual Gradle installation needed — use the Gradle Wrapper (`./gradlew`)

## Build & Test

```bash
# Build all JVM modules (runtime, generator, tokens)
./gradlew build

# Run all tests (JVM modules)
./gradlew test

# Run all tests including Android modules (requires Android SDK)
./gradlew build

# Run specific module tests
./gradlew :flexiscale-runtime:test
./gradlew :flexiscale-generator:test
./gradlew :flexiscale-tokens:test

# Generate XML resources
./gradlew :flexiscale-generator:run

# Clean all build outputs
./gradlew clean
```

> **Note:** The project uses the Gradle Wrapper (`./gradlew`), which automatically downloads the correct Gradle version.

## Tech Stack

- **Language:** Kotlin 2.2.0
- **JDK:** 21
- **Build System:** Gradle 8.14 (via Wrapper)
- **Android Plugin:** AGP 8.13.0
- **Testing:** kotlin.test + JUnit 4 (Android modules)
- **Available libraries (via version catalog):**
  - [kotlinx-datetime](https://github.com/Kotlin/kotlinx-datetime)
  - [kotlinx-serialization-json](https://github.com/Kotlin/kotlinx-serialization)
  - [kotlinx-coroutines](https://github.com/Kotlin/kotlinx-coroutines)

## Scaling Formula

`DefaultScaleStrategy` uses linear interpolation:

| Range | dpScale | spScale |
|-------|---------|---------|
| 192 → 360dp | `sw / 360` | `0.82 → 1.0` (linear) |
| 360 → 2560dp | `1.0 → 3.20` (linear) | `1.0 → 1.70` (linear) |

## Configuration

- **Version catalog:** `gradle/libs.versions.toml` — centralizes all dependency versions
- **Build cache:** Enabled in `gradle.properties` for faster rebuilds
- **Configuration cache:** Enabled in `gradle.properties` (reuses build configuration across runs)
- **CI/CD:** GitHub Actions workflow in `.github/workflows/ci.yml` — builds and tests all modules with Android SDK

## Roadmap

| Phase | Description | Status |
|-------|-------------|--------|
| **Phase 1** | Runtime | ✅ Complete |
| **Phase 2** | Generator | ✅ Substantially complete |
| **Phase 3** | Tokens Resource Generation | ⏳ In progress |
| **Phase 4** | Compose APIs | ⏳ In progress |
| **Phase 5** | Maven Central Publishing | ❌ Not started |
| **Phase 6** | Documentation | ⏳ In progress |
| **Phase 7** | Sample Apps | ❌ Not started |
