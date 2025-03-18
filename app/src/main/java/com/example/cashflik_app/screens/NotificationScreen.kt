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
            items(getDummyNotifications()) { notification ->
                NotificationCard(notification = notification)
            }
        }
    }

    BackHandler { // Add BackHandler here
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
fun NotificationCard(notification: Notification) {
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
                    .background(CustomGreenColor),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "S",
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

data class Notification(
    val dateTime: String,
    val title: String,
    val description: String
)

fun getDummyNotifications(): List<Notification> {
    return listOf(
        Notification(
            "25 Jun 2024 03:30PM",
            "Successfully Upload Review",
            "In publishing and graphic design, Lorem ipsum is a placeholder text commonly used."
        ),
        Notification(
            "25 Jun 2024 03:30PM",
            "Successfully Upload Review",
            "In publishing and graphic design, Lorem ipsum is a placeholder text commonly used."
        ),
        Notification(
            "25 Jun 2024 03:30PM",
            "Successfully Upload Review",
            "In publishing and graphic design, Lorem ipsum is a placeholder text commonly used."
        ),
        Notification(
            "25 Jun 2024 03:30PM",
            "Successfully Upload Review",
            "In publishing and graphic design, Lorem ipsum is a placeholder text commonly used."
        ),
        Notification(
            "25 Jun 2024 03:30PM",
            "Successfully Upload Review",
            "In publishing and graphic design, Lorem ipsum is a placeholder text commonly used."
        ),
        Notification(
            "25 Jun 2024 03:30PM",
            "Successfully Upload Review",
            "In publishing and graphic design, Lorem ipsum is a placeholder text commonly used."
        ),
        Notification(
            "25 Jun 2024 03:30PM",
            "Successfully Upload Review",
            "In publishing and graphic design, Lorem ipsum is a placeholder text commonly used."
        ),
        Notification(
            "25 Jun 2024 03:30PM",
            "Successfully Upload Review",
            "In publishing and graphic design, Lorem ipsum is a placeholder text commonly used."
        ),
        Notification(
            "25 Jun 2024 03:30PM",
            "Successfully Upload Review",
            "In publishing and graphic design, Lorem ipsum is a placeholder text commonly used."
        )
    )
}