@file:OptIn(ExperimentalMaterial3Api::class)

package com.bocchi.mitarjeta.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bocchi.mitarjeta.R
import com.bocchi.mitarjeta.formfield.FormField
import com.bocchi.mitarjeta.ui.theme.Titulos
import com.bocchi.mitarjeta.ui.theme.backgroud


@Preview(showBackground = true)
@Composable
fun RegisterViewPreview() {
    val navController = rememberNavController()
    RegisterView(navController = navController)
}

@Composable
fun RegisterView(navController: NavController) {

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

            OutlinedTextField( //Campo de texto Nombre
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


//            OutlinedTextField( //Campo de texto CURP
//                modifier = Modifier
//                    .width(250.dp)
//                    .height(50.dp),
//                value = "",
//                onValueChange = {/**/ },
//                label = {
//                    Text(
//                        text = "CURP",
//                        color = Titulos
//                    )
//                },
//                shape = RoundedCornerShape(20.dp),
//                colors = TextFieldDefaults.outlinedTextFieldColors(
//                    containerColor = Color.White,
//                    focusedBorderColor = Color.Blue,   // Borde activo
//                    unfocusedBorderColor = Color.Gray, // Borde inactivo
//                )
//            )


        }
    }


}