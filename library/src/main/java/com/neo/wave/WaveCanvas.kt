package com.neo.wave

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

data class WaveCanvas(
    val waveCount: Int = 1,
    val wavePointCount: Int = 5,
    val waveSpeed: WaveSpeed,
    val waveColors: List<Color> = listOf(Color.Red),
    val isDebug: Boolean = false
) {

    private val waves: MutableList<Wave> = mutableListOf()

    fun setup(width: Float, height: Float) {
        for (i in 0 until waveCount) {
            waves.add(Wave(pointCount = wavePointCount, color = waveColors[i], speed = waveSpeed, canvasWidth = width, canvasHeight = height))
        }
    }

    private fun update() {
        waves.forEach { it.update() }
    }

    @Composable
    fun Render(
        modifier: Modifier = Modifier,
    ) {

        val frameState = StepFrame {
            update()
        }

        Canvas(
            modifier = modifier
        ) {
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