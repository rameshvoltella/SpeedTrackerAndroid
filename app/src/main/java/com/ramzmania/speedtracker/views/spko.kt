package com.ramzmania.speedtracker.views
import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.SolidColor
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun Speedometer(
    modifier: Modifier = Modifier,
    maxSpeed: Int = 60,
    borderSize: Float = 36f,
    textGap: Float = 50f,
    metricText: String = "km/h",
    borderColor: Color = Color.Black,
    fillColor: Int = Color.Green.toArgb(),
    textColor: Int = Color.Yellow.toArgb(),
    currentSpeed: MutableState<Int>,
    onSpeedChangeListener: (Int) -> Unit = {}
) {
    var angle by remember { mutableStateOf(220f) }
//    val textBounds = remember { mutableStateOf<Rect>(Rect()) }
    val textBounds = remember { mutableStateOf(Rect(Offset.Zero, Size.Zero)) }
    Canvas(modifier = modifier) {
        val centerY = size.height / 2
        val centerX = size.width / 2
        val indicatorBorderRect = Rect(0f, 0f, size.width, size.height)
        val tickBorderRect = Rect(
            borderSize + TICK_MARGIN,
            borderSize + TICK_MARGIN,
            size.width - borderSize - TICK_MARGIN,
            size.height - borderSize - TICK_MARGIN
        )

        // Draw major ticks
        for (s in MIN_SPEED..maxSpeed step 10) {
            val startX =
                centerX + (centerX - borderSize - MAJOR_TICK_SIZE) * cos(mapSpeedToAngle(s))
            val startY =
                centerY - (centerY - borderSize - MAJOR_TICK_SIZE) * sin(mapSpeedToAngle(s))
            val endX =
                centerX + (centerX - borderSize - TICK_MARGIN) * cos(mapSpeedToAngle(s))
            val endY =
                centerY - (centerY - borderSize - TICK_MARGIN) * sin(mapSpeedToAngle(s))

            drawLine(
                color = borderColor,
                start = Offset(startX.toFloat(), startY.toFloat()),
                end = Offset(endX.toFloat(), endY.toFloat()),
                strokeWidth = MAJOR_TICK_WIDTH
            )
            val textX =
                centerX + (centerX - borderSize - MAJOR_TICK_SIZE - TICK_MARGIN - TICK_TEXT_MARGIN) * cos(
                    mapSpeedToAngle(
                        s
                    )
                )
            val textY =
                centerY - (centerY - borderSize - MAJOR_TICK_SIZE - TICK_MARGIN - TICK_TEXT_MARGIN) * sin(
                    mapSpeedToAngle(
                        s
                    )
                )
            val speedText = s.toString()


// Inside the Canvas block
            drawIntoCanvas { canvas ->
                val paint = androidx.compose.ui.graphics.Paint().asFrameworkPaint().apply {
                    color = textColor
                    textSize = 40f
                }
                canvas.nativeCanvas.drawText(
                    speedText,
                    textX.toFloat(),
                    textY.toFloat(),
                    paint
                )

                val metricPaint = androidx.compose.ui.graphics.Paint().asFrameworkPaint().apply {
                    color = textColor
                    textSize = 50f
                }
                canvas.nativeCanvas.drawText(
                    metricText,
                    centerX,
                    centerY + 260f / 2 + textGap,
                    metricPaint
                )
            }
        }

        // Draw minor ticks
        for (s in MIN_SPEED..maxSpeed step 2) {
            if (s % 10 != 0) {
                val startX =
                    centerX + (centerX - borderSize - MINOR_TICK_SIZE) * cos(mapSpeedToAngle(s))
                val startY =
                    centerY - (centerY - borderSize - MINOR_TICK_SIZE) * sin(mapSpeedToAngle(s))
                val endX =
                    centerX + (centerX - borderSize - TICK_MARGIN) * cos(mapSpeedToAngle(s))
                val endY =
                    centerY - (centerY - borderSize - TICK_MARGIN) * sin(mapSpeedToAngle(s))
                drawLine(
                    color = borderColor,
                    start = Offset(startX.toFloat(), startY.toFloat()),
                    end = Offset(endX.toFloat(), endY.toFloat()),
                    strokeWidth = MINOR_TICK_WIDTH
                )
            }
        }

        // Draw indicator border

        drawArc(
            brush = SolidColor(borderColor),
            startAngle = 140f,
            sweepAngle = 260f,
            useCenter = false,
            topLeft = indicatorBorderRect.topLeft,
            size = indicatorBorderRect.size,
            style = Stroke(width = borderSize)
        )

        // Draw tick border
        drawArc(
            brush = SolidColor(borderColor),
            startAngle = START_ANGLE,
            sweepAngle = SWEEP_ANGLE,
            useCenter = false,
            topLeft = tickBorderRect.topLeft,
            size = tickBorderRect.size,
            style = Stroke(width = 4f)
        )

// Draw indicator fill
        drawArc(
            brush = SolidColor(Color.Green),
            startAngle = START_ANGLE,
            sweepAngle = MIN_ANGLE - angle,
            useCenter = false,
            topLeft = indicatorBorderRect.topLeft,
            size = indicatorBorderRect.size,
            style = Stroke(width = borderSize)
        )



        // Draw speed
        drawIntoCanvas { canvas ->
            val speedText = currentSpeed.value.toString()
            val cx = size.width / 2f
            val cy = size.height / 2f

            val paint = androidx.compose.ui.graphics.Paint().asFrameworkPaint().apply {
                color = textColor
                textSize = 260f
            }

            val textBounds = android.graphics.Rect()
            paint.apply {
                textSize = 260f
            }
            paint.getTextBounds(speedText, 0, speedText.length, textBounds)
            val textHeight = textBounds.height()
            val textWidth = paint.measureText(speedText)

            canvas.nativeCanvas.drawText(
                speedText,
                cx - textWidth / 2,
                cy + textHeight / 2,
                paint
            )
        }
    }
}

private fun mapSpeedToAngle(speed: Int): Float {
    return (220f + ((-40f - 220f) / (60 - 0)) * (speed - 0))
}

private const val MIN_SPEED = 0
private const val TICK_MARGIN = 10f
private const val TICK_TEXT_MARGIN = 30f
private const val MAJOR_TICK_SIZE = 50f
private const val MINOR_TICK_SIZE = 25f
private const val MAJOR_TICK_WIDTH = 4f
private const val MINOR_TICK_WIDTH = 2f
private const val START_ANGLE = 140f
private const val SWEEP_ANGLE = 260f
private const val MIN_ANGLE = 220f

@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
fun SpeedometerPreview() {
    Speedometer(
        Modifier.size(300.dp),
        currentSpeed = mutableStateOf(900)
    )
}
