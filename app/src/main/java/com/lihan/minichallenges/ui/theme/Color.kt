package com.lihan.minichallenges.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)


// DawnAndDuskThemeTransition
val ColorScheme.StarActive
    get() = Color(0xFFFF9334)

val ColorScheme.CardBackground
    @Composable
    get() = if (isSystemInDarkTheme()){
        Color(0xFF2C144D)
    }else{
        Color.White
    }

val ColorScheme.StarInactive
    @Composable
    get() = if (isSystemInDarkTheme()){
        Color(0xFF482D6D)
    }else{
        Color(0xFFD7E4F7)
    }

val ColorScheme.TextColor
    @Composable
    get() = if (isSystemInDarkTheme()){
        Color(0xFFE6E5FE)
    }else{
        Color(0xFF14042B)
    }

val ColorScheme.BackgroundGradient
    @Composable
    get() = if (isSystemInDarkTheme()){
        Brush.verticalGradient(
            colors = listOf(
                Color(0xFF210A41),
                Color(0xFF120327)
            )
        )
    }else{
        Brush.verticalGradient(
            colors = listOf(
                Color(0xFFC6ECFE),
                Color(0xFF6689F9)
            )
        )
    }
