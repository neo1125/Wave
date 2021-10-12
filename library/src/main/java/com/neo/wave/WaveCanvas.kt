package com.neo.wave

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.withTransform
import kotlin.math.min
import kotlin.math.max

data class WaveCanvas(
    val waveCount: Int = 1,
    val waveColors: List<Color> = listOf(Color.Red),
    val progress: Float,
    val isDebug: Boolean = false
) {

    private val waves: MutableList<Wave> = mutableListOf()

    fun setup(width: Float, height: Float) {
        for (i in 0 until waveCount) {
            waves.add(Wave(color = waveColors[i], canvasWidth = width, canvasHeight = height))
        }
    }

    fun update() {
        waves.forEach { it.update() }
    }

    @Composable
    fun Render(
        modifier: Modifier = Modifier,
        frameState: State<Long>
    ) {
        Canvas(
            modifier = modifier
        ) {
            val maxOffsetY: Float = size.height * (1f - 0.99f)
            val minOffsetY: Float = size.height * (1f - 0.03f)
            val top = max(maxOffsetY, min(minOffsetY, (size.height * (1f - progress))))
            withTransform({
                translate(left = 0f, top = top)
            }) {
                drawIntoCanvas {
                    it.save()
                    frameState.value
                    waves.forEach { wave ->
                        drawWave(wave = wave)

                        if (isDebug) {
                            drawWaveDebug(wave)
                        }
                    }
                }

            }

        }
    }
}