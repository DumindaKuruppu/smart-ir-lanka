package com.lanka.smartir.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.PowerSettingsNew
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lanka.smartir.ui.components.GlassButton
import com.lanka.smartir.ui.components.GlassIconButton
import com.lanka.smartir.ui.theme.LankaTeal
import com.lanka.smartir.ui.theme.VibrantAmber
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FanRemoteScreen(
    deviceName: String,
    onBack: () -> Unit
) {
    var speed by remember { mutableIntStateOf(1) }
    var isPowerOn by remember { mutableStateOf(false) }
    var isLightOn by remember { mutableStateOf(false) }
    val onSurfaceColor = MaterialTheme.colorScheme.onSurface

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(deviceName, color = MaterialTheme.colorScheme.onBackground) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.onBackground
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
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Power and Light Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                GlassIconButton(
                    icon = Icons.Default.PowerSettingsNew,
                    contentDescription = "Power",
                    onClick = { isPowerOn = !isPowerOn },
                    tint = if (isPowerOn) VibrantAmber else onSurfaceColor
                )
                GlassIconButton(
                    icon = Icons.Default.Lightbulb,
                    contentDescription = "Light",
                    onClick = { isLightOn = !isLightOn },
                    tint = if (isLightOn) LankaTeal else onSurfaceColor
                )
            }

            // Circular Speed Dial
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.size(300.dp)
            ) {
                SpeedDial(
                    speed = speed,
                    onSpeedChange = { speed = it }
                )
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = speed.toString(),
                        fontSize = 64.sp,
                        fontWeight = FontWeight.Bold,
                        color = LankaTeal
                    )
                    Text(
                        text = "SPEED",
                        fontSize = 14.sp,
                        color = onSurfaceColor.copy(alpha = 0.5f)
                    )
                }
            }

            // Timer Settings
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    "TIMER SETTINGS",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = onSurfaceColor.copy(alpha = 0.5f),
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    listOf("1h", "2h", "4h").forEach { timer ->
                        GlassButton(
                            modifier = Modifier.weight(1f),
                            onClick = { /* Set Timer */ }
                        ) {
                            Text(timer, color = onSurfaceColor, fontWeight = FontWeight.Medium)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SpeedDial(
    speed: Int,
    onSpeedChange: (Int) -> Unit
) {
    val trackColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)
    Canvas(modifier = Modifier
        .size(250.dp)
        .pointerInput(Unit) {
            detectTapGestures { offset ->
                // Simple hit detection for 5 segments
            }
        }
    ) {
        val strokeWidth = 20.dp.toPx()
        val radius = size.minDimension / 2 - strokeWidth

        // Draw background arc segments
        for (i in 1..5) {
            val startAngle = 150f + (i - 1) * 48f
            drawArc(
                color = if (i <= speed) LankaTeal else trackColor,
                startAngle = startAngle,
                sweepAngle = 40f,
                useCenter = false,
                style = Stroke(width = strokeWidth, cap = StrokeCap.Round),
                size = size.copy(width = radius * 2, height = radius * 2),
                topLeft = center.copy(x = center.x - radius, y = center.y - radius)
            )
        }
    }

    // Interactive buttons around the dial
    Box(modifier = Modifier.size(280.dp)) {
        for (i in 1..5) {
            val angle = (170f + (i - 1) * 50f) * (PI / 180f).toFloat()
            val x = 140.dp + 110.dp * cos(angle)
            val y = 140.dp + 110.dp * sin(angle)
            
            Surface(
                onClick = { onSpeedChange(i) },
                modifier = Modifier
                    .offset(x = x - 20.dp, y = y - 20.dp)
                    .size(40.dp),
                color = Color.Transparent
            ) {
                // Invisible touch targets
            }
        }
    }
}
