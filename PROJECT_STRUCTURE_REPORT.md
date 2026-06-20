# FlexiScale — Project Structure Report

> **Generated:** June 20, 2026  
> **Project Name:** FlexiScale  
> **Root:** `flexiscale`  
> **Group ID:** `io.github.hardikvaghani.flexiscale`  
> **JDK:** 21 (Kotlin JVM)  
> **Kotlin Version:** 2.2.0  
> **Gradle Version:** 8.14  
> **Build System:** Gradle (Wrapper)

---

## 1. Project Overview

FlexiScale is a **Kotlin multi-module JVM project** built with Gradle. It is organized into **4 subprojects** (packages) plus root-level build configuration. No Kotlin source files (`.kt`) have been created yet — the project is in its initial scaffolding phase with only `src/main/` and `src/test/` directories created as empty placeholders under each module.

---

## 2. Directory Tree

```
flexiscale/
│
├── .gitignore
├── .gradle/                          # (gitignored) Gradle build cache
├── .idea/                            # IntelliJ IDEA project files
│   ├── .gitignore
│   ├── .name                         # "FlexiScale"
│   ├── dictionaries/
│   │   └── project.xml
│   ├── gradle.xml
│   ├── kotlinc.xml
│   ├── misc.xml
│   ├── vcs.xml
│   └── workspace.xml
│
├── README.md
├── build.gradle.kts                  # *** NEW *** Root build file (centralizes plugin version + group/version)
├── build/                            # (gitignored) Root build output
├── gradle.properties
├── gradle/
│   ├── libs.versions.toml            # Version catalog
│   └── wrapper/
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
│
├── gradlew                           # Unix Gradle wrapper script
├── gradlew.bat                       # Windows Gradle wrapper script
├── settings.gradle.kts               # Root settings / multi-module config
│
├── flexiscale-compose/
│   ├── build.gradle.kts
│   └── src/
│       ├── main/                     # (empty, scaffolded)
│       └── test/                     # (empty, scaffolded)
│
├── flexiscale-generator/
│   ├── build.gradle.kts
│   └── src/
│       ├── main/                     # (empty, scaffolded)
│       └── test/                     # (empty, scaffolded)
│
├── flexiscale-runtime/
│   ├── build.gradle.kts
│   └── src/
│       ├── main/                     # (empty, scaffolded)
│       └── test/                     # (empty, scaffolded)
│
└── flexiscale-tokens/
    ├── build.gradle.kts
    └── src/
        ├── main/                     # (empty, scaffolded)
        └── test/                     # (empty, scaffolded)
```

---

## 3. Packages (Subprojects)

| # | Module | Full Path | Group | Version |
|---|--------|-----------|-------|---------|
| 1 | `:flexiscale-runtime` | `flexiscale-runtime/` | `io.github.hardikvaghani.flexiscale` | unspecified |
| 2 | `:flexiscale-compose` | `flexiscale-compose/` | `io.github.hardikvaghani.flexiscale` | unspecified |
| 3 | `:flexiscale-generator` | `flexiscale-generator/` | `io.github.hardikvaghani.flexiscale` | unspecified |
| 4 | `:flexiscale-tokens` | `flexiscale-tokens/` | `io.github.hardikvaghani.flexiscale` | unspecified |

All 4 modules share an identical `build.gradle.kts` configuration (see Section 4.4).

---

## 4. File Inventory

### 4.1 Root Build Configuration

| File | Purpose |
|------|---------|
| `settings.gradle.kts` | **Entry point.** Defines the project name (`FlexiScale`), configures dependency resolution (Maven Central + Google repos), applies the Foojay Toolchains plugin (JDK auto-download), and includes all 4 subprojects. |
| `build.gradle.kts` | **Root build file (newly created).** Declares `kotlin("jvm") version "2.2.0" apply false` to centralize the plugin version. Sets `group` and `version` for all subprojects via `subprojects { }`. |
| `gradle.properties` | Enables **build cache** and **configuration cache** for faster incremental builds. |
| `gradle/libs.versions.toml` | **Version catalog.** Centralizes dependency versions (Kotlin 2.2.0, kotlinx-datetime 0.6.2, kotlinx-serialization-json 1.8.1, kotlinx-coroutines 1.10.2). |
| `.gitignore` | Ignores `.gradle/`, `build/`, `.idea/` entries, IDE and OS specific files. |

### 4.2 Gradle Wrapper

| File | Purpose |
|------|---------|
| `gradlew` | **Unix/macOS** shell script — downloads and runs Gradle 8.14 automatically. |
| `gradlew.bat` | **Windows** batch script — same purpose as `gradlew`. |
| `gradle/wrapper/gradle-wrapper.jar` | Bootstrap JAR that downloads the specified Gradle distribution. |
| `gradle/wrapper/gradle-wrapper.properties` | Configures the wrapper to download `gradle-8.14-bin.zip` from services.gradle.org. |

### 4.3 IntelliJ IDEA Configuration (`.idea/`)

| File | Purpose |
|------|---------|
| `.name` | Stores the project display name: `FlexiScale` |
| `gradle.xml` | Links IntelliJ to the Gradle project |
| `kotlinc.xml` | Configures Kotlin plugin version (`2.2.0`) |
| `misc.xml` | JDK toolchain config (JDK 21, temurin-21) |
| `vcs.xml` | Git VCS root mapping |
| `dictionaries/project.xml` | Custom dictionary with project-specific words (`flexi`, `foojay`) |
| `.gitignore` | Ignores `shelf/` and `workspace.xml` |
| `workspace.xml` | IntelliJ workspace state (auto-generated) |

### 4.4 Module Build Files (all 4 identical)

Each `build.gradle.kts` now uses the Kotlin plugin version declared in the root (no version needed locally), and `group`/`version` are inherited from the root's `subprojects { }` block:

```kotlin
plugins {
    kotlin("jvm")
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}
```

| Module | File |
|--------|------|
| `flexiscale-runtime` | `flexiscale-runtime/build.gradle.kts` |
| `flexiscale-compose` | `flexiscale-compose/build.gradle.kts` |
| `flexiscale-generator` | `flexiscale-generator/build.gradle.kts` |
| `flexiscale-tokens` | `flexiscale-tokens/build.gradle.kts` |

### 4.5 Other Root Files

| File | Purpose |
|------|---------|
| `README.md` | Project documentation — describes the actual project structure, modules, build commands, and tech stack. Updated to reflect real 4-subproject setup. |

### 4.6 Source Directories (placeholder — empty)

Every module has:

- `src/main/` — empty (no Kotlin source files yet)
- `src/test/` — empty (no test files yet)

---

## 5. Dependencies (Version Catalog)

### Libraries

| Alias | Maven Coordinates | Version |
|-------|-------------------|---------|
| `kotlinGradlePlugin` | `org.jetbrains.kotlin:kotlin-gradle-plugin` | 2.2.0 |
| `kotlinxDatetime` | `org.jetbrains.kotlinx:kotlinx-datetime` | 0.6.2 |
| `kotlinxSerialization` | `org.jetbrains.kotlinx:kotlinx-serialization-json` | 1.8.1 |
| `kotlinxCoroutines` | `org.jetbrains.kotlinx:kotlinx-coroutines-core` | 1.10.2 |

### Bundles

| Bundle Name | Includes |
|-------------|----------|
| `kotlinxEcosystem` | `kotlinxDatetime`, `kotlinxSerialization`, `kotlinxCoroutines` |

### Plugins

| Plugin ID | Alias | Version |
|-----------|-------|---------|
| `org.jetbrains.kotlin.plugin.serialization` | `kotlinPluginSerialization` | 2.2.0 |

---

## 6. Summary Statistics

| Metric | Count |
|--------|-------|
| **Subprojects (packages)** | 4 |
| **Source files (Kotlin)** | 0 |
| **Build files (`.gradle.kts`)** | 6 (root `settings.gradle.kts` + root `build.gradle.kts` + 4 module `build.gradle.kts`) |
| **Config files** | 16 total (see full inventory) |
| **Shell scripts** | 2 (`gradlew`, `gradlew.bat`) |
| **JAR files** | 1 (`gradle-wrapper.jar`) |

---

## 7. Notes

- **No Kotlin source code** has been written yet — all `src/main/` and `src/test/` directories are empty.
- The project uses **JUnit Platform** (`useJUnitPlatform()`) for testing, though no tests exist.
- The `README.md` was rewritten to remove old template references (`app`, `utils`, `buildSrc`) and now accurately describes the 4-module structure and root `build.gradle.kts`.
- The `compiler.xml` is listed by `.idea/` but wasn't found on disk — it may be created by IntelliJ on first open.
- The `gradle-wrapper.jar` is stored in Git (explicitly unignored via `.gitignore` pattern `!gradle/wrapper/gradle-wrapper.jar`).
