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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.practice.ui.theme.PracticeTheme
import kotlinx.coroutines.launch

class capractice2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProfileCard()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileCard() {

    var showSheet by remember {
        mutableStateOf(false)
    }

    val sheetState = rememberModalBottomSheetState()

    val coroutineScope = rememberCoroutineScope()

    OutlinedCard(
        modifier = Modifier
            .padding(20.dp)
    ) {

        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            Text("Aslam Husain")

            Text("@aslamhusain")

            Button (
                onClick = {
                    showSheet = true
                }
            ) {
                Text("View Full Bio")
            }
        }
    }

    if (showSheet) {

        ModalBottomSheet(
            onDismissRequest = {
                showSheet = false
            },
            sheetState = sheetState
        ) {

            ProfileBioContent(
                onClose = {

                    coroutineScope.launch {

                        sheetState.hide()

                        showSheet = false
                    }
                }
            )
        }
    }
}

@Composable
fun ProfileBioContent(
    onClose: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {

        Text("Full Bio")

        Text("Name: Aslam Husain")

        Text("Handle: @aslamhusain")

        Text("Developer and Computer Science student")

        Text("Skills: Kotlin, Android, Java, C++, React")

        Text("Location: India")

        Button(
            onClick = {
                onClose()
            }
        ) {
            Text("Close")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview4() {
   ProfileCard()
}