package com.bocchi.mitarjeta.ui.designs

import android.annotation.SuppressLint
import android.util.Property
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bocchi.mitarjeta.R
import com.bocchi.mitarjeta.TarjetasDebito
import com.bocchi.mitarjeta.botonescuadrados.BotonesCuadrados
import com.bocchi.mitarjeta.botonescuadrados.Seleccionado
import com.bocchi.mitarjeta.navigation.NavItemList
import com.bocchi.mitarjeta.ui.theme.MiTarjetaTheme
import com.bocchi.mitarjeta.ui.theme.SecondButton
import com.bocchi.mitarjeta.ui.theme.Titulos
import com.bocchi.mitarjeta.ui.theme.backgroud
import com.google.relay.compose.BoxScopeInstanceImpl.align
import com.google.relay.compose.ColumnScopeInstanceImpl.align
import java.util.Collections.addAll


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RecargaView(navController: NavController, uid: String?) {
    var selectedMonto = remember { mutableStateOf(50) }
    var agregarTarjeta = remember { mutableStateOf(false) }

    // Lista reactiva de tarjetas
    val tarjetasDebitoList = remember { mutableStateListOf<TarjetasDebito>() }
    val tarjetas =
        remember { mutableStateListOf<String>().apply { addAll(obtenerNumerosTarjetas()) } }
    var tarjetaSeleccionada by remember { mutableStateOf(tarjetas.firstOrNull() ?: "") }
    //menu variables
    var selectedRoute = remember { mutableStateOf("home") }


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
}

@Composable
fun seleccionMontoRecargas(): Int {
    var selectedMonto = remember { mutableStateOf("50") }
    //Caja de seleccionar monto
    Box(
        Modifier
            .border(width = 1.dp, color = Color(0xFF4FE49F))
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(horizontal = 40.dp)) {
            textDescription(
                Modifier
                    .width(329.dp)
                    .height(26.dp)
                    .align(Alignment.CenterHorizontally),
                "Selecciona el monto deseado"
            )
            Row(
                modifier = Modifier
                    .padding(20.dp, 10.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(40.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                botonCuadrado("50", selectedMonto.value == "50") { selectedMonto.value = "50" }
                botonCuadrado("100", selectedMonto.value == "100") { selectedMonto.value = "100" }
            }
            Row(
                modifier = Modifier
                    .padding(20.dp, 10.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(40.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                botonCuadrado("150", selectedMonto.value == "150") { selectedMonto.value = "150" }
                botonCuadrado("200", selectedMonto.value == "200") { selectedMonto.value = "200" }
            }
        }
    }
    return selectedMonto.value.toInt()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun seleccionarTarjetaRecarga(
    tarjetasList: List<String>, tarjetaSeleccionada: String,
    onSeleccionarTarjeta: (String) -> Unit, onAgregarTarjeta: () -> Unit
) {

    var isExpanded = remember { mutableStateOf(false) }


    Box(
        modifier = Modifier
            .border(width = 1.dp, color = Color(0xFF4FE49F))
            .fillMaxWidth()
            .height(116.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(58.dp, 5.dp)
                .fillMaxWidth()
                .fillMaxHeight()
                .align(Alignment.Center)
        ) {
            textDescription(
                Modifier
                    .width(329.dp)
                    .height(26.dp)
                    .align(Alignment.CenterHorizontally),
                "Selecciona tu metodo de pago"
            )
            ExposedDropdownMenuBox(
                expanded = isExpanded.value,
                onExpandedChange = { isExpanded.value = !isExpanded.value }) {
                OutlinedTextField(
                    modifier = Modifier.menuAnchor(),
                    value = tarjetaSeleccionada,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded.value) },
                    shape = RoundedCornerShape(20.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        containerColor = Color.White,
                        focusedBorderColor = Color.Blue,
                        unfocusedBorderColor = Color.Gray,
                    )
                )
                ExposedDropdownMenu(
                    expanded = isExpanded.value,
                    onDismissRequest = { isExpanded.value = false }) {
                    tarjetasList.forEach { tarjeta ->
                        DropdownMenuItem(
                            text = { Text(tarjeta) },
                            onClick = {
                                onSeleccionarTarjeta(tarjeta)
                                isExpanded.value = false
                            },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                        )
                    }
                }
            }
            textDescription(
                Modifier
                    .width(329.dp)
                    .height(26.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(horizontal = 20.dp)
                    .clickable { onAgregarTarjeta() },
                "Agregar tarjeta +"
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun agegarTarjetaRecarga(expand: Boolean, onTarjetaAgregada: (TarjetasDebito) -> Unit) {
    var isExpand = remember { mutableStateOf(expand) }
    var numeroTarjeta by rememberSaveable { mutableStateOf("") }
    var titular by rememberSaveable { mutableStateOf("") }
    var expiracion by rememberSaveable { mutableStateOf("") }
    var cvv by rememberSaveable { mutableStateOf("") }
    var validate =
        numeroTarjeta.isNotEmpty() && titular.isNotEmpty() && expiracion.isNotEmpty() && cvv.isNotEmpty()

    //escuchar si hay algun cambio
    LaunchedEffect(expand) {
        isExpand.value = expand
    }

    if (isExpand.value) {
        //caja de rellenar tarjeta
        Box(
            Modifier
                .border(width = 1.dp, color = Color(0xFF4FE49F))
                .fillMaxWidth()
                .height(216.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(58.dp, 20.dp)
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                Row {
                    //numero de tarjeta
                    OutlinedTextField(
                        modifier = Modifier
                            .padding(1.dp)
                            .width(192.dp)
                            .height(45.dp),
                        value = numeroTarjeta,
                        textStyle = TextStyle(
                            fontSize = 11.sp,
                            fontFamily = FontFamily(Font(R.font.relay_niramit_medium)),
                            fontWeight = FontWeight(500),
                        ),
                        onValueChange = { numeroTarjeta = it },
                        placeholder = {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(25.dp),
                                text = "Numero de la tarjeta",
                                color = Titulos,
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    fontFamily = FontFamily(Font(R.font.relay_niramit_medium)),
                                ),
                            )
                        },
                        shape = RoundedCornerShape(20.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            containerColor = Color.White,
                            focusedBorderColor = Color.Blue,   // Borde activo
                            unfocusedBorderColor = Color.Gray, // Borde inactivo
                        ),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            autoCorrect = false,
                            keyboardType = KeyboardType.Number,
                        )
                    )
                    Image(
                        modifier = Modifier
                            .width(42.dp)
                            .height(26.dp)
                            .padding(end = 2.dp)
                            .align(Alignment.CenterVertically),
                        painter = painterResource(id = R.drawable.visa_logo),
                        contentDescription = "image description",
                        contentScale = ContentScale.Fit
                    )

                    Image(
                        modifier = Modifier
                            .width(42.dp)
                            .height(26.dp)
                            .padding(start = 2.dp)
                            .align(Alignment.CenterVertically),
                        painter = painterResource(id = R.drawable.master_card),
                        contentDescription = "image description",
                        contentScale = ContentScale.Fit
                    )
                }
                //titular
                OutlinedTextField(
                    modifier = Modifier
                        .padding(1.dp)
                        .width(192.dp)
                        .height(45.dp),
                    textStyle = TextStyle(
                        fontSize = 11.sp,
                        fontFamily = FontFamily(Font(R.font.relay_niramit_medium)),
                        fontWeight = FontWeight(500),
                    ),
                    value = titular,
                    onValueChange = { titular = it },
                    placeholder = {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(25.dp),
                            text = "Titulas de la tarjeta",
                            color = Titulos,
                            style = TextStyle(
                                fontSize = 10.sp,
                                fontFamily = FontFamily(Font(R.font.relay_niramit_medium)),
                                fontWeight = FontWeight(500),
                            ),
                        )
                    },
                    shape = RoundedCornerShape(20.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        containerColor = Color.White,
                        focusedBorderColor = Color.Blue,   // Borde activo
                        unfocusedBorderColor = Color.Gray, // Borde inactivo
                    ),
                    singleLine = true,
                )
                Row {
                    //Fecha de expiracion
                    OutlinedTextField(
                        modifier = Modifier
                            .padding(1.dp)
                            .width(192.dp)
                            .height(45.dp),
                        textStyle = TextStyle(
                            fontSize = 11.sp,
                            fontFamily = FontFamily(Font(R.font.relay_niramit_medium)),
                            fontWeight = FontWeight(500),
                        ),
                        value = expiracion,
                        onValueChange = { expiracion = it },
                        placeholder = {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(25.dp),
                                text = "DD/MM",
                                color = Titulos,
                                style = TextStyle(
                                    fontSize = 10.sp,
                                    fontFamily = FontFamily(Font(R.font.relay_niramit_medium)),
                                    fontWeight = FontWeight(500),
                                ),
                            )
                        },
                        shape = RoundedCornerShape(20.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            containerColor = Color.White,
                            focusedBorderColor = Color.Blue,   // Borde activo
                            unfocusedBorderColor = Color.Gray, // Borde inactivo
                        ),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            autoCorrect = false,
                            keyboardType = KeyboardType.Number,
                        )
                    )
                    //cvv
                    OutlinedTextField(
                        modifier = Modifier
                            .padding(1.dp)
                            .width(192.dp)
                            .height(45.dp),
                        textStyle = TextStyle(
                            fontSize = 11.sp,
                            fontFamily = FontFamily(Font(R.font.relay_niramit_medium)),
                            fontWeight = FontWeight(500),
                        ),
                        value = cvv,
                        onValueChange = { cvv = it },
                        placeholder = {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(25.dp),
                                text = "CVV",
                                color = Titulos,
                                style = TextStyle(
                                    fontSize = 10.sp,
                                    fontFamily = FontFamily(Font(R.font.relay_niramit_medium)),
                                    fontWeight = FontWeight(500),
                                ),
                            )
                        },
                        shape = RoundedCornerShape(20.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            containerColor = Color.White,
                            focusedBorderColor = Color.Blue,   // Borde activo
                            unfocusedBorderColor = Color.Gray, // Borde inactivo
                        ),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            autoCorrect = false,
                            keyboardType = KeyboardType.Number,
                        )
                    )
                }
                Button(
                    modifier = Modifier
                        .width(140.dp)
                        .height(35.dp)
                        .align(Alignment.CenterHorizontally),
                    enabled = validate,
                    onClick = {
                        /*TODO
                        *  falta agregar la tarjeta a base de datos*/
                        onTarjetaAgregada(TarjetasDebito(numeroTarjeta, titular, expiracion, cvv))
                        isExpand.value = false
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = SecondButton)
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        text = "Agregar tarjeta",
                        style = TextStyle(
                            fontSize = 13.sp,
                            fontFamily = FontFamily(Font(R.font.relay_niramit_medium)),
                            fontWeight = FontWeight(500),
                            color = Color(0xFF29536D),
                        )
                    )
                }

            }
        }
    }
}

@Composable
fun mostrarMontosRecarga(monto: Int) {
    // precios
    var subtotal by remember { mutableStateOf(monto) }
    var impuesto by remember { mutableStateOf(0) }

    LaunchedEffect(monto) {
        subtotal = monto
        if (monto > 100) {
            impuesto = (monto * .02).toInt()
        } else
            impuesto = 0
    }

    // Muestras de pago
    Box(
        Modifier
            .border(width = 1.dp, color = Color(0xFF4FE49F))
            .fillMaxWidth()
            .height(100.dp)
    ) {
        Column(modifier = Modifier.padding(58.dp, 20.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier
                        .width(100.dp)
                        .height(20.dp),
                    text = "Subtotal",
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 19.6.sp,
                        fontFamily = FontFamily(Font(R.font.relay_inter_bold)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFF29536D),
                    )
                )
                Text(
                    modifier = Modifier
                        .width(100.dp)
                        .height(20.dp),

                    text = "$subtotal $",
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 19.6.sp,
                        fontFamily = FontFamily(Font(R.font.relay_inter_bold)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFF29536D),
                    )
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier
                        .width(100.dp)
                        .height(20.dp),
                    text = "Impuesto",
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 19.6.sp,
                        fontFamily = FontFamily(Font(R.font.relay_inter_bold)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFF29536D),
                    )
                )
                Text(
                    modifier = Modifier
                        .width(100.dp)
                        .height(20.dp),
                    text = "$impuesto $",
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 19.6.sp,
                        fontFamily = FontFamily(Font(R.font.relay_inter_bold)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFF29536D),
                    )
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier
                        .width(100.dp)
                        .height(20.dp),
                    text = "Total",
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 19.6.sp,
                        fontFamily = FontFamily(Font(R.font.relay_inter_bold)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFF29536D),
                    )
                )
                Text(
                    modifier = Modifier
                        .width(100.dp)
                        .height(20.dp),
                    text = "${impuesto + subtotal} $",
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 19.6.sp,
                        fontFamily = FontFamily(Font(R.font.relay_inter_bold)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFF29536D),
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRecargas() {
}

fun obtenerNumerosTarjetas(): MutableList<String> {
    /*TODO
    *  FALTA LA EXTRACCION DE BASE DE DATOS PARA LAS TARJETAS*/
    //var tarjetasDebito:MutableList<TarjetasDebito> = mutableListOf(TarjetasDebito("------","----","-----","----"))
    var numeroTarjetas: MutableList<String> = mutableListOf("------")

    return numeroTarjetas
}