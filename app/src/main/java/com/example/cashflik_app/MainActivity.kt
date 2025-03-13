package com.example.cashflik_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.cashflik_app.screens.LoginPage
import com.example.cashflik_app.screens.SecondLoadingScreen
import com.example.cashflik_app.screens.FirstSplashScreen
import com.example.cashflik_app.screens.SignupPage
import com.example.cashflik_app.screens.OtpScreen
import kotlinx.coroutines.delay
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
    val currentRoute = navBackStackEntry?.destination?.route ?: "splash" // Default route

    val context = LocalContext.current // ✅ Move this inside the @Composable function

    // This composable handles screen transitions
    NavHost(navController = navController, startDestination = currentRoute) {
        composable("splash") {
            FirstSplashScreen(navController = navController)
        }
        composable("second_loading") {
            SecondLoadingScreen(navController = navController)
        }
        composable("login") {
            LoginPage(onSignupClick = { navController.navigate("signup") })
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
                onOtpVerified = { enteredOtp ->
                    // Handle OTP verification logic (API call)
                    val otpVerifiedSuccessfully = true // Replace with actual logic

                    if (otpVerifiedSuccessfully) {
                        // ✅ Use context safely inside the lambda
                        (context as? ComponentActivity)?.finishAffinity()
                    } else {
                        // Handle OTP verification failure (e.g., show an error message)
                    }
                }
            )
        }
    }
}
