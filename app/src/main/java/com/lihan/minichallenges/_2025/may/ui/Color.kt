package com.lihan.minichallenges._2025.may.ui

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

//May
//Daily Word Card
val ColorScheme.DailyWordCardButtonColor
    get() = Color(0xFF6B74F8)

val ColorScheme.DailyWordCardBg
    @Composable
    get() = Brush.verticalGradient(
        listOf(
            Color(0xFF6B74F8),
            Color(0xFFFDE5F3),
            Color(0xFFFEF7EE),
        )
    )
val ColorScheme.DailyWordCardIconColor
    get() = Color(0xFFFFFFFF)

val ColorScheme.DailyWordCardDecorative
    get() = Color(0xFFFFFFFF).copy(alpha = 0.4f)

val ColorScheme.DailyWordCardPrimaryText
    get() = Color(0xFF13182C)

val ColorScheme.DailyWordCardSecondaryText
    get() = Color(0xFF4C4F59)