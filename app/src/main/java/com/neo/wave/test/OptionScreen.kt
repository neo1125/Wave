package com.neo.wave.test

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Slider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.neo.wave.WaveSpeed
import com.neo.wave.WaveView
import com.neo.wave.test.component.CheckBoxText
import com.neo.wave.test.component.OptionControl

@ExperimentalComposeUiApi
@Composable
fun OptionScreen(
    modifier: Modifier = Modifier
) {

    val colors: List<Color> = listOf(
        Color.Red.copy(0.5f),
        Color.Blue.copy(0.5f),
        Color.Green.copy(0.5f)
    )
    var progress by remember { mutableStateOf(0f) }
    var waveColor by remember { mutableStateOf(colors.first()) }
    var wavePointCount by remember { mutableStateOf(5f) }
    var waveSpeed by remember { mutableStateOf(WaveSpeed.NORMAL) }
    var isDrag by remember { mutableStateOf(true) }
    var isDebugMode by remember { mutableStateOf(false) }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        WaveView(
            modifier = Modifier
                .size(width = 300.dp, height = 450.dp)
                .padding(vertical = 30.dp)
                .border(width = 2.dp, color = Color.LightGray),
            //wavePointCount = wavePointCount.toInt(),
            waveColor = waveColor,
            waveSpeed = waveSpeed,
            progress = progress,
            dragEnabled = isDrag,
            isDebugMode = isDebugMode,
            onProgressUpdated = {
                progress = it
            }
        )

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            OptionControl(text = "Wave Progress") {
                Slider(
                    modifier = Modifier.padding(horizontal = 10.dp),
                    value = progress,
                    onValueChange = {
                        progress = it
                    }
                )
            }

            OptionControl(text = "Wave Color") {
                CheckBoxText(
                    text = "Red",
                    checked = waveColor == colors.first(),
                    onCheckedChange = {
                        waveColor = colors.first()
                    }
                )

                CheckBoxText(
                    text = "Blue",
                    checked = waveColor == colors[1],
                    onCheckedChange = {
                        waveColor = colors[1]
                    }
                )

                CheckBoxText(
                    text = "Green",
                    checked = waveColor == colors.last(),
                    onCheckedChange = {
                        waveColor = colors.last()
                    }
                )
            }

            OptionControl(text = "Wave Point Count") {
                Slider(
                    modifier = Modifier.padding(horizontal = 10.dp),
                    value = wavePointCount,
                    steps = 9,
                    onValueChange = {
                        wavePointCount = it
                    }
                )
            }

            OptionControl(text = "Wave Speed") {
                CheckBoxText(
                    text = "Slow",
                    checked = waveSpeed == WaveSpeed.SLOW,
                    onCheckedChange = {
                        waveSpeed = WaveSpeed.SLOW
                    }
                )

                CheckBoxText(
                    text = "Normal",
                    checked = waveSpeed == WaveSpeed.NORMAL,
                    onCheckedChange = {
                        waveSpeed = WaveSpeed.NORMAL
                    }
                )

                CheckBoxText(
                    text = "Fast",
                    checked = waveSpeed == WaveSpeed.FAST,
                    onCheckedChange = {
                        waveSpeed = WaveSpeed.FAST
                    }
                )
            }

            OptionControl(text = "Drag") {
                CheckBoxText(
                    text = "Drag Enabled",
                    checked = isDrag,
                    onCheckedChange = {
                        isDrag = it
                    }
                )
            }

            OptionControl(text = "Debug Mode") {
                CheckBoxText(
                    text = "Debug Mode Enabled",
                    checked = isDebugMode,
                    onCheckedChange = {
                        isDebugMode = it
                    }
                )
            }
        }
    }
}