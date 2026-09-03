package com.example.practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practice.ui.theme.PracticeTheme

class ComposeAnimation : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
           AnimationScreen()
        }
    }
}

//@Composable
//fun AnimationScreen() {
//    var isExpanded by remember { mutableStateOf(false) }
//    val scale by animateFloatAsState(
//        targetValue = if (isExpanded) 1.2f else 1.0f,
//        animationSpec = tween(durationMillis = 300),
//        label = "scaleAnimation"
//    )
//
//    val alpha by animateFloatAsState(
//        targetValue = if (isExpanded) 0.5f else 1.0f,
//        animationSpec = tween(durationMillis = 300),
//        label = "alphaAnimation"
//    )
//
//    Box(modifier = Modifier.fillMaxSize(),
//        contentAlignment = Alignment.Center){
//        Box(modifier = Modifier.size((100+scale).dp).alpha(alpha)
//            .background(Color.Black).clickable{isExpanded=!isExpanded},
//            contentAlignment = Alignment.Center){
//            Text(text = if (isExpanded) "Shrink" else "Expand",
//                color = Color.White)
//
//        }
//    }
//
//}
@Composable
fun AnimationScreen() {
    Text(text="Compose Animation Demo - CSE226", modifier = Modifier
        .fillMaxWidth()
        .padding(top = 24.dp),
        textAlign = TextAlign.Center,
        fontSize = 28.sp,
        fontWeight = FontWeight.Bold)
    var isExpanded by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }
    val scale by animateFloatAsState(
        targetValue = if (isExpanded) 1.5f else 1f,
        animationSpec = tween(500),
        label = "scale"
    )

    val alpha by animateFloatAsState(
        targetValue = if (isExpanded) 0.5f else 1f,
        animationSpec = tween(500),
        label = "alpha"
    )

    val color by animateColorAsState(
        targetValue = if (isExpanded) Color.Blue else Color.Black,
        animationSpec = tween(5000),
        label = "color"
    )
    val rotation by animateFloatAsState(
        targetValue = if (isExpanded) 360f else 0f,
        animationSpec = tween(800),
        label = "rotation"
    )

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Box(
            modifier = Modifier
                .size(100.dp)
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                    this.alpha = alpha
                    rotationZ = rotation
                }
                .clip(RoundedCornerShape(16.dp))
                .background(color)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) {
                    isExpanded = !isExpanded
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = if (isExpanded) "Shrink" else "Expand",
                color = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    PracticeTheme {
       AnimationScreen()
    }
}