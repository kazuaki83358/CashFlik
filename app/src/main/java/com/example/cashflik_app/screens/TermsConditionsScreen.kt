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
fun TermsConditionsScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Terms and Conditions") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    navigationIconContentColor = MaterialTheme.colorScheme.onSurfaceVariant
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
                text = "Terms and Conditions",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
            )

            Text(
                text = """
                    Welcome to Cash Flik! By accessing and using our platform, you agree to comply with the following terms and conditions. Please read them carefully.
                """.trimIndent(),
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            )

            SectionHeading(text = "1. Acceptance of Terms")
            Text(
                text = "By using Cash Flik, you acknowledge that you have read, understood, and agree to be bound by these Terms and Conditions.",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.fillMaxWidth()
            )

            SectionHeading(text = "2. User Accounts")
            Text(
                text = "You are responsible for maintaining the confidentiality of your account credentials. You agree to provide accurate and complete information when registering and using Cash Flik.",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.fillMaxWidth()
            )

            SectionHeading(text = "3. User-Generated Content")
            Text(
                text = "By submitting video reviews, you grant Cash Flik a non-exclusive, worldwide, royalty-free license to use, reproduce, distribute, and display your content for the purpose of operating and promoting the platform. You are solely responsible for the content you submit and ensure it complies with applicable laws and our content guidelines.",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.fillMaxWidth()
            )

            SectionHeading(text = "4. Earning and Redemption of Coins")
            Text(
                text = "You may earn coins for submitting approved video reviews, as outlined in our platform's guidelines. The redemption of these coins is subject to our policies, which may be updated from time to time.",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.fillMaxWidth()
            )

            SectionHeading(text = "5. Intellectual Property")
            Text(
                text = "The Cash Flik platform and its original content, features, and functionality are owned by [Your Company Name] and are protected by intellectual property laws. You may not reproduce, modify, or distribute our content without our prior written consent.",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.fillMaxWidth()
            )

            SectionHeading(text = "6. Limitation of Liability")
            Text(
                text = "Cash Flik is provided on an 'as is' and 'as available' basis. We shall not be liable for any indirect, incidental, special, consequential, or punitive damages arising out of your use of the platform.",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.fillMaxWidth()
            )

            SectionHeading(text = "7. Governing Law")
            Text(
                text = "These Terms and Conditions shall be governed by and construed in accordance with the laws of India, without regard to its conflict of law provisions.",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.fillMaxWidth()
            )

            SectionHeading(text = "8. Changes to Terms")
            Text(
                text = "We reserve the right to modify or update these Terms and Conditions at any time. Your continued use of Cash Flik after any such changes constitutes your acceptance of the new Terms.",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.fillMaxWidth()
            )

            SectionHeading(text = "9. Contact Us")
            Text(
                text = "If you have any questions about these Terms and Conditions, please contact us at flikcash1@gmail.com.",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

