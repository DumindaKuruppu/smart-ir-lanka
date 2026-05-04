package com.lanka.smartir.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Air
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Tv
import androidx.compose.material.icons.filled.WindPower
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lanka.smartir.ui.components.GlassCard
import com.lanka.smartir.ui.components.GlassIconButton
import com.lanka.smartir.ui.theme.LankaTeal
import com.lanka.smartir.ui.theme.VibrantAmber

data class Device(
    val id: String,
    val name: String,
    val brand: String,
    val type: DeviceType,
    val icon: ImageVector
)

enum class DeviceType {
    TV, FAN, AC
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    onDeviceClick: (Device) -> Unit,
    onSettingsClick: () -> Unit
) {
    val devices = listOf(
        Device("1", "Living Room TV", "Innovex", DeviceType.TV, Icons.Default.Tv),
        Device("2", "Master Fan", "Abans", DeviceType.FAN, Icons.Default.WindPower),
        Device("3", "Bedroom AC", "Innovex", DeviceType.AC, Icons.Default.Air),
        Device("4", "Dialog TV", "Dialog", DeviceType.TV, Icons.Default.Tv),
        Device("5", "Peo TV", "SLT", DeviceType.TV, Icons.Default.Tv)
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Smart IR Lanka",
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                },
                actions = {
                    IconButton(onClick = onSettingsClick) {
                        Icon(Icons.Default.Settings, contentDescription = "Settings", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* Add device */ },
                containerColor = LankaTeal,
                contentColor = Color.White
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Device")
            }
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp)
        ) {
            Text(
                "My Devices",
                style = MaterialTheme.typography.headlineSmall,
                color = Color.White,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(devices) { device ->
                    DeviceCard(device = device, onClick = { onDeviceClick(device) })
                }
            }
        }
    }
}

@Composable
fun DeviceCard(device: Device, onClick: () -> Unit) {
    Surface(
        onClick = onClick,
        color = Color.Transparent
    ) {
        GlassCard(
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = device.icon,
                contentDescription = null,
                tint = LankaTeal,
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = device.name,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.White
            )
            Text(
                text = device.brand,
                fontSize = 12.sp,
                color = Color.White.copy(alpha = 0.6f)
            )
        }
    }
}
