package com.example.cashflik_app.screens

import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.addCallback
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.semantics.Role
import androidx.navigation.NavController // Import NavController
import com.example.cashflik_app.R
import com.example.cashflik_app.ui.theme.CustomBlueColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginPage(
    navController: NavController, // Add NavController parameter
    onForgotPasswordClick: () -> Unit,
    onLoginSuccess: () -> Unit
) {
    val onBackPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    BackPressHandler(onBackPressedDispatcher)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = CustomBlueColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // Top Section (Logo)
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .size(140.dp)
                .padding(top = 60.dp)
                .align(Alignment.CenterHorizontally) // Center the Image horizontally
        )

        // Login Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clip(RoundedCornerShape(16.dp)),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "LOGIN HERE",
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Text(
                    text = "Welcome back! You've been missed.",
                    style = TextStyle(fontSize = 14.sp, color = Color.Gray),
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Mobile Number Input
                var mobileNumber by remember { mutableStateOf("") }
                OutlinedTextField(
                    value = mobileNumber,
                    onValueChange = { mobileNumber = it },
                    label = { Text("Mobile Number") },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_phone),
                            contentDescription = "Phone Icon",
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        cursorColor = Color.Black,
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = Color.Gray
                    )
                )

                // Password Input
                var password by remember { mutableStateOf("") }
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
                    visualTransformation = PasswordVisualTransformation(),
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_lock),
                            contentDescription = "Lock Icon",
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        cursorColor = Color.Black,
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = Color.Gray
                    )
                )

                // Forgot Password Link
                Text(
                    text = "Forgot Password?",
                    style = TextStyle(fontSize = 12.sp, color = Color.Blue),
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(bottom = 16.dp)
                        .clickable(onClick = { onForgotPasswordClick() }, role = Role.Button)
                )

                // Login Button
                Button(
                    onClick = {
                        // TODO: Implement actual login logic here.
                        // If login is successful, call onLoginSuccess.
                        onLoginSuccess()
                    },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
                ) {
                    Text(text = "Login", color = Color.White)
                }

                // Signup Link
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    Text(text = "Don't have an account? ")
                    Text(
                        text = "Signup",
                        style = TextStyle(color = Color.Blue, textDecoration = TextDecoration.Underline),
                        modifier = Modifier.clickable(onClick = { navController.navigate("signup") }, role = Role.Button) // Use navController here
                    )
                }

                // Social Login
                Text(
                    text = "or continue with",
                    style = TextStyle(fontSize = 12.sp, color = Color.Gray),
                    modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                )

                IconButton(onClick = { /* Handle Google Login */ }) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_google),
                        contentDescription = "Google Login",
                        modifier = Modifier.size(42.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun BackPressHandler(onBackPressedDispatcher: OnBackPressedDispatcher?) {
    val context = LocalContext.current
    onBackPressedDispatcher?.addCallback(context as ComponentActivity) {
        context.finish()
    }
}