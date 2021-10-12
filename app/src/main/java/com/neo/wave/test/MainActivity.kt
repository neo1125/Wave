package com.neo.wave.test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.neo.wave.WaveView
import com.neo.wave.test.ui.theme.WaveTheme

class MainActivity : ComponentActivity() {

    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WaveTheme {

                var progress by remember { mutableStateOf(0.5f) }

                Surface(color = MaterialTheme.colors.background) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        WaveView(
                            modifier = Modifier
                                .size(300.dp)
                                .background(Color.White)
                                .clip(RoundedCornerShape(50)),
                            waveColor = MaterialTheme.colors.primary.copy(0.4f),
                            progress = progress,
                            onProgressUpdated = {
                                progress = it
                            }
                        )
                    }
                    Row {
                        Button(onClick = {
                            val updateValue = progress + 0.1f
                            if (updateValue < 1.0f) {
                                progress = updateValue
                            }
                        }) {
                            Text("increace")
                        }
                        Button(onClick = {
                            val updateValue = progress - 0.1f
                            if (updateValue > 0f) {
                                progress = updateValue
                            }
                        }) {
                            Text("deincreace")
                        }
                    }
                }
            }
        }
    }
}