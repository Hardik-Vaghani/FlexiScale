# Migration Guide

## From sdp/ssp to FlexiScale

### Dependency

**Before:**
```kotlin
implementation("com.intuit.sdp:sdp-android:1.1.0")
implementation("com.intuit.ssp:ssp-android:1.1.0")
```

**After:**
```kotlin
implementation("io.github.hardikvaghani.flexiscale:flexiscale-resources:0.1.0")
implementation("io.github.hardikvaghani.flexiscale:flexiscale-compose:0.1.0")
```

### Layout Files

**Before:**
```xml
android:textSize="@dimen/_16ssp"
android:padding="@dimen/_16sdp"
```

**After:**
```xml
android:textSize="@dimen/flexi_sp_16"
android:padding="@dimen/flexi_dp_16"
```

### Compose

**Before:**
```kotlin
// Manual scaling or custom solution
```

**After:**
```kotlin
import io.github.hardikvaghani.flexiscale.compose.extensions.fdp
import io.github.hardikvaghani.flexiscale.compose.extensions.fsp

Text(fontSize = 16.fsp(), modifier = Modifier.padding(16.fdp()))
```
