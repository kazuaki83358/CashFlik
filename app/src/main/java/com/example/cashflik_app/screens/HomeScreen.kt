package com.example.cashflik_app.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cashflik_app.R
import com.example.cashflik_app.ui.theme.CustomBlueColor
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

data class Category(val name: String, val icon: Int)

@Composable
fun HomeScreen(navController: NavController) {
    val categories = listOf(
        Category("Laptop", R.drawable.laptop),
        Category("Mobile", R.drawable.phone),
        Category("Earbuds", R.drawable.earbuds),
        Category("Smart Watch", R.drawable.watch),
        Category("Television", R.drawable.television),
        Category("Camera", R.drawable.camera),
        Category("Accessories", R.drawable.accessories),
        Category("Appliances", R.drawable.appliances)
    )

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val reviews = listOf(
        Review(
            "Dell XPS 15",
            "Processor: Intel Core i7, RAM: 16GB, Storage: 512GB SSD",
            R.drawable.laptop_placeholder,
            "03 Apr 2025 08:00 PM",
            "50",
            4.5f
        ),
        Review(
            "Sony Alpha 7 III",
            "Sensor: 24.2MP Full-Frame, Video: 4K, Lens Mount: E-mount",
            R.drawable.camera_placeholder,
            "02 Apr 2025 06:30 PM",
            "50",
            4.8f
        ),
        Review(
            "Samsung Galaxy Buds Pro",
            "Audio: Active Noise Cancelling, Battery: Up to 8 hours",
            R.drawable.earbuds_placeholder,
            "01 Apr 2025 10:00 AM",
            "50",
            4.2f
        ),
        Review(
            "Apple Watch Series 9",
            "Features: GPS, Blood Oxygen sensor, Always-On",
            R.drawable.watch_placeholder,
            "31 Mar 2025 04:00 PM",
            "50",
            4.7f
        ),
        Review(
            "LG OLED C3",
            "Display: 55-inch OLED, Resolution: 4K, Smart TV",
            R.drawable.television_placeholder,
            "30 Mar 2025 09:15 AM",
            "50",
            4.9f
        )
    )

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val drawerWidth = screenWidth * 0.6f

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = { DrawerScreen(navController = navController, modifier = Modifier.width(drawerWidth)) }, // Pass navController here
        content = {
            Scaffold(
                bottomBar = {
                    BottomNavigation(navController = navController, currentRoute = "home")
                }
            ) { paddingValues ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFFF0F0F0))
                        .verticalScroll(rememberScrollState())
                        .padding(paddingValues)
                ) {
                    WelcomeSection(navController = navController, onProfileClick = { scope.launch { drawerState.open() } })
                    UploadEarnSection()
                    CategorySection(categories = categories)

                    Column(modifier = Modifier.weight(1f)) {
                        MyReviewSection(reviews = reviews, navController = navController) // Pass navController
                    }
                }
            }
        }
    )
}

@Composable
fun UploadEarnSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.reviewicon),
                contentDescription = "Upload Review",
                modifier = Modifier.size(50.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text("Upload Review", color = Color.Black)
        }

        Image(
            painter = painterResource(id = R.drawable.arrowicon),
            contentDescription = "Arrow",
            modifier = Modifier.size(30.dp)
        )

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.earnmoneyicon),
                contentDescription = "Earn Money",
                modifier = Modifier.size(50.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text("Earn Money", color = Color.Black)
        }
    }
}

@Composable
fun WelcomeSection(navController: NavController, onProfileClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(CustomBlueColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = { navController.navigate("notificationScreen") }) {
                    Icon(Icons.Filled.Notifications, contentDescription = "Notifications", tint = Color.White)
                }
                IconButton(onClick = onProfileClick) {
                    Icon(Icons.Filled.Person, contentDescription = "Profile", tint = Color.White)
                }
            }
            Text(
                text = "Welcome Back 👋",
                style = TextStyle(color = Color.White, fontSize = 18.sp)
            )
            CurrentTimeText() // Calling the new composable here
            Spacer(modifier = Modifier.height(16.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.profile_photo),
                    contentDescription = "Profile",
                    modifier = Modifier
                        .size(35.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = "Yash Jadam",
                        style = TextStyle(color = Color.White, fontWeight = FontWeight.Bold)
                    )
                    Text(
                        text = "1500",
                        style = TextStyle(color = Color.White.copy(alpha = 0.7f))
                    )
                }
            }
        }
        Image(
            painter = painterResource(id = R.drawable.wave),
            contentDescription = "Wave",
            modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter),
            contentScale = ContentScale.FillWidth
        )
    }
}

@Composable
fun CurrentTimeText() {
    val currentTime = remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        while (true) {
            val calendar = Calendar.getInstance()
            val format = SimpleDateFormat("EEE, dd MMM", Locale.getDefault())
            currentTime.value = format.format(calendar.time)
            kotlinx.coroutines.delay(1000) // Update every second
        }
    }

    Text(
        text = currentTime.value,
        style = TextStyle(color = Color.White.copy(alpha = 0.7f), fontSize = 14.sp)
    )
}

@Composable
fun CategorySection(categories: List<Category>) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Category", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.Black))
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(horizontalArrangement = Arrangement.spacedBy(54.dp)) {
            items(categories) { category ->
                CategoryItem(category = category)
            }
        }
    }
}

@Composable
fun CategoryItem(category: Category) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = category.icon),
            contentDescription = category.name,
            modifier = Modifier.size(28.dp) // Decreased size
        )
        Spacer(modifier = Modifier.height(4.dp)) // Added space
        Text(text = category.name, fontSize = 12.sp, color = Color.Black)
    }
}

@Composable
fun MyReviewSection(reviews: List<Review>, navController: NavController) {
    Column(modifier = Modifier.padding(16.dp)) {
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "My Review",
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp),
                color = Color.Black // Added color
            )
            Text(
                text = "View All",
                color = Color.Blue,
                modifier = Modifier.clickable {
                    navController.navigate("reviewHistoryScreen") // Navigate to the review history screen
                }
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(reviews) { review ->
                ReviewItem(review = review)
            }
        }
    }
}

@Composable
fun ReviewItem(review: Review) {
    Card(
        modifier = Modifier.width(200.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            Image(
                painter = painterResource(id = review.image),
                contentDescription = review.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(8.dp)) {
                Text(text = review.date, fontSize = 10.sp)
                Text(text = review.title, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                Text(text = review.description, fontSize = 12.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = review.points, fontSize = 14.sp, color = Color.Green)
            }
        }
    }
}