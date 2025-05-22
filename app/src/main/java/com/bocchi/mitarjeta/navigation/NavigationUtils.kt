package com.bocchi.mitarjeta.navigation

import androidx.compose.runtime.MutableState
import androidx.navigation.NavController

fun handleNavigationWithLogout(titulo: String, navController: NavController, selectedRoute: MutableState<String>, openDialog: MutableState<Boolean>) {
    if (titulo == "close") {
        openDialog.value = true
    } else {
        selectedRoute.value = titulo
        navController.navigate(titulo) {
            launchSingleTop = true
        }
    }
}