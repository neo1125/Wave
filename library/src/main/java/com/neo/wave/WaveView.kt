package com.neo.wave

import android.view.MotionEvent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.platform.LocalDensity

@ExperimentalComposeUiApi
@Composable
fun WaveView(
    modifier: Modifier = Modifier,
    waveColor: Color = Color.Red,
    waveSpeed: WaveSpeed = WaveSpeed.NORMAL,
    progress: Float = 0f,
    isDebugMode: Boolean = false,
    onProgressUpdated: (progress: Float) -> Unit = {}
) {
    val density = LocalDensity.current
    var waveProgress by remember { mutableStateOf(progress) }
    var viewHeight by remember { mutableStateOf(0f)}
    var waveCanvas by remember { mutableStateOf<WaveCanvas?>(null)}
    val frameState = StepFrame(callbackTime = waveSpeed.time) {
        waveCanvas?.update()
    }

    LaunchedEffect(progress) {
        waveProgress = progress
    }

    Box(
        modifier = modifier
    ) {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .pointerInteropFilter {
                    when (it.action) {
                        MotionEvent.ACTION_MOVE -> {
                            waveProgress = (viewHeight - it.y) / viewHeight
                            onProgressUpdated(waveProgress)
                        }
                        MotionEvent.ACTION_UP -> {
                            waveProgress = (viewHeight - it.y) / viewHeight
                            onProgressUpdated(waveProgress)
                        }
                    }
                    true
                }
            ,
            contentAlignment = Alignment.Center
        ) {

            val viewWidth: Float = with(density) { maxWidth.toPx() }
            viewHeight = with(density) { maxHeight.toPx() }

            waveCanvas = WaveCanvas(
                waveCount = 1,
                waveColors = listOf(waveColor),
                progress = waveProgress,
                isDebug = isDebugMode
            ).apply {
                setup(width = viewWidth, height = viewHeight)
            }

            waveCanvas?.Render(
                modifier = Modifier.fillMaxSize(),
                frameState = frameState
            )
        }

//        Ruler(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(Color.Transparent)
//        )
    }
}

@Composable
fun Ruler(
    modifier: Modifier = Modifier,
    strokeWidth: Float = 2f,
    color: Color = Color.LightGray.copy(0.3f)
) {
    Canvas(
        modifier = modifier
    ) {
        val spacing = size.height / 9
        for (i in 0 until 10) {
            drawLine(
                color = color,
                start = Offset(0f, spacing * i - (strokeWidth / 2)),
                end = Offset(size.width, spacing * i - (strokeWidth / 2)),
                strokeWidth = strokeWidth
            )
        }
    }
}