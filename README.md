# FlexiScale

[![CI](https://github.com/hardik-vaghani/flexiscale/actions/workflows/ci.yml/badge.svg)](https://github.com/hardik-vaghani/flexiscale/actions/workflows/ci.yml)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Maven Central](https://img.shields.io/maven-central/v/io.github.hardikvaghani.flexiscale/flexiscale-runtime)](https://central.sonatype.com/namespace/io.github.hardikvaghani.flexiscale)
[![Kotlin](https://img.shields.io/badge/Kotlin-2.2.0-purple.svg)](https://kotlinlang.org)

A **responsive UI scaling library for Android** — providing consistent scaling across all form factors from Wear OS (192dp) to Ultra Wide Monitors (2560dp).

FlexiScale automatically adapts your UI dimensions to the device's screen width using a formula-based scaling engine, pre-generated XML resources, and Jetpack Compose extensions.

---

## Features

- **2369 screen width buckets** — every integer from 192dp to 2560dp with optimized scale profiles
- **Formula-based scaling** — linear interpolation for dp and sp values
- **Jetpack Compose support** — `.fdp()` and `.fsp()` composable extensions
- **XML/View support** — pre-generated `@dimen/` resources for all buckets
- **Design tokens** — spacing, typography, radius, elevation, size, icon, motion, opacity, stroke, and accessibility
- **Extensible strategies** — implement `ScaleStrategy` for custom scaling logic
- **Multi-module** — use only what you need

## Why FlexiScale?

Traditional Android scaling approaches either lack precision (density buckets) or require manual calculation. FlexiScale provides:

| Approach | Precision | Automation | Compose | XML |
|----------|-----------|------------|---------|-----|
| Density buckets (mdpi/hdpi/xhdpi) | ❌ Coarse | ✅ Auto | ❌ | ✅ |
| sdp/ssp library | ✅ Fine | ✅ Auto | ❌ | ✅ |
| **FlexiScale** | **✅ Precise** | **✅ Auto** | **✅ Yes** | **✅ Yes** |

## Installation

Add the dependency to your module's `build.gradle.kts`:

```kotlin
dependencies {
    // For Compose support
    implementation("io.github.hardikvaghani.flexiscale:flexiscale-compose:0.1.0")

    // For XML/View support
    implementation("io.github.hardikvaghani.flexiscale:flexiscale-resources:0.1.0")

    // Core runtime only (if you need programmatic scaling)
    implementation("io.github.hardikvaghani.flexiscale:flexiscale-runtime:0.1.0")

    // Design system tokens
    implementation("io.github.hardikvaghani.flexiscale:flexiscale-tokens:0.1.0")
}
```

### Version Catalog

```toml
[versions]
flexiscale = "0.1.0"

[libraries]
flexiscale-compose = { module = "io.github.hardikvaghani.flexiscale:flexiscale-compose", version.ref = "flexiscale" }
flexiscale-runtime = { module = "io.github.hardikvaghani.flexiscale:flexiscale-runtime", version.ref = "flexiscale" }
flexiscale-tokens = { module = "io.github.hardikvaghani.flexiscale:flexiscale-tokens", version.ref = "flexiscale" }
flexiscale-resources = { module = "io.github.hardikvaghani.flexiscale:flexiscale-resources", version.ref = "flexiscale" }
```

## Usage

### Jetpack Compose

```kotlin
import io.github.hardikvaghani.flexiscale.compose.extensions.fdp
import io.github.hardikvaghani.flexiscale.compose.extensions.fsp

@Composable
fun ResponsiveCard() {
    Text(
        text = "Hello, FlexiScale!",
        fontSize = 16.fsp(),      // Scales with screen width
        modifier = Modifier
            .padding(16.fdp())     // Scales with screen width
            .width(200.fdp())      // Scales with screen width
    )
}
```

### XML / View System

```xml
<TextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:textSize="@dimen/flexi_sp_16"
    android:padding="@dimen/flexi_dp_16" />
```

### Programmatic Scaling

```kotlin
import io.github.hardikvaghani.flexiscale.runtime.FlexiScale

// Scale 16dp for a device with 411dp smallest width
val scaledDp = FlexiScale.dp(smallestWidthDp = 411, value = 16.0)

// Scale 14sp for a device with 360dp smallest width
val scaledSp = FlexiScale.sp(smallestWidthDp = 360, value = 14.0)

// Or pass a ScreenInfo object
val info = ScreenInfo(smallestWidthDp = 411, widthDp = 411, heightDp = 900)
val result = FlexiScale.dp(screenInfo = info, value = 16.0)
```

### Custom Scaling Strategy

```kotlin
import io.github.hardikvaghani.flexiscale.runtime.strategy.ScaleStrategy
import io.github.hardikvaghani.flexiscale.runtime.model.ScreenBucket

class MyCustomStrategy : ScaleStrategy {
    override fun dpScale(bucket: ScreenBucket): Double {
        // Custom scaling logic
        return bucket.minWidthDp / 360.0
    }

    override fun spScale(bucket: ScreenBucket): Double {
        // Custom scaling logic
        return bucket.minWidthDp / 360.0
    }
}
```

## Screen Buckets

FlexiScale defines 2369 screen width buckets covering every integer from 192dp to 2560dp:

| Range | Category | Example Devices |
|-------|----------|----------------|
| 192–239 | Small Wear OS | Smartwatches |
| 240–279 | Large Wear OS | Smartwatches |
| 280–319 | Compact Phone | Small form-factor phones |
| 320–359 | Small Phone | iPhone SE, older Androids |
| 360–410 | Standard Phone | Pixel, Galaxy series |
| 411–479 | Large Phone | Galaxy Ultra, iPhone Pro Max |
| 480–599 | Foldable | Galaxy Fold, Pixel Fold |
| 600–719 | Tablet | iPad Mini, Galaxy Tab |
| 720–839 | Large Tablet | iPad, Galaxy Tab Plus |
| 840–959 | Chromebook | Pixelbook, Chromebooks |
| 960–1199 | Desktop | Desktop windows |
| 1200–1599 | Ultra Wide | Ultra-wide monitors |
| 1600–2560 | TV/Large Desktop | 4K TVs, large monitors |

## Scaling Formula

`DefaultScaleStrategy` applies linear interpolation:

| Range | dpScale | spScale |
|-------|---------|---------|
| 192 → 360dp | `sw / 360` | `0.82 → 1.0` (linear) |
| 360 → 2560dp | `1.0 → 3.20` (linear) | `1.0 → 1.70` (linear) |

## Modules

| Module | Published | Description |
|--------|-----------|-------------|
| `flexiscale-runtime` | ✅ Maven Central | Core scaling engine |
| `flexiscale-tokens` | ✅ Maven Central | Design system tokens |
| `flexiscale-compose` | ✅ Maven Central | Compose extensions |
| `flexiscale-resources` | ✅ Maven Central | XML dimension resources |
| `flexiscale-generator` | ❌ Internal | XML resource generator |

## Architecture

```
┌─────────────────────────────────────────────┐
│            flexiscale-compose                │
│  .fdp() / .fsp() composable extensions      │
├─────────────────────────────────────────────┤
│            flexiscale-resources              │
│  @dimen/flexi_dp_* / @dimen/flexi_sp_*      │
├───────────────────────┬─────────────────────┤
│   flexiscale-runtime  │  flexiscale-tokens   │
│   ScreenBucket        │  Spacing, Typography │
│   ScaleStrategy       │  Radius, Elevation   │
│   ResponsiveScaler    │  Motion, Opacity     │
│   ScreenInfo          │  Size, Icon, Stroke  │
└───────────────────────┴─────────────────────┘
```

## Build & Test

```bash
# Build all JVM modules
./gradlew build

# Run all JVM tests
./gradlew test

# Publish to local Maven
./gradlew publishToMavenLocal

# Generate XML resources
./gradlew :flexiscale-generator:run
```

## Tech Stack

- **Language:** Kotlin 2.2.0
- **JDK:** 21
- **Build System:** Gradle 8.14
- **Android Plugin:** AGP 8.13.0
- **Testing:** kotlin.test
- **Metadata:** Apache 2.0 License

## FAQ

**Q: How is this different from sdp/ssp?**
A: FlexiScale provides the same precision but adds first-class Compose support, design tokens, and a pluggable strategy system.

**Q: Do I need all modules?**
A: No. Use only what you need — runtime only, Compose + runtime, or XML resources only.

**Q: Is it production-ready?**
A: The library is in active development (v0.1.0). Core APIs are stable.

## Roadmap

- [ ] Sample apps (Compose + XML)
- [ ] Animation/transition support
- [ ] Custom theme integration
- [ ] R8/ProGuard rules
- [ ] Compose Multiplatform support

## License

```
Copyright 2025 Hardik Vaghani

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

## Acknowledgements

- [sdp](https://github.com/intuit/sdp) / [ssp](https://github.com/intuit/ssp) — inspiration for the Android dimension scaling approach
- JetBrains — Kotlin and tooling
- Google — Android Jetpack Compose

---

<p align="center">
  Made with ❤️ by <a href="https://github.com/hardik-vaghani">Hardik Vaghani</a>
</p>
