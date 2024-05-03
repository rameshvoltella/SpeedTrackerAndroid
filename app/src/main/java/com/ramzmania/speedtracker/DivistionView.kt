package com.ramzmania.speedtracker

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ramzmania.speedtracker.views.SpeedometerComposeView
import kotlinx.coroutines.launch

@Composable
fun EqualDivide()
{
    var targetValue by remember { mutableStateOf(0f) }
    val progress = remember(targetValue) { Animatable(initialValue = 0f) }
    val scope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize()) {

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .background(color = colorResource(id = R.color.background_blue))
        ) {
            SpeedometerComposeView(progress = progress.value.toInt(), needleColor = Color.Red, speedTextColor = colorResource(
                id = R.color.white,
            ),movingSpeedTextColor= Color.Red
            )

            Button(
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
            ){

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

                Image(

                    painter = painterResource(id = R.drawable.car),
                    contentDescription = "Your Image",
                    modifier = Modifier
                        .size(100.dp)
                        .padding(8.dp)
                        .align(Alignment.Center)

                )

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

@Preview
@Composable
fun prev()
{
    EqualDivide()
}