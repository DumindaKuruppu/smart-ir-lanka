package com.lanka.smartir.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lanka.smartir.ui.components.GlassButton
import com.lanka.smartir.ui.components.GlassIconButton
import com.lanka.smartir.ui.theme.LankaTeal
import com.lanka.smartir.ui.theme.VibrantAmber

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TVRemoteScreen(
    deviceName: String,
    onBack: () -> Unit
) {
    var showNumPad by remember { mutableStateOf(false) }
    var isPowerOn by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(deviceName, color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                actions = {
                    IconButton(onClick = { showNumPad = !showNumPad }) {
                        Icon(
                            if (showNumPad) Icons.Default.Apps else Icons.Default.Dialpad,
                            contentDescription = "Toggle NumPad",
                            tint = if (showNumPad) LankaTeal else Color.White
                        )
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
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Power and Mute
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                GlassIconButton(
                    icon = Icons.Default.PowerSettingsNew,
                    contentDescription = "Power",
                    onClick = { isPowerOn = !isPowerOn },
                    tint = if (isPowerOn) VibrantAmber else Color.White
                )
                GlassIconButton(
                    icon = Icons.Default.VolumeOff,
                    contentDescription = "Mute",
                    onClick = { }
                )
            }

            AnimatedVisibility(
                visible = showNumPad,
                enter = expandVertically(),
                exit = shrinkVertically()
            ) {
                NumberPad()
            }

            if (!showNumPad) {
                Spacer(modifier = Modifier.weight(1f))
                DPad()
                Spacer(modifier = Modifier.weight(1f))
                
                // Volume and Channel Controls
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    VerticalControlGroup("VOL")
                    VerticalControlGroup("CH")
                }
                
                Spacer(modifier = Modifier.weight(0.5f))

                // Colored Buttons
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    ColorButton(Color.Red)
                    ColorButton(Color.Green)
                    ColorButton(Color.Yellow)
                    ColorButton(Color.Blue)
                }
            }
        }
    }
}

@Composable
fun DPad() {
    Box(
        modifier = Modifier.size(220.dp),
        contentAlignment = Alignment.Center
    ) {
        // Center OK button
        GlassIconButton(
            icon = Icons.Default.Check,
            contentDescription = "OK",
            onClick = { },
            modifier = Modifier.size(70.dp)
        )

        // Directional buttons
        DirectionalButton(Icons.Default.KeyboardArrowUp, Modifier.align(Alignment.TopCenter))
        DirectionalButton(Icons.Default.KeyboardArrowDown, Modifier.align(Alignment.BottomCenter))
        DirectionalButton(Icons.Default.KeyboardArrowLeft, Modifier.align(Alignment.CenterStart))
        DirectionalButton(Icons.Default.KeyboardArrowRight, Modifier.align(Alignment.CenterEnd))
    }
}

@Composable
fun DirectionalButton(icon: androidx.compose.ui.graphics.vector.ImageVector, modifier: Modifier) {
    IconButton(
        onClick = { },
        modifier = modifier.size(60.dp)
    ) {
        Icon(icon, contentDescription = null, tint = Color.White, modifier = Modifier.size(36.dp))
    }
}

@Composable
fun NumberPad() {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.padding(vertical = 16.dp)
    ) {
        val rows = listOf(
            listOf("1", "2", "3"),
            listOf("4", "5", "6"),
            listOf("7", "8", "9"),
            listOf("-", "0", "PREV")
        )
        rows.forEach { row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                row.forEach { digit ->
                    GlassButton(
                        onClick = { },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(digit, color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Composable
fun VerticalControlGroup(label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        GlassIconButton(icon = Icons.Default.Add, contentDescription = "+", onClick = { })
        Text(label, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp)
        GlassIconButton(icon = Icons.Default.Remove, contentDescription = "-", onClick = { })
    }
}

@Composable
fun ColorButton(color: Color) {
    Box(
        modifier = Modifier
            .size(50.dp, 12.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(color.copy(alpha = 0.8f))
    )
}
