package com.lihan.minichallenges._2025.february.domain

import kotlinx.coroutines.flow.Flow

interface BatteryObserve {
    fun updateBatteryLevel(): Flow<Float>
}