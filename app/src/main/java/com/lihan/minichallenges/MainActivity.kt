package com.lihan.minichallenges

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.lihan.minichallenges.february2025.BatteryIndicator
import com.lihan.minichallenges.february2025.data.DeviceBatteryObserve
import com.lihan.minichallenges.february2025.domain.BatteryObserve
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
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val batteryLevelObserve by batteryObserve.updateBatteryLevel().collectAsStateWithLifecycle(0f)
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xFFC8C8C8))
                            .padding(innerPadding),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Text(text = "Battery Level: $batteryLevelObserve")
                        BatteryIndicator(
                            level = batteryLevelObserve,
                            widthDp = 200.dp,
                            heightDp = 100.dp
                        )

                    }
                }
            }
        }
    }
}
