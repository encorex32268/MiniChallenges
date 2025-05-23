package com.lihan.minichallenges.february2025.domain

import kotlinx.coroutines.flow.Flow

interface BatteryObserve {
    fun updateBatteryLevel(): Flow<Float>
}