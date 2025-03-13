package com.example.cashflik_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.cashflik_app.screens.*
import com.example.cashflik_app.ui.theme.CashflikAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CashflikAppTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: "splash"

    val context = LocalContext.current

    NavHost(navController = navController, startDestination = currentRoute) {
        composable("splash") {
            FirstSplashScreen(navController = navController)
        }
        composable("second_loading") {
            SecondLoadingScreen(navController = navController)
        }
        composable("login") {
            LoginPage(
                onSignupClick = { navController.navigate("signup") },
                onForgotPasswordClick = { navController.navigate("forgotPassword") },
                onLoginSuccess = { navController.navigate("home") } // Add onLoginSuccess
            )
        }
        composable("signup") {
            SignupPage(
                onLoginClick = { navController.navigate("login") },
                onSignupClick = { navController.navigate("otp") }
            )
        }
        composable("otp") {
            OtpScreen(
                onBackClick = { navController.popBackStack() },
                onOtpVerified = { enteredOtp: String ->
                    // TODO: Implement actual OTP verification logic here
                    val otpVerifiedSuccessfully = true
                    if (otpVerifiedSuccessfully) {
                        (context as? ComponentActivity)?.finishAffinity()
                    } else {
                        // TODO: Handle OTP verification failure
                    }
                }
            )
        }
        composable("forgotPassword") {
            ForgotPasswordScreen(
                onBackClick = { navController.popBackStack() },
                onSendClick = { mobileNumber ->
                    navController.navigate("forgetOtp?mobile=$mobileNumber")
                }
            )
        }
        composable("forgetOtp?mobile={mobile}") { backStackEntry ->
            val mobileNumber = backStackEntry.arguments?.getString("mobile") ?: ""
            ForgotOtpScreen(
                phoneNumber = mobileNumber,
                onBackClick = { navController.popBackStack() },
                onOtpVerified = {
                    // TODO: Implement actual OTP verification logic here
                    val otpVerifiedSuccessfully = true
                    if (otpVerifiedSuccessfully) {
                        navController.navigate("createNewPassword")
                    } else {
                        // TODO: Handle OTP verification failure
                    }
                }
            )
        }
        composable("createNewPassword") {
            CreateNewPasswordScreen(
                onBackClick = { navController.popBackStack() },
                onSubmitClick = {
                    // TODO: Implement actual password reset logic here (API call)
                    // TODO: Handle password reset failure
                    navController.navigate("passwordUpdated")
                }
            )
        }
        composable("passwordUpdated") {
            PasswordUpdatedScreen(onLoginClick = { navController.navigate("login") })
        }
        composable("home") { // Add home route
            HomeScreen()
        }
    }
}