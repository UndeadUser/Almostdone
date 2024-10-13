package com.example.almostdone.navigation

sealed class DrawerScreens(val title: String, val route: String) {
    object Home : DrawerScreens("Home", "home")
    object Profile : DrawerScreens("Profile", "profile")
    object Inbox : DrawerScreens("Inbox", "inbox")
    object Settings : DrawerScreens("Settings", "settings")
}
