package com.example.cashflik_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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
    NavHost(navController = navController, startDestination = "splash") { // Set splash as startDestination

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
            HomeScreen(navController = navController)
        }

        composable("addReview") {
            AddReviewScreen(navController = navController)
        }

        composable("reviewHistory") {
            ReviewHistoryScreen(navController = navController)
        }

        composable("wallet") {
            WalletScreen(navController = navController)
        }

        composable(
            "reviewDetails/{title}/{description}/{image}/{date}/{points}/{stars}",
            arguments = listOf(
                navArgument("title") { type = NavType.StringType },
                navArgument("description") { type = NavType.StringType },
                navArgument("image") { type = NavType.IntType },
                navArgument("date") { type = NavType.StringType },
                navArgument("points") { type = NavType.StringType },
                navArgument("stars") { type = NavType.FloatType }
            )
        ) { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title") ?: ""
            val description = backStackEntry.arguments?.getString("description") ?: ""
            val image = backStackEntry.arguments?.getInt("image") ?: R.drawable.ic_launcher_foreground
            val date = backStackEntry.arguments?.getString("date") ?: ""
            val points = backStackEntry.arguments?.getString("points") ?: ""
            val stars = backStackEntry.arguments?.getFloat("stars") ?: 0f

            ReviewDetailsScreen(
                navController = navController,
                title = title,
                description = description,
                image = image,
                date = date,
                points = points,
                stars = stars
            )
        }
    }
}