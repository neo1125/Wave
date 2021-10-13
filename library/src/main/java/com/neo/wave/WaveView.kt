package com.neo.wave

import android.view.MotionEvent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.platform.LocalDensity
import kotlin.math.max
import kotlin.math.min

@ExperimentalComposeUiApi
@Composable
fun WaveView(
    modifier: Modifier = Modifier,
    waveColor: Color = Color.Red,
    wavePointCount: Int = 5,
    waveSpeed: WaveSpeed = WaveSpeed.NORMAL,
    progress: Float = 0f,
    dragEnabled: Boolean = true,
    isDebugMode: Boolean = false,
    onProgressUpdated: (progress: Float) -> Unit = {}
) {
    val density = LocalDensity.current
    var waveProgress by remember { mutableStateOf(calculateProgress(progress = progress)) }
    var viewHeight by remember { mutableStateOf(0f)}
    var waveCanvas by remember { mutableStateOf<WaveCanvas?>(null)}

    LaunchedEffect(progress) {
        waveProgress = calculateProgress(progress = progress)
    }

    Box(
        modifier = modifier
    ) {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .clipToBounds()
                .pointerInteropFilter {
                    if (dragEnabled) {
                        when (it.action) {
                            MotionEvent.ACTION_MOVE, MotionEvent.ACTION_UP -> {
                                waveProgress = calculateProgress(progress = (viewHeight - it.y) / viewHeight)
                                onProgressUpdated(waveProgress)
                            }
                        }
                    }
                    true
                },
            contentAlignment = Alignment.Center
        ) {

            val viewWidth: Float = with(density) { maxWidth.toPx() }
            viewHeight = with(density) { maxHeight.toPx() }

            waveCanvas = WaveCanvas(
                waveCount = 1,
                waveColors = listOf(waveColor),
                wavePointCount = wavePointCount,
                waveSpeed = waveSpeed,
                isDebug = isDebugMode
            ).apply {
                setup(width = viewWidth, height = viewHeight)
            }

            waveCanvas?.Render(
                modifier = Modifier.fillMaxSize().offset(y = maxHeight * (1f - waveProgress)),
            )
        }
    }
}

fun calculateProgress(minProgress: Float = 0.03f, maxProgress: Float = 0.99f, progress: Float): Float {
    return min(maxProgress, max(minProgress, progress))
}

