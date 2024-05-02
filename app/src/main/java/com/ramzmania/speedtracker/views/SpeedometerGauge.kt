package com.ramzmania.speedtracker.views

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import kotlin.math.cos
import kotlin.math.sin

@Composable
fun SpeedometerGauge(speed: Float, maxSpeed: Float, modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Canvas(modifier = Modifier.size(200.dp)) {
            val center = Offset(size.width / 2, size.height / 2)
            val radius = size.width / 2
            val anglePerUnit = 180f / (maxSpeed / 20f)

            // Draw speedometer arc
            drawArc(
                color = Color.Blue,
                startAngle = 180f,
                sweepAngle = 180f,
                useCenter = false,
                style = Stroke(width = 20.dp.toPx())
            )

            // Draw speedometer numbers
            for (i in 0..(maxSpeed / 20).toInt()) {
                val value = i * 20
                val valueAngle = 180 - (i * anglePerUnit)
                val textX = center.x + ((radius - 40.dp.toPx()) * cos(Math.toRadians(valueAngle.toDouble()))).toFloat()
                val textY = center.y - ((radius - 40.dp.toPx()) * sin(Math.toRadians(valueAngle.toDouble()))).toFloat()

                drawContext.canvas.nativeCanvas.drawText(
                    value.toString(),
                    textX,
                    textY,
                    android.graphics.Paint().apply {
                        color = Color.Black.toArgb()
                        textSize = 24f // Adjust text size as needed
                        textAlign = android.graphics.Paint.Align.CENTER
                        isAntiAlias = true
                        isFakeBoldText = true
                    }
                )
            }

            // Draw speedometer needle
            val needleLength = radius - 20.dp.toPx()
//            val needleAngle = Math.toRadians(180 - ((speed / maxSpeed) * 180))
            val needleAngle = Math.toRadians(180 - ((speed / maxSpeed.toDouble()) * 180)) // Convert speed to Double
            val needleEndX = center.x + (needleLength * cos(needleAngle)).toFloat()
            val needleEndY = center.y - (needleLength * sin(needleAngle)).toFloat()
            drawLine(
                color = Color.Red,
                start = center,
                end = Offset(needleEndX, needleEndY),
                strokeWidth = 5.dp.toPx()
            )
        }
    }
}


@Composable
fun SpeedometerGauge2(speed: Float, maxSpeed: Float, modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Canvas(modifier = Modifier.size(200.dp)) {
            val center = Offset(size.width / 2, size.height / 2)
            val radius = size.width / 2
            val anglePerNumber = 180f / 8 // Display 8 numbers between 0 and 300
            val interval = maxSpeed / 8 // Interval between each number

            // Draw speedometer arc
            drawArc(
                color = Color.Blue,
                startAngle = 180f,
                sweepAngle = 180f,
                useCenter = false,
                style = Stroke(width = 20.dp.toPx())
            )

            // Draw speedometer numbers
            for (i in 0 until 8) {
                val value = i * interval
                val valueAngle = 180 - (i * anglePerNumber)
                val textX = center.x + ((radius - 40.dp.toPx()) * cos(Math.toRadians(valueAngle.toDouble()))).toFloat()
                val textY = center.y - ((radius - 40.dp.toPx()) * sin(Math.toRadians(valueAngle.toDouble()))).toFloat()

                drawContext.canvas.nativeCanvas.drawText(
                    value.toInt().toString(),
                    textX,
                    textY,
                    android.graphics.Paint().apply {
                        color = Color.Black.toArgb()
                        textSize = 24f // Adjust text size as needed
                        textAlign = android.graphics.Paint.Align.CENTER
                        isAntiAlias = true
                        isFakeBoldText = true
                    }
                )
            }

            // Draw speedometer needle
            val needleLength = radius - 20.dp.toPx()
            val needleAngle = Math.toRadians(180 - ((speed / maxSpeed.toDouble()) * 180)) // Convert speed to Double
            val needleEndX = center.x + (needleLength * cos(needleAngle)).toFloat()
            val needleEndY = center.y - (needleLength * sin(needleAngle)).toFloat()
            drawLine(
                color = Color.Red,
                start = center,
                end = Offset(needleEndX, needleEndY),
                strokeWidth = 5.dp.toPx()
            )
        }
    }
}
@Composable
fun SpeedometerGauge4(speed: Float, maxSpeed: Float, modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Canvas(modifier = Modifier.size(200.dp)) {
            val center = Offset(size.width / 2, size.height / 2)
            val radius = size.width / 2
            val numbersToDisplay = listOf(0f, 80f, 100f, 200f, 250f, 300f)
            val anglePerNumber = 180f / numbersToDisplay.size

            // Draw speedometer arc
            drawArc(
                color = Color.Blue,
                startAngle = 180f,
                sweepAngle = 180f,
                useCenter = false,
                style = Stroke(width = 20.dp.toPx())
            )

            // Draw speedometer numbers
            for ((index, number) in numbersToDisplay.withIndex()) {
                val valueAngle = 180 - (index * anglePerNumber)
                val textX = center.x + ((radius - 40.dp.toPx()) * cos(Math.toRadians(valueAngle.toDouble()))).toFloat()
                val textY = center.y - ((radius - 40.dp.toPx()) * sin(Math.toRadians(valueAngle.toDouble()))).toFloat()

                drawContext.canvas.nativeCanvas.drawText(
                    number.toInt().toString(),
                    textX,
                    textY,
                    android.graphics.Paint().apply {
                        color = Color.Black.toArgb()
                        textSize = 24f // Adjust text size as needed
                        textAlign = android.graphics.Paint.Align.CENTER
                        isAntiAlias = true
                        isFakeBoldText = true
                    }
                )
            }

            // Draw speedometer needle
            val needleLength = radius - 20.dp.toPx()
            val needleAngle = Math.toRadians(180 - ((speed / maxSpeed.toDouble()) * 180)) // Convert speed to Double
            val needleEndX = center.x + (needleLength * cos(needleAngle)).toFloat()
            val needleEndY = center.y - (needleLength * sin(needleAngle)).toFloat()
            drawLine(
                color = Color.Red,
                start = center,
                end = Offset(needleEndX, needleEndY),
                strokeWidth = 5.dp.toPx()
            )
        }
    }



}
@Composable
fun SpeedometerGauge45(speed: Float, maxSpeed: Float, modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Canvas(modifier = Modifier.size(200.dp)) {
            val center = Offset(size.width / 2, size.height / 2)
            val radius = size.width / 2
            val numbersToDisplay = listOf(0f, 80f, 100f, 200f, 250f, 280f,380f)
            val anglePerNumber = 180f / numbersToDisplay.size

            // Draw speedometer arc
            drawArc(
                color = Color.Blue,
                startAngle = 180f,
                sweepAngle = 180f,
                useCenter = false,
                style = Stroke(width = 20.dp.toPx())
            )

            // Draw speedometer numbers
            for ((index, number) in numbersToDisplay.withIndex()) {
                val valueAngle = (180 - (index * anglePerNumber)) * (Math.PI / 180)// Convert degrees to radians
                val textX = center.x + ((radius - 40.dp.toPx()) * cos(valueAngle)).toFloat()
                val textY = center.y - ((radius - 40.dp.toPx()) * sin(valueAngle)).toFloat()

                drawContext.canvas.nativeCanvas.drawText(
                    number.toInt().toString(),
                    textX,
                    textY,
                    android.graphics.Paint().apply {
                        color = Color.Black.toArgb()
                        textSize = 24f // Adjust text size as needed
                        textAlign = android.graphics.Paint.Align.CENTER
                        isAntiAlias = true
                        isFakeBoldText = true
                    }
                )
            }

            // Draw speedometer needle
            val needleLength = radius - 20.dp.toPx()
            val needleAngle = Math.toRadians(180 - ((speed / maxSpeed.toDouble()) * 180)) // Convert speed to Double
            val needleEndX = center.x + (needleLength * cos(needleAngle)).toFloat()
            val needleEndY = center.y - (needleLength * sin(needleAngle)).toFloat()
            drawLine(
                color = Color.Red,
                start = center,
                end = Offset(needleEndX, needleEndY),
                strokeWidth = 5.dp.toPx()
            )
        }
    }
}

@Composable
fun SpeedometerGauges(speed: Float, maxSpeed: Float, modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Canvas(modifier = Modifier.size(200.dp)) {
            val center = Offset(size.width / 2, size.height / 2)
            val radius = size.width / 2
            val numbersToDisplay = listOf(0f, 80f, 100f, 200f, 250f, 300f)
            val anglePerNumber = 180f / numbersToDisplay.size

            // Draw speedometer arc with rounded corners
            drawRoundRect(
                color = Color.Blue,
                topLeft = Offset(center.x - radius, center.y - radius),
                size = Size(radius * 2, radius),
                cornerRadius = CornerRadius(radius)
            )

            // Draw speedometer numbers
            for ((index, number) in numbersToDisplay.withIndex()) {
                val valueAngle = (180 - (index * anglePerNumber)) * (Math.PI / 180) // Convert degrees to radians
                val textX = center.x + ((radius - 40.dp.toPx()) * cos(valueAngle)).toFloat()
                val textY = center.y - ((radius - 40.dp.toPx()) * sin(valueAngle)).toFloat()

                drawContext.canvas.nativeCanvas.drawText(
                    number.toInt().toString(),
                    textX,
                    textY,
                    android.graphics.Paint().apply {
                        color = Color.Black.toArgb()
                        textSize = 24f // Adjust text size as needed
                        textAlign = android.graphics.Paint.Align.CENTER
                        isAntiAlias = true
                        isFakeBoldText = true
                    }
                )
            }

            // Draw speedometer needle
            val needleLength = radius - 20.dp.toPx()
            val needleAngle = Math.toRadians(180 - ((speed / maxSpeed.toDouble()) * 180)) // Convert speed to Double
            val needleEndX = center.x + (needleLength * cos(needleAngle)).toFloat()
            val needleEndY = center.y - (needleLength * sin(needleAngle)).toFloat()
            drawLine(
                color = Color.Red,
                start = center,
                end = Offset(needleEndX, needleEndY),
                strokeWidth = 5.dp.toPx()
            )
        }
    }
}
@Composable
fun SpeedometerGauged(speed: Float, maxSpeed: Float, modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Canvas(modifier = Modifier.size(200.dp)) {
            val center = Offset(size.width / 2, size.height / 2)
            val radius = size.width / 2
            val numbersToDisplay = listOf(0f, 80f, 100f, 200f, 250f, 300f)
            val anglePerNumber = 180f / numbersToDisplay.size

            // Draw speedometer arc
            drawArc(
                color = Color.Blue,
                startAngle = 180f,
                sweepAngle = 180f,
                useCenter = false,
                style = Stroke(width = 20.dp.toPx())
            )

            // Draw rounded ends
            drawArc(
                color = Color.Blue,
                startAngle = 180f,
                sweepAngle = 90f,
                useCenter = false,
                style = Stroke(width = 20.dp.toPx())
            )

            drawArc(
                color = Color.Blue,
                startAngle = 270f,
                sweepAngle = 90f,
                useCenter = false,
                style = Stroke(width = 20.dp.toPx())
            )

            // Draw speedometer numbers
            for ((index, number) in numbersToDisplay.withIndex()) {
                val valueAngle = (180 - (index * anglePerNumber)) * (Math.PI / 180) // Convert degrees to radians
                val textX = center.x + ((radius - 40.dp.toPx()) * cos(valueAngle)).toFloat()
                val textY = center.y - ((radius - 40.dp.toPx()) * sin(valueAngle)).toFloat()

                drawContext.canvas.nativeCanvas.drawText(
                    number.toInt().toString(),
                    textX,
                    textY,
                    android.graphics.Paint().apply {
                        color = Color.Black.toArgb()
                        textSize = 24f // Adjust text size as needed
                        textAlign = android.graphics.Paint.Align.CENTER
                        isAntiAlias = true
                        isFakeBoldText = true
                    }
                )
            }

            // Draw speedometer needle
            val needleLength = radius - 20.dp.toPx()
            val needleAngle = Math.toRadians(180 - ((speed / maxSpeed.toDouble()) * 180)) // Convert speed to Double
            val needleEndX = center.x + (needleLength * cos(needleAngle)).toFloat()
            val needleEndY = center.y - (needleLength * sin(needleAngle)).toFloat()
            drawLine(
                color = Color.Red,
                start = center,
                end = Offset(needleEndX, needleEndY),
                strokeWidth = 5.dp.toPx()
            )
        }
    }
}
@Composable
fun SpeedometerGaugeeee(speed: Float, maxSpeed: Float, modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Canvas(modifier = Modifier.size(200.dp)) {
            val center = Offset(size.width / 2, size.height / 2)
            val radius = size.width / 2

            // Draw speedometer arc
            drawArc(
                color = Color.Blue,
                startAngle = 180f,
                sweepAngle = 180f,
                useCenter = false,
                style = Stroke(width = 20.dp.toPx())
            )

            // Draw rounded ends
            drawArc(
                color = Color.Blue,
                startAngle = 180f,
                sweepAngle = 90f,
                useCenter = false,
                style = Stroke(width = 20.dp.toPx())
            )

            drawArc(
                color = Color.Blue,
                startAngle = 270f,
                sweepAngle = 90f,
                useCenter = false,
                style = Stroke(width = 20.dp.toPx())
            )

            // Draw speedometer needle
            val needleLength = radius - 20.dp.toPx()
            val needleAngle = Math.toRadians(180 - ((speed / maxSpeed.toDouble()) * 180)) // Convert speed to Double
            val needleEndX = center.x + (needleLength * cos(needleAngle)).toFloat()
            val needleEndY = center.y - (needleLength * sin(needleAngle)).toFloat()
            drawLine(
                color = Color.Red,
                start = center,
                end = Offset(needleEndX, needleEndY),
                strokeWidth = 5.dp.toPx()
            )
        }
    }
}

@Composable
fun SpeedometerGaugegogo(speed: Float, maxSpeed: Float, modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Canvas(modifier = Modifier.size(200.dp)) {
            val center = Offset(size.width / 2, size.height / 2)
            val radius = size.width / 2
            val textAngles = listOf(10f, 50f, 40f, 100f, 300f) // Angles for text positions

            // Draw speedometer arc
            drawArc(
                color = Color.Blue,
                startAngle = 180f,
                sweepAngle = 180f,
                useCenter = false,
                style = Stroke(width = 20.dp.toPx())
            )

            // Draw rounded ends
            drawArc(
                color = Color.Blue,
                startAngle = 180f,
                sweepAngle = 90f,
                useCenter = false,
                style = Stroke(width = 20.dp.toPx())
            )

            drawArc(
                color = Color.Blue,
                startAngle = 270f,
                sweepAngle = 90f,
                useCenter = false,
                style = Stroke(width = 20.dp.toPx())
            )

            // Draw text at specified angles
            textAngles.forEach { angle ->
                val textX = center.x + ((radius - 40.dp.toPx()) * cos(Math.toRadians(angle.toDouble()))).toFloat()
                val textY = center.y - ((radius - 40.dp.toPx()) * sin(Math.toRadians(angle.toDouble()))).toFloat()
                drawContext.canvas.nativeCanvas.drawText(
                    angle.toInt().toString(),
                    textX,
                    textY,
                    android.graphics.Paint().apply {
                        color = Color.Black.toArgb()
                        textSize = 24f // Adjust text size as needed
                        textAlign = android.graphics.Paint.Align.CENTER
                        isAntiAlias = true
                        isFakeBoldText = true
                    }
                )
            }

            // Draw speedometer needle
            val needleLength = radius - 20.dp.toPx()
            val needleAngle = Math.toRadians(180 - ((speed / maxSpeed.toDouble()) * 180)) // Convert speed to Double
            val needleEndX = center.x + (needleLength * cos(needleAngle)).toFloat()
            val needleEndY = center.y - (needleLength * sin(needleAngle)).toFloat()
            drawLine(
                color = Color.Red,
                start = center,
                end = Offset(needleEndX, needleEndY),
                strokeWidth = 5.dp.toPx()
            )
        }
    }
}
@Composable
fun Speedometer(speed: Float) {
    val speedAngle = (speed / 280f) * 180f - 90f
    Canvas(modifier = Modifier.size(200.dp)) {
        val canvasWidth = size.width
        val canvasHeight = size.height

        val centerX = canvasWidth / 2
        val centerY = canvasHeight / 2

        val radius = (canvasWidth / 2) * 0.9f

        // Draw the speedometer dial
        drawCircle(
            color = Color.LightGray,
            radius = radius,
            center = Offset(centerX, centerY)
        )

        // Draw the needle
        drawLine(
            color = Color.Black,
            start = Offset(centerX, centerY),
            end = polarToCartesian(radius, speedAngle, centerX, centerY),
            strokeWidth = 4f
        )

        // Draw speed indicators
        val indicatorRadius = radius * 0.85f
        val indicatorLength = 20f
        val totalIndicators = 14
        val angleIncrement = 180f / totalIndicators
        for (i in 0..totalIndicators) {
            val angle = (angleIncrement * i) - 90f
            val startX = centerX + (indicatorRadius * cos(Math.toRadians(angle.toDouble()))).toFloat()
            val startY = centerY + (indicatorRadius * sin(Math.toRadians(angle.toDouble()))).toFloat()
            val endX = centerX + ((indicatorRadius - indicatorLength) * cos(Math.toRadians(angle.toDouble()))).toFloat()
            val endY = centerY + ((indicatorRadius - indicatorLength) * sin(Math.toRadians(angle.toDouble()))).toFloat()
            drawLine(
                color = Color.Black,
                start = Offset(startX, startY),
                end = Offset(endX, endY),
                strokeWidth = 2f
            )
        }
    }
}

//private fun polarToCartesian(radius: Float, angle: Float, centerX: Float, centerY: Float): Offset {
//    val x = centerX + radius * cos(Math.toRadians(angle.toDouble())).toFloat()
//    val y = centerY + radius * sin(Math.toRadians(angle.toDouble())).toFloat()
//    return Offset(x, y)
//}
@Composable
fun Speedometeree(speed: Float) {
    val speedAngle = (speed / 280f) * 180f - 90f
    Canvas(modifier = Modifier.size(200.dp)) {
        val canvasWidth = size.width
        val canvasHeight = size.height

        val centerX = canvasWidth / 2
        val centerY = canvasHeight

        val radius = (canvasWidth / 2) * 0.9f

        // Draw the speedometer dial as a semicircle
        drawArc(
            color = Color.LightGray,
            startAngle = 180f,
            sweepAngle = 180f,
            useCenter = false,
            topLeft = Offset(centerX - radius, centerY - radius),
            size = Size(radius * 2, radius)
        )

        // Draw the needle
        drawLine(
            color = Color.Black,
            start = Offset(centerX, centerY),
            end = polarToCartesian(radius, speedAngle, centerX, centerY),
            strokeWidth = 4f
        )

        // Draw speed indicators
        val indicatorRadius = radius * 0.85f
        val indicatorLength = 20f
        val totalIndicators = 14
        val angleIncrement = 180f / totalIndicators
        for (i in 0..totalIndicators) {
            val angle = (angleIncrement * i) - 90f
            val startX = centerX + (indicatorRadius * cos(Math.toRadians(angle.toDouble()))).toFloat()
            val startY = centerY + (indicatorRadius * sin(Math.toRadians(angle.toDouble()))).toFloat()
            val endX = centerX + ((indicatorRadius - indicatorLength) * cos(Math.toRadians(angle.toDouble()))).toFloat()
            val endY = centerY + ((indicatorRadius - indicatorLength) * sin(Math.toRadians(angle.toDouble()))).toFloat()
            drawLine(
                color = Color.Black,
                start = Offset(startX, startY),
                end = Offset(endX, endY),
                strokeWidth = 2f
            )
        }
    }
}

private fun polarToCartesian(radius: Float, angle: Float, centerX: Float, centerY: Float): Offset {
    val x = centerX + radius * cos(Math.toRadians(angle.toDouble())).toFloat()
    val y = centerY + radius * sin(Math.toRadians(angle.toDouble())).toFloat()
    return Offset(x, y)
}

//@Preview
//@Composable
//fun SpeedometerGaugePreview() {
//    SpeedometerGauge45(speed = 120f, maxSpeed = 300f)
//}
@Preview
@Composable
fun SpeedometerGaugePreview() {
    SpeedometerGauge45(speed = 0f, maxSpeed = 280f)
}
