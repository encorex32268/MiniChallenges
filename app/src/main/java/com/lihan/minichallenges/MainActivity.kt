package com.lihan.minichallenges

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.lihan.minichallenges.february2025.data.DeviceBatteryObserve
import com.lihan.minichallenges.february2025.domain.BatteryObserve
import com.lihan.minichallenges.march2025.MarsWeather
import com.lihan.minichallenges.march2025.MarsWeatherInfoUi
import com.lihan.minichallenges.ui.theme.MiniChallengesTheme

class MainActivity : ComponentActivity() {

    private val batteryObserve: BatteryObserve by lazy {
        DeviceBatteryObserve(this@MainActivity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MiniChallengesTheme {
                var marsWeatherUi = remember {
                    MarsWeatherInfoUi(
                        placeName = "Olympus Mons",
                        temperature = "-63",
                        temperatureHeight = "-52",
                        temperatureLow = "-73",
                        windSpeed = "27km/h NW",
                        pressure = "600 Pa",
                        uv = "0.5 mSv/day",
                        martianDate = "914 Sol"
                    )
                }
                Surface(modifier = Modifier.fillMaxSize()) {
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
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
            }
        }
    }
}
