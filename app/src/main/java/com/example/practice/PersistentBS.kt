package com.example.practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

// Compose Layouts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape

// Material 3 Components
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch

import androidx.compose.ui.tooling.preview.Preview
class PersistentBS : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Makes app draw behind status bar
        setContent {
            PersistentBottomSheetScreen() // Calling our composable screen
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersistentBottomSheetScreen() {

    // 1. Scaffold state to control the persistent sheet
    val scaffoldState = rememberBottomSheetScaffoldState()

    // 2. Coroutine scope required for programmatic state animations
    val coroutineScope = rememberCoroutineScope()

    // 3. Replace standard Scaffold with BottomSheetScaffold
    BottomSheetScaffold(
        scaffoldState = scaffoldState,

        // 4. Set the visible height when sheet is in collapsed state
        sheetPeekHeight = 80.dp,

        sheetShape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        sheetContainerColor = MaterialTheme.colorScheme.surfaceVariant,

        // 5. THE PERSISTENT BOTTOM SHEET CONTENT SLOT
        sheetContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 10.dp)
            ) {
                // Persistent Header / Mini Player Bar
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            text = "CSE226 - Android App Deployment",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Lecture 01 • Live Streaming",
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                    }

                    IconButton(onClick = { /* Handle Play */ }) {
                        Icon(
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = "Play"
                        )
                    }
                }

                Divider(modifier = Modifier.padding(vertical = 12.dp))

                // Expanded Sheet Content (Visible when swiped or animated up)
                Text(
                    text = "Lecture Notes & Outline",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "1. Introduction to Material Design 3\n" +
                            "2. Modal vs. Persistent Bottom Sheets\n" +
                            "3. Coroutine-driven State Animations\n" +
                            "4. Hands-on Classroom Demo",
                    fontSize = 14.sp,
                    lineHeight = 22.sp
                )

                Spacer(modifier = Modifier.height(30.dp))
            }
        }
    ) { innerPadding ->

        // 6. MAIN SCREEN CONTENT SLOT
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Main Screen View",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Buttons to control the sheet programmatically
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    Button(
                        onClick = {
                            coroutineScope.launch {
                                scaffoldState.bottomSheetState.expand()
                            }
                        }
                    ) {
                        Text("Expand Sheet")
                    }

                    OutlinedButton(
                        onClick = {
                            coroutineScope.launch {
                                scaffoldState.bottomSheetState.partialExpand()
                            }
                        }
                    ) {
                        Text("Collapse Sheet")
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PersistentBottomSheetScreenPreview() {
    PersistentBottomSheetScreen()
}