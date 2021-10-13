package com.neo.wave

import kotlin.math.sin

data class Point(
    val index: Int,
    val x: Float,
    var y: Float,
    val fixedY: Float = y,
    val speed: Float = WaveSpeed.NORMAL.value,
    var curSpeed: Float = index.toFloat(),
    val max: Double = Math.random() * 5 + 10
) {
    fun update() {
        curSpeed += speed
        y = fixedY + (sin(curSpeed) * max).toFloat()
    }
}