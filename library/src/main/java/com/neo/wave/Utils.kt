package com.neo.wave

import androidx.compose.runtime.*

@Composable
fun StepFrame(callbackTime: Long = 30L, callback:(frameTime: Long) -> Unit): State<Long> {
    val millis = remember { mutableStateOf(0L) }
    var lastFrameTime: Long = 0
    LaunchedEffect(Unit) {
        val startTime = withFrameMillis { it }
        while (true) {
            withFrameMillis { frameTime ->
                millis.value = frameTime - startTime
                if (lastFrameTime == 0L || (frameTime - lastFrameTime) >= callbackTime) {
                    lastFrameTime = frameTime
                    callback.invoke(frameTime)
                }
            }
        }
    }
    return millis
}