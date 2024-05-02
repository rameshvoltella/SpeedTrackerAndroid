package com.ramzmania.speedtracker.views
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import kotlin.math.cos
import kotlin.math.sin
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CustomSemiCircle(
    modifier: Modifier = Modifier,
    color: Color = Color.Blue,
    strokeWidth: Float = 4f,
    minAngle: Float = 220f,
    maxAngle: Float = -40f,
    startAngle: Float = 140f,
    sweepAngle: Float = 260f
) {
    Canvas(modifier = modifier.fillMaxSize()) {
        val centerX = size.width / 2
        val centerY = size.height

        val path = Path()

        // Move to starting point
        path.moveTo(centerX, centerY)

        // Calculate start and end points for the arc
        val startX = centerX + (size.width / 2) * cos(Math.toRadians(startAngle.toDouble())).toFloat()
        val startY = centerY - (size.width / 2) * sin(Math.toRadians(startAngle.toDouble())).toFloat()

        val endX = centerX + (size.width / 2) * cos(Math.toRadians(startAngle + sweepAngle.toDouble())).toFloat()
        val endY = centerY - (size.width / 2) * sin(Math.toRadians(startAngle + sweepAngle.toDouble())).toFloat()

        // Create the bounding rectangle
        val rect = Rect(centerX - size.width / 2, centerY - size.width / 2, centerX + size.width / 2, centerY + size.width / 2)

        // Add arc
        path.arcTo(rect, startAngle, sweepAngle, true)

        // Close path
        path.lineTo(centerX, centerY)

        // Draw the semi-circle
        drawPath(
            path = path,
            color = color,
            style = Stroke(width = strokeWidth)
        )
    }
}

@Composable
fun CustomHalfCircle(
    modifier: Modifier = Modifier,
    fillColor: Color = Color.Red, // Color for the filled arc
    strokeColor: Color = Color.Blue, // Color for the stroke
    strokeWidth: Float = 4f,
    startAngle: Float = -40f,
    sweepAngle: Float = 180f // 50% of circle
) {
    Canvas(modifier = modifier.fillMaxSize()) {
        val centerX = size.width / 2
        val centerY = size.height

        val path = Path()

        // Move to starting point
        path.moveTo(centerX, centerY)

        // Create the bounding rectangle
        val rect = Rect(centerX - size.width / 2, centerY - size.width / 2, centerX + size.width / 2, centerY + size.width / 2)

        // Add arc
        path.arcTo(rect, startAngle, sweepAngle, true)

        // Draw the filled arc with fillColor
        drawPath(
            path = path,
            color = fillColor,
            style = Fill
        )

        // Draw the stroke with strokeColor
        drawPath(
            path = path,
            color = strokeColor,
            style = Stroke(width = strokeWidth)
        )
    }
}

@Composable
fun CustomSemiCircle3(
    modifier: Modifier = Modifier,
    color: Color = Color.Blue,
    strokeWidth: Float = 8f, // Adjust thickness as needed
    startAngle: Float = 140f,
    sweepAngle: Float = 260f
) {
    Canvas(modifier = modifier.fillMaxSize()) {
        val centerX = size.width / 2
        val centerY = size.height

        val path = Path()
        val path2 = Path()

        // Move to starting point
        path.moveTo(centerX, centerY)
        path2.moveTo(centerX, centerY)

        // Create the bounding rectangle
        val rect = Rect(centerX - size.width / 2, centerY - size.width / 2, centerX + size.width / 2, centerY + size.width / 2)

        // Add arc
        path.arcTo(rect, startAngle, sweepAngle, true)
        path2.arcTo(rect, startAngle, 42f, true)

        // Draw the semi-circle with thicker border
        drawPath(
            path = path,
            color = color,
            style = Stroke(width = strokeWidth)
        )
        drawPath(
            path = path2,
            color = Color.Green,
            style = Stroke(width = 10f)
        )
    }
}



/*@Composable
fun CustomSemiCircle3(
    modifier: Modifier = Modifier,
    color: Color = Color.Blue,
    strokeWidth: Float = 8f, // Adjust thickness as needed
    startAngle: Float = 140f,
    sweepAngle: Float = 260f
) {
    Canvas(modifier = modifier.fillMaxSize()) {
        val centerX = size.width / 2
        val centerY = size.height

        val path = Path()
        val path2 = Path()

        // Move to starting point
        path.moveTo(centerX, centerY)
        path2.moveTo(centerX, centerY)

        // Create the bounding rectangle
        val rect = Rect(centerX - size.width / 2, centerY - size.width / 2, centerX + size.width / 2, centerY + size.width / 2)

        // Add arc for the main circle
        path.arcTo(rect, startAngle, sweepAngle, true)

        // Calculate the start and end points for the smaller circle
        val smallerCircleStartX = centerX + (size.width / 2) * cos(Math.toRadians(startAngle + sweepAngle.toDouble())).toFloat()
        val smallerCircleStartY = centerY - (size.width / 2) * sin(Math.toRadians(startAngle + sweepAngle.toDouble())).toFloat()

        val smallerCircleEndX = centerX + (size.width / 2) * cos(Math.toRadians(startAngle + sweepAngle + 90.toDouble())).toFloat()
        val smallerCircleEndY = centerY - (size.width / 2) * sin(Math.toRadians(startAngle + sweepAngle + 90.toDouble())).toFloat()

        // Add arc for the smaller circle below
        path2.arcTo(centerX - size.width / 2, centerY - size.width / 2, centerX + size.width / 2, centerY + size.width / 2, startAngle + sweepAngle, -90f, false)

        // Draw the main circle
        drawPath(
            path = path,
            color = color,
            style = Stroke(width = strokeWidth)
        )

        // Draw the smaller circle
        drawPath(
            path = path2,
            color = Color.Green, // Adjust color as needed
            style = Stroke(width = strokeWidth) // Adjust thickness as needed
        )
    }
}*/
@Composable
fun CustomSemiCircle0(
    modifier: Modifier = Modifier,
    color: Color = Color.Blue,
    strokeWidth: Float = 8f, // Adjust thickness as needed
    startAngle: Float = 140f,
    sweepAngle: Float = 260f
) {
    Canvas(modifier = modifier.fillMaxSize()) {
        val centerX = size.width / 2
        val centerY = size.height

        val path = Path()
        val path2 = Path()

        // Move to starting point
        path.moveTo(centerX, centerY)
        path2.moveTo(centerX, centerY)

        // Create the bounding rectangle
        val rect = Rect(centerX - size.width / 2, centerY - size.width / 2, centerX + size.width / 2, centerY + size.width / 2)

        // Add arc for the main circle
        path.arcTo(rect, startAngle, sweepAngle, true)

        // Calculate the start and end points for the smaller circle
        val smallerCircleStartX = centerX + (size.width / 2) * cos(Math.toRadians(startAngle + sweepAngle.toDouble())).toFloat()
        val smallerCircleStartY = centerY - (size.width / 2) * sin(Math.toRadians(startAngle + sweepAngle.toDouble())).toFloat()

        val smallerCircleEndX = centerX + (size.width / 2) * cos(Math.toRadians(startAngle + sweepAngle + 90.toDouble())).toFloat()
        val smallerCircleEndY = centerY - (size.width / 2) * sin(Math.toRadians(startAngle + sweepAngle + 90.toDouble())).toFloat()

        // Add arc for the smaller circle below
        val smallerCircleRect = Rect(centerX - size.width / 2, centerY - size.width / 2, centerX + size.width / 2, centerY + size.width / 2)
        path2.arcTo(smallerCircleRect, startAngle + sweepAngle, -90f, true)

        // Draw the main circle
        drawPath(
            path = path,
            color = color,
            style = Stroke(width = strokeWidth)
        )

        // Draw the smaller circle
        drawPath(
            path = path2,
            color = Color.Green, // Adjust color as needed
            style = Stroke(width = strokeWidth) // Adjust thickness as needed
        )
    }
}
@Composable
fun CustomSemiCircle300(
    modifier: Modifier = Modifier,
    mainCircleColor: Color = Color.Blue,
    smallerCircleColor: Color = Color.Green,
    strokeWidth: Float = 8f, // Adjust thickness as needed
    mainCircleStartAngle: Float = 140f,
    mainCircleSweepAngle: Float = 260f,
    smallerCircleStartAngle: Float = mainCircleStartAngle + mainCircleSweepAngle,
    smallerCircleSweepAngle: Float = -90f
) {
    Canvas(modifier = modifier.fillMaxSize()) {
        val centerX = size.width / 2
        val centerY = size.height

        val mainCirclePath = Path()
        val smallerCirclePath = Path()

        // Move to starting point for main circle
        mainCirclePath.moveTo(centerX, centerY)

        // Create the bounding rectangle for both circles
        val rect = Rect(centerX - size.width / 2, centerY - size.width / 2, centerX + size.width / 2, centerY + size.width / 2)

        // Add arc for the main circle
        mainCirclePath.arcTo(rect, mainCircleStartAngle, mainCircleSweepAngle, true)

        // Add arc for the smaller circle
        smallerCirclePath.arcTo(rect, smallerCircleStartAngle, smallerCircleSweepAngle, true)

        // Draw the main circle
        drawPath(
            path = mainCirclePath,
            color = mainCircleColor,
            style = Stroke(width = strokeWidth)
        )

        // Draw the smaller circle
        drawPath(
            path = smallerCirclePath,
            color = smallerCircleColor,
            style = Stroke(width = strokeWidth)
        )
    }
}


@Composable
fun CustomSemiCircle2(
    modifier: Modifier = Modifier,
    color: Color = Color.Blue,
    strokeWidth: Float = 4f,
    startAngle: Float = 140f,
    sweepAngle: Float = 260f
) {
    Canvas(modifier = modifier.fillMaxSize()) {
        val centerX = size.width / 2
        val centerY = size.height

        val path = Path()

        // Move to starting point
        path.moveTo(centerX, centerY)

        // Create the bounding rectangle
        val rect = Rect(centerX - size.width / 2, centerY - size.width / 2, centerX + size.width / 2, centerY + size.width / 2)

        // Add arc
        path.arcTo(rect, startAngle, sweepAngle, true)

        // Draw the semi-circle
        drawPath(
            path = path,
            color = color,
            style = Stroke(width = strokeWidth)
        )
    }
}

//@Preview
//@Composable
//fun CustomSemiCirclePreview() {
//    Surface(color = Color.White) {
//        Column {
//            CustomSemiCircle0(
//                modifier = Modifier.size(200.dp),
//                color = Color.Blue
//
//            )
//        }
//    }
//}
@Preview
@Composable
fun CustomSemiCircle3Preview() {
    Surface(color = Color.White) {
        Column {
            CustomSemiCircle300(
                modifier = Modifier.size(200.dp),
                mainCircleColor = Color.Blue,
                smallerCircleColor = Color.Red, // Customize color for the smaller circle
                strokeWidth = 8f,
                mainCircleStartAngle = 140f,
                mainCircleSweepAngle = 220f,
                smallerCircleStartAngle = 140f, // Example value, adjust as needed
                smallerCircleSweepAngle = 218f // Example value, adjust as needed
            )
        }
    }
}

