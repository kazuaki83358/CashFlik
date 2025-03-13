package com.example.cashflik_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cashflik_app.screens.LoginPage
import com.example.cashflik_app.screens.SecondLoadingScreen
import com.example.cashflik_app.screens.FirstSplashScreen
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

    // This composable handles screen transitions
    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
            FirstSplashScreen(navController = navController)
        }
        composable("second_loading") {
            SecondLoadingScreen(navController = navController)
        }
        composable("login") {
            LoginPage() // LoginPage doesn't need NavController in this case
        }
    }
}