package com.example.practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.practice.ui.theme.PracticeTheme
import kotlinx.coroutines.delay

class capractice : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticeTheme {
                CheckoutScreen()
            }
        }
    }
}

@Composable
fun CheckoutScreen() {

    var isProcessing by remember {
        mutableStateOf(false)
    }

    var statusText by remember {
        mutableStateOf("Ready to purchase")
    }

    LaunchedEffect(isProcessing) {

        if (isProcessing) {

            delay(2000)

            statusText = "Payment Verified"

            isProcessing = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(text = statusText)

        if (isProcessing) {

            CircularProgressIndicator(
                modifier = Modifier.padding(20.dp)
            )
        }

        Button (
            onClick = {
                isProcessing = true
                statusText = "Processing Payment..."
            },
            enabled = !isProcessing
        ) {
            Text("Complete Purchase")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    PracticeTheme {
        CheckoutScreen()
    }
}
