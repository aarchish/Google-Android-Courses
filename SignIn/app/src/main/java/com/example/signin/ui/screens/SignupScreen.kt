package com.example.signin.ui.screens

import android.app.Activity
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
fun SignupScreen() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember {
        mutableStateOf(
            ""
        )
    }

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
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Confirm Password") },
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            // TODO: Implement signup logic using Credential Manager
        }) {
            Text("Signup")
        }
    }
}


@Composable
fun SignupScreen() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    val context = LocalContext.current
    val credentialManager = remember { CredentialManager.getInstance(context) }

    Column(
        // ... layout as before
    ) {
        // ... Input fields for email, password, and confirm password

        Button(onClick = {
            if (password == confirmPassword) {
                val credential = PasswordCredential(
                    id = email,
                    username = email,
                    password = password
                )
                // Request Credential Manager to save the new credential
                credentialManager.saveCredentials(
                    listOf(credential),
                    activity = LocalContext.current as Activity
                ) { result ->
                    if (result is SaveResult.Success) {
                        // Credential saved successfully
                        // ... Optionally, authenticate the user immediately
                        // ... Navigate to the home screen
                    } else {
                        // Handle errors during credential saving
                        // ... Show an error message
                    }
                }
            } else {
                // Passwords don't match, show an error message
            }
        }) {
            Text("Signup")
        }
    }
}