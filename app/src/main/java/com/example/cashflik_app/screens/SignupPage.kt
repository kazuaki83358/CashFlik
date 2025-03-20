package com.example.cashflik_app.screens

import android.app.Activity
import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.cashflik_app.R
import com.example.cashflik_app.ui.theme.CustomBlueColor
import com.example.cashflik_app.viewmodel.SignupViewModel
import com.google.i18n.phonenumbers.PhoneNumberUtil

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupPage(navController: NavController, signupViewModel: SignupViewModel = viewModel()) {
    val context = LocalContext.current
    var name by remember { mutableStateOf("") }
    var mobileNumber by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    val isLoading by signupViewModel.isLoading.collectAsState()

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
        )

        // Signup Card
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
                // Title
                Text(
                    text = "CREATE NEW ACCOUNT",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "Welcome back, you've been missed!",
                    style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray),
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Input Fields
                InputField(
                    value = name,
                    label = "Name",
                    icon = R.drawable.ic_person,
                    onValueChange = { name = it.trim() }
                )
                InputField(
                    value = mobileNumber,
                    label = "Mobile Number",
                    icon = R.drawable.ic_phone,
                    onValueChange = { mobileNumber = it.trim() }
                )
                InputField(
                    value = password,
                    label = "Password",
                    icon = R.drawable.ic_lock,
                    isPassword = true,
                    onValueChange = { password = it.trim() }
                )
                InputField(
                    value = confirmPassword,
                    label = "Confirm Password",
                    icon = R.drawable.ic_lock,
                    isPassword = true,
                    onValueChange = { confirmPassword = it.trim() }
                )

                // Signup Button
                Button(
                    onClick = {
                        if (!isValidMobileNumber(mobileNumber)) {
                            Toast.makeText(context, "Enter a valid mobile number with +91", Toast.LENGTH_LONG).show()
                            return@Button
                        }

                        if (password != confirmPassword) {
                            Toast.makeText(context, "Passwords do not match", Toast.LENGTH_LONG).show()
                            return@Button
                        }

                        val activity = context as? Activity
                        if (activity != null) {
                            signupViewModel.sendOtp(
                                phoneNumber = mobileNumber,
                                activity = activity,
                                onCodeSent = { verificationId -> // Receive verificationId
                                    navController.navigate("otp/$mobileNumber/$password/$verificationId") // Pass verificationId
                                },
                                onError = { error ->
                                    Toast.makeText(context, error, Toast.LENGTH_LONG).show()
                                }
                            )
                        } else {
                            Toast.makeText(context, "Error: Context is not an Activity", Toast.LENGTH_SHORT).show()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                    enabled = !isLoading
                ) {
                    Text(text = if (isLoading) "Loading..." else "Signup", color = Color.White)
                }

                // Login Link
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "Already have an account? ")
                    Text(
                        text = "Log in",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = Color.Blue,
                            textDecoration = TextDecoration.Underline
                        ),
                        modifier = Modifier.clickable(onClick = { navController.navigate("login") }, role = Role.Button)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(
    value: String,
    label: String,
    icon: Int,
    isPassword: Boolean = false,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        textStyle = MaterialTheme.typography.bodyMedium.copy(color = Color.Black),
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = if (isPassword) androidx.compose.ui.text.input.KeyboardType.Password else androidx.compose.ui.text.input.KeyboardType.Text
        ),
        leadingIcon = {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = label,
                modifier = Modifier.size(24.dp)
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
            cursorColor = MaterialTheme.colorScheme.primary,
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = Color.Gray
        )
    )
}

// Function to validate mobile number format
fun isValidMobileNumber(mobileNumber: String): Boolean {
    val phoneNumberUtil = PhoneNumberUtil.getInstance()
    return try {
        val parsedNumber = phoneNumberUtil.parse(mobileNumber, "IN") // "IN" for India
        phoneNumberUtil.isValidNumber(parsedNumber)
    } catch (e: Exception) {
        false
    }
}