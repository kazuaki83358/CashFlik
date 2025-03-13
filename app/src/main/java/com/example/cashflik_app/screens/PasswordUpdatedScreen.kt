package com.example.cashflik_app.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cashflik_app.R

@Composable
fun PasswordUpdatedScreen(onLoginClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF192A56)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Logo Image
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "App Logo",
            modifier = Modifier.size(140.dp)
        )

        // Checkmark Icon
        Icon(
            painter = painterResource(id = R.drawable.tick),
            contentDescription = "Password Updated Checkmark",
            modifier = Modifier
                .size(140.dp)
                .padding(top = 24.dp),
            tint = Color.Unspecified // Disable global tinting
        )

        // Success Message
        Text(
            text = "PASSWORD UPDATED",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(top = 24.dp)
        )

        // Additional Message
        Text(
            text = "Your password has been updated!",
            fontSize = 16.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 8.dp, start = 32.dp, end = 32.dp)
        )

        // Login Button
        Button(
            onClick = onLoginClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp, start = 16.dp, end = 16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2ECC71))
        ) {
            Text(text = "Login", color = Color.White)
        }
    }
}