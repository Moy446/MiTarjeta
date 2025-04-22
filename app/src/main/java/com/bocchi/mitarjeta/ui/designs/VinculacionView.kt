package com.bocchi.mitarjeta.ui.designs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bocchi.mitarjeta.R
import com.bocchi.mitarjeta.navigation.NavItemList
import com.bocchi.mitarjeta.navigation.NavItemList.navItemList
import com.bocchi.mitarjeta.ui.theme.MiTarjetaTheme
import com.bocchi.mitarjeta.ui.theme.backgroud

@Composable
fun VinculacionView(navController: NavController){ //navController: controlador de navegación entre pantallas.

    var UIDTarjeta by rememberSaveable { mutableStateOf("") }

    //menu variables
    var selectedRoute = remember { mutableStateOf("Vinculo") }

    MiTarjetaTheme {
        Scaffold(bottomBar = {
            //Impresion del Menu
            menuView(
                navItemList = NavItemList.navItemList,
                selectedView = selectedRoute.value,
                onItemSelected = { titulo ->
                    selectedRoute.value = titulo
                    navController.navigate(titulo)
                },
            )
        }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(backgroud)
                    .padding(top = 40.dp, bottom = 50.dp)
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 80.dp)
                        .align(Alignment.Center)
                        .verticalScroll(rememberScrollState())
                ) {
                    Row {
                        botonBack(
                            Modifier
                                .padding(start = 30.dp, end = 50.dp, top = 15.dp)
                                .clickable {
                                    navController.popBackStack()
                                })
                        textTittle(
                            Modifier
                                .width(170.dp)
                                .height(52.dp),
                            "Recargas"
                        )
                    }

                    textDescription(
                        Modifier
                            .width(329.dp)
                            .height(26.dp)
                            .align(Alignment.CenterHorizontally),
                        "Proporciona los siguientes datos",
                    )
                    ReadOnlyTextField(uid)

                    selectedMonto.value = seleccionMontoRecargas()

                    seleccionarTarjetaRecarga(
                        tarjetasList = tarjetas,
                        tarjetaSeleccionada = tarjetaSeleccionada,
                        onSeleccionarTarjeta = { tarjetaSeleccionada = it },
                        onAgregarTarjeta = { agregarTarjeta.value = !agregarTarjeta.value }
                    )

                    if (agregarTarjeta.value) {
                        agegarTarjetaRecarga(true) { nuevaTarjeta ->
                            if (!tarjetasDebitoList.contains(nuevaTarjeta)) {
                                tarjetasDebitoList.add(nuevaTarjeta)
                                var numero = nuevaTarjeta.numeroTarjeta
                                numero = numero.reversed()
                                numero = "**${numero[3]}${numero[2]}${numero[1]}${numero[0]}"
                                tarjetas.add(numero)
                                tarjetaSeleccionada = numero
                            }
                            agregarTarjeta.value = false
                        }
                    }

                    mostrarMontosRecarga(selectedMonto.value)

                    //botton pagar
                    Button(modifier = Modifier
                        .padding(vertical = 5.dp)
                        .width(301.dp)
                        .height(42.dp)
                        .align(Alignment.CenterHorizontally),
                        shape = RoundedCornerShape(20.dp),
                        enabled = tarjetaSeleccionada != "------",
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFE9762B)
                        ),
                        onClick = {
                            /*TODO
                                una tarjeta la cual va a pagar*/
                        }) {
                        Text(
                            text = "Pagar",
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontFamily = FontFamily(Font(R.font.relay_niramit_medium)),
                                fontWeight = FontWeight(500),
                                color = Color(0xFF3058B6),

                                )
                        )
                    }
                }
                //Impresion del Menu
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(106.dp)
                        .align(Alignment.BottomCenter)
                ) {
                }
            }
        }
    }

   /*
    menuView(
        navItemList = navItemList,
        selectedView = selectedRoute,
        onItemSelected = { nuevoItem -> onItemSelected = nuevoItem
    ) { }

    */
}