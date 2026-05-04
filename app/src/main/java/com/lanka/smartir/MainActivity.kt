package com.lanka.smartir

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import com.lanka.smartir.ui.screens.*
import com.lanka.smartir.ui.theme.SmartIRLankaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var currentScreen by remember { mutableStateOf<Screen>(Screen.Dashboard) }
            var selectedDevice by remember { mutableStateOf<Device?>(null) }
            var irPolarityShift by remember { mutableStateOf(false) }

            SmartIRLankaTheme {
                when (currentScreen) {
                    is Screen.Dashboard -> {
                        DashboardScreen(
                            onDeviceClick = { device ->
                                selectedDevice = device
                                currentScreen = if (device.type == DeviceType.FAN) Screen.FanRemote else Screen.TVRemote
                            },
                            onSettingsClick = { currentScreen = Screen.Settings }
                        )
                    }
                    is Screen.FanRemote -> {
                        FanRemoteScreen(
                            deviceName = selectedDevice?.name ?: "Fan Remote",
                            onBack = { currentScreen = Screen.Dashboard }
                        )
                    }
                    is Screen.TVRemote -> {
                        TVRemoteScreen(
                            deviceName = selectedDevice?.name ?: "TV Remote",
                            onBack = { currentScreen = Screen.Dashboard }
                        )
                    }
                    is Screen.Settings -> {
                        SettingsScreen(
                            isPolarityShiftEnabled = irPolarityShift,
                            onPolarityShiftChange = { irPolarityShift = it },
                            onBack = { currentScreen = Screen.Dashboard }
                        )
                    }
                }
            }
        }
    }
}

sealed class Screen {
    object Dashboard : Screen()
    object FanRemote : Screen()
    object TVRemote : Screen()
    object Settings : Screen()
}
