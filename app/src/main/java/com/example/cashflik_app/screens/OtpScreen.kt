package com.example.cashflik_app.screens

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cashflik_app.ui.theme.CustomBlueColor
import com.example.cashflik_app.viewmodel.SignupViewModel
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OtpScreen(
    navController: NavController,
    signupViewModel: SignupViewModel,
    phoneNumber: String,
    password: String,
    verificationId: String // Receive verificationId
) {
    var otpValues by remember { mutableStateOf(List(6) { "" }) }
    val focusRequesters = List(6) { FocusRequester() }
    val context = LocalContext.current
    var isLoading by remember { mutableStateOf(false) }
    var timer by remember { mutableStateOf(30) }
    var canResend by remember { mutableStateOf(false) }

    // Handle back navigation to the signup screen
    BackHandler {
        navController.navigate("signup") {
            popUpTo("otp") { inclusive = true }
        }
    }

    // Start timer for OTP resend (Dummy, will be implemented later)
    LaunchedEffect(Unit) {
        while (timer > 0) {
            delay(1000L)
            timer--
        }
        canResend = true
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = CustomBlueColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // Back button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = Color.White,
                modifier = Modifier.clickable {
                    navController.navigate("signup") {
                        popUpTo("otp") { inclusive = true }
                    }
                }
            )
        }

        // OTP Card UI
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
                    text = "ENTER VERIFICATION CODE",
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "Enter the 6-digit code sent to (+91) $phoneNumber",
                    style = TextStyle(fontSize = 14.sp, color = Color.Gray, textAlign = TextAlign.Center),
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // OTP Input Fields
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    repeat(6) { index ->
                        OutlinedTextField(
                            value = otpValues[index],
                            onValueChange = { value ->
                                if (value.length <= 1 && value.all { it.isDigit() }) {
                                    val newOtpValues = otpValues.toMutableList()
                                    newOtpValues[index] = value
                                    otpValues = newOtpValues.toList()

                                    if (value.isNotEmpty() && index < 5) {
                                        focusRequesters[index + 1].requestFocus()
                                    }
                                } else if (value.isEmpty() && otpValues[index].isNotEmpty()) {
                                    val newOtpValues = otpValues.toMutableList()
                                    newOtpValues[index] = ""
                                    otpValues = newOtpValues.toList()
                                    if (index > 0) {
                                        focusRequesters[index - 1].requestFocus()
                                    }
                                }
                            },
                            modifier = Modifier
                                .width(50.dp)
                                .padding(4.dp)
                                .focusRequester(focusRequesters[index]),
                            singleLine = true,
                            textStyle = TextStyle(
                                textAlign = TextAlign.Center,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            ),
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                        )
                    }
                }

                // Loading Indicator
                if (isLoading) {
                    CircularProgressIndicator(modifier = Modifier.padding(top = 16.dp))
                }

                // Verify OTP Button
                Button(
                    onClick = {
                        val enteredOtp = otpValues.joinToString("")
                        isLoading = true
                        signupViewModel.verifyOtp(
                            otp = enteredOtp,
                            phoneNumber = phoneNumber,
                            password = password,
                            verificationId = verificationId, // Pass verificationId
                            onSuccess = {
                                isLoading = false
                                Toast.makeText(context, "OTP Verified! Account Created.", Toast.LENGTH_LONG).show()
                                navController.navigate("login") {
                                    popUpTo("otp") { inclusive = true }
                                    launchSingleTop = true
                                }
                            },
                            onError = { error ->
                                isLoading = false
                                Toast.makeText(context, error, Toast.LENGTH_LONG).show()
                            }
                        )
                    },

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
                    enabled = otpValues.all { it.isNotEmpty() } && !isLoading
                ) {
                    Text(text = "Verify OTP", color = Color.White)
                }

                // Resend OTP (Dummy)
                Text(
                    text = if (canResend) "Resend OTP" else "Resend OTP in 0:${timer}s",
                    style = TextStyle(color = if (canResend) Color.Blue else Color.Gray, textDecoration = TextDecoration.Underline),
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .clickable(enabled = canResend) {
                            canResend = false
                            timer = 30
                            // TODO: Implement resend OTP logic
                        }
                )
            }
        }
    }
}
