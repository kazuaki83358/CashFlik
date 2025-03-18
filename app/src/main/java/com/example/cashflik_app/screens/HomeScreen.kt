package com.example.cashflik_app.screens

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
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch

data class Category(val name: String, val icon: Int)

@Composable
fun HomeScreen(navController: NavController) {
    val categories = listOf(
        Category("Laptop", R.drawable.laptop),
        Category("Mobile", R.drawable.phone),
        Category("Earbuds", R.drawable.earbuds),
        Category("Smart Watch", R.drawable.watch)
    )

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val reviews = listOf(
        Review(
            "OnePlus 11R 5G",
            "Camera: Sensor: 50MP Main Camera with Sony IMX890",
            R.drawable.ic_launcher_foreground,
            "26 Jun 2024 01:30 PM",
            "120",
            4.0f
        ),
        Review(
            "OnePlus 11R 5G",
            "Camera: Sensor: 50MP Main Camera with Sony IMX890",
            R.drawable.ic_launcher_foreground,
            "25 Jun 2024 03:30 PM",
            "120",
            4.0f
        ),
        Review(
            "OnePlus 11R 5G",
            "Camera: Sensor: 50MP Main Camera with Sony IMX890",
            R.drawable.ic_launcher_foreground,
            "25 Jun 2024 03:30 PM",
            "120",
            4.0f
        ),
        Review(
            "OnePlus 11R 5G",
            "Camera: Sensor: 50MP Main Camera with Sony IMX890",
            R.drawable.ic_launcher_foreground,
            "25 Jun 2024 03:30 PM",
            "120",
            4.0f
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
                        MyReviewSection(reviews = reviews)
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
            Text("Earn Money", color = Color.Black)
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
            Text(
                text = "Thu, 25 Jan 2024",
                style = TextStyle(color = Color.White.copy(alpha = 0.7f), fontSize = 14.sp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = "Profile",
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = "Laura Hernandez",
                        style = TextStyle(color = Color.White, fontWeight = FontWeight.Bold)
                    )
                    Text(
                        text = "1200",
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
fun MyReviewSection(reviews: List<Review>) {
    Column(modifier = Modifier.padding(16.dp)) {
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "My Review",
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp),
                color = Color.Black // Added color
            )
            Text(text = "View All", color = Color.Blue, modifier = Modifier.clickable { /*TODO*/ })
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
