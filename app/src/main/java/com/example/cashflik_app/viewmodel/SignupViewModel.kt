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
import java.util.concurrent.TimeUnit

class SignupViewModel : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    private var verificationId: String? = null

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    // Step 1: Send OTP for Phone Verification
    fun sendOtp(
        phoneNumber: String,
        activity: Activity,
        onCodeSent: (String) -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            _isLoading.value = true

            try {
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

                // This is safe to call inside coroutine
                PhoneAuthProvider.verifyPhoneNumber(options)

            } catch (e: Exception) {
                _isLoading.value = false
                Log.e("SignupViewModel", "Exception during sendOtp", e)
                onError(e.localizedMessage ?: "Failed to start OTP verification")
            }
        }
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
        val id = verificationId
        if (id.isEmpty()) {
            onError("Invalid verification ID")
            return
        }

        _isLoading.value = true
        val credential = PhoneAuthProvider.getCredential(id, otp)
        signInWithCredential(credential, phoneNumber, password, name, onSuccess, onError)
    }

    // Step 3: Sign in with Phone Credential
    private fun signInWithCredential(
        credential: PhoneAuthCredential,
        phoneNumber: String,
        password: String,
        name: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = task.result?.user
                    user?.let {
                        registerUserInAuth(phoneNumber, password, name, it.uid, onSuccess, onError)
                    }
                } else {
                    _isLoading.value = false
                    onError(task.exception?.localizedMessage ?: "Sign in failed")
                }
            }
    }

    // Step 4: Register Email/Password User and Store Data (using Email UID)
    private fun registerUserInAuth(
        phoneNumber: String,
        password: String,
        name: String,
        phoneAuthUid: String, // Phone auth UID
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        val email = "$phoneNumber@cashflik.com"

        auth.fetchSignInMethodsForEmail(email)
            .addOnCompleteListener { authTask ->
                if (authTask.isSuccessful) {
                    val methods = authTask.result?.signInMethods
                    if (methods.isNullOrEmpty()) {
                        // User does not exist, create email/password account
                        auth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener { registerTask ->
                                if (registerTask.isSuccessful) {
                                    // Use email/password user UID
                                    saveUserToFirestore(registerTask.result?.user?.uid ?: phoneAuthUid, phoneNumber, name, onSuccess, onError)
                                } else {
                                    _isLoading.value = false
                                    onError(registerTask.exception?.localizedMessage ?: "Auth registration failed")
                                }
                            }
                    } else {
                        // User already exists, just store data (using email UID)
                        saveUserToFirestore(auth.currentUser?.uid ?: phoneAuthUid, phoneNumber, name, onSuccess, onError)
                    }
                } else {
                    _isLoading.value = false
                    onError(authTask.exception?.localizedMessage ?: "Error checking existing account")
                }
            }
    }

    // Step 5: Store User Data in Firestore (using Email UID)
    private fun saveUserToFirestore(
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

        viewModelScope.launch {
            db.collection("users").document(uid)
                .set(user)
                .addOnSuccessListener {
                    Log.d("SignupViewModel", "User stored successfully (using email UID)")
                    _isLoading.value = false
                    onSuccess()
                }
                .addOnFailureListener { e ->
                    _isLoading.value = false
                    Log.e("SignupViewModel", "Error storing user: ", e)
                    onError("Failed to store user data")
                }
        }
    }
}