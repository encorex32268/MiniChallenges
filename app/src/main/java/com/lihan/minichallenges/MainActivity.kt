package com.lihan.minichallenges

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import com.lihan.minichallenges.february2025.data.DeviceBatteryObserve
import com.lihan.minichallenges.february2025.domain.BatteryObserve
import com.lihan.minichallenges.march2025.BackCard
import com.lihan.minichallenges.march2025.FrontCard
import com.lihan.minichallenges.march2025.MarsWeather
import com.lihan.minichallenges.march2025.MarsWeatherInfoUi
import com.lihan.minichallenges.march2025.People
import com.lihan.minichallenges.march2025.PeopleResponse
import com.lihan.minichallenges.march2025.SpacecraftFlipCards
import com.lihan.minichallenges.ui.theme.MiniChallengesTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

class MainActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MiniChallengesTheme {
                var people = remember { mutableStateListOf<People>() }
                var numberString = remember { mutableStateOf("") }
                LaunchedEffect(Unit,people) {
                    if (people.isEmpty()){
                        launch{
                            val jsonString = withContext(Dispatchers.IO) {
                                this@MainActivity.assets.open("spacer.json")
                                    .bufferedReader()
                                    .use { it.readText() }
                            }
                            val result = Json.decodeFromString<PeopleResponse>(jsonString)
                            people.clear()
                            people.addAll(result.people)
                            numberString.value = result.number.toString()
                        }
                    }
               }
                Surface(modifier = Modifier.fillMaxSize()) {
                    Box(
                        modifier = Modifier.fillMaxSize().background(Color.White),
                        contentAlignment = Alignment.Center
                    ){
                        SpacecraftFlipCards(
                            frontSide = {
                                FrontCard(
                                    title = "ISS Spacecraft",
                                    subTitle = "${numberString.value} crew members"
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
        }
    }
}
