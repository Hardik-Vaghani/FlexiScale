# Jetpack Compose Integration

FlexiScale provides composable extension functions for easy responsive scaling in Jetpack Compose.

## Setup

Ensure the `flexiscale-compose` dependency is added:

```kotlin
dependencies {
    implementation("io.github.hardikvaghani.flexiscale:flexiscale-compose:0.1.0")
}
```

## Extension Functions

### `.fdp()` — Responsive Density-Independent Pixels

Available on `Int`, `Float`, and `Double`:

```kotlin
import io.github.hardikvaghani.flexiscale.compose.extensions.fdp

@Composable
fun ResponsiveLayout() {
    Column(
        modifier = Modifier
            .padding(16.fdp())     // Int → Dp
            .width(200.0.fdp())    // Double → Dp
            .height(150.5f.fdp())  // Float → Dp
    ) { ... }
}
```

### `.fsp()` — Responsive Scale-Independent Pixels

Available on `Int`, `Float`, and `Double`:

```kotlin
import io.github.hardikvaghani.flexiscale.compose.extensions.fsp

@Composable
fun ResponsiveText() {
    Text(
        text = "Hello",
        fontSize = 16.fsp()      // Scales with screen width
    )
}
```

## Full Example

```kotlin
@Composable
fun ProfileCard(name: String, bio: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.fdp())
    ) {
        Column(
            modifier = Modifier.padding(24.fdp())
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.headlineMedium,
                fontSize = 24.fsp()
            )
            Spacer(modifier = Modifier.height(8.fdp()))
            Text(
                text = bio,
                fontSize = 14.fsp()
            )
        }
    }
}
```

## How It Works

The extension functions:
1. Resolve the device's `smallestWidthDp` via `ComposeScreenInfoResolver`
2. Find the matching `ScreenBucket`
3. Apply the `DefaultScaleStrategy` to compute the scaled value
4. Return the result as a `Dp` value
