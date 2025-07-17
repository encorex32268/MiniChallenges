package com.lihan.minichallenges

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.lihan.minichallenges._2025.may.data.AndroidTextToSpeech
import com.lihan.minichallenges._2025.may.presentation.DailyWordCardScreen
import com.lihan.minichallenges.ui.theme.MiniChallengesTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val textSpeaker = AndroidTextToSpeech(applicationContext)
            val speechState by textSpeaker.isSpeakerReady.collectAsStateWithLifecycle()
            MiniChallengesTheme {
                val scope = rememberCoroutineScope()
                Surface(modifier = Modifier.fillMaxSize()) {
                    DailyWordCardScreen(
                        onSpeakerClick = {
                            scope.launch {
                                textSpeaker.speak(it.word)
                                delay(1000L)
                                textSpeaker.speak(it.definition)
                            }
                        },
                        enableSpeechButton = speechState
                    )
                }
            }
        }
    }
}
