package com.lihan.minichallenges.february2025

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.lihan.minichallenges.ui.theme.MiniChallengesTheme


/**
 *  Test: adb shell dumpsys battery set level 70
 */
@Composable
fun BatteryIndicator(
    level: Float,
    widthDp: Dp,
    heightDp: Dp,
    modifier: Modifier = Modifier,
    outSizeCornerRadius: Dp = 16.dp
) {

    val density = LocalDensity.current

    val cornerRadius = with(density){
        CornerRadius(outSizeCornerRadius.toPx(),outSizeCornerRadius.toPx())
    }

    val batteryWidth = remember(widthDp){
        with(density){ widthDp.toPx() }
    }
    val batteryHeight = remember(heightDp){
        with(density){ heightDp.toPx() }
    }

    val batteryContentWidth = remember(level){
        when(level){
            in 0.0..0.20 -> batteryWidth * 0.2f
            in 0.20..0.80 -> batteryWidth * level
            in 0.80..1.0 -> batteryWidth
            else -> batteryWidth
        }
    }

    val batteryContentColor = remember(level) {
        when(level){
            in 0.0..0.20 -> Color(0xFFFF4E51)
            in 0.20..0.80 -> Color(0xFFFCB966)
            in 0.80..1.0 -> Color(0xFF19D181)
            else -> Color(0xFF19D181)
        }
    }

    val animatableBatteryContent = animateFloatAsState(
        targetValue = if (level <= 0) 0f else batteryContentWidth,
        animationSpec = tween(1000)
    )

    Canvas(
        modifier = modifier.size(
            width = widthDp,
            height = heightDp
        )
    ) {

        //batteryContent
        drawRoundRect(
            color = batteryContentColor,
            size = Size(
                width = animatableBatteryContent.value,
                height = batteryHeight
            ),
            topLeft = Offset(10f,10f),
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
                start = Offset(lineX, 10f),
                end = Offset(lineX, batteryHeight + 10f),
                strokeWidth = 10f
            )
        }


        //body
        drawRoundRect(
            color = Color.White,
            size = Size(batteryWidth,batteryHeight),
            topLeft = Offset(10f,10f),
            style = Stroke(
                width = 15.dp.value
            ),
            cornerRadius = cornerRadius
        )
        //batteryHead
        drawRoundRect(
            color = Color.White,
            size = Size(batteryWidth / 20 ,batteryHeight / 2),
            topLeft = Offset(batteryWidth + 2f, batteryHeight * 0.25f ) ,
            cornerRadius = cornerRadius
        )



    }

}

@Composable
@Preview(
    showBackground = true,
    backgroundColor = 0xFFC8C8C8
)
private fun BatteryIndicatorPreview() {
    MiniChallengesTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            BatteryIndicator(
                level = 0.21f,
                widthDp = 200.dp,
                heightDp = 200.dp
            )
        }
    }

}