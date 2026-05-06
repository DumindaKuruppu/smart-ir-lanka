package com.lanka.smartir.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Air
import androidx.compose.material.icons.filled.Tv
import androidx.compose.material.icons.filled.WindPower
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.lanka.smartir.ui.theme.LankaTeal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddDeviceScreen(
    onDeviceAdded: (Device) -> Unit,
    onBack: () -> Unit
) {
    val availableDevices = listOf(
        Device("1", "Living Room TV", "Innovex", DeviceType.TV, Icons.Default.Tv),
        Device("2", "Master Fan", "Abans", DeviceType.FAN, Icons.Default.WindPower),
        Device("3", "Bedroom AC", "Innovex", DeviceType.AC, Icons.Default.Air),
        Device("4", "Dialog TV", "Dialog", DeviceType.TV, Icons.Default.Tv),
        Device("5", "Peo TV", "SLT", DeviceType.TV, Icons.Default.Tv),
        Device("6", "Guest Room Fan", "KDK", DeviceType.FAN, Icons.Default.WindPower),
        Device("7", "Kitchen TV", "Samsung", DeviceType.TV, Icons.Default.Tv),
        Device("8", "Office AC", "LG", DeviceType.AC, Icons.Default.Air)
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Add Remote", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp)
        ) {
            items(availableDevices) { device ->
                ListItem(
                    headlineContent = { Text(device.name) },
                    supportingContent = { Text(device.brand) },
                    leadingContent = {
                        Icon(
                            device.icon,
                            contentDescription = null,
                            tint = LankaTeal
                        )
                    },
                    trailingContent = {
                        IconButton(onClick = { onDeviceAdded(device) }) {
                            Icon(Icons.Default.Add, contentDescription = "Add")
                        }
                    }
                )
                HorizontalDivider(thickness = 0.5.dp, color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.2f))
            }
        }
    }
}
