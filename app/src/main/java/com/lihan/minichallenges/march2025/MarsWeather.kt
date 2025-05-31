package com.lihan.minichallenges.march2025

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lihan.minichallenges.R
import com.lihan.minichallenges.ui.theme.MiniChallengesTheme


val chivoMonoFont = FontFamily(
    fonts = listOf<Font>(
        Font(R.font.chivomono_variablefont_wght)
    )
)


val Primary = Color(0xFF14171E)
val Secondary = Color(0xFF474F63)
val Orange = Color(0xFFCD533C)
val ConditionSurface = Color(0xFFF9E8E5)

val ColorScheme.BackgroundGradientMars
    get() = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF210A41),
            Color(0xFF120327)
        )
    )

@Composable
fun MarsWeather(
    placeName: String,
    temperature: String,
    temperatureHeight: String,
    temperatureLow: String,
    windSpeed: String,
    pressure: String,
    uv: String,
    martianDate: String,
    modifier: Modifier = Modifier
) {
    val density = LocalDensity.current
    val screenHeight = LocalConfiguration.current.screenHeightDp
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(brush = MaterialTheme.colorScheme.BackgroundGradientMars)
    ) {
        Image(
            modifier = Modifier
                .height((screenHeight * 0.45f).dp)
                .align(Alignment.BottomCenter),
            painter = painterResource(R.drawable.mars_surface),
            contentDescription = null,
            contentScale = ContentScale.FillHeight
        )
        Column(
            modifier = modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .padding(horizontal = 50.dp)
                .drawBehind {
                    val width = size.width
                    val height = size.height
                    val topRightCornerRadius = with(density) { 32.dp.toPx() }
                    val path = Path().apply {
                        lineTo(x = width - topRightCornerRadius, y = 0f)
                        lineTo(x = width, y = topRightCornerRadius)
                        lineTo(x = width, y = height)
                        lineTo(x = width, y = height)
                        lineTo(x = 0f, y = height)
                        close()
                    }
                    drawPath(
                        path = path,
                        color = Color.White
                    )

                }
                .padding(16.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.mars_location),
                    contentDescription = "Location",
                    tint = Color(0x4D9E83C5)
                )
                Text(
                    text = placeName,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontFamily = chivoMonoFont,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                        color = Color(0xFF9E83C5),
                    )
                )
            }
            Spacer(modifier = Modifier.height(86.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.mars_wind),
                    contentDescription = "Wind",
                    tint = Color(0xFFCD533C)
                )
                Text(
                    text = "Dust Storm",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontFamily = chivoMonoFont,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                        color = Color(0xFFCD533C),
                    )

                )
            }
            Row(
                modifier = Modifier.fillMaxWidth()
            ){
                Row(
                    modifier = Modifier.fillMaxWidth().weight(1f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = temperature,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Normal,
                            fontFamily = chivoMonoFont,
                            fontSize = 64.sp,
                            color = Color(0xFF14171E),
                        )
                    )
                    Text(
                        modifier = Modifier.alignByBaseline(),
                        text = "°C",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontFamily = chivoMonoFont,
                            fontWeight = FontWeight.Normal,
                            fontSize = 24.sp,
                            color = Color(0xFF14171E)
                        )
                    )
                }
                Column(
                    modifier = Modifier.align(Alignment.CenterVertically)
                ){
                    Text(
                        text = "H：${temperatureHeight}°C",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontFamily = chivoMonoFont,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            textAlign = TextAlign.Center,
                            color = Secondary
                        )
                    )
                    Text(
                        text = "L：${temperatureLow}°C",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontFamily = chivoMonoFont,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            textAlign = TextAlign.Center,
                            color = Secondary
                        )
                    )
                }
            }
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ){
                    InfoItem(
                        modifier = Modifier
                            .weight(1f),
                        title = "Wind speed",
                        text = windSpeed
                    )
                    InfoItem(
                        modifier = Modifier.weight(1f),
                        title = "Pressure",
                        text = pressure
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ){
                    InfoItem(
                        modifier = Modifier.weight(1f),
                        title = "UV Radiation",
                        text = uv
                    )
                    InfoItem(
                        modifier = Modifier.weight(1f),
                        title = "Martian date",
                        text = martianDate
                    )
                }
            }


        }
    }


}

@Composable
private fun InfoItem(
    title: String,
    text: String,
    titleTextStyle: TextStyle = MaterialTheme.typography.bodyMedium.copy(
        fontFamily = chivoMonoFont,
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        color = Orange
    ),
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium.copy(
        fontFamily = chivoMonoFont,
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
        color = Primary
    ),
    modifier: Modifier = Modifier
){
    Column(modifier = modifier
        .fillMaxWidth()
        .background(color = ConditionSurface)
        .padding(vertical = 12.dp)
        .padding(start = 12.dp)
        ,
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ){
        Text(
            text = title,
            style = titleTextStyle
        )
        Text(
            text = text,
            style = textStyle
        )
    }

}

data class MarsWeatherInfoUi(
    val placeName: String = "Olympus Mons",
    val temperature: String = "-63",
    val temperatureHeight: String = "-52",
    val temperatureLow: String = "-73",
    val windSpeed: String = "27km/h NW",
    val pressure: String = "600 Pa",
    val uv: String = "0.5 mSv/day",
    val martianDate: String = "914 Sol"
)

@Composable
@Preview()
private fun MarsWeatherPreview() {
    val marsWeatherUi  = MarsWeatherInfoUi()
    MiniChallengesTheme {
        MarsWeather(
            placeName = marsWeatherUi.placeName,
            temperature = marsWeatherUi.temperature,
            temperatureHeight = marsWeatherUi.temperatureHeight,
            temperatureLow = marsWeatherUi.temperatureLow,
            windSpeed = marsWeatherUi.windSpeed,
            pressure = marsWeatherUi.pressure,
            uv = marsWeatherUi.uv,
            martianDate = marsWeatherUi.martianDate
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun InfoItemPreview() {
    MiniChallengesTheme {
        InfoItem(
            title = "Wind speed",
            text = "27km/h NW"
        )
    }
}