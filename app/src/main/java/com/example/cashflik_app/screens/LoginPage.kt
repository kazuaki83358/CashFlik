package com.example.cashflik_app.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cashflik_app.R
import com.example.cashflik_app.ui.theme.CustomBlueColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = CustomBlueColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // Top Section (Logo and Title)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 60.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo), // Replace with your logo
                contentDescription = "Logo",
                modifier = Modifier.size(140.dp)
            )
        }

        // Login Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clip(RoundedCornerShape(16.dp)),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFFFFFFF) // Changed card color
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "LOGIN HERE",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "Welcome back you've been missed!",
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = Color.Gray
                    ),
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Mobile Number Input
                var mobileNumber by remember { mutableStateOf("") }
                OutlinedTextField(
                    value = mobileNumber,
                    onValueChange = { mobileNumber = it },
                    label = { Text("Mobile Number") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    leadingIcon = { // Changed to leadingIcon
                        Icon(
                            painter = painterResource(id = R.drawable.ic_phone), // Replace with your phone icon
                            contentDescription = "Phone Icon",
                            modifier = Modifier.size(24.dp) // Adjusted icon size
                        )
                    }
                )

                // Password Input
                var password by remember { mutableStateOf("") }
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    visualTransformation = PasswordVisualTransformation(),
                    leadingIcon = { // Changed to leadingIcon
                        Icon(
                            painter = painterResource(id = R.drawable.ic_lock), // Replace with your lock icon
                            contentDescription = "Lock Icon",
                            modifier = Modifier.size(24.dp) // Adjusted icon size
                        )
                    }
                )

                // Forgot Password
                Text(
                    text = "Forgot Password",
                    style = TextStyle(
                        fontSize = 12.sp,
                        color = Color.Blue
                    ),
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(bottom = 16.dp)
                )

                // Login Button
                Button(
                    onClick = { /* Handle Login */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)) // Corrected
                ) {
                    Text(text = "Login", color = Color.White)
                }

                // Signup Link
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "If You don't have an account? ")
                    Text(
                        text = "Signup",
                        style = TextStyle(color = Color.Blue)
                    )
                }

                // Social Login Options
                Text(
                    text = "or continue with",
                    style = TextStyle(
                        fontSize = 12.sp,
                        color = Color.Gray
                    ),
                    modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    IconButton(onClick = { /* Handle Google Login */ }) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_google), // Replace with your Google icon
                            contentDescription = "Google Login",
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPagePreview() {
    LoginPage()
}