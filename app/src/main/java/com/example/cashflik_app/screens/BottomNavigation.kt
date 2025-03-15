package com.example.cashflik_app.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cashflik_app.R

@Composable
fun BottomNavigation(
    navController: NavController,
    currentRoute: String?
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
    ) {
        Image(
            painter = painterResource(id = R.drawable.navbar),
            contentDescription = "Bottom Wave",
            modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter),
            contentScale = ContentScale.FillWidth
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BottomNavItem(
                icon = R.drawable.ic_home,
                text = "Home",
                isSelected = currentRoute == "home",
                onClick = {
                    if (currentRoute != "home") {
                        navController.navigate("home")
                    }
                }
            )
            BottomNavItem(
                icon = R.drawable.add,
                text = "Add Review",
                isSelected = currentRoute == "addReview",
                onClick = {
                    if (currentRoute != "addReview") {
                        navController.navigate("addReview")
                    }
                }
            )
            BottomNavItem(
                icon = R.drawable.list,
                text = "Review History",
                isSelected = currentRoute == "reviewHistory",
                onClick = {
                    if (currentRoute != "reviewHistory") {
                        navController.navigate("reviewHistory")
                    }
                }
            )
            BottomNavItem(
                icon = R.drawable.wallet,
                text = "Wallet",
                isSelected = currentRoute == "wallet",
                onClick = {
                    if (currentRoute != "wallet") {
                        navController.navigate("wallet")
                    }
                }
            )
        }
    }
}

@Composable
fun BottomNavItem(icon: Int, text: String, isSelected: Boolean, onClick: () -> Unit) {
    val iconColor = if (isSelected) Color.Yellow else Color.White
    Column(
        modifier = Modifier
            .clickable(enabled = !isSelected, onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = text,
            tint = iconColor,
            modifier = Modifier.size(25.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(text, color = iconColor, fontSize = 12.sp)
    }
}