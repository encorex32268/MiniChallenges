package com.lihan.minichallenges._2025.february.data

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import com.lihan.minichallenges._2025.february.domain.BatteryObserve
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged


class DeviceBatteryObserve(
    private val context: Context
): BatteryObserve {

    override fun updateBatteryLevel(): Flow<Float> = callbackFlow {
        val batteryReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                val level = (intent?.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) ?: -1) * 0.01f
                trySend(level).isSuccess
            }
        }

        val filter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        context.registerReceiver(batteryReceiver, filter)

        awaitClose {
            context.unregisterReceiver(batteryReceiver)
        }
    }.distinctUntilChanged()
}