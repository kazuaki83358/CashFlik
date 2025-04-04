package com.example.cashflik_app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutUsScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("About Us") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF192F6A), // Custom blue color
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
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
            Text(
                text = "About Us",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
            )

            Text(
                text = """
                    Cash Flik is revolutionizing qualitative research by gathering authentic, emotion-rich video feedback directly from users. Our AI-powered platform processes these into deep consumer insights, helping brands make smarter decisions—fast and affordably.

                    Our mission is to democratize deep customer insights for businesses of all sizes—without the delay and cost of traditional research firms. We empower individuals to share their genuine experiences and opinions through short video reviews, earning rewards for their valuable feedback.

                    We are currently focused on the Indian market, leveraging its mobile-first culture and linguistic diversity with AI transcription and translation. This allows us to tap into a rich pool of diverse perspectives.

                    Our platform utilizes cutting-edge AI features, including emotion and sentiment detection, script-reading/fake detection, and fraud analysis, to ensure the authenticity and quality of the feedback we provide to businesses.

                    We believe in the power of real human emotion to drive better product development and marketing strategies. By connecting businesses directly with the authentic voices of their consumers, Cash Flik helps build stronger brands and more customer-centric products.
                """.trimIndent(),
                fontSize = 16.sp,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}