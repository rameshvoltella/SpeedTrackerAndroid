package com.ramzmania.speedtracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ramzmania.speedtracker.ui.theme.SpeedTrackerTheme
import com.ramzmania.speedtracker.views.SpeedometerWrapper
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpeedTrackerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SpeedoMeterScreen()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}
@Composable
fun MySpeedometerScreen() {
    var currentSpeed by remember { mutableStateOf(80) }


}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SpeedTrackerTheme {
        SpeedoMeterScreen()
    }
}

@Composable
fun SpeedoMeterScreen() {
    var targetValue by remember { mutableStateOf(0f) }
    val progress = remember(targetValue) { Animatable(initialValue = 0f) }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Slider(
            value = targetValue,
            onValueChange = { targetValue = it }
        )

        val intValue = targetValue * 55
        Text(
            text = "${intValue.toInt()}"
        )

        Button(
            onClick = {
                scope.launch {
                    progress.animateTo(
                        targetValue = intValue,
                        animationSpec = tween(
                            durationMillis = 1000,
                            easing = FastOutLinearInEasing
                        )
                    )
                }
            }
        ) {
            Text(text = "Go")
        }
        Spacer(modifier = Modifier.height(20.dp))

        SpeedoMeter(progress = progress.value.toInt())
    }
}

@Composable
fun SpeedoMeter(
    progress: Int
) {
    val arcDegrees = 275
    val startArcAngle = 135f
    val startStepAngle = -45
    val numberOfMarkers = 55
    val degreesMarkerStep = arcDegrees / numberOfMarkers

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f),
        onDraw = {
            drawIntoCanvas { canvas ->
                val w = drawContext.size.width
                val h = drawContext.size.height
                val centerOffset = Offset(w / 2f, h / 2f)
                val quarterOffset = Offset(w / 4f, h / 4f)

                val (mainColor, secondaryColor) = when {
                    progress < 20 -> Color(0xFFD32F2F) to Color(0xFFFFCDD2)
                    progress < 40 -> Color(0xFFF57C00) to Color(0xFFFFE0B2)
                    else -> Color(0xFF388E3C) to Color(0xFFC8E6C9)
                }
                val paint = Paint().apply {
                    color = mainColor
                }
                val centerArcSize = Size(w / 2f, h / 2f)
                val centerArcStroke = Stroke(20f, 0f, StrokeCap.Round)

                drawArc(
                    secondaryColor,
                    startArcAngle,
                    arcDegrees.toFloat(),
                    false,
                    topLeft = quarterOffset,
                    size = centerArcSize,
                    style = centerArcStroke
                )

                drawArc(
                    mainColor,
                    startArcAngle,
                    (degreesMarkerStep * progress).toFloat(),
                    false,
                    topLeft = quarterOffset,
                    size = centerArcSize,
                    style = centerArcStroke
                )

                drawCircle(mainColor, 50f, centerOffset)
                drawCircle(androidx.compose.ui.graphics.Color.White, 25f, centerOffset)
                drawCircle(androidx.compose.ui.graphics.Color.Black, 20f, centerOffset)

                for ((counter, degrees) in (startStepAngle..(startStepAngle + arcDegrees) step degreesMarkerStep).withIndex()) {
                    val lineEndX = 80f
                    paint.color = mainColor
                    val lineStartX = if (counter % 5 == 0) {
                        paint.strokeWidth = 3f
                        0f
                    } else {
                        paint.strokeWidth = 1f
                        lineEndX * .2f
                    }
                    canvas.save()
                    canvas.rotate(degrees.toFloat(), w / 2f, h / 2f)
                    canvas.drawLine(
                        Offset(lineStartX, h / 2f),
                        Offset(lineEndX, h / 2f),
                        paint
                    )

                    if (counter == progress) {
                        paint.color = androidx.compose.ui.graphics.Color.Black
                        canvas.drawPath(
                            Path().apply {
                                moveTo(w / 2, (h / 2) - 5)
                                lineTo(w / 2, (h / 2) + 5)
                                lineTo(w / 4f, h / 2)
                                lineTo(w / 2, (h / 2) - 5)
                                close()
                            },
                            paint
                        )
                    }
                    canvas.restore()
                }
            }
        }
    )
}