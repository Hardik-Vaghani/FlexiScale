# FlexiScale

A multi-module Kotlin JVM project built with Gradle.

## Project Structure

```
FlexiScale/
├── build.gradle.kts                  # Root build: centralizes Kotlin version + common settings
├── settings.gradle.kts               # Project name, repository config, module declarations
├── gradle.properties                 # Build cache & configuration cache
├── gradle/
│   ├── libs.versions.toml            # Version catalog (Kotlin, kotlinx libraries)
│   └── wrapper/
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── flexiscale-runtime/               # Core runtime module
├── flexiscale-compose/               # Composition module
├── flexiscale-generator/             # Code generation module
└── flexiscale-tokens/                # Token management module
```

## Modules

| Module | Path | Description |
|--------|------|-------------|
| `:flexiscale-runtime` | `flexiscale-runtime/` | Core runtime |
| `:flexiscale-compose` | `flexiscale-compose/` | Composition layer |
| `:flexiscale-generator` | `flexiscale-generator/` | Code generation |
| `:flexiscale-tokens` | `flexiscale-tokens/` | Token management |

## Prerequisites

- JDK 21+ (automatically resolved via the Foojay Toolchains plugin if not installed locally)
- No manual Gradle installation needed — use the Gradle Wrapper (`./gradlew`)

## Build & Test

```bash
./gradlew build      # Compile and run all checks (including tests)
./gradlew check      # Run all verification tasks
./gradlew test       # Run unit tests
./gradlew clean      # Clean all build outputs
```

> **Note:** The project uses the Gradle Wrapper (`./gradlew`), which automatically downloads the correct Gradle version. This is the recommended approach for production projects.
> [Learn more about the Gradle Wrapper](https://docs.gradle.org/current/userguide/gradle_wrapper.html).

## Tech Stack

- **Language:** Kotlin 2.2.0
- **JDK:** 21
- **Build System:** Gradle 8.14 (via Wrapper)
- **Testing:** JUnit Platform
- **Available libraries (via version catalog):**
  - [kotlinx-datetime](https://github.com/Kotlin/kotlinx-datetime)
  - [kotlinx-serialization-json](https://github.com/Kotlin/kotlinx-serialization)
  - [kotlinx-coroutines](https://github.com/Kotlin/kotlinx-coroutines)

## Configuration

- **Version catalog:** `gradle/libs.versions.toml` — centralizes all dependency versions
- **Build cache:** Enabled in `gradle.properties` for faster rebuilds
- **Configuration cache:** Enabled in `gradle.properties` (reuses build configuration across runs)
