package com.lihan.minichallenges

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.lihan.minichallenges.february2025.BatteryIndicator
import com.lihan.minichallenges.february2025.BatterySection
import com.lihan.minichallenges.february2025.data.DeviceBatteryObserve
import com.lihan.minichallenges.february2025.domain.BatteryObserve
import com.lihan.minichallenges.march2025.DawnAndDusk
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
                var stars by rememberSaveable {
                    mutableIntStateOf(0)
                }
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ){
                        DawnAndDusk(
                            stars = stars,
                            onStarClick = {
                                stars = it
                            }
                        )
                    }
                }
            }
        }
    }
}
