package com.lanka.smartir.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Code
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lanka.smartir.ui.components.GlassCard
import com.lanka.smartir.ui.theme.LankaTeal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    isPolarityShiftEnabled: Boolean,
    onPolarityShiftChange: (Boolean) -> Unit,
    onBack: () -> Unit
) {
    val onSurface = MaterialTheme.colorScheme.onSurface
    val onBackground = MaterialTheme.colorScheme.onBackground

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Settings", color = onBackground) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = onBackground
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Transparent
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
            // Compatibility Section
            Text(
                "Compatibility",
                style = MaterialTheme.typography.titleMedium,
                color = onBackground,
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
                            color = onSurface
                        )
                        Text(
                            "Fix for Samsung/Xiaomi hardware",
                            style = MaterialTheme.typography.bodySmall,
                            color = onSurface.copy(alpha = 0.6f)
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

            Spacer(modifier = Modifier.height(32.dp))

            // Developer Section
            Text(
                "Developer Info",
                style = MaterialTheme.typography.titleMedium,
                color = onBackground,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            GlassCard(modifier = Modifier.fillMaxWidth()) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.Code,
                        contentDescription = null,
                        tint = LankaTeal,
                        modifier = Modifier.size(32.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(
                            "Smart IR Lanka Team",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = onSurface
                        )
                        Text(
                            "Crafted in Sri Lanka 🇱🇰",
                            fontSize = 14.sp,
                            color = onSurface.copy(alpha = 0.6f)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    "Building high-end smart home solutions for local hardware.",
                    style = MaterialTheme.typography.bodySmall,
                    color = onSurface.copy(alpha = 0.8f)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { /* Open contact email or website */ },
                    colors = ButtonDefaults.buttonColors(containerColor = LankaTeal.copy(alpha = 0.1f)),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Contact Support", color = LankaTeal, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // App Version Footer
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        "Version 1.0.0 (Pro)",
                        fontSize = 12.sp,
                        color = onSurface.copy(alpha = 0.4f)
                    )
                    Text(
                        "© 2024 Smart IR Lanka",
                        fontSize = 12.sp,
                        color = onSurface.copy(alpha = 0.4f)
                    )
                }
            }
        }
    }
}
