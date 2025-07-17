@file:OptIn(ExperimentalAnimationApi::class)

package com.lihan.minichallenges._2025.february

import com.lihan.minichallenges.ui.theme.MiniChallengesTheme


import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.lihan.minichallenges.R

private val fontFamily = FontFamily(
    fonts = listOf<Font>(
        Font(resId = R.font.figtree_medium, weight = FontWeight.Medium),
        Font(resId = R.font.figtree_semibold, weight = FontWeight.SemiBold)
    )
)

@Composable
fun ThousandsSeparatorPicker(
    title: String = "Thousands separator",
    texts: List<String> = listOf("1.000", "1,000", "1 000"),
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 32.dp
) {
    val density = LocalDensity.current

    var selectedIndex by remember {
        mutableIntStateOf(0)
    }
    var boxSize by remember {
        mutableStateOf<IntSize?>(null)
    }
    var dynamicOffsetX by remember {
        mutableFloatStateOf(0f)
    }

    val textOffsetXList = remember {
        mutableStateListOf<Pair<Int,Float>>()
    }

    var rectAnimatable = animateFloatAsState(
        targetValue = dynamicOffsetX,
        animationSpec = tween(1000)
    )

    Column(
        modifier = modifier.fillMaxWidth().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
    ){
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(color = Color(0x148138FF))
                .drawBehind{
                    if (boxSize != null){
                        val width = with(density){(boxSize?.width?.dp?.value?:0f) - cornerRadius.value}
                        val height = with(density){(boxSize?.height?.dp?.value?:0f) - cornerRadius.value}
                        drawRoundRect(
                            color = Color.White,
                            topLeft = Offset(16f + rectAnimatable.value, cornerRadius.value/2),
                            size = Size(
                                width = width,
                                height = height
                            ),
                            cornerRadius = CornerRadius(
                                x = cornerRadius.value,
                                y = cornerRadius.value
                            )
                        )
                    }
                }
            ,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            texts.forEachIndexed { index , text ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(16.dp))
                        .onGloballyPositioned{
                            if (boxSize == null){
                                boxSize = it.size
                            }
                            if (textOffsetXList.size != text.length){
                                textOffsetXList.add(index to with(density) { it.positionInParent().x } )
                            }
                        }
                        .clickable{
                            selectedIndex = index
                            dynamicOffsetX = textOffsetXList[index].second
                        }
                        .padding(16.dp)
                    ,
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = text,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center,
                            color = if (selectedIndex == index) Color.Black else Color(0xFF24005A)
                        )
                    )
                }
            }

        }
    }
}

@Composable
@Preview(showBackground = true)
private fun CustomTabPreview() {
    MiniChallengesTheme {
        ThousandsSeparatorPicker()
    }
}