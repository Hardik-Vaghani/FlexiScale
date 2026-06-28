# Architecture

## Multi-Module Design

FlexiScale is organized into 5 modules:

```
┌─────────────────────────────────────────────┐
│            flexiscale-compose                │
│    Compose extensions (.fdp, .fsp)          │
│    Depends on: runtime, resources            │
├─────────────────────────────────────────────┤
│            flexiscale-resources              │
│    Pre-generated XML dimension resources     │
│    2369 screen buckets, 4K entries each      │
├───────────────────────┬─────────────────────┤
│   flexiscale-runtime  │  flexiscale-tokens   │
│   Core scaling engine │  Design system       │
│   ─────────────────── │  ─────────────────── │
│   ScreenBucket        │  SpacingTokens       │
│   ScaleStrategy       │  TypographyTokens    │
│   ResponsiveScaler    │  RadiusTokens        │
│   ScreenBucketResolver│  ElevationTokens     │
│   ScreenInfo          │  SizeTokens          │
│   FlexiScale (entry)  │  IconTokens          │
│                       │  MotionTokens        │
│                       │  OpacityTokens       │
│                       │  StrokeTokens        │
│                       │  AccessibilityTokens │
└───────────────────────┴─────────────────────┘
```

## Data Flow

```
Screen Width (dp)
       ↓
ScreenBucketResolver.resolve(width)
       ↓
ScreenBucket (minWidthDp, description)
       ↓
ResponsiveScaler.dp(bucket, value)
       ↓
ScaleStrategy.dpScale(bucket)
       ↓
value * scale = scaled value
```

## Module Responsibilities

### flexiscale-runtime
Core library with no Android dependencies. Contains the scaling engine, strategies, models, and resolvers.

### flexiscale-tokens
Design system constants. Pure Kotlin/JVM module with no Android dependencies.

### flexiscale-resources
Generated Android resource module. Contains `dimensions.xml` files for each screen bucket.

### flexiscale-compose
Android Compose module providing composable extension functions. Depends on runtime and resources.

### flexiscale-generator
Internal CLI tool that generates the XML dimension resources. Not published.
