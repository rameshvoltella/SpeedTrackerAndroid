package com.ramzmania.speedtracker.views
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.viewinterop.AndroidView
import com.ramzmania.speedtracker.views.Speedometer

@Composable
fun SpeedometerWrapper(
    modifier: Modifier = Modifier,
    speed: Int,
    maxSpeed: Int,
    onSpeedSet: (Int) -> Unit,
    borderSize: Float,
    textGap: Float,
    metricText: String,
    borderColor: Int,
    fillColor: Int,
    textColor: Int
) {
    AndroidView(
        modifier = modifier.fillMaxSize(),
        factory = { context ->
            Speedometer(context).apply {
                // Set initial speed and max speed
                setSpeed(speed, 0)
                this.maxSpeed = maxSpeed

                // Set attributes
                this.borderSize = borderSize
                this.textGap = textGap
                this.metricText = metricText
                this.borderColor = borderColor
                this.fillColor = fillColor
                this.textColor = textColor

                // Set listener for speed changes
////                setOnSpeedChangeListener { newSpeed ->
//                    onSpeedSet(newSpeed)
//                }
            }
        }
    )
}

