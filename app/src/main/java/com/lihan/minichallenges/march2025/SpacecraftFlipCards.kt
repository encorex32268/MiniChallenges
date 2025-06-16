package com.lihan.minichallenges.march2025

import android.annotation.SuppressLint
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lihan.minichallenges.R
import com.lihan.minichallenges.ui.theme.MiniChallengesTheme
import kotlinx.serialization.Serializable

@Serializable
data class PeopleResponse(
    val people: List<People>,
    val number: Int,
    val message: String
)

@Serializable
data class People(
    val craft: String,
    val name: String
)

val FrontBackground = Color(0xFF420794)
val BackBackground = Color(0xFFCAD5FC)

val FrontReversIcon = Color(0xFF551DC3)
val BackReversIcon = Color(0xFFB9BDF6)

@Composable
fun SpacecraftFlipCards(
    frontSide: @Composable () -> Unit,
    backSide: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    minHeight: Dp = 200.dp
) {
    val density = LocalDensity.current

    var isFlip by remember {
        mutableStateOf(false)
    }
    val animateRotationY by animateFloatAsState(
        targetValue = if (isFlip) 180f else 0f,
        tween(
            600,
            easing = CubicBezierEasing(0.4f, 0f, 0.2f, 1f)
        )
    )
    val colorFilter by remember {
        derivedStateOf{
            if (isFlip){
                ColorFilter.tint(color = Color(0xFF635CEF).copy(alpha = 0.15f))
            }else{
                ColorFilter.tint(color = Color(0xFF635CEF).copy(alpha = 0.5f))
            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = minHeight)
            .graphicsLayer{
                rotationY = animateRotationY
                cameraDistance = 24 * density.density
            },
        contentAlignment = Alignment.Center
    ){
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.flip_background),
            contentDescription = "Background",
            tint = if (animateRotationY > 90f){
                BackBackground
            }else{
                FrontBackground
            }
        )
        Box(
            modifier = Modifier
                .paint(
                    painter = painterResource(R.drawable.flip_border),
                    colorFilter = colorFilter
                ),
            contentAlignment = Alignment.Center
        ){

            Image(
                modifier = Modifier.align(Alignment.BottomEnd),
                painter = painterResource(R.drawable.flip_rocker),
                contentDescription = null,
                colorFilter = colorFilter
            )
            IconButton(
                modifier = Modifier.align(Alignment.TopStart),
                onClick = {
                    isFlip = !isFlip
                }
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.flip_revers),
                    contentDescription = "revers",
                    tint = if (isFlip) BackReversIcon else FrontReversIcon
                )
            }
            if(animateRotationY > 90f){
                backSide()
            }else{
                frontSide()
            }
        }
    }
}

@Composable
fun FrontCard(
    title: String,
    subTitle: String,
    modifier: Modifier = Modifier,
    titleTextStyle: TextStyle = MaterialTheme.typography.bodyMedium.copy(
        fontFamily = chivoMonoFont,
        fontSize = 28.sp,
        fontWeight = FontWeight.Medium,
        color = Color(0xFFE6E5FE)
    ),
    subTitleTextStyle: TextStyle = MaterialTheme.typography.bodyMedium.copy(
        fontFamily = chivoMonoFont,
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
        color = Color(0xFFB1AEFC)
    )
){
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = title,
            style = titleTextStyle
        )
        Text(
            text = subTitle,
            style = subTitleTextStyle
        )
    }

}

@Composable
@Preview
private fun FrontCardPreview(){
    MiniChallengesTheme {
        FrontCard(
            title = "ISS Spacecraft",
            subTitle = "12 crew members"
        )
    }
}

@SuppressLint("DefaultLocale")
@Composable
fun BackCard(
    people: List<People>,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium.copy(
        color = Color(0xFF1E093B),
        fontFamily = chivoMonoFont,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        textAlign = TextAlign.Start
    )
){
    Column(
        modifier = modifier.graphicsLayer{
            rotationY = -180f
        },
        verticalArrangement = Arrangement.Center,
    ){
        people.forEachIndexed {  index , people ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ){
                Text(
                    text = String.format("%2d." , index+1),
                    style = textStyle
                )
                Text(
                    text = people.name,
                    style = textStyle
                )
            }

        }
    }
}

@Composable
@Preview
private fun BackCardPreview(){
    val people = remember {
        (0..10).map {
            People(
                craft = "ISS",
                name = "Name ${it}"
            )
        }
    }
    MiniChallengesTheme {
        BackCard(
            people = people
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun SpacecraftFlipCardsPreview() {
    val people = listOf(
        People(craft = "ISS", name = "Oleg Kononenko"),
        People(craft = "ISS", name = "Nikolai Chub"),
        People(craft = "ISS", name = "Tracy Caldwell Dyson"),
        People(craft = "ISS", name = "Matthew Dominick"),
        People(craft = "ISS", name = "Michael Barratt"),
        People(craft = "ISS", name = "Jeanette Epps"),
        People(craft = "ISS", name = "Alexander Grebenkin"),
        People(craft = "ISS", name = "Butch Wilmore"),
        People(craft = "ISS", name = "Sunita Williams"),
        People(craft = "Tiangong", name = "Li Guangsu"),
        People(craft = "Tiangong", name = "Li Cong"),
        People(craft = "Tiangong", name = "Ye Guangfu")
    )

    MiniChallengesTheme {
        Box(
            modifier = Modifier.fillMaxSize().background(Color.White),
            contentAlignment = Alignment.Center
        ){
            SpacecraftFlipCards(
                modifier = Modifier.fillMaxWidth(),
                frontSide = {
                    FrontCard(
                        title = "ISS Spacecraft",
                        subTitle = "12 crew members"
                    )
                },
                backSide = {
                    BackCard(
                        people = people
                    )
                },
            )
        }
    }
}
