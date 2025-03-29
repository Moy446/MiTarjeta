package com.bocchi.mitarjeta.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bocchi.mitarjeta.ui.theme.backgroud
import com.bocchi.mitarjeta.ui.theme.FirstButton
import com.bocchi.mitarjeta.ui.theme.SecondButton
import com.bocchi.mitarjeta.ui.theme.Titulos
import com.bocchi.mitarjeta.ui.theme.Words
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavController
import com.bocchi.mitarjeta.R
import com.bocchi.mitarjeta.btnqr.BtnQr



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginView(navController: NavController) {
    var curp by rememberSaveable   { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var rememberUser by rememberSaveable { mutableStateOf(false) }
    Box(modifier = Modifier.fillMaxSize().background(backgroud)) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(start = 55.dp, top = 88.dp, end = 55.dp, bottom = 70.dp),
            //.background(color = MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally) {

            Image( //Logo de la app
                modifier = Modifier
                    .width(250.dp)
                    .height(250.dp),
                painter = painterResource(id = R.drawable.mi_tarjeta_logo),
                contentDescription = "logo de la aplicación",
                contentScale = ContentScale.Crop
            )
            OutlinedTextField( //Campo de texto CURP
                modifier = Modifier
                    .width(250.dp)
                    .height(50.dp),
                value = curp,
                onValueChange = { curp = it },
                label = {
                    Text(
                        text = "CURP",
                        color = Titulos
                    )
                },
                shape = RoundedCornerShape(20.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color.White,
                    focusedBorderColor = Color.Blue,   // Borde activo
                    unfocusedBorderColor = Color.Gray, // Borde inactivo
                )

            )
            Spacer(modifier = Modifier.height(15.dp)) //Espacio entre los campos de texto

            OutlinedTextField( //Campo de texto Password
                modifier = Modifier
                    .width(250.dp)
                    .height(50.dp),
                value = password,
                onValueChange = { password = it },
                label = {
                    Text(
                        text = "Contraseña",
                        color = Titulos
                    )
                },
                shape = RoundedCornerShape(20.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color.White,
                    focusedBorderColor = Color.Blue,   // Borde activo
                    unfocusedBorderColor = Color.Gray, // Borde inactivo
                )
            )

            Spacer(modifier = Modifier.height(15.dp)) //Espacio entre los campos de texto


            Row ( //Checkbox de recordar usuario
                modifier = Modifier
                    .width(190.dp)
                    .height(50.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Checkbox(
                    checked = rememberUser,
                    onCheckedChange = {rememberUser = it},
                    colors = CheckboxDefaults.colors(
                        checkedColor = Titulos,
                        uncheckedColor = Color.Gray
                    )
                )
                Text(
                    text = "Recordar usuario",
                    color = Words,
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button( //Boton Ingresar
                modifier = Modifier
                    .width(250.dp)
                    .height(50.dp),
                onClick = { navController.navigate("home") },
                colors = ButtonDefaults.buttonColors(containerColor = FirstButton)
                
            ) {
                Text(
                    text = "Ingresar",
                    color = Titulos
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Button( //Boton Registrarse
                modifier = Modifier
                    .width(250.dp)
                    .height(50.dp),
                onClick = { navController.navigate("registro") },
                colors = ButtonDefaults.buttonColors(containerColor = SecondButton)

            ) {
                Text(
                    text = "Registrar",
                    color = Titulos
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            BtnQr(
                modifier = Modifier
                    .width(250.dp)
                    .height(50.dp),
            )
            Text(
                text = "¿Olvidaste tu contraseña?",
                color = Words,
                modifier = Modifier.padding(top = 20.dp)
            )

        }
    }

}