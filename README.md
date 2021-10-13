# Wave View
Wave Progress View for android compose

## Sample

## Download
```kotlin

```

## Quick Start
```kotlin
var progress by remember { mutableStateOf(0.5f) }

WaveView(
    modifier = Modifier
        .size(150.dp)
        .clip(RoundedCornerShape(50)),
    waveColor = MaterialTheme.colors.primary.copy(0.4f),
    wavePointCount = 10,
    waveSpeed = WaveSpeed.FAST,
    progress = progress,
    dragEnabled = true,
    isDebugMode = false,
    onProgressUpdated = {
        progress = it
    }
)
```