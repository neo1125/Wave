package com.neo.wave.test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.neo.wave.WaveSpeed
import com.neo.wave.WaveView
import com.neo.wave.test.ui.theme.WaveTheme

class MainActivity : ComponentActivity() {

    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WaveTheme {

                var progress1 by remember { mutableStateOf(0.5f) }
                var progress2 by remember { mutableStateOf(0.5f) }

                Surface(color = MaterialTheme.colors.background) {
//                    Box(
//                        modifier = Modifier.fillMaxSize(),
//                        contentAlignment = Alignment.Center
//                    ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            modifier = Modifier.wrapContentSize(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Column {
                                WaveView(
                                    modifier = Modifier
                                        .size(150.dp)
                                        .clip(RoundedCornerShape(50)),
                                    waveColor = MaterialTheme.colors.primary.copy(0.4f),
                                    wavePointCount = 10,
                                    waveSpeed = WaveSpeed.FAST,
                                    progress = progress1,
                                    onProgressUpdated = {
                                        progress1 = it
                                    }
                                )
                                Description("Speed: FAST, Point: 10")
                            }

                            Column {
                                WaveView(
                                    modifier = Modifier
                                        .size(150.dp),
                                    waveColor = MaterialTheme.colors.error.copy(0.4f),
                                    wavePointCount = 10,
                                    waveSpeed = WaveSpeed.SLOW,
                                    progress = progress2,
                                    onProgressUpdated = {
                                        progress2 = it
                                    }
                                )
                                Description("Speed: SLOW, Point: 10")
                            }
                        }

                        Row(
                            modifier = Modifier.wrapContentSize(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Column {
                                WaveView(
                                    modifier = Modifier
                                        .size(150.dp)
                                        .clip(RoundedCornerShape(10f)),
                                    wavePointCount = 30,
                                    waveColor = MaterialTheme.colors.secondaryVariant.copy(0.4f),
                                    progress = progress1,
                                    onProgressUpdated = {
                                        progress1 = it
                                    }
                                )
                                Description("Speed: NORMAL, Point: 30")
                            }

                            Column {
                                WaveView(
                                    modifier = Modifier
                                        .size(150.dp),
                                    waveColor = MaterialTheme.colors.secondary.copy(0.4f),
                                    wavePointCount = 3,
                                    progress = progress2,
                                    dragEnabled = false,
                                    onProgressUpdated = {
                                        progress2 = it
                                    }
                                )
                                Description("Speed: NORMAL, Point: 3")
                            }
                        }
                    }


//                    }
//                    Row {
//                        Button(onClick = {
//                            val updateValue = progress + 0.1f
//                            if (updateValue < 1.0f) {
//                                progress = updateValue
//                            }
//                        }) {
//                            Text("increace")
//                        }
//                        Button(onClick = {
//                            val updateValue = progress - 0.1f
//                            if (updateValue > 0f) {
//                                progress = updateValue
//                            }
//                        }) {
//                            Text("deincreace")
//                        }
//                    }
                }
            }
        }
    }
}

@Composable
fun Description(text: String) {
    Text(
        text = text,
        textAlign = TextAlign.Center,
        style = TextStyle(fontSize = 12.sp),
    )
}