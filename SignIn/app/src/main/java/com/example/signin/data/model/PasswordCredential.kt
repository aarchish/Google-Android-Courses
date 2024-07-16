package com.example.signin.data.model

// In your 'model' package (e.g., app/src/main/java/kotlin/com/yourcompany/yourappname/data/model)
data class PasswordCredential(
    val id: String, // Unique identifier for the credential
    val username: String,
    val password: String
)