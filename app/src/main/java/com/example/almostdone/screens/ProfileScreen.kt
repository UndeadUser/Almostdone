package com.example.almostdone.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ProfileScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Profile", style = MaterialTheme.typography.h4)
    }
}
