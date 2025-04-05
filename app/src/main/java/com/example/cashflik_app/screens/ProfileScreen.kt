package com.example.cashflik_app.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cashflik_app.R
import androidx.activity.compose.BackHandler

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController) {
    var name by remember { mutableStateOf("Yash Jadam") }
    var mobileNumber by remember { mutableStateOf("+91-9034340417") }
    var emailId by remember { mutableStateOf("Yash@gmail.com") }
    var address by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var pincode by remember { mutableStateOf("") }
    var dob by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("male") }
    var expanded by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Profile") },
                navigationIcon = {
                    IconButton(onClick = {
                        if (navController.previousBackStackEntry != null) {
                            navController.popBackStack()
                        } else {
                            navController.navigate("home") {
                                popUpTo(0)
                            }
                        }
                    }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { navController.navigate("notificationScreen") }) {
                        Icon(Icons.Filled.Notifications, contentDescription = "Notifications")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF192F6A), RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp))
                    .padding(vertical = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = name,
                    style = TextStyle(color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Image(
                    painter = painterResource(id = R.drawable.profileicon),
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(80.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = { Icon(painterResource(id = R.drawable.ic_person), contentDescription = "Person Icon", modifier = Modifier.size(24.dp)) }
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = mobileNumber,
                onValueChange = { mobileNumber = it },
                label = { Text("Mobile Number") },
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = { Icon(painterResource(id = R.drawable.ic_phone), contentDescription = "Phone Icon", modifier = Modifier.size(24.dp)) }
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = emailId,
                onValueChange = { emailId = it },
                label = { Text("Email Id") },
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = { Icon(painterResource(id = R.drawable.ic_phone), contentDescription = "Email Icon", modifier = Modifier.size(24.dp)) }
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = address,
                onValueChange = { address = it },
                label = { Text("Address") },
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = { Icon(painterResource(id = R.drawable.ic_location), contentDescription = "Location Icon", modifier = Modifier.size(24.dp)) }
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = city,
                onValueChange = { city = it },
                label = { Text("City") },
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = { Icon(painterResource(id = R.drawable.ic_city), contentDescription = "City Icon", modifier = Modifier.size(24.dp)) }
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = pincode,
                onValueChange = { pincode = it },
                label = { Text("Pincode") },
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = { Icon(painterResource(id = R.drawable.ic_pincode), contentDescription = "Pincode Icon", modifier = Modifier.size(24.dp)) }
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = dob,
                onValueChange = { dob = it },
                label = { Text("DOB") },
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = { Icon(painterResource(id = R.drawable.ic_calendar), contentDescription = "Calendar Icon", modifier = Modifier.size(24.dp)) }
            )

            Spacer(modifier = Modifier.height(16.dp))

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded },
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = gender,
                    onValueChange = { },
                    readOnly = true,
                    label = { Text("Gender") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier.menuAnchor()
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    DropdownMenuItem(text = { Text("Male") }, onClick = { gender = "Male"; expanded = false })
                    DropdownMenuItem(text = { Text("Female") }, onClick = { gender = "Female"; expanded = false })
                    DropdownMenuItem(text = { Text("Other") }, onClick = { gender = "Other"; expanded = false })
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { /* Handle save action */ },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Save")
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