package com.example.cashflik_app.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cashflik_app.R
import com.example.cashflik_app.ui.theme.CustomGreenColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Notification", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF00215E),
                    titleContentColor = Color.White
                ),
                navigationIcon = {
                    IconButton(onClick = {
                        if (navController.previousBackStackEntry != null) {
                            navController.popBackStack()
                        } else {
                            navController.navigate("home")
                        }
                    }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(getAppNotifications()) { notification ->
                NotificationCard(notification = notification)
            }
        }
    }

    BackHandler {
        if (navController.previousBackStackEntry != null) {
            navController.popBackStack()
        } else {
            navController.navigate("home") {
                popUpTo(0)
            }
        }
    }
}

@Composable
fun NotificationCard(notification: AppNotification) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(notification.iconBackgroundColor),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    notification.iconText,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    notification.dateTime,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
                Text(
                    notification.title,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
                Text(
                    notification.description,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

data class AppNotification(
    val dateTime: String,
    val title: String,
    val description: String,
    val iconText: String,
    val iconBackgroundColor: Color
)

fun getAppNotifications(): List<AppNotification> {
    return listOf(
        AppNotification(
            "04 Apr 2025 10:15PM",
            "Successfully Uploaded Review",
            "Your review for Sony WH-1000XM5 has been successfully uploaded.",
            "S",
            CustomGreenColor
        ),
        AppNotification(
            "04 Apr 2025 10:10PM",
            "You Earned 50 Coins!",
            "Thank you for your review! You have earned 50 coins.",
            "C",
            Color.Yellow.darker() // Slightly darker yellow for coins
        ),
        AppNotification(
            "04 Apr 2025 09:00AM",
            "Redemption Successful!",
            "Your redemption request for 500 coins has been processed successfully.",
            "R",
            Color.Blue.darker() // Slightly darker blue for redemption
        ),
        AppNotification(
            "03 Apr 2025 08:45PM",
            "Successfully Uploaded Review",
            "Your review for Dell XPS 15 is now live.",
            "D",
            CustomGreenColor
        ),
        AppNotification(
            "03 Apr 2025 08:40PM",
            "You Earned 50 Coins!",
            "Your contribution is appreciated! 50 coins added to your wallet.",
            "C",
            Color.Yellow.darker()
        ),
        AppNotification(
            "02 Apr 2025 07:00PM",
            "Successfully Uploaded Review",
            "Your review for Sony Alpha 7 III has been published.",
            "S",
            CustomGreenColor
        ),
        AppNotification(
            "02 Apr 2025 06:55PM",
            "You Earned 50 Coins!",
            "Keep reviewing! You just earned 50 more coins.",
            "C",
            Color.Yellow.darker()
        ),
        AppNotification(
            "01 Apr 2025 10:30AM",
            "Successfully Uploaded Review",
            "Your review for Samsung Galaxy Buds Pro is now available.",
            "S",
            CustomGreenColor
        ),
        AppNotification(
            "01 Apr 2025 10:25AM",
            "You Earned 50 Coins!",
            "Another great review! You've received 50 coins.",
            "C",
            Color.Yellow.darker()
        ),
        AppNotification(
            "31 Mar 2025 04:15PM",
            "Successfully Uploaded Review",
            "Your review for Apple Watch Series 9 has been uploaded.",
            "A",
            CustomGreenColor
        ),
        AppNotification(
            "31 Mar 2025 04:10PM",
            "You Earned 50 Coins!",
            "Thanks for your feedback! 50 coins have been added to your account.",
            "C",
            Color.Yellow.darker()
        ),
        AppNotification(
            "30 Mar 2025 09:30AM",
            "Successfully Uploaded Review",
            "Your review for LG OLED C3 is now visible to others.",
            "L",
            CustomGreenColor
        ),
        AppNotification(
            "30 Mar 2025 09:25AM",
            "You Earned 50 Coins!",
            "Your review was helpful! Here are 50 coins for you.",
            "C",
            Color.Yellow.darker()
        ),
        AppNotification(
            "29 Mar 2025 08:10PM",
            "Successfully Uploaded Review",
            "Your review for Google Pixel 8 Pro has been successfully uploaded.",
            "G",
            CustomGreenColor
        ),
        AppNotification(
            "29 Mar 2025 08:05PM",
            "You Earned 50 Coins!",
            "Keep up the great work! You've earned 50 more coins.",
            "C",
            Color.Yellow.darker()
        ),
        AppNotification(
            "28 Mar 2025 04:30PM",
            "Successfully Uploaded Review",
            "Your review for Bose QuietComfort 45 is now live.",
            "B",
            CustomGreenColor
        ),
        AppNotification(
            "28 Mar 2025 04:25PM",
            "You Earned 50 Coins!",
            "Thank you for sharing your thoughts! 50 coins added.",
            "C",
            Color.Yellow.darker()
        ),
        AppNotification(
            "27 Mar 2025 09:45AM",
            "Successfully Uploaded Review",
            "Your review for Lenovo ThinkPad X1 Carbon is now published.",
            "L",
            CustomGreenColor
        ),
        AppNotification(
            "27 Mar 2025 09:40AM",
            "You Earned 50 Coins!",
            "Your review was valuable! Here's a reward of 50 coins.",
            "C",
            Color.Yellow.darker()
        ),
        AppNotification(
            "26 Mar 2025 06:00PM",
            "Successfully Uploaded Review",
            "Your review for Canon EOS R6 Mark II has been uploaded.",
            "C",
            CustomGreenColor
        ),
        AppNotification(
            "26 Mar 2025 05:55PM",
            "You Earned 50 Coins!",
            "Another helpful review! You've received 50 coins.",
            "C",
            Color.Yellow.darker()
        ),
        AppNotification(
            "25 Mar 2025 07:30PM",
            "Successfully Uploaded Review",
            "Your review for Sony WH-1000XM5 is now available.",
            "S",
            CustomGreenColor
        ),
        AppNotification(
            "25 Mar 2025 07:25PM",
            "You Earned 50 Coins!",
            "Keep reviewing and earning! 50 coins added to your wallet.",
            "C",
            Color.Yellow.darker()
        ),
        AppNotification(
            "24 Mar 2025 11:00AM",
            "Redemption Successful!",
            "Your recent redemption of 1000 coins is complete.",
            "R",
            Color.Blue.darker()
        ),
        AppNotification(
            "23 Mar 2025 02:00PM",
            "New Features Available!",
            "Check out the latest features in the app for an enhanced experience.",
            "N",
            Color.LightGray.darker()
        )
    ).shuffled() // Shuffle for more realistic display
}

fun Color.darker(factor: Float = 0.8f): Color = Color(
    red = (this.red * factor).coerceIn(0f, 1f),
    green = (this.green * factor).coerceIn(0f, 1f),
    blue = (this.blue * factor).coerceIn(0f, 1f),
    alpha = this.alpha
)