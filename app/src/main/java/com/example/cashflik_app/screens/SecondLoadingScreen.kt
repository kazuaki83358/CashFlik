package com.example.cashflik_app.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.draw.alpha
import androidx.compose.foundation.Canvas
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.unit.Dp
import androidx.compose.animation.core.*
import kotlinx.coroutines.delay
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cashflik_app.R
import com.example.cashflik_app.ui.theme.CustomBlueColor

@Composable
fun SecondLoadingScreen(navController: NavController) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    var currentPage by remember { mutableStateOf(0) } // State for the current page
    val pageCount = 3 // Number of loading screens/pages

    // Prevent back button navigation
    BackHandler {
        // Do nothing, prevent back navigation
    }

    // Simulate navigation after a delay (if necessary)
    LaunchedEffect(Unit) {
        delay(1000)
        navController.navigate("login") // Navigate to the login screen after delay
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = CustomBlueColor)
    ) {
        // Coin behind text (Left side, faded)
        Image(
            painter = painterResource(id = R.drawable.coin1),
            contentDescription = "Background Coin",
            modifier = Modifier
                .size(screenWidth * 0.4f) // 40% of screen width
                .align(Alignment.CenterStart)
                .offset(x = (-screenWidth * 0.1f), y = screenHeight * 0.25f)
                .alpha(0.6f) // Slightly faded effect
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = screenWidth * 0.05f) // 5% padding
                .align(Alignment.Center)
        ) {
            Image(
                painter = painterResource(id = R.drawable.secondscreenimage),
                contentDescription = "Main Image",
                modifier = Modifier.size(screenWidth * 0.6f) // 60% of screen width
            )
            Spacer(modifier = Modifier.height(screenHeight * 0.02f)) // 2% of screen height

            Text(
                text = "Upload reviews and earn money",
                style = TextStyle(
                    fontSize = (screenWidth * 0.05f).value.sp, // Convert Dp to sp correctly
                    color = Color(0xFF00FF66),
                    textAlign = TextAlign.Center
                )
            )
            Spacer(modifier = Modifier.height(screenHeight * 0.01f)) // 1% of screen height

            Text(
                text = "You can consider ways to make money by writing and uploading reviews.",
                style = TextStyle(
                    fontSize = (screenWidth * 0.035f).value.sp, // Convert Dp to sp correctly
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            )
        }

        // Coin at the bottom right corner
        Image(
            painter = painterResource(id = R.drawable.coin2),
            contentDescription = "Bottom Right Coin",
            modifier = Modifier
                .size(screenWidth * 0.35f) // 35% of screen width
                .align(Alignment.BottomEnd)
                .offset(x = screenWidth * 0.05f, y = (-screenHeight * 0.01f))
        )

        // Page Indicator with animation
        PageIndicator(
            currentPage = currentPage,
            pageCount = pageCount,
            modifier = Modifier
                .align(Alignment.BottomCenter) // Position at the bottom center
                .padding(bottom = 24.dp) // Add some padding
        )

        // Simulate page changes with faster animation - Replace with your actual logic
        LaunchedEffect(Unit) {
            while (true) {
                delay(1000) // Reduce delay to 1 second for faster page change
                currentPage = (currentPage + 1) % pageCount
            }
        }
    }
}

@Composable
fun PageIndicator(
    currentPage: Int,
    pageCount: Int,
    modifier: Modifier = Modifier,
    activeColor: Color = Color.Green,
    inactiveColor: Color = Color.White,
    activeWidthRatio: Float = 2f,
    dotSize: Dp = 10.dp
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 0 until pageCount) {
            val isActive = i == currentPage

            val animatedDotWidth by animateDpAsState(
                targetValue = if (isActive) dotSize * activeWidthRatio else dotSize,
                animationSpec = tween(durationMillis = 100, easing = LinearOutSlowInEasing) // Reduced duration to 100 ms
            )

            val dotColor = if (isActive) activeColor else inactiveColor

            Canvas(modifier = Modifier.size(width = animatedDotWidth, height = dotSize)) {
                val cornerRadiusValue = dotSize.toPx() / 2
                drawRoundRect(
                    color = dotColor,
                    cornerRadius = CornerRadius(cornerRadiusValue, cornerRadiusValue)
                )
            }

            if (i < pageCount - 1) {
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SecondLoadingScreenPreview() {
    SecondLoadingScreen(navController = rememberNavController())
}
