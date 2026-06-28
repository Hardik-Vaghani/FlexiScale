# XML / View System Integration

FlexiScale provides pre-generated `@dimen/` resources for all 2369 screen width buckets, usable directly in XML layouts.

## Setup

Add the `flexiscale-resources` dependency:

```kotlin
dependencies {
    implementation("io.github.hardikvaghani.flexiscale:flexiscale-resources:0.1.0")
}
```

## Usage

### Dimension References

```xml
<TextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:textSize="@dimen/flexi_sp_16"
    android:padding="@dimen/flexi_dp_16"
    android:layout_margin="@dimen/flexi_dp_8" />
```

### Available Resources

All resources follow the naming pattern:
- `@dimen/flexi_dp_{value}` — dp values (e.g., `flexi_dp_16`, `flexi_dp_24`)
- `@dimen/flexi_sp_{value}` — sp values (e.g., `flexi_sp_14`, `flexi_sp_18`)

Resources are generated for values from `0.5` to `500.0` with a step of `0.5`.

### How It Works

When Android renders your layout, it uses the resource qualifier system to pick the correct `dimensions.xml` file based on the device's `smallestWidthDp`. Each file contains pre-scaled dimension values, eliminating runtime calculation overhead.
