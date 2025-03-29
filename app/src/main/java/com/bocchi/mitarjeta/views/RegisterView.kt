package com.bocchi.mitarjeta.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bocchi.mitarjeta.formfield.FormField
import com.bocchi.mitarjeta.ui.theme.backgroud


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterView(navController: NavController) {
    Box(
        modifier = androidx.compose.ui.Modifier.background(backgroud)
            .fillMaxSize()
            .padding(start = 55.dp, top = 88.dp, end = 55.dp, bottom = 70.dp)
    )

}