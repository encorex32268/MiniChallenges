package com.lihan.minichallenges.march2025


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lihan.minichallenges.R
import com.lihan.minichallenges.ui.theme.BackgroundGradient
import com.lihan.minichallenges.ui.theme.CardBackground
import com.lihan.minichallenges.ui.theme.Fonts
import com.lihan.minichallenges.ui.theme.MiniChallengesTheme
import com.lihan.minichallenges.ui.theme.StarActive
import com.lihan.minichallenges.ui.theme.StarInactive
import com.lihan.minichallenges.ui.theme.TextColor

@Composable
fun DawnAndDusk(
    stars: Int,
    onStarClick: (Int) -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = MaterialTheme.colorScheme.BackgroundGradient
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(vertical = 120.dp , horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Image(
                modifier = Modifier.alpha(
                    if (isSystemInDarkTheme()){
                        0f
                    }else{
                        1f
                    }
                ),
                imageVector = ImageVector.vectorResource(R.drawable.sun),
                contentDescription = "Sun"
            )
            Image(
                modifier = Modifier.alpha(
                    if (isSystemInDarkTheme()){
                        1f
                    }else{
                        0f
                    }
                ),
                imageVector = ImageVector.vectorResource(R.drawable.moon),
                contentDescription = "Moon"
            )
        }
        Text(
            text = "How was your day ?",
            style = MaterialTheme.typography.displayMedium.copy(
                fontSize = 32.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.TextColor,
                fontFamily = Fonts.interFontFamily
            )
        )
        Spacer(Modifier.height(12.dp))
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(100.dp))
                .background(color = MaterialTheme.colorScheme.CardBackground)
                .padding(horizontal = 36.dp, vertical = 12.dp)
            ,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.75.dp, Alignment.CenterHorizontally)
        ){
            repeat(5){
                val index = it + 1
                Icon(
                    modifier = Modifier
                        .size(48.dp)
                        .clickable(
                            indication = null,
                            interactionSource = null,
                            onClick = {
                                onStarClick(index)
                            }
                        )
                    ,
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = if (index <= stars){
                        MaterialTheme.colorScheme.StarActive
                    }else{
                        MaterialTheme.colorScheme.StarInactive
                    }
                )
            }
        }



    }
}


@Composable
@PreviewLightDark
private fun DawnAndDuskPreview(){
    MiniChallengesTheme {
        var index by remember {
            mutableIntStateOf(0)
        }
        DawnAndDusk(
            stars = index,
            onStarClick = {
                index = it
            }
        )
    }
}
