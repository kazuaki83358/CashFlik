package com.example.cashflik_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.cashflik_app.screens.*
import com.example.cashflik_app.ui.theme.CashflikAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

    LocalContext.current // Unused, can remove if not needed.

    NavHost(navController = navController, startDestination = currentRoute) {
        composable("splash") {
            FirstSplashScreen(navController = navController)
        }
        composable("second_loading") {
            SecondLoadingScreen(navController = navController)
        }
        composable("login") {
            LoginPage(
                navController = navController,
                onForgotPasswordClick = { navController.navigate("forgotPassword") },
                onLoginSuccess = { navController.navigate("home") }
            )
        }
        composable("signup") {
            SignupPage(navController = navController)
        }
        composable("otp") {
            OtpScreen(navController = navController) {
                navController.navigate("login")
            }
        }
        composable("forgotPassword") {
            ForgotPasswordScreen(navController = navController) { mobileNumber ->
                navController.navigate("forgetOtp/$mobileNumber")
            }
        }
        composable("forgetOtp/{mobile}") { backStackEntry ->
            val mobileNumber = backStackEntry.arguments?.getString("mobile") ?: ""
            ForgotOtpScreen(
                phoneNumber = mobileNumber,
                onBackClick = { navController.navigate("login") },
                onOtpVerified = {
                    navController.navigate("createNewPassword") {
                        popUpTo("forgotPassword") { inclusive = true }
                    }
                }
            )
        }
        composable("createNewPassword") {
            CreateNewPasswordScreen(
                navController = navController,
                onSubmitClick = {
                    navController.navigate("passwordUpdated") {
                        popUpTo("createNewPassword") { inclusive = true }
                    }
                }
            )
        }
        composable("passwordUpdated") {
            PasswordUpdatedScreen(onLoginClick = { navController.navigate("login") })
        }
        composable("home") {
            HomeScreen()
        }
    }
}