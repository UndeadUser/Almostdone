package com.example.almostdone.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun InboxScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Inbox", style = MaterialTheme.typography.h4)
    }
}
