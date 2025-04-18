package com.bocchi.mitarjeta.ui.designs

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavController

@Composable
fun VinculacionView(navController: NavController){
    var UID by rememberSaveable   { mutableStateOf("") }
        Box()
}