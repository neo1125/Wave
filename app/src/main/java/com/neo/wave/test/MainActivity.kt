package com.neo.wave.test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.neo.wave.test.ui.theme.WaveTheme

class MainActivity : ComponentActivity() {

    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WaveTheme {

                OptionScreen(modifier = Modifier.fillMaxSize())

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