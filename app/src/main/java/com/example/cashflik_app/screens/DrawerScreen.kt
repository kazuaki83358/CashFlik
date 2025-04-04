package com.example.cashflik_app.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cashflik_app.R
import com.example.cashflik_app.ui.theme.CustomGreenColor

@Composable
fun DrawerScreen(navController: NavController, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .background(Color.White)
    ) {
        // Header Section (Customized)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(CustomGreenColor)
                .padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.profileicon),
                    contentDescription = "Profile",
                    modifier = Modifier
                        .size(40.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = "Yash Jadam",
                        style = TextStyle(color = Color.White, fontWeight = FontWeight.Bold)
                    )
                    Text(
                        text = "1500 Coins",
                        style = TextStyle(color = Color.White.copy(alpha = 0.7f))
                    )
                }
            }
        }

        Divider(color = Color.LightGray, thickness = 1.dp)

        // Menu Items
        DrawerItem(navController = navController, icon = R.drawable.ic_profile, text = "Profile", route = "profileScreen")
        DrawerItem(icon = R.drawable.ic_share, text = "Share & Refer", onClick = { /* Handle Share & Refer */ })
        DrawerItem(navController = navController, icon = R.drawable.ic_about_us, text = "About Us", route = "aboutUsScreen")
        DrawerItem(navController = navController, icon = R.drawable.ic_terms, text = "Terms and Conditions", route = "termsConditionsScreen")
        DrawerItem(navController = navController, icon = R.drawable.ic_privacy, text = "Privacy Policy", route = "privacyPolicyScreen")

        Divider(color = Color.LightGray, thickness = 1.dp)

        // Contact Us Section
        Text(
            text = "Contact Us",
            style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 16.sp),
            modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_email), // Replace with your email icon
                contentDescription = "Email",
                modifier = Modifier.size(20.dp),
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = "flikcash1@gmail.com", fontSize = 14.sp)
        }

        Spacer(modifier = Modifier.weight(1f)) // Push Logout to the bottom

        Divider(color = Color.LightGray, thickness = 1.dp)

        DrawerItem(icon = R.drawable.ic_logout, text = "Logout", onClick = { /* Handle Logout */ })
    }
}

@Composable
fun DrawerItem(navController: NavController, icon: Int, text: String, route: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navController.navigate(route) }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = text,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = text, fontSize = 16.sp)
    }
}

@Composable
fun DrawerItem(icon: Int, text: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = text,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = text, fontSize = 16.sp)
    }
}