package com.lihan.minichallenges._2025.may.data

import android.content.Context
import android.speech.tts.TextToSpeech
import com.lihan.minichallenges._2025.may.domain.TextSpeaker
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.Locale

class AndroidTextToSpeech(
    private val context: Context
): TextSpeaker {

    private val _isSpeakerReady = MutableStateFlow(false)
    val isSpeakerReady = _isSpeakerReady.asStateFlow()

    private var tts: TextToSpeech?= null

    init {
        initTextToSpeech()
    }

    fun initTextToSpeech(){
        tts = TextToSpeech(
            context
        ) {status ->
            if (status == TextToSpeech.SUCCESS) {
                tts?.apply {
                    language = Locale.US
                    setSpeechRate(0.7f)
                }?: throw Exception("TextToSpeech Initial failed")
                _isSpeakerReady.update { true }
            }else{
                _isSpeakerReady.update { false }
            }
        }
    }


    override fun speak(text: String) {
        if (!_isSpeakerReady.value) return
        tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "tts1")
    }

    override fun shutdown() {
        if (!_isSpeakerReady.value) return
        tts?.shutdown()
    }
}