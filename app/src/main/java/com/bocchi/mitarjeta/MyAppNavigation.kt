package com.bocchi.mitarjeta

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.bocchi.mitarjeta.ui.designs.RecargaView
import com.bocchi.mitarjeta.ui.designs.TarjetasView
import com.bocchi.mitarjeta.views.LoginView
import com.bocchi.mitarjeta.views.RegisterView

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
        composable("home") { TarjetasView(navController,"OECM030426HJCTBSA1") }
        composable("recargas/{uid}",
            arguments = listOf(navArgument("uid") { type = NavType.StringType })){
            backStackEntry->
            val uid = backStackEntry.arguments?.getString("uid")
            RecargaView(navController,uid) }
        composable("register") {  RegisterView(navController)}
        composable("curp") { /* Aquí puedes agregar la vista para CURP */ }

        // Agrega más destinos según sea necesario
    }
}