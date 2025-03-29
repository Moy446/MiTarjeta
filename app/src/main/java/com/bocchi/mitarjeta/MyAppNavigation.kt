package com.bocchi.mitarjeta

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bocchi.mitarjeta.views.LoginView

@Composable
fun MyAppNavigation() {
    // Aquí puedes definir la lógica de navegación y las pantallas de tu aplicación
    // Por ejemplo, puedes usar un NavHost para gestionar la navegación entre diferentes pantallas
    // y pasar los parámetros necesarios a cada pantalla.
    // Puedes utilizar la biblioteca de navegación de Jetpack Compose para facilitar esto.
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") { LoginView(navController) }
        //composable("home") { HomeView(navController) }
        // Agrega más destinos según sea necesario
    }
}