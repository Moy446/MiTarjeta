package com.bocchi.mitarjeta.ui.designs

import android.annotation.SuppressLint
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bocchi.mitarjeta.R
import com.bocchi.mitarjeta.database.AuthRepository
import com.bocchi.mitarjeta.database.setTarjeta
import com.bocchi.mitarjeta.navigation.NavItemList
import com.bocchi.mitarjeta.navigation.NavItemList.navItemList
import com.bocchi.mitarjeta.navigation.handleNavigationWithLogout
import com.bocchi.mitarjeta.ui.theme.MiTarjetaTheme
import com.bocchi.mitarjeta.ui.theme.backgroud

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun VinculacionView(navController: NavController) { //navController: controlador de navegación entre pantallas.

    //variables para una tarjeta
    var UIDTarjeta by rememberSaveable { mutableStateOf("") }
    var saldoTarjeta by rememberSaveable { mutableStateOf("") }

    //menu variables
    var selectedRoute = remember { mutableStateOf("vinculacion") }

    //variable para el dialog
    var openDialogClose = remember { mutableStateOf (false) }

    MiTarjetaTheme {
        Scaffold(bottomBar = {
            //Impresion del Menu
            menuView(
                navItemList = NavItemList.navItemList,
                selectedView = selectedRoute.value,
                onItemSelected = { titulo ->
                    handleNavigationWithLogout(titulo,navController,selectedRoute,openDialogClose)
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
                        .padding(bottom = 80.dp, start = 10.dp, end = 10.dp)
                        .align(Alignment.Center)
                        .verticalScroll(rememberScrollState())
                ) {
                    Row{
                        botonBack(
                            Modifier
                                .padding(30.dp, 15.dp)
                                .clickable {
                                    navController.popBackStack()
                                })

                        textTittle(
                            Modifier
                                .width(250.dp)
                                .height(52.dp),
                            "Vinculacion"
                        )
                    }
                    textDescription(
                        Modifier
                            .width(329.dp)
                            .height(26.dp)
                            .align(Alignment.CenterHorizontally),
                        "Selecciona una forma para vincular tu tarjeta"
                    )

                    ReadOnlyTextField(UIDTarjeta)

                    Row (modifier = Modifier
                        .padding(40.dp, 20.dp)){

                        //boton RFID
                        Button(modifier = Modifier
                            .padding(end = 10.dp)
                            .width(135.dp)
                            .height(54.dp)
                            ,colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFE9762B)
                                ),
                            shape = RoundedCornerShape(40.dp),
                            onClick = {
                                /*TODO
                                ACTIVAR EL SENSEOR RFID Y LEER LA TARJETA
                                 */
                            }) {
                            Text(
                                text = "RFID",
                                style = TextStyle(
                                    fontSize = 20.sp,
                                    fontFamily = FontFamily(Font(R.font.relay_niramit_medium)),
                                    fontWeight = FontWeight(500),
                                    color = Color(0xFF3058B6),
                                )
                            )
                        }
                        //boton QR
                        Button(modifier = Modifier
                            .padding(start = 10.dp)
                            .width(135.dp)
                            .height(54.dp)
                            ,colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFE9762B)
                            ),
                            shape = RoundedCornerShape(40.dp),
                            onClick = {
                                /*TODO
                                    activar la camara QR y leer el codigo
                                 */
                                setTarjeta(AuthRepository.getCurrentUser().toString(),UIDTarjeta,saldoTarjeta)
                                navController.navigate("home")
                            }) {
                            Text(
                                text = "QR",
                                style = TextStyle(
                                    fontSize = 20.sp,
                                    fontFamily = FontFamily(Font(R.font.relay_niramit_medium)),
                                    fontWeight = FontWeight(500),
                                    color = Color(0xFF3058B6),
                                )
                            )
                        }
                    }

                    //botton Vincular
                    Button(modifier = Modifier
                        .padding(vertical = 5.dp)
                        .width(301.dp)
                        .height(42.dp)
                        .align(Alignment.CenterHorizontally),
                        shape = RoundedCornerShape(20.dp),
                        enabled = UIDTarjeta != "",
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFE9762B)
                        ),
                        onClick = {
                            /*TODO
                                Agregar a la base de datos
                                el uid de la tarjeta que se esta mandado
                                */

                        }) {
                        Text(
                            text = "Vincular",
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontFamily = FontFamily(Font(R.font.relay_niramit_medium)),
                                fontWeight = FontWeight(500),
                                color = Color(0xFF3058B6),
                            )
                        )
                    }
                }
            }
        }
    }
    if (openDialogClose.value) {
        AlertDialog(
            icon = R.drawable.menu_close_img,
            dialogTitle = "Cerrar sesión",
            dialogText = "¿Está seguro que quiere cerrar sesión?",
            dialogConfirm = "Cerrar sesión",
            showDialog = openDialogClose.value,
            onAccept = {
                openDialogClose.value = false
                com.bocchi.mitarjeta.database.AuthRepository.signOut()
                navController.navigate("login") {
                    popUpTo(0) { inclusive = true }
                }
            },
            onDismiss = {
                openDialogClose.value = false
            }
        )
    }   
}

@Preview
@Composable
fun PreviewVinculacion() {
    var navController = rememberNavController()
    VinculacionView(navController)
}