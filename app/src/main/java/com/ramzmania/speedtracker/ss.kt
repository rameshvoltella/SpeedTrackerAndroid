package com.ramzmania.speedtracker
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import android.graphics.Paint // Import the Android Paint class
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun VerticalLinesWithFill() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        drawIntoCanvas { canvas ->
            val centerX = size.width / 2f
            val startY = 50f
            val endY = size.height - 50f

            val paint = Paint().apply {
                color = Color.Red.toArgb()
                strokeWidth = 5f // Adjust line width as needed
            }

            val lineOffset = 40.dp.toPx() // Distance between the lines

            // Draw the first line centered in the canvas
            val firstStartX = centerX - lineOffset / 2
            canvas.nativeCanvas.drawLine(firstStartX, startY, firstStartX, endY, paint)

            // Draw the second line with the same distance from the first line
            val secondStartX = centerX + lineOffset / 2
            canvas.nativeCanvas.drawLine(secondStartX, startY, secondStartX, endY, paint)

            // Fill the space between the lines with a color
            val fillColor = Color.Blue // Change to the desired fill color
            canvas.nativeCanvas.drawRect(firstStartX, startY, secondStartX, endY, paint.apply {
                color = fillColor.toArgb()
            })
        }
    }
}
@Composable
fun VerticalLinesWithFill2() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        drawIntoCanvas { canvas ->
            val centerX = size.width / 2f
            val centerY = size.height / 2f
            val startY = 50f
            val endY = size.height - 50f

            val paint = Paint().apply {
                color = Color.Red.toArgb()
                strokeWidth = 5f // Adjust line width as needed
            }

            val lineOffset = 40.dp.toPx() // Distance between the lines

            // Draw the first line centered in the canvas
            val firstStartX = centerX - lineOffset / 2
            canvas.nativeCanvas.drawLine(firstStartX, startY, firstStartX, endY, paint)

            // Draw the second line with the same distance from the first line
            val secondStartX = centerX + lineOffset / 2
            canvas.nativeCanvas.drawLine(secondStartX, startY, secondStartX, endY, paint)

            // Fill the space between the lines with a color
            val fillColor = Color.Blue // Change to the desired fill color
            canvas.nativeCanvas.drawRect(firstStartX, startY, secondStartX, endY, paint.apply {
                color = fillColor.toArgb()
            })

            // Rotate canvas back to its original orientation
            canvas.nativeCanvas.rotate(-40f, centerX, centerY)
        }
    }
}

@Composable
fun LineAtAngle() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        drawIntoCanvas { canvas ->
            val startX = size.width / 2f
            val startY = size.height / 2f
            val angle = 0.0 // Angle in degrees

            val length = size.width / 3 // Length of the line

            val endX = startX + length * cos(angle * PI / 180).toFloat()
            val endY = startY + length * sin(angle * PI / 180).toFloat()

            val paint = Paint().apply {
                color = Color.Red.toArgb()
                strokeWidth = 5f // Adjust line width as needed
            }

            canvas.nativeCanvas.drawLine(startX, startY, endX, endY, paint)
        }
    }
}
@Composable
fun VerticalLinesWithFill3() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        drawIntoCanvas { canvas ->
            val centerX = size.width / 2f
            val centerY = size.height / 2f
            val startY = 50f
            val endY = size.height - 50f

            val paint = Paint().apply {
                color = Color.Red.toArgb()
                strokeWidth = 5f // Adjust line width as needed
            }

            val lineOffset = 40.dp.toPx() // Distance between the lines
            val rotationDegrees = 5f // Rotation angle in degrees

            // Draw the first line centered in the canvas with rotation
            val firstStartX = centerX - lineOffset / 2
            canvas.nativeCanvas.save() // Save canvas state before rotation
            canvas.nativeCanvas.rotate(rotationDegrees, centerX, centerY)
            canvas.nativeCanvas.drawLine(firstStartX, startY, firstStartX, endY, paint)
            canvas.nativeCanvas.restore() // Restore canvas state after rotation

            // Draw the second line with the same distance from the first line and rotation
            val secondStartX = centerX + lineOffset / 2
            canvas.nativeCanvas.save() // Save canvas state before rotation
            canvas.nativeCanvas.rotate(rotationDegrees, centerX, centerY)
            canvas.nativeCanvas.drawLine(secondStartX, startY, secondStartX, endY, paint)
            canvas.nativeCanvas.restore() // Restore canvas state after rotation

            // Fill the space between the lines with a color
            val fillColor = Color.Blue // Change to the desired fill color
            canvas.nativeCanvas.drawRect(firstStartX, startY, secondStartX, endY, paint.apply {
                color = fillColor.toArgb()
            })
        }
    }
}
@Composable
fun VerticalLinesWithFill333() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        drawIntoCanvas { canvas ->
            val centerX = size.width / 2f
            val centerY = size.height / 2f
            val startY = 50f
            val endY = size.height - 50f

            val paint = Paint().apply {
                color = Color.Red.toArgb()
                strokeWidth = 5f // Adjust line width as needed
            }

            val lineOffset = 40.dp.toPx() // Distance between the lines
            val rotationDegrees = -10f // Rotation angle in degrees

            // Draw the first line centered in the canvas
            val firstStartX = centerX - lineOffset / 2
            canvas.nativeCanvas.drawLine(firstStartX, startY, firstStartX, endY, paint)

            // Save the current canvas state
            canvas.nativeCanvas.save()

            // Rotate canvas by the specified angle around the center
            canvas.nativeCanvas.rotate(rotationDegrees, centerX, centerY)

            // Draw the second line with rotation
            val secondStartX = centerX + lineOffset / 2
            canvas.nativeCanvas.drawLine(secondStartX, startY, secondStartX, endY, paint)

            // Restore the canvas to its original state
            canvas.nativeCanvas.restore()

            // Fill the space between the lines with a color
            val fillColor = Color.Blue // Change to the desired fill color
            canvas.nativeCanvas.drawRect(firstStartX, startY, secondStartX, endY, paint.apply {
                color = fillColor.toArgb()
            })
        }
    }
}

@Composable
fun VerticalLinesWithFill3d33() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        drawIntoCanvas { canvas ->
            val centerX = size.width / 2f
            val centerY = size.height / 2f
            val startY = 50f
            val endY = size.height - 50f

            val paint = Paint().apply {
                color = Color.Red.toArgb()
                strokeWidth = 5f // Adjust line width as needed
            }

            val lineOffset = 40.dp.toPx() // Distance between the lines
            val rotationDegrees = 10f // Rotation angle in degrees

            // Draw the first line centered in the canvas
            val firstStartX = centerX - lineOffset / 2
            canvas.nativeCanvas.drawLine(firstStartX, startY, firstStartX, endY, paint)

            // Save the current canvas state
            canvas.nativeCanvas.save()

            // Rotate canvas by the specified angle around the center for the second line
            canvas.nativeCanvas.rotate(-rotationDegrees, centerX, centerY)

            // Draw the second line with rotation
            val secondStartX = centerX + lineOffset / 2
            canvas.nativeCanvas.drawLine(secondStartX, startY, secondStartX, endY, paint)

            // Restore the canvas to its original state
            canvas.nativeCanvas.restore()

            // Fill the space between the lines with a color
            val fillColor = Color.Blue // Change to the desired fill color
            canvas.nativeCanvas.drawRect(firstStartX, startY, secondStartX, endY, paint.apply {
                color = fillColor.toArgb()
            })
        }
    }
}

@Composable
fun VerticalLinesWithFdill333() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        drawIntoCanvas { canvas ->
            val centerX = size.width / 2f
            val centerY = size.height / 2f
            val startY = 50f
            val endY = size.height - 50f

            val paint = Paint().apply {
                color = Color.Red.toArgb()
                strokeWidth = 5f // Adjust line width as needed
            }

            val lineOffset = 40.dp.toPx() // Distance between the lines
            val rotationDegrees = 10f // Rotation angle in degrees

            // Draw the first line centered in the canvas
            val firstStartX = centerX - lineOffset / 2
            canvas.nativeCanvas.drawLine(firstStartX, startY, firstStartX, endY, paint)

            // Save the current canvas state
            canvas.nativeCanvas.save()

            // Rotate canvas by the specified angle around the center for the second line
            canvas.nativeCanvas.rotate(rotationDegrees, centerX, centerY)

            // Draw the second line with rotation
            val secondStartX = centerX + lineOffset / 2
            canvas.nativeCanvas.drawLine(secondStartX, startY, secondStartX, endY, paint)

            // Restore the canvas to its original state
            canvas.nativeCanvas.restore()

            // Fill the space between the lines with a color
            val fillColor = Color.Blue // Change to the desired fill color
            canvas.nativeCanvas.drawRect(firstStartX, startY, secondStartX, endY, paint.apply {
                color = fillColor.toArgb()
            })
        }
    }
}
@Composable
fun VerticalLinssesWithFill333() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        drawIntoCanvas { canvas ->
            val centerX = size.width / 2f
            val centerY = size.height / 2f
            val startY = 50f
            val endY = size.height - 50f

            val paint = Paint().apply {
                color = Color.Red.toArgb()
                strokeWidth = 5f // Adjust line width as needed
            }

            val lineOffset = 40.dp.toPx() // Distance between the lines
            val rotationDegrees = 40f // Rotation angle in degrees

            // Save the current canvas state
            canvas.nativeCanvas.save()

            // Rotate canvas by the specified angle around the center for the first line
            canvas.nativeCanvas.rotate(-rotationDegrees, centerX, centerY)

            // Draw the first line with rotation
            val firstStartX = centerX - lineOffset / 2
            canvas.nativeCanvas.drawLine(firstStartX, startY, firstStartX, endY, paint)

            // Restore the canvas to its original state
            canvas.nativeCanvas.restore()

            // Save the current canvas state
            canvas.nativeCanvas.save()

            // Rotate canvas by the specified angle around the center for the second line
            canvas.nativeCanvas.rotate(rotationDegrees, centerX, centerY)

            // Draw the second line with rotation
            val secondStartX = centerX + lineOffset / 2
            canvas.nativeCanvas.drawLine(secondStartX, startY, secondStartX, endY, paint)

            // Restore the canvas to its original state
            canvas.nativeCanvas.restore()

            // Fill the space between the lines with a color
            val fillColor = Color.Blue // Change to the desired fill color
            canvas.nativeCanvas.drawRect(firstStartX, startY, secondStartX, endY, paint.apply {
                color = fillColor.toArgb()
            })
        }
    }
}

@Composable
fun VerticalLinesWissthFill333() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        drawIntoCanvas { canvas ->
            val centerX = size.width / 2f
            val centerY = size.height / 2f
            val startY = 50f
            val endY = size.height - 50f

            val paint = Paint().apply {
                color = Color.Red.toArgb()
                strokeWidth = 5f // Adjust line width as needed
            }

            val lineLength = 100.dp.toPx() // Length of the lines
            val rotationDegrees =0f // Rotation angle in degrees

            // Draw the first line centered in the canvas
            val startX1 = centerX - lineLength / 2
            val endX1 = centerX + lineLength / 2
            canvas.nativeCanvas.drawLine(startX1, centerY, endX1, centerY, paint)

            // Save the current canvas state
            canvas.nativeCanvas.save()

            // Rotate canvas by the specified angle around the center for the first line
            canvas.nativeCanvas.rotate(-rotationDegrees, centerX, centerY)

            // Draw the second line with rotation
            val startX2 = centerX - lineLength / 2
            val endX2 = centerX + lineLength / 2
            canvas.nativeCanvas.drawLine(startX2, centerY, endX2, centerY, paint)

            // Restore the canvas to its original state
            canvas.nativeCanvas.restore()

            // Save the current canvas state
            canvas.nativeCanvas.save()

            // Rotate canvas by the specified angle around the center for the second line
            canvas.nativeCanvas.rotate(rotationDegrees, centerX, centerY)

            // Draw the third line with rotation
            val startX3 = centerX - lineLength / 2
            val endX3 = centerX + lineLength / 2
            canvas.nativeCanvas.drawLine(startX3, centerY, endX3, centerY, paint)

            // Restore the canvas to its original state
            canvas.nativeCanvas.restore()

            // Fill the space between the lines with a color
            val fillColor = Color.Blue // Change to the desired fill color
            canvas.nativeCanvas.drawRect(startX2, startY, endX2, endY, paint.apply {
                color = fillColor.toArgb()
            })
        }
    }
}

@Preview
@Composable
fun PRE()
{
    VerticalLinesWissthFill333()
}
