package com.example.cashflik_app.screens

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.CloudUpload
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cashflik_app.R
import com.example.cashflik_app.ui.theme.CustomBlueColor
import com.example.cashflik_app.ui.theme.CustomGreenColor // Import CustomGreenColor

@Composable
fun AddReviewScreen(navController: NavController) {
    val context = LocalContext.current

    // Handle back button press
    BackHandler {
        navController.navigate("home") {
            popUpTo("addReview") { inclusive = true }
        }
    }

    Scaffold(
        bottomBar = {
            BottomNavigation(navController = navController, currentRoute = "addReview")
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
        ) {
            // Top Section (Upload Reviews and Earn Money)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = CustomBlueColor)
                    .padding(16.dp)
            ) {
                Column {
                    Text(
                        text = "Add Review",
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Upload reviews and earn money",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Image(
                            painter = painterResource(id = R.drawable.usingtab),
                            contentDescription = "Upload Review Image",
                            modifier = Modifier.size(80.dp)
                        )
                    }
                }
            }

            // Form Section
            Column(modifier = Modifier.padding(16.dp)) {
                var category by remember { mutableStateOf("") }
                var title by remember { mutableStateOf("") }
                var reviewText by remember { mutableStateOf("") }
                var rating by remember { mutableIntStateOf(0) }

                Text("Category", style = MaterialTheme.typography.titleMedium)
                OutlinedTextField(
                    value = category,
                    onValueChange = { category = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Select Category") },
                    trailingIcon = { Icon(Icons.Filled.ArrowDropDown, contentDescription = "Dropdown") }
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text("Title", style = MaterialTheme.typography.titleMedium)
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Enter Title") }
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text("Write Review", style = MaterialTheme.typography.titleMedium)
                OutlinedTextField(
                    value = reviewText,
                    onValueChange = { reviewText = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    placeholder = { Text("Type...") }
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text("Rating", style = MaterialTheme.typography.titleMedium)
                Row(modifier = Modifier.fillMaxWidth()) {
                    repeat(5) { index ->
                        val starIcon = if (index < rating) Icons.Filled.Star else Icons.Outlined.StarOutline
                        IconButton(onClick = { rating = index + 1 }) {
                            Icon(starIcon, contentDescription = "Star ${index + 1}")
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text("Upload Video", style = MaterialTheme.typography.titleMedium)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .border(1.dp, Color.Gray, RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(Icons.Filled.CloudUpload, contentDescription = "Upload Video", modifier = Modifier.size(48.dp))
                        Text(
                            text = "Video formats (MP4, AVI, MOV)\nMax upload size: 2GB",
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        if (title.isBlank() || reviewText.isBlank()) {
                            Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                        } else {
                            navController.navigate("home") {
                                popUpTo("addReview") { inclusive = true }
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = CustomGreenColor) // Set button color
                ) {
                    Text("Submit")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddReviewScreenPreview() {
    val navController = NavController(LocalContext.current)
    AddReviewScreen(navController = navController)
}