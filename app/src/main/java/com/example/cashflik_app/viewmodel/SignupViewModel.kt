package com.example.cashflik_app.viewmodel

import android.app.Activity
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cashflik_app.model.User
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.concurrent.TimeUnit

class SignupViewModel : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    private var verificationId: String? = null

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    // Step 1: Send OTP
    fun sendOtp(
        phoneNumber: String,
        activity: Activity,
        onCodeSent: (String) -> Unit,
        onError: (String) -> Unit
    ) {
        _isLoading.value = true

        val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                Log.d("SignupViewModel", "OTP Auto-Verified")
                _isLoading.value = false
            }

            override fun onVerificationFailed(e: FirebaseException) {
                _isLoading.value = false
                Log.e("SignupViewModel", "OTP verification failed", e)
                onError(e.localizedMessage ?: "OTP Verification failed")
            }

            override fun onCodeSent(id: String, token: PhoneAuthProvider.ForceResendingToken) {
                verificationId = id
                _isLoading.value = false
                Log.d("SignupViewModel", "OTP code sent successfully")
                onCodeSent(id)
            }
        }

        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(activity)
            .setCallbacks(callbacks)
            .build()

        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    // Step 2: Verify OTP
    fun verifyOtp(
        otp: String,
        phoneNumber: String,
        password: String,
        verificationId: String,
        name: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        if (verificationId.isEmpty()) {
            onError("Invalid verification ID")
            return
        }

        val credential = PhoneAuthProvider.getCredential(verificationId, otp)

        viewModelScope.launch {
            _isLoading.value = true
            try {
                val authResult = auth.signInWithCredential(credential).await()
                val phoneAuthUid = authResult.user?.uid ?: throw Exception("UID not found")
                handleUserCreation(phoneNumber, password, name, phoneAuthUid, onSuccess, onError)
            } catch (e: Exception) {
                Log.e("SignupViewModel", "OTP Sign-in failed", e)
                _isLoading.value = false
                onError(e.localizedMessage ?: "Sign-in failed")
            }
        }
    }

    // Step 3: Handle Firebase Auth and Firestore save
    private suspend fun handleUserCreation(
        phoneNumber: String,
        password: String,
        name: String,
        phoneAuthUid: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        val email = "$phoneNumber@cashflik.com"

        try {
            val signInMethods = auth.fetchSignInMethodsForEmail(email).await().signInMethods ?: emptyList()

            val uid = if (signInMethods.isEmpty()) {
                val user = auth.createUserWithEmailAndPassword(email, password).await().user
                user?.uid ?: throw Exception("Email auth UID not found")
            } else {
                auth.currentUser?.uid ?: phoneAuthUid
            }

            saveUserToFirestore(uid, phoneNumber, name, onSuccess, onError)

        } catch (e: Exception) {
            Log.e("SignupViewModel", "Auth error", e)
            _isLoading.value = false
            onError(e.localizedMessage ?: "Registration failed")
        }
    }

    // Step 4: Save user in Firestore
    private suspend fun saveUserToFirestore(
        uid: String,
        phone: String,
        name: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        val user = User(
            id = uid,
            phone = phone,
            name = name,
            email = "",
            address = "",
            city = "",
            pincode = "",
            dob = "",
            gender = ""
        )

        try {
            db.collection("users").document(uid).set(user).await()
            Log.d("SignupViewModel", "User saved to Firestore")
            onSuccess()
        } catch (e: Exception) {
            Log.e("SignupViewModel", "Firestore error", e)
            onError("Failed to store user data")
        } finally {
            _isLoading.value = false
        }
    }
}
