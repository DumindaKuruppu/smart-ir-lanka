package com.lanka.smartir.data

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Air
import androidx.compose.material.icons.filled.Tv
import androidx.compose.material.icons.filled.WindPower
import com.lanka.smartir.ui.screens.Device
import com.lanka.smartir.ui.screens.DeviceType
import androidx.core.content.edit

class DeviceRepository(context: Context) {
    private val sharedPreferences = context.getSharedPreferences("smart_ir_prefs", Context.MODE_PRIVATE)

    private val allDevices = listOf(
        Device("1", "Living Room TV", "Innovex", DeviceType.TV, Icons.Default.Tv),
        Device("2", "Master Fan", "Abans", DeviceType.FAN, Icons.Default.WindPower),
        Device("3", "Bedroom AC", "Innovex", DeviceType.AC, Icons.Default.Air),
        Device("4", "Dialog TV", "Dialog", DeviceType.TV, Icons.Default.Tv),
        Device("5", "Peo TV", "SLT", DeviceType.TV, Icons.Default.Tv),
        Device("6", "Guest Room Fan", "KDK", DeviceType.FAN, Icons.Default.WindPower),
        Device("7", "Kitchen TV", "Samsung", DeviceType.TV, Icons.Default.Tv),
        Device("8", "Office AC", "LG", DeviceType.AC, Icons.Default.Air)
    )

    fun getAvailableDevices(): List<Device> = allDevices

    fun getSavedDeviceIds(): Set<String> {
        return sharedPreferences.getStringSet("saved_device_ids", setOf("1", "2")) ?: setOf("1", "2")
    }

    fun saveDeviceId(id: String) {
        val ids = getSavedDeviceIds().toMutableSet()
        ids.add(id)
        sharedPreferences.edit { putStringSet("saved_device_ids", ids) }
    }

    fun getUserDevices(): List<Device> {
        val savedIds = getSavedDeviceIds()
        return allDevices.filter { it.id in savedIds }
    }
}
