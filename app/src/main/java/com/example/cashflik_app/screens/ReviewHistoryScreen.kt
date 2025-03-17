package com.example.cashflik_app.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.cashflik_app.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReviewHistoryScreen(navController: NavHostController) {
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
                        navController.navigate("home") {
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
            ReviewGrid(navController = navController)
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
            shape = RoundedCornerShape(4.dp)
        ) {
            Icon(Icons.Filled.DateRange, contentDescription = "Date", modifier = Modifier.size(20.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text("Date")
        }
    }
}

@Composable
fun ReviewGrid(navController: NavHostController) {
    val reviews = listOf(
        Review("OnePlus 11R 5G", "50MP Main Camera with Sony IMX890", R.drawable.oneplus, "25 Jun 2024", "120", 4.0f), // Added stars
        Review("MacBook Pro", "M3 Chip with 16GB RAM", R.drawable.macbook, "20 Jan 2024", "250", 3.5f), // Added stars
        Review("Samsung Galaxy S23", "108MP Camera, Snapdragon 8 Gen 2", R.drawable.samsung, "15 Mar 2024", "180", 4.5f), // Added stars
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(reviews) { review ->
            ReviewCard(review = review, navController = navController)
        }
    }
}

@Composable
fun ReviewCard(review: Review, navController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate("reviewDetails/${review.title}/${review.description}/${review.image}/${review.date}/${review.points}/${review.stars}") // Pass stars
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .height(260.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .heightIn(max = 200.dp)
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