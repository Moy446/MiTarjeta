@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.bocchi.mitarjeta.views

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bocchi.mitarjeta.database.AuthRepository
import com.bocchi.mitarjeta.R
import com.bocchi.mitarjeta.btnqr.BtnQr
import com.bocchi.mitarjeta.database.CRUDUsers
import com.bocchi.mitarjeta.ui.designs.validacionCurp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

/* ESTO SIRVE PARA MOSTRAR EL PREVIEW*/
@Preview(showBackground = true)
@Composable
fun LoginViewPreview() {
        val navController = rememberNavController()
        LoginView(navController = navController)
    }
/* AQUI TERMINA LA MUESTRA DEL PREVIEW*/

//@Preview(showBackground = true)
@Composable
fun LoginView(navController: NavController) {
    var user by rememberSaveable   { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var rememberUser by rememberSaveable { mutableStateOf(false) }
    var loading by remember { mutableStateOf(false) }
    var message by remember { mutableStateOf("") }
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
                    .defaultMinSize(minHeight = 64.dp),
                value = user,
                onValueChange = {if (it.length <= 18){ // Limitar a 18 caracteres
                    user = it.uppercase() // Convierte a mayúsculas
                } },
                label = {
                    Text(

                        text = "CURP",
                        color = Titulos,
                        style = TextStyle(
                            fontSize = 10.sp,
                            fontFamily = FontFamily(Font(R.font.relay_niramit_medium)),
                            fontWeight = FontWeight(500)
                        )
                    )
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    autoCorrect = false,
                ),
                shape = RoundedCornerShape(16.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color.White,
                    focusedBorderColor = Color.Blue,   // Borde activo
                    unfocusedBorderColor = Color.Gray, // Borde inactivo
                )

            )
            Spacer(modifier = Modifier.height(15.dp)) //Espacio entre los campos de texto

            OutlinedTextField( //Campo de texto Password
                modifier = Modifier
                    .width(250.dp).defaultMinSize(minHeight = 64.dp),
                value = password,
                onValueChange = { password = it },
                label = {
                    Text(
                        text = "Contraseña",
                        color = Titulos,
                        style = TextStyle(
                            fontSize = 10.sp,
                            fontFamily = FontFamily(Font(R.font.relay_niramit_medium)),
                            fontWeight = FontWeight(500)
                        )
                    )
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    autoCorrect = false,
                    keyboardType = KeyboardType.Password
                ),
                shape = RoundedCornerShape(16.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color.White,
                    focusedBorderColor = Color.Blue,   // Borde activo
                    unfocusedBorderColor = Color.Gray, // Borde inactivo
                )
                ,visualTransformation = PasswordVisualTransformation()

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
                onClick = {
                    if(user.isNotEmpty() && password.isNotEmpty()) {
                        if(validacionCurp(user) == true){
                            loading = true
                            CRUDUsers.signInWithCurp(user, password) { success, errorMessage ->
                                loading = false
                                if (success) {
                                    navController.navigate("home/${user}")
                                } else {
                                    Toast.makeText(
                                        navController.context,
                                        "Autenticación fallida: Credenciales incorrectas",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    message = errorMessage ?: "Error desconocido"
                                }
                            }
                        } else{
                            Toast.makeText(
                                navController.context,
                                "CURP no válido",
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
                    text = "Ingresar",
                    color = Titulos
                )
            }
            Spacer(modifier = Modifier.height(20.dp))

            Button( //Boton Registrarse
                modifier = Modifier
                    .width(250.dp)
                    .height(50.dp),
                onClick = { navController.navigate("register") },
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
                    .height(50.dp)

            )
            Text(
                text = "¿Olvidaste tu contraseña?",

                color = Words,
                modifier = Modifier.padding(top = 20.dp).clickable {
                    if (user.isNotEmpty()) {
                        recuperarPassword(user)
                    } else {
                        Toast.makeText(
                            navController.context,
                            "Por favor, ingresa tu CURP",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },

            )

        }
    }

}

fun recuperarPassword(curp: String) {
    val db = FirebaseFirestore.getInstance()
    val auth = FirebaseAuth.getInstance()
    db.collection("Usuarios")
        .whereEqualTo("curp", curp)
        .get()
        .addOnSuccessListener { documents ->
            if (documents.isEmpty) {
                // No se encontró el CURP
                Toast.makeText(
                    null,
                    "No se encontró el CURP",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                for (document in documents) {
                    val email = document.getString("email")
                    if (email != null) {
                        auth.sendPasswordResetEmail(email)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    // Envío exitoso
                                    Toast.makeText(
                                        null,
                                        "Se ha enviado un correo para restablecer la contraseña",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                } else {
                                    // Error al enviar el correo
                                    Toast.makeText(
                                        null,
                                        "Error al enviar el correo: ${task.exception?.message}",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                    }
                }
            }
        }
        .addOnFailureListener { exception ->
            // Error al buscar el CURP
            Toast.makeText(
                null,
                "Error al buscar el CURP: ${exception.message}",
                Toast.LENGTH_SHORT
            ).show()
        }
}

