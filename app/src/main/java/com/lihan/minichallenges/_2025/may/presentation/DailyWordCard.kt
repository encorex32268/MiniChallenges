package com.lihan.minichallenges._2025.may.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lihan.minichallenges.R
import com.lihan.minichallenges._2025.may.domain.WordDefinition
import com.lihan.minichallenges._2025.may.domain.wordList
import com.lihan.minichallenges._2025.may.ui.DailyWordCardBg
import com.lihan.minichallenges._2025.may.ui.DailyWordCardButtonColor
import com.lihan.minichallenges._2025.may.ui.DailyWordCardIconColor
import com.lihan.minichallenges._2025.may.ui.DailyWordCardPrimaryText
import com.lihan.minichallenges._2025.may.ui.DailyWordCardSecondaryText
import com.lihan.minichallenges.ui.theme.MiniChallengesTheme

val PoltawskiNowy = FontFamily(
    fonts = listOf(Font(R.font.poltawskinowy_variablefont_wght)))

@Composable
fun DailyWordCardScreen(
    modifier: Modifier = Modifier,
    enableSpeechButton: Boolean = false,
    onSpeakerClick: (WordDefinition) -> Unit
){
    val wordDefinition = remember{ wordList.random() }
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(brush = MaterialTheme.colorScheme.DailyWordCardBg),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.DailyWordCardIconColor
            )
        ){
            Column(
                modifier = Modifier.padding(
                    horizontal = 32.dp,
                    vertical = 120.dp
                ),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = wordDefinition.word,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = MaterialTheme.colorScheme.DailyWordCardPrimaryText,
                        fontFamily = PoltawskiNowy,
                        fontWeight = FontWeight.Bold,
                        fontSize = 38.sp
                    )
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = wordDefinition.definition,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.DailyWordCardSecondaryText,
                        fontFamily = PoltawskiNowy,
                        fontWeight = FontWeight.Normal,
                        fontSize = 19.sp
                    )
                )
            }

        }
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .alpha(
                    if (enableSpeechButton){
                        1f
                    }else{
                        0.5f
                    }
                )
                .clickable{
                    if (enableSpeechButton){
                        onSpeakerClick(wordDefinition)
                    }
                }
                .background(
                    color = MaterialTheme.colorScheme.DailyWordCardButtonColor,
                    shape = CircleShape
                )
                .padding(12.dp)

        ){
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.volume_max),
                contentDescription = "volume_max",
                tint = Color.Unspecified
            )
        }

    }
}

@Composable
@Preview
private fun DailyWordCardScreenPreview(){
    MiniChallengesTheme {
        DailyWordCardScreen(
            onSpeakerClick = {}
        )
    }
}