package com.lanka.smartir.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.lanka.smartir.ui.components.GlassCard
import com.lanka.smartir.ui.theme.LankaTeal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    isPolarityShiftEnabled: Boolean,
    onPolarityShiftChange: (Boolean) -> Unit,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Settings", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp)
        ) {
            Text(
                "Compatibility",
                style = MaterialTheme.typography.titleMedium,
                color = Color.White,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            GlassCard(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            "IR Polarity Shift",
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            "Handle Samsung/Xiaomi IR polarity shifts",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White.copy(alpha = 0.6f)
                        )
                    }
                    Switch(
                        checked = isPolarityShiftEnabled,
                        onCheckedChange = onPolarityShiftChange,
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = LankaTeal,
                            checkedTrackColor = LankaTeal.copy(alpha = 0.5f)
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
            
            Text(
                "App Info",
                style = MaterialTheme.typography.titleMedium,
                color = Color.White,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            
            GlassCard(modifier = Modifier.fillMaxWidth()) {
                Text("Version: 1.0.0 (Pro)", color = Color.White)
                Text("Region: Sri Lanka", color = Color.White.copy(alpha = 0.6f))
            }
        }
    }
}
