package com.neo.wave

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope

data class Wave(
    val pointCount: Int = 5,
    val color: Color,
    val canvasWidth: Float,
    val canvasHeight: Float,
) {
    val points: MutableList<Point> = mutableListOf()
    init {
        val gap: Float = canvasWidth / (pointCount - 1)
        for (i in 0 until pointCount) {
            points.add(Point(index = i, gap * i, 0f))
        }
    }

    fun update() {
        points.forEach {
            it.update()
        }
    }
}

fun DrawScope.drawWave(wave: Wave) {
    val path = Path()
    path.reset()

    var prevPoint = wave.points.firstOrNull() ?: Point(0, 0f, 0f)
    prevPoint.apply {
        path.moveTo(x, y)
    }

    wave.points.forEachIndexed { index, point ->
        kotlin.runCatching {
            wave.points[index + 1].apply {
                val x2 = (prevPoint.x + this.x) / 2
                val y2 = (prevPoint.y + this.y) / 2
                path.quadraticBezierTo(point.x, point.y, x2, y2)
                prevPoint = this
            }
        }
    }

    path.lineTo(prevPoint.x, prevPoint.y)
    path.lineTo(wave.canvasWidth, wave.canvasHeight)
    path.lineTo(wave.points.firstOrNull()?.x ?: 0f, wave.canvasHeight)
    path.close()

    drawPath(path = path, color = wave.color)
}

fun DrawScope.drawWaveDebug(
    wave: Wave,
    strokeWidth: Float = 5f,
    arcSize: Float = 20f
) {
    wave.points.forEachIndexed { index, point ->
        kotlin.runCatching {
            wave.points[index + 1].apply {
                drawLine(
                    color = Color.Yellow,
                    start = Offset(x = point.x, y = point.y),
                    end = Offset(x = this.x, y = this.y),
                    strokeWidth = strokeWidth
                )
            }
        }

        drawArc(
            color = Color.Green,
            startAngle = 0f,
            sweepAngle = 360f,
            topLeft = Offset(point.x - (arcSize / 2), point.y - (arcSize / 2)),
            size = Size(arcSize, arcSize),
            useCenter = true
        )
    }
}