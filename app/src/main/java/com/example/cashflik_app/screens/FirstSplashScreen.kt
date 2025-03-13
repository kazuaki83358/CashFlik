package com.example.cashflik_app.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
import com.example.cashflik_app.R
import com.example.cashflik_app.ui.theme.CustomBlueColor // Import the color

@Composable
fun FirstSplashScreen(navController: NavController) {
    // Simulate a delay before navigating
    LaunchedEffect(Unit) {
        delay(1000) // Wait for 2 seconds
        navController.navigate("second_loading") // Navigate to the next screen (second loading screen)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = CustomBlueColor), // Using the predefined color
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier.size(150.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FirstSplashScreenPreview() {
    // Pass a dummy NavController in the preview
    FirstSplashScreen(navController = rememberNavController())
}
