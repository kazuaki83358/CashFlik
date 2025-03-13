package com.example.cashflik_app.screens

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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cashflik_app.ui.theme.CustomBlueColor // Replace with your actual theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotOtpScreen( // Renamed for clarity
    phoneNumber: String, // Added phoneNumber parameter
    onBackClick: () -> Unit,
    onOtpVerified: (String) -> Unit
) {
    var otpValues by remember { mutableStateOf(List(4) { "" }) }
    val focusRequesters = List(4) { FocusRequester() }
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = CustomBlueColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
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
                modifier = Modifier.clickable { onBackClick() }
            )
        }

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
                    text = "Enter the 4-digit code sent to $phoneNumber", // Use phoneNumber
                    style = TextStyle(fontSize = 14.sp, color = Color.Gray, textAlign = TextAlign.Center),
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    repeat(4) { index ->
                        OutlinedTextField(
                            value = otpValues[index],
                            onValueChange = { value ->
                                if (value.length <= 1 && value.all { it.isDigit() }) {
                                    val newOtpValues = otpValues.toMutableList()
                                    if (index < newOtpValues.size) {
                                        newOtpValues[index] = value
                                        otpValues = newOtpValues.toList()

                                        if (value.length == 1 && index < 3) {
                                            focusRequesters[index + 1].requestFocus()
                                        }
                                    }
                                }
                            },
                            modifier = Modifier
                                .width(60.dp)
                                .padding(4.dp)
                                .focusRequester(focusRequesters[index]),
                            singleLine = true,
                            textStyle = TextStyle(
                                textAlign = TextAlign.Center,
                                color = Color.Black
                            ),
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                        )
                    }
                }

                Button(
                    onClick = {
                        val enteredOtp = otpValues.joinToString("")
                        onOtpVerified(enteredOtp)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
                ) {
                    Text(text = "Verify OTP", color = Color.White)
                }

                Text(
                    text = "Resend OTP",
                    style = TextStyle(color = Color.Blue, textDecoration = TextDecoration.Underline),
                    modifier = Modifier.padding(top = 16.dp)
                )

                Text(
                    text = "0:30s",
                    style = TextStyle(color = Color.Gray),
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        }
    }
}