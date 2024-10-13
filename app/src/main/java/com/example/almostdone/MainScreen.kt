package com.example.almostdone

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.almostdone.navigation.DrawerScreens
import com.example.almostdone.navigation.Navigation
import kotlinx.coroutines.launch

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    // Get the current back stack entry to check the current route
    val currentBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry.value?.destination?.route

    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = {
            DrawerContent(navController = navController, scaffoldState = scaffoldState)
        },
        topBar = {
            TopAppBar(
                title = { Text(text = "") },
                navigationIcon = {
                    IconButton(onClick = {
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menu"
                        )
                    }
                },
                actions = {
                    // Show the search icon only for the Home screen
                    if (currentRoute == DrawerScreens.Home.route) {
                        SearchAppBar()
                    }
                }
            )
        },
        content = { paddingValues ->
            Navigation(navController = navController, modifier = Modifier.padding(paddingValues))
        }
    )
}

@Composable
fun SearchAppBar() {
    // State to determine whether the search mode is active
    var searchMode by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }

    if (searchMode) {
        // Search mode active, show search field
        TextField(
            value = searchText,
            onValueChange = { searchText = it },
            placeholder = { Text("Search...") },
            singleLine = true,
            // Ensure the TextField has enough height and width
            modifier = Modifier
                .fillMaxWidth() // Full width to fit in the TopAppBar
                .height(56.dp), // Material Design recommends a height of 56dp for AppBars
            textStyle = MaterialTheme.typography.body1,  // Correct text style for proper font size
            trailingIcon = {
                IconButton(onClick = {
                    // Clear the search text and exit search mode
                    searchText = ""
                    searchMode = false
                }) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = "Close search")
                }
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.surface, // Optional: Make background match the app bar
                textColor = Color.Black, // Set text color to black
                placeholderColor = Color.Gray // Optional: Set placeholder color
            )
        )
    } else {
        // Show the search icon on the right side of the app bar
        IconButton(onClick = {
            searchMode = true // Activate search mode when the icon is clicked
        }) {
            Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
        }
    }
}

@Composable
fun DrawerContent(navController: NavHostController, scaffoldState: ScaffoldState) {
    val scope = rememberCoroutineScope()

    Column {
        Text("", style = MaterialTheme.typography.h6, modifier = Modifier.padding(16.dp))
        Divider()

        // Drawer Items
        DrawerItem(item = DrawerScreens.Home, navController, scaffoldState)
        DrawerItem(item = DrawerScreens.Profile, navController, scaffoldState)
        DrawerItem(item = DrawerScreens.Inbox, navController, scaffoldState)
        DrawerItem(item = DrawerScreens.Settings, navController, scaffoldState)
    }
}

@Composable
fun DrawerItem(item: DrawerScreens, navController: NavHostController, scaffoldState: ScaffoldState) {
    val scope = rememberCoroutineScope()

    TextButton(onClick = {
        navController.navigate(item.route)
        scope.launch {
            scaffoldState.drawerState.close()
        }
    }) {
        Text(text = item.title)
    }
}