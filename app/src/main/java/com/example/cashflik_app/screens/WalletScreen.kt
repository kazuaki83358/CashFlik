package com.example.cashflik_app.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cashflik_app.R
import com.example.cashflik_app.ui.theme.CustomBlueColor
import com.example.cashflik_app.ui.theme.CustomGreenColor

// Data class representing a transaction
data class Transaction(
    val type: String,
    val description: String,
    val time: String,
    val date: String,
    val amount: String
)

@Composable
fun WalletScreen(navController: NavController) {
    Scaffold(
        topBar = { WalletTopBar(navController) },
        bottomBar = { BottomNavigation(navController = navController, currentRoute = "wallet") }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFF0F0F0))
        ) {
            MyWalletSection()
            Spacer(modifier = Modifier.height(8.dp))
            TransactionSection()
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WalletTopBar(navController: NavController) {
    TopAppBar(
        title = { Text("Wallet", color = Color.White) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = CustomBlueColor,
            titleContentColor = Color.White
        ),
        navigationIcon = {
            IconButton(onClick = { navController.navigate("home") }) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
            }
        },
        actions = {
            IconButton(onClick = { navController.navigate("notificationScreen") }) {
                Icon(Icons.Filled.Notifications, contentDescription = "Notifications", tint = Color.White)
            }
        }
    )
}

@Composable
fun MyWalletSection() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(text = "My Wallet", fontWeight = FontWeight.SemiBold, color = Color.Black, fontSize = 18.sp)
                Text(text = "1500", fontWeight = FontWeight.Bold, fontSize = 32.sp, color = CustomGreenColor)
            }
            Text(text = "Withdrawal", color = Color.Blue, modifier = Modifier.clickable { /* TODO */ })
        }
    }
}

@Composable
fun TransactionSection() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Transactions", fontWeight = FontWeight.SemiBold, color = Color.Black, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(getDummyTransactions()) { transaction ->
                TransactionItem(transaction = transaction)
            }
        }
    }
}

@Composable
fun TransactionItem(transaction: Transaction) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = if (transaction.type == "Paid") R.drawable.arrowup else R.drawable.arrowdown),
            contentDescription = transaction.type,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = transaction.description, fontWeight = FontWeight.SemiBold, color = Color.Black)
            Text(text = "${transaction.time}, ${transaction.date}", fontSize = 12.sp, color = Color.Black)
        }
        Text(
            text = transaction.amount,
            fontWeight = FontWeight.SemiBold,
            color = if (transaction.type == "Paid") Color.Red else CustomGreenColor
        )
    }
}

fun getDummyTransactions(): List<Transaction> {
    return listOf(
        Transaction("Paid", "Paid To Manish", "02:34 PM", "25 February 2022", "-₹300"),
        Transaction("Received", "Received from Worryease", "02:34 PM", "25 February 2022", "+₹500"),
        Transaction("Paid", "Paid To Manish", "02:34 PM", "25 February 2022", "-₹300"),
        Transaction("Received", "Received from Worryease", "02:34 PM", "25 February 2022", "+₹300"),
        Transaction("Paid", "Paid To Manish", "02:34 PM", "25 February 2022", "-₹300"),
        Transaction("Received", "Received from Worryease", "02:34 PM", "25 February 2022", "+₹300")
    )
}

@Preview(showBackground = true)
@Composable
fun WalletScreenPreview() {
    WalletScreen(navController = rememberNavController())
}