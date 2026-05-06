package com.lanka.smartir

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import com.lanka.smartir.data.DeviceRepository
import com.lanka.smartir.ir.IrController
import com.lanka.smartir.ui.screens.*
import com.lanka.smartir.ui.theme.SmartIRLankaTheme

class MainActivity : ComponentActivity() {
    private lateinit var deviceRepository: DeviceRepository
    private lateinit var irController: IrController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        deviceRepository = DeviceRepository(this)
        irController = IrController(this)
        enableEdgeToEdge()
        setContent {
            var currentScreen by remember { 
                mutableStateOf<Screen>(
                    if (irController.hasIrEmitter()) Screen.Dashboard else Screen.NoIrEmitter
                )
            }
            var selectedDevice by remember { mutableStateOf<Device?>(null) }
            var irPolarityShift by remember { mutableStateOf(false) }

            var userDevices by remember { 
                mutableStateOf(deviceRepository.getUserDevices())
            }

            SmartIRLankaTheme {
                when (currentScreen) {
                    is Screen.Dashboard -> {
                        DashboardScreen(
                            devices = userDevices,
                            onDeviceClick = { device ->
                                selectedDevice = device
                                currentScreen = if (device.type == DeviceType.FAN) Screen.FanRemote else Screen.TVRemote
                            },
                            onSettingsClick = { currentScreen = Screen.Settings },
                            onAddDeviceClick = { currentScreen = Screen.AddDevice }
                        )
                    }
                    is Screen.AddDevice -> {
                        AddDeviceScreen(
                            availableDevices = deviceRepository.getAvailableDevices(),
                            onDeviceAdded = { device ->
                                if (!userDevices.any { it.id == device.id }) {
                                    deviceRepository.saveDeviceId(device.id)
                                    userDevices = deviceRepository.getUserDevices()
                                }
                                currentScreen = Screen.Dashboard
                            },
                            onBack = { currentScreen = Screen.Dashboard }
                        )
                    }
                    is Screen.FanRemote -> {
                        FanRemoteScreen(
                            deviceName = selectedDevice?.name ?: "Fan Remote",
                            irController = irController,
                            onBack = { currentScreen = Screen.Dashboard }
                        )
                    }
                    is Screen.TVRemote -> {
                        TVRemoteScreen(
                            deviceName = selectedDevice?.name ?: "TV Remote",
                            irController = irController,
                            onBack = { currentScreen = Screen.Dashboard }
                        )
                    }
                    is Screen.NoIrEmitter -> {
                        NoIrEmitterScreen()
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
    object AddDevice : Screen()
    object FanRemote : Screen()
    object TVRemote : Screen()
    object Settings : Screen()
    object NoIrEmitter : Screen()
}
