package com.example.signin.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.ui.platform.LocalContext
import androidx.glance.layout.height
import androidx.compose.ui.unit.dp
import androidx.credentials.CredentialManager
import androidx.credentials.PasswordCredential

@Composable
fun LoginScreen() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val credentialManager = remember { CredentialManager.getInstance(context) }
    val credentials = credentialManager.getCredentials()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            // TODO: Implement login logic using Credential Manager
        }) {
            Text("Login")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {// TODO: Navigate to SignupScreen
        }) {
            Text("Signup")
        }
    }
}




@Composable
fun LoginScreen() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }val context = LocalContext.current
    val credentialManager = remember { CredentialManager.getInstance(context) }

    Column(
        // ... layout as before
    ) {
        // ... Input fields for email and password

        Button(onClick = {
            val credential = PasswordCredential(
                id = email, // Using email as a simple identifier
                username = email,
                password = password
            )
            // Request Credential Manager to get matching credentials
            credentialManager.getCredentials(
                GetPasswordRequest.Builder()
                    .setPasswordLoginHint(email)
                    .build(),
                activity = LocalContext.current as Activity
            ) { result ->
                if (result.credential != null) {
                    // Handle successful credential retrieval
                    val retrievedCredential = result.credential as PasswordCredential
                    // ... Authenticate user with your backend (if applicable)
                    // ... Navigate to the home screen
                } else {
                    // No matching credentials found, prompt user to sign up or try again
                    // ... Show an error message or navigate to SignupScreen
                }
            }
        }) {
            Text("Login")
        }// ... Signup button
    }
}