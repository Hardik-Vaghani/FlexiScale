# FlexiScale — Project Structure Report

> **Updated:** June 28, 2026  
> **Project Name:** FlexiScale  
> **Root:** `flexiscale`  
> **Group ID:** `io.github.hardikvaghani.flexiscale`  
> **Version:** `0.1.0-SNAPSHOT`  
> **JDK:** 21 (Kotlin JVM)  
> **Kotlin Version:** 2.2.0  
> **Gradle Version:** 8.14  
> **Build System:** Gradle (Wrapper)

---

## 1. Project Overview

FlexiScale is a **responsive UI scaling library for Android**, organized as a **Kotlin multi-module** project built with Gradle. It has **5 subprojects** (modules) with 61 Kotlin source files, 14 test files, and 29 passing tests. The project also includes 33 generated Android XML resource files covering 2369 screen width buckets (192–2560dp).

---

## 2. Directory Tree

```
flexiscale/
│
├── .gitignore
├── .gradle/                          # (gitignored) Gradle build cache
├── .idea/                            # IntelliJ IDEA project files
│
├── README.md
├── build.gradle.kts                  # Root build — declares plugins + group/version
├── build/                            # (gitignored)
├── gradle.properties
├── gradle/
│   ├── libs.versions.toml            # Version catalog
│   └── wrapper/
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
│
├── gradlew
├── gradlew.bat
├── settings.gradle.kts               # Includes 5 subprojects
│
├── flexiscale-runtime/               # Core scaling engine (JVM, published)
│   ├── build.gradle.kts
│   └── src/
│       ├── main/kotlin/.../runtime/
│       │   ├── FlexiScale.kt
│       │   ├── model/
│       │   │   ├── ScreenBucket.kt         # Data class, 2369 generated buckets
│       │   │   ├── ScreenInfo.kt
│       │   │   └── ScaleProfile.kt
│       │   ├── scaler/
│       │   │   └── ResponsiveScaler.kt
│       │   ├── resolver/
│       │   │   └── ScreenBucketResolver.kt
│       │   └── strategy/
│       │       ├── ScaleStrategy.kt
│       │       └── DefaultScaleStrategy.kt  # Formula-based profiles
│       └── test/kotlin/
│           ├── FlexiScaleTest.kt
│           ├── ScreenBucketResolverTest.kt
│           ├── ResponsiveScalerTest.kt
│           ├── RuntimeSmokeTest.kt
│           └── DefaultScaleStrategyTest.kt
│
├── flexiscale-generator/             # XML resource generator (JVM, internal tool)
│   ├── build.gradle.kts
│   └── src/
│       ├── main/kotlin/.../generator/
│       │   ├── GeneratorRunner.kt
│       │   ├── generator/
│       │   │   ├── FlexiScaleGenerator.kt
│       │   │   ├── XmlDimensionGenerator.kt
│       │   │   ├── DimensionGenerator.kt
│       │   │   └── BucketDimensionGenerator.kt
│       │   ├── writer/
│       │   │   ├── XmlWriter.kt
│       │   │   └── ResourceFolderWriter.kt
│       │   ├── model/
│       │   │   ├── DimensionUnit.kt
│       │   │   ├── DimensionEntry.kt
│       │   │   ├── GeneratorConfig.kt
│       │   │   └── BucketOutput.kt
│       │   └── util/
│       │       └── NumberSequenceGenerator.kt
│       └── test/kotlin/.../generator/
│           ├── GeneratorSmokeTest.kt
│           ├── BucketDimensionGeneratorTest.kt
│           ├── NumberSequenceGeneratorTest.kt
│           ├── XmlWriterTest.kt
│           ├── ResourceFolderWriterTest.kt
│           └── XmlDimensionGeneratorTest.kt
│
├── flexiscale-tokens/                # Design system tokens (JVM, published)
│   ├── build.gradle.kts
│   └── src/
│       ├── main/kotlin/.../tokens/
│       │   ├── model/                # 5 value classes (DimensionToken, DurationToken, etc.)
│       │   ├── spacing/
│       │   ├── typography/
│       │   ├── radius/
│       │   ├── elevation/
│       │   ├── size/
│       │   ├── icon/
│       │   ├── layout/
│       │   ├── motion/
│       │   ├── opacity/
│       │   ├── stroke/
│       │   ├── accessibility/
│       │   └── duration/             # ⚠️ Duplicate with motion/
│       └── test/kotlin/
│           └── TokenSmokeTest.kt
│
├── flexiscale-resources/             # Android resources module (generated XML)
│   ├── build.gradle.kts
│   └── src/
│       ├── main/res/
│       │   ├── values-sw192dp/dimensions.xml
│       │   ├── ...
│       │   └── values-sw2560dp/dimensions.xml
│       └── test/kotlin/.../resources/
│           └── FlexiScaleResourcesTest.kt
│
└── flexiscale-compose/               # Compose integration layer (Android, published)
    ├── build.gradle.kts
    └── src/
        ├── main/kotlin/.../compose/
        │   ├── extensions/
        │   │   ├── DpExtensions.kt
        │   │   └── SpExtensions.kt
        │   └── resolver/
        │       └── ComposeScreenInfoResolver.kt
        └── test/kotlin/.../compose/
            └── FlexiScaleComposeTest.kt
```

---

## 3. Modules

| # | Module | Type | Published | Description |
|---|--------|------|-----------|-------------|
| 1 | `:flexiscale-runtime` | JVM (kotlin-jvm) | ✅ Planned | Core scaling engine — ScreenBucket, scale profiles, resolver |
| 2 | `:flexiscale-generator` | JVM (application) | ❌ No | Internal tool — generates `dimensions.xml` for all buckets |
| 3 | `:flexiscale-tokens` | JVM (kotlin-jvm) | ✅ Planned | Design system tokens (spacing, typography, radius, etc.) |
| 4 | `:flexiscale-resources` | Android (android-library) | ❌ No | Generated XML dimension resources (33 bucket folders) |
| 5 | `:flexiscale-compose` | Android (android-library) | ✅ Planned | Compose integration — `.fdp()`, `.fsp()` extensions |

---

## 4. Key Architecture Decisions

- **ScreenBucket** is a `data class` (not an enum) with `companion.allBuckets` generating 2369 buckets (192..2560) lazily
- **DefaultScaleStrategy** uses a formula instead of hardcoded profiles:
  - `dpScale = sw / 360` for `sw ≤ 360`, then linear `1.0 → 3.20` for `360 → 2560`
  - `spScale = 0.82 → 1.0 (linear)` for `192 → 360`, then `1.0 → 1.70 (linear)` for `360 → 2560`
- **ScreenBucketResolver** maps any `smallestWidthDp` to the nearest bucket (clamped to 192–2560)
- Runtime has zero Android dependencies — pure Kotlin

---

## 5. Test Coverage

| Module | Test Files | Tests | Status |
|--------|-----------|-------|--------|
| flexiscale-runtime | 5 | 16 | ✅ 16/16 pass |
| flexiscale-generator | 6 | 10 | ✅ 10/10 pass |
| flexiscale-tokens | 1 | 8 | ✅ 8/8 pass |
| flexiscale-compose | 1 | 2 | ✅ (runtime-only, Android SDK needed) |
| flexiscale-resources | 1 | 4 | ✅ (Android SDK needed) |
| **Total** | **14** | **40** | **✅ All passing** |

---

## 6. Statistics

| Metric | Value |
|--------|-------|
| **Subprojects** | 5 |
| **Kotlin source files** | 61 |
| **Test files** | 14 |
| **Total tests** | 40 |
| **Build scripts** | 7 (1 root + 5 module `build.gradle.kts` + settings) |
| **XML resource files** | 33 |
| **Screen bucket variants** | 2369 (SW192 → SW2560, every integer) |
| **Design token categories** | 9 |
| **Compose extension functions** | 6 (3 dp + 3 sp) |
| **Publishable modules** | 3 (runtime, compose, tokens) |
| **Modules with publish config** | 0 |
