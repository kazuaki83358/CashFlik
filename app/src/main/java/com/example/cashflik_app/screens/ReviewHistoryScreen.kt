package com.example.cashflik_app.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cashflik_app.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReviewHistoryScreen(navController: NavController) {
    val currentRoute = navController.currentBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Review History") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF00215E),
                    titleContentColor = Color.White
                ),
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate("home") { // Always navigate to home
                            popUpTo(0)
                        }
                    }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                actions = {
                    IconButton(onClick = { /* Handle notification click */ }) {
                        Icon(Icons.Filled.Notifications, contentDescription = "Notifications", tint = Color.White)
                    }
                }
            )
        },
        bottomBar = {
            BottomNavigation(navController = navController, currentRoute = currentRoute)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            SearchAndFilterRow()
            Spacer(modifier = Modifier.height(16.dp))
            ReviewGrid()
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
fun SearchAndFilterRow() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedTextField(
            value = "",
            onValueChange = {},
            modifier = Modifier.weight(1f),
            placeholder = { Text("Search...") },
            leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Search") }
        )
        OutlinedButton(
            onClick = { /* Handle date filter */ },
            modifier = Modifier.wrapContentWidth(),
            contentPadding = PaddingValues(16.dp, 8.dp),
            shape = RoundedCornerShape(4.dp) // Adjust corner radius here
        ) {
            Icon(Icons.Filled.DateRange, contentDescription = "Date", modifier = Modifier.size(20.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text("Date")
        }
    }
}

@Composable
fun ReviewGrid() {
    val reviews = listOf(
        Review("OnePlus 11R 5G", "50MP Main Camera with Sony IMX890", R.drawable.ic_launcher_foreground, "25 Jun 2024", "120"),
        Review("MacBook Pro", "M3 Chip with 16GB RAM", R.drawable.ic_launcher_foreground, "20 Jan 2024", "250"),
        Review("Samsung Galaxy S23", "108MP Camera, Snapdragon 8 Gen 2", R.drawable.ic_launcher_foreground, "15 Mar 2024", "180"),
        Review("Sony WH-1000XM5", "Noise Cancelling Headphones", R.drawable.ic_launcher_foreground, "05 Apr 2024", "90"),
        Review("iPad Air", "M1 Chip, 10.9-inch Display", R.drawable.ic_launcher_foreground, "10 May 2024", "150"),
        Review("Google Pixel 7a", "64MP Camera, Tensor G2", R.drawable.ic_launcher_foreground, "30 May 2024", "110"),
        Review("Dell XPS 13", "Intel Core i7, 16GB RAM", R.drawable.ic_launcher_foreground, "01 Jun 2024", "200"),
        Review("Fitbit Charge 5", "Fitness Tracker with GPS", R.drawable.ic_launcher_foreground, "12 Jun 2024", "80")
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(reviews) { review ->
            ReviewCard(review = review)
        }
    }
}

@Composable
fun ReviewCard(review: Review) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .height(260.dp) // Set a fixed height here (adjust as needed)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f) // Allow this column to take up remaining space
                    .heightIn(max = 200.dp) // Limit the max height of this column
            ) {
                Image(
                    painter = painterResource(id = review.image),
                    contentDescription = review.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = review.date, fontSize = 12.sp)
                Text(text = review.title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(
                    text = review.description,
                    fontSize = 12.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Text(
                text = review.points,
                fontSize = 14.sp,
                color = Color.Green,
                fontWeight = FontWeight.Bold
            )
        }
    }
}