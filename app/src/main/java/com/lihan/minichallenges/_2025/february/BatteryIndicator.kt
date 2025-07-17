package com.lihan.minichallenges._2025.february

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.lihan.minichallenges.R
import com.lihan.minichallenges.ui.theme.MiniChallengesTheme


/**
 *  Test: adb shell dumpsys battery set level 70
 */

enum class BatteryStatus{
    LOW,
    MEDIUM,
    FULL
}


@Composable
fun BatterySection(
    level: Float,
    modifier: Modifier = Modifier,
    outSizeCornerRadius: Dp = 16.dp
){

    val batteryStatus = remember(level){
        when(level){
            in 0.0..0.20 -> BatteryStatus.LOW
            in 0.20..0.80 -> BatteryStatus.MEDIUM
            in 0.80..1.0 -> BatteryStatus.FULL
            else -> BatteryStatus.FULL
        }
    }

    val isBeating = remember(batteryStatus){
        batteryStatus == BatteryStatus.LOW
    }

    val infiniteTransition = rememberInfiniteTransition(label = "pulse")

    val animatedScale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 600, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "scale"
    )

    val animatedColor by infiniteTransition.animateColor(
        initialValue = Color(0xFFEF1242),
        targetValue = Color(0xFFFF4E51),
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 600, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "color"
    )
    val finalScale = if (isBeating) animatedScale else 1f
    val finalColor = if (isBeating) animatedColor else Color(0xFFC1C5D2)
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ){
        Icon(
            modifier = Modifier
                .size(48.dp)
                .graphicsLayer(
                    scaleX = finalScale,
                    scaleY = finalScale
                ),
            imageVector = when(batteryStatus){
                BatteryStatus.LOW -> ImageVector.vectorResource(R.drawable.low_light_small)
                else -> ImageVector.vectorResource(R.drawable.low_dark)
            },
            contentDescription = "Low battery",
            tint = finalColor
        )
        Box(
            modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
        ){
            BatteryIndicator(
                modifier = Modifier
                    .fillMaxHeight(0.75f)
                    .fillMaxWidth()
                ,
                level = level,
                outSizeCornerRadius = outSizeCornerRadius
            )
        }

        Icon(
            modifier = Modifier.size(48.dp),
            imageVector = when(batteryStatus){
                BatteryStatus.FULL -> ImageVector.vectorResource(R.drawable.full_light)
                else -> ImageVector.vectorResource(R.drawable.full_dark)
            },
            contentDescription = "Full battery",
            tint = Color.Unspecified
        )

    }
}

@Composable
fun BatteryIndicator(
    level: Float,
    modifier: Modifier = Modifier,
    outSizeCornerRadius: Dp = 16.dp
) {
    val density = LocalDensity.current

    val animatedLevel by animateFloatAsState(
        targetValue = level.coerceIn(0f, 1f),
        animationSpec = tween(durationMillis = 700),
        label = "Battery Progress"
    )

    Canvas(
        modifier = modifier
    ) {
        val cornerRadius = with(density) {
            CornerRadius(outSizeCornerRadius.toPx(), outSizeCornerRadius.toPx())
        }
        val batteryWidth = with(density) { size.width }
        val batteryHeight = with(density) { size.height }

        val batteryContentWidth = when {
            animatedLevel <= 0.20f -> batteryWidth * 0.2f
            animatedLevel <= 0.80f -> batteryWidth * animatedLevel
            else -> batteryWidth
        }

        val batteryContentColor = when (level) {
            in 0.0..0.20 -> Color(0xFFFF4E51)
            in 0.20..0.80 -> Color(0xFFFCB966)
            in 0.80..1.0 -> Color(0xFF19D181)
            else -> Color(0xFF19D181)
        }


        //batteryContent
        drawRoundRect(
            color = batteryContentColor,
            size = Size(
                width = batteryContentWidth,
                height = batteryHeight
            ),
            topLeft = Offset(0f, 0f),
            style = Fill,
            cornerRadius = cornerRadius
        )

        val lineCount = when {
            level <= 0.20 -> 0
            level <= 0.40 -> 1
            level <= 0.60 -> 2
            level <= 0.80 -> 3
            else -> 4
        }
        repeat(lineCount) {
            val lineX = batteryWidth * 0.2f * (it + 1)
            drawLine(
                color = Color.White.copy(alpha = 0.5f),
                start = Offset(lineX, 0f),
                end = Offset(lineX, batteryHeight + 10f),
                strokeWidth = 10f
            )
        }


        //body
        drawRoundRect(
            color = Color.White,
            size = Size(batteryWidth, batteryHeight),
            topLeft = Offset(0f, 0f),
            style = Stroke(
                width = 15.dp.value
            ),
            cornerRadius = cornerRadius
        )
        //batteryHead
        drawRoundRect(
            color = Color.White,
            size = Size(batteryWidth / 20, batteryHeight / 2),
            topLeft = Offset(batteryWidth, batteryHeight * 0.25f),
            cornerRadius = cornerRadius
        )
    }

}


@Composable
@Preview(
    showBackground = true,
    showSystemUi = true,
    backgroundColor = 0xFFE7E9EF
)
private fun BatteryIndicatorPreview() {
    MiniChallengesTheme {
        var level by remember {
            mutableFloatStateOf(0f)
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){

//            Button(
//                onClick = {
//                    level += 0.1f
//                }
//            ) {
//                Text(" + ")
//            }
//
//            Button(
//                onClick = {
//                    level -= 0.1f
//                }
//            ) {
//                Text(" - ")
//            }

            BatterySection(
                modifier = Modifier.fillMaxWidth().height(100.dp),
                level = level,
            )
        }
    }

}