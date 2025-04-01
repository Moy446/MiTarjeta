@file:OptIn(ExperimentalMaterial3Api::class)

package com.bocchi.mitarjeta.views

import android.widget.Toast
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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bocchi.mitarjeta.AuthRepository
import com.bocchi.mitarjeta.R
import com.bocchi.mitarjeta.formfield.FormField
import com.bocchi.mitarjeta.ui.theme.FirstButton
import com.bocchi.mitarjeta.ui.theme.SecondButton
import com.bocchi.mitarjeta.ui.theme.Titulos
import com.bocchi.mitarjeta.ui.theme.Words
import com.bocchi.mitarjeta.ui.theme.backgroud


@Preview(showBackground = true)
@Composable
fun RegisterViewPreview() {
    val navController = rememberNavController()
    RegisterView(navController = navController)
}

@Composable
fun RegisterView(navController: NavController) {
    var user by rememberSaveable   { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var curpChecked by remember { mutableStateOf(false) }
    var loading by remember { mutableStateOf(false) }
    var message by remember { mutableStateOf("") }

    Box(
        modifier = androidx.compose.ui.Modifier.background(backgroud)
            .fillMaxSize()
            .padding(start = 1.dp, top = 1.dp, end = 1.dp, bottom = 1.dp)
    ){


        Column(modifier = Modifier
            .fillMaxSize()
            .padding(start = 5.dp, top = 65.dp, end = 5.dp, bottom = 5.dp),
            //.background(color = MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally){
            Text(
                modifier = Modifier
                    .width(200.dp)
                    .height(52.dp),

                text = "Registro",
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                style = TextStyle(
                    fontSize = 40.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF3058B6),

                    )
            )

            Spacer(modifier = Modifier.height(30.dp))

            OutlinedTextField( //Campo de texto Curp
                enabled = false,
                modifier = Modifier
                    .width(250.dp)
                    .height(50.dp),
                value = "",
                onValueChange = {/**/ },
                label = {
                    Text(
                        text = "Curp",
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

            Spacer(modifier = Modifier.height(30.dp))

            OutlinedTextField( //Campo de texto Correo
                modifier = Modifier
                    .width(250.dp)
                    .height(50.dp),
                value = user,
                onValueChange = { user = it },
                label = {
                    Text(
                        text = "Correo Electrónico",
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

            Spacer(modifier = Modifier.height(30.dp))

            OutlinedTextField( //Campo de texto Contraseña
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
                ),
                visualTransformation = PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.height(30.dp))

            Button( //Boton Validacion
                modifier = Modifier
                    .width(250.dp)
                    .height(50.dp),
                onClick = { navController.navigate("") },
                colors = ButtonDefaults.buttonColors(containerColor = SecondButton)

            ) {
                Text(
                    text = "Validar CURP",
                    color = Titulos
                )
            }
            Spacer(modifier = Modifier.height(30.dp))

            Row ( //Checkbox de Verificar
                modifier = Modifier
                    .width(190.dp)
                    .height(50.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Validacion INE",
                    color = Titulos,
                )
                Checkbox(
                    checked = curpChecked,
                    onCheckedChange = {curpChecked = it},
                    colors = CheckboxDefaults.colors(
                        checkedColor = Titulos,
                        uncheckedColor = Color.Gray
                    )
                )

            }

            Spacer(modifier = Modifier.height(30.dp))

            Button( //Boton Registrarse
                modifier = Modifier
                    .width(250.dp)
                    .height(50.dp),
                onClick = {
                    if (user.isNotEmpty() && password.isNotEmpty()) {
                        if(validacionCorreo(user) == true){
                            loading = true
                            AuthRepository.createAccount(user, password) { success, error ->
                                loading = false
                                if (success) {
                                    navController.navigate("home")
                                } else {
                                    message = error ?: "Error desconocido"
                                }
                            }
                        }else{
                            Toast.makeText(
                                navController.context,
                                "Correo no valido",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        Toast.makeText(
                            navController.context,
                            "Por favor, completa todos los campos",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = FirstButton)

            ) {
                Text(
                    text = "Registrar",
                    color = Titulos
                )
            }


        }



    }

}

fun validacionCorreo(user: String): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(user).matches()
}