package com.example.almostdone.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.almostdone.navigation.DrawerScreens
import com.example.almostdone.screens.HomeScreen
import com.example.almostdone.screens.InboxScreen
import com.example.almostdone.screens.ProfileScreen
import com.example.almostdone.screens.SettingsScreen

@Composable
fun Navigation(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController, startDestination = DrawerScreens.Home.route, modifier = modifier) {
        composable(DrawerScreens.Home.route) {
            HomeScreen()
        }
        composable(DrawerScreens.Profile.route) {
            ProfileScreen()
        }
        composable(DrawerScreens.Inbox.route) {
            InboxScreen()
        }
        composable(DrawerScreens.Settings.route) {
            SettingsScreen()
        }
    }
}