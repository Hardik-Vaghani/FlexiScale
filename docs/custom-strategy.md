# Custom Scaling Strategies

FlexiScale supports pluggable scaling strategies via the `ScaleStrategy` interface.

## Interface

```kotlin
interface ScaleStrategy {
    fun dpScale(bucket: ScreenBucket): Double
    fun spScale(bucket: ScreenBucket): Double
}
```

- `dpScale` — Returns the multiplier for density-independent pixels
- `spScale` — Returns the multiplier for scale-independent pixels

## Default Strategy

The default implementation uses linear interpolation:

```kotlin
class DefaultScaleStrategy : ScaleStrategy {
    override fun dpScale(bucket: ScreenBucket): Double = when {
        bucket.minWidthDp <= 360 -> bucket.minWidthDp / 360.0
        else -> 1.0 + (bucket.minWidthDp - 360) * (2.20 / 2200)
    }

    override fun spScale(bucket: ScreenBucket): Double = when {
        bucket.minWidthDp <= 360 -> 0.82 + (bucket.minWidthDp - 192) * (0.18 / 168)
        else -> 1.0 + (bucket.minWidthDp - 360) * (0.70 / 2200)
    }
}
```

## Custom Strategy Example

```kotlin
class AggressiveScaleStrategy : ScaleStrategy {
    override fun dpScale(bucket: ScreenBucket): Double =
        1.0 + (bucket.minWidthDp - 360) * 0.002

    override fun spScale(bucket: ScreenBucket): Double =
        1.0 + (bucket.minWidthDp - 360) * 0.001
}
```

## Using a Custom Strategy

```kotlin
val scaler = ResponsiveScaler(strategy = AggressiveScaleStrategy())
val scaled = scaler.dp(bucket = ScreenBucketResolver.resolve(411), value = 16.0)
```
