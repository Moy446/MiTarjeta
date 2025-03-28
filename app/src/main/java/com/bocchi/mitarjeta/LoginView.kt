package com.bocchi.mitarjeta

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bocchi.mitarjeta.formfield.FormField
import com.bocchi.mitarjeta.ui.theme.backgroud
import java.lang.reflect.Field


@Preview(showBackground = true)
@Composable
fun LoginView(modifier: Modifier = Modifier) {
//    var curp by remember { mutableSetOf("")  }
//    var password by remember { mutableSetOf("") }
    Box(modifier = Modifier.fillMaxSize().background(backgroud)) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(start = 55.dp, top = 88.dp, end = 55.dp, bottom = 248.dp),
            //.background(color = MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally) {

            Image(
                modifier = Modifier
                    .width(250.dp)
                    .height(250.dp),
                painter = painterResource(id = R.drawable.mi_tarjeta_logo),
                contentDescription = "logo de la aplicación",
                contentScale = ContentScale.Crop
            )
            FormField(modifier = Modifier
                .width(250.dp)
                .width(250.dp).align(
                    Alignment.CenterHorizontally
                ), label = "CURP")


        }
    }
}