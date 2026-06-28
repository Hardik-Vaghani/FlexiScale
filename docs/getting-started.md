# Getting Started with FlexiScale

## Prerequisites

- Android Studio Hedgehog or later
- JDK 21+
- Android SDK (API 21+)
- Gradle 8.14+ (via wrapper)

## Installation

### Step 1: Add the dependency

```kotlin
// build.gradle.kts (module-level)
dependencies {
    implementation("io.github.hardikvaghani.flexiscale:flexiscale-runtime:0.1.0")

    // Optional — Compose support
    implementation("io.github.hardikvaghani.flexiscale:flexiscale-compose:0.1.0")

    // Optional — Pre-generated XML resources
    implementation("io.github.hardikvaghani.flexiscale:flexiscale-resources:0.1.0")

    // Optional — Design tokens
    implementation("io.github.hardikvaghani.flexiscale:flexiscale-tokens:0.1.0")
}
```

### Step 2: Start scaling

**Compose:**
```kotlin
import io.github.hardikvaghani.flexiscale.compose.extensions.fdp
import io.github.hardikvaghani.flexiscale.compose.extensions.fsp

@Composable
fun MyScreen() {
    Text(
        text = "Responsive Text",
        fontSize = 18.fsp(),
        modifier = Modifier.padding(24.fdp())
    )
}
```

**XML:**
```xml
<TextView
    android:textSize="@dimen/flexi_sp_16"
    android:padding="@dimen/flexi_dp_16" />
```

## Next Steps

- [Compose Guide](compose.md) — Detailed Compose usage
- [XML Guide](xml.md) — XML/View system integration
- [Custom Strategy](custom-strategy.md) — Implementing custom scaling logic
- [Architecture](architecture.md) — Project architecture overview
