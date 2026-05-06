package com.lanka.smartir.ui.screens

import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.lanka.smartir.ui.theme.LankaTeal
import com.lanka.smartir.ui.theme.SmartIRLankaTheme
import com.lanka.smartir.ui.theme.VibrantAmber

@Composable
fun NoIrEmitterScreen() {
    val context = LocalContext.current
    val affiliateUrl = "https://example.com/usb-c-ir-blaster-affiliate" // Replace with actual affiliate link

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.Warning,
                contentDescription = null,
                modifier = Modifier.size(100.dp),
                tint = VibrantAmber
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            Text(
                text = "No IR Blaster Detected",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                text = "It seems your device doesn't have a built-in Infrared emitter. This is required to control appliances directly from your phone.",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                textAlign = TextAlign.Center
            )
            
            Spacer(modifier = Modifier.height(32.dp))
            
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = LankaTeal.copy(alpha = 0.1f)
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Don't worry!",
                        fontWeight = FontWeight.Bold,
                        color = LankaTeal
                    )
                    Text(
                        text = "You can still use this app by plugging in an external USB-C IR blaster.",
                        textAlign = TextAlign.Center,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    Button(
                        onClick = {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(affiliateUrl))
                            context.startActivity(intent)
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = LankaTeal)
                    ) {
                        Text("Get USB-C IR Blaster")
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            TextButton(
                onClick = { (context as? Activity)?.finish() }
            ) {
                Text(
                    text = "Exit App",
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "Light Mode")
@Composable
fun NoIrEmitterScreenPreview() {
    SmartIRLankaTheme(darkTheme = false) {
        NoIrEmitterScreen()
    }
}

@Preview(
    showBackground = true,
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun NoIrEmitterScreenDarkPreview() {
    SmartIRLankaTheme(darkTheme = true) {
        NoIrEmitterScreen()
    }
}
