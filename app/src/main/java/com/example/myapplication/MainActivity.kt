package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LampTestScreen(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun LampTestScreen(modifier: Modifier = Modifier) {
    var log by remember { mutableStateOf("") }

    fun appendLog(text: String) {
        log += text + "\n"
    }

    Column(modifier = modifier
        .fillMaxSize()
        .padding(16.dp)) {

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(onClick = {
                val lamp = Lamp()

                lamp.turnOn()
                appendLog(if (lamp.isShining()) "PASS: Lamp is shining" else "FAIL: Lamp not shining")
                lamp.turnOff()
                appendLog(if (!lamp.isShining()) "PASS: Lamp off" else "FAIL: Lamp still on")

                lamp.turnOn()
                repeat(10) { lamp.brighten() }
                appendLog(if (lamp.getIntensity() == 10) "PASS: Intensity 10" else "FAIL: Wrong intensity")

                lamp.brighten()
                appendLog(if (lamp.isBulbBurned() && !lamp.isOn()) "PASS: Bulb burned, lamp off" else "FAIL: Burn logic failed")

                lamp.replaceBulb()
                lamp.turnOn()
                repeat(lamp.getIntensity()) { lamp.dim() }
                appendLog(if (!lamp.isOn()) "PASS: Lamp off after dim" else "FAIL: Lamp still on")

                lamp.turnOff()
                appendLog(if (lamp.replaceBulb()) "PASS: Bulb replaced off" else "FAIL: Cannot replace off")
                lamp.turnOn()
                appendLog(if (!lamp.replaceBulb()) "PASS: Cannot replace while on" else "FAIL: Replaced while on")

                repeat(11) { lamp.brighten() } // burn
                lamp.turnOn()
                appendLog(if (!lamp.isShining()) "PASS: Lamp not shining with burned bulb" else "FAIL: Lamp shining with burned bulb")

            }) {
                Text("Run Lamp Tests")
            }

            Button(onClick = { log = "" }) {
                Text("Reset Logs")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = log)
    }
}