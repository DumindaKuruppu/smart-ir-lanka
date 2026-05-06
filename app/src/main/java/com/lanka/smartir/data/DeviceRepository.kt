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
        Device("1", "Innovex Fan", "Innovex", DeviceType.FAN, Icons.Default.WindPower),
        Device("2", "Panasonic Fan", "Panasonic", DeviceType.FAN, Icons.Default.WindPower),
        Device("3", "Dialog TV", "Dialog", DeviceType.TV, Icons.Default.Tv),
        Device("4", "Peo TV", "SLT", DeviceType.TV, Icons.Default.Tv),
        Device("5", "Freesat", "Freesat", DeviceType.TV, Icons.Default.Tv),
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
