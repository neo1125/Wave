# Wave View
Wave Progress View for android compose

## Sample
<img src="sample.gif" width="50%" height="50%">

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



## Reference
- https://github.com/ch8n/Compose-boids-flocking/blob/main/src/main/kotlin/main.kt
- https://www.youtube.com/watch?v=LLfhY4eVwDY