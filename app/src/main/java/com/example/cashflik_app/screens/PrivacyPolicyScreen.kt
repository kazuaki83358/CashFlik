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
fun PrivacyPolicyScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Privacy Policy") },
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
                text = "Privacy Policy",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
            )

            Text(
                text = "At Cash Flik, we are committed to protecting your privacy and ensuring the security of your personal information. This Privacy Policy outlines how we collect, use, and safeguard your data when you use our platform.",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            )

            SectionHeading(text = "Information We Collect")
            Text(
                text = """
                    * <b>Personal Information:</b> When you register, we collect your name, contact details (email, phone number), and demographic information.
                    * <b>Usage Data:</b> We collect information about how you interact with Cash Flik, including the reviews you submit, the content you view, and your activity on the platform.
                    * <b>Device Information:</b> We may collect information about your device, such as the device model, operating system, and unique identifiers.
                    * <b>Location Data:</b> With your permission, we may collect your device's location to enhance certain features.
                    * <b>Video Feedback:</b> The core of our platform involves collecting video feedback, which may include your image and voice.
                """.trimIndent(),
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.fillMaxWidth()
            )

            SectionHeading(text = "How We Use Your Information")
            Text(
                text = """
                    * To provide and improve our services, personalize your experience, and facilitate qualitative research.
                    * To process your reviews, reward your contributions (coins), and manage your account.
                    * To communicate with you about platform updates, new features, and relevant information.
                    * To analyze user behavior and trends to enhance our platform and user experience.
                    * To detect and prevent fraud and ensure the authenticity of feedback.
                    * To comply with legal obligations.
                """.trimIndent(),
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.fillMaxWidth()
            )

            SectionHeading(text = "Data Security")
            Text(
                text = "We implement industry-standard security measures to protect your personal information from unauthorized access, use, or disclosure. This includes encryption, secure storage, and regular security audits.",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.fillMaxWidth()
            )

            SectionHeading(text = "Data Sharing")
            Text(
                text = """
                    * <b>Businesses:</b> The video feedback you provide and its AI-analyzed insights will be shared with our business clients for research purposes. Your personally identifiable information will not be directly shared with them without your explicit consent.
                    * <b>Service Providers:</b> We may share your information with trusted third-party service providers who assist us in operating our platform, such as hosting, analytics, and customer support. These providers are contractually obligated to protect your data.
                    * <b>Legal Requirements:</b> We may disclose your information if required by law or legal process.
                """.trimIndent(),
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.fillMaxWidth()
            )

            SectionHeading(text = "Your Rights")
            Text(
                text = "You have the right to access, correct, or delete your personal information. You can also manage your communication preferences and control the collection of location data. Please contact us at flikcash1@gmail.com for any requests or concerns regarding your data.",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.fillMaxWidth()
            )

            SectionHeading(text = "Data Retention")
            Text(
                text = "We will retain your personal information for as long as necessary to provide our services and as required by applicable laws.",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.fillMaxWidth()
            )

            SectionHeading(text = "Changes to this Privacy Policy")
            Text(
                text = "We may update this Privacy Policy from time to time. We will notify you of any significant changes by posting the updated policy on our platform or through other communication channels.",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.fillMaxWidth()
            )

            SectionHeading(text = "Consent")
            Text(
                text = "By using Cash Flik, you consent to the practices described in this Privacy Policy.",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun SectionHeading(text: String) {
    Text(
        text = text,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onBackground, // Use onBackground
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
    )
}