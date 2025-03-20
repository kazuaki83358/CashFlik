package com.example.cashflik_app.model

data class User(
    val id: String = "",  // Firebase UID
    val name: String = "",
    val phone: String = "",
    val email: String? = null,
    val address: String? = null,
    val city: String? = null,
    val pincode: String? = null,
    val dob: String? = null,
    val gender: String? = null
)
