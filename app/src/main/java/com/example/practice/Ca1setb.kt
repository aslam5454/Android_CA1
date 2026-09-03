package com.example.practice

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.RadioButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

class Ca1setb : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProfileScreen()

        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen() {
    var role by remember { mutableStateOf("Member") }
    var showSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    OutlinedCard(
        modifier = Modifier.fillMaxSize().padding(20.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("User Profile")
            Text("Name: Aslam")
            Text("Role: $role")
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = { showSheet = true }
            ) {
                Text("View Bio & Settings")
            }
        }
    }
    if (showSheet) {
        ModalBottomSheet(sheetState = sheetState, onDismissRequest = { showSheet = false }
        ) {
            Column(modifier = Modifier.fillMaxWidth().padding(20.dp)
            ) {
                Text("Select User Role")
                listOf("Admin", "Member", "Guest").forEach {
                    Row {
                        RadioButton(
                            selected = role == it, onClick = { role = it }
                        )
                        Text(text = it, modifier = Modifier.padding(top = 12.dp))
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = {
                        Toast.makeText(context ,"Saved",Toast.LENGTH_SHORT).show()
                        scope.launch { sheetState.hide()
                            showSheet = false
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Save & Close")
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview10() {
    ProfileScreen()
}