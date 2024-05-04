package com.ramzmania.speedtracker

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.ramzmania.speedtracker.views.SpeedometerComposeView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun EqualDivide() {
    val context = LocalContext.current
    val fusedLocationClient = remember {
        LocationServices.getFusedLocationProviderClient(
            context
        )
    }
//    val speedProgress = remember { mutableStateOf(0) }
    var targetValue by remember { mutableStateOf(0f) }
    var progress = remember(targetValue) { Animatable(initialValue = 0f) }
    val scope = rememberCoroutineScope()
    var speedtext by remember {
        mutableStateOf("0km")
    }
    val offsetY = rememberInfiniteTransition(label = "animationcar").animateFloat(
        initialValue = 0f,
        targetValue = 10f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000), // Adjust duration as needed
            repeatMode = RepeatMode.Reverse
        ), label = "animationcar"
    )

//    LaunchedEffect(key1 = speedtext)
//    {
//
//    }


    DisposableEffect(Unit) {
        val locationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 1000)
            .setWaitForAccurateLocation(true)
            .setMinUpdateIntervalMillis(1000)
            .setMaxUpdateDelayMillis(1000)
            .build()
        val locationCallback = object : LocationCallback() {
            @SuppressLint("SetTextI18n")
            override fun onLocationResult(locationResult: LocationResult) {
                for (location in locationResult.locations) {
                    // Calculate distance if needed

                    val speed = location.speed // Speed in meters/second

                    // Calculate speed
                    val speedKmH = speed * 3.6 // Convert speed to km/h

                    speedtext=String.format("%.1f", speed * 3.6)+" km/hr"
                    if (speedKmH <= 250) {
                            val data = calculateSeekBarValue(speedKmH, 250, 55)
                            scope.launch {
                                progress.animateTo(
                                    targetValue = data.toFloat(),
                                    animationSpec = tween(
                                        durationMillis = 1000,
                                        easing = FastOutLinearInEasing
                                    )
                                )
                            }

//                        progress.value= data.toFloat()
//                        CoroutineScope(Dispatchers.IO).launch {
//
//                            progress.animateTo(
//                                targetValue = data.toFloat(),
//                                animationSpec = tween(
//                                    durationMillis = 1000,
//                                    easing = FastOutLinearInEasing
//                                )
//                            )
//                        }
                    }


                    // Update UI based on speed

                }
            }
        }

        // Request location updates
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                null /* Looper */
            )
        }


        // Cleanup when not needed
        onDispose {
            fusedLocationClient.removeLocationUpdates(locationCallback)
        }
    }
    Column(modifier = Modifier.fillMaxSize()) {

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .background(color = colorResource(id = R.color.background_blue))
        ) {
            SpeedometerComposeView(
                progress = progress.value.toInt(),
                needleColor = Color.Red,
                speedTextColor = colorResource(
                    id = R.color.white,
                ),
                movingSpeedTextColor = Color.Red
            )


            /*Button(
                onClick = {
                    scope.launch {
                        progress.animateTo(
                            targetValue = 40f,
                            animationSpec = tween(
                                durationMillis = 1000,
                                easing = FastOutLinearInEasing
                            )
                        )
                    }
                }
            ) {
                Text(text = "Go")
            }*/

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.TopCenter
            ) {
                Text(text = speedtext)
            }
        }

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .background(color = colorResource(id = R.color.white))
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(horizontal = 16.dp)
                    .fillMaxHeight() // Fill the available height
                    .width(200.dp) // Set fixed width
                    .background(color = Color.Black)
            ) {

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .width(30.dp)
                            .padding(8.dp)
                            .background(color = Color.White)
                    )
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .width(30.dp)
                            .padding(8.dp)
                            .background(color = Color.White)
                    )

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .width(30.dp)
                            .padding(8.dp)
                            .background(color = Color.White)
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier.offset(y = offsetY.value.dp)
                    ) {
                        Image(

                            painter = painterResource(id = R.drawable.car),
                            contentDescription = "Your Image",
                            modifier = Modifier
                                .size(100.dp)
                                .padding(8.dp)
                                .align(Alignment.Center)

                        )
                    }
                }
                /*modifier = Modifier
                    .width(400.dp)
                    .height(300.dp)
                    .padding(8.dp)
                    .align(Alignment.Center)
                    .background(color = Color.Green)*/
            }

        }

    }

}

fun calculateSeekBarValue(rangeValue: Double, rangeMaxValue: Int, seekBarMaxValue: Int): Int {
    return ((rangeValue.toFloat() / rangeMaxValue.toFloat()) * seekBarMaxValue).toInt()
}

@Preview
@Composable
fun prev() {
    EqualDivide()
}