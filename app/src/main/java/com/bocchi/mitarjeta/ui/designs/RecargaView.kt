package com.bocchi.mitarjeta.ui.designs

import android.util.Property
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import com.bocchi.mitarjeta.R
import com.bocchi.mitarjeta.botonescuadrados.BotonesCuadrados
import com.bocchi.mitarjeta.botonescuadrados.Seleccionado
import com.bocchi.mitarjeta.ui.theme.MiTarjetaTheme
import com.bocchi.mitarjeta.ui.theme.SecondButton
import com.bocchi.mitarjeta.ui.theme.Titulos
import com.bocchi.mitarjeta.ui.theme.backgroud
import com.google.relay.compose.BoxScopeInstanceImpl.align
import com.google.relay.compose.ColumnScopeInstanceImpl.align




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecargaView(navController: NavController) {

}


@Composable
fun SeleccionMontoRecargas (){
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
                botonCuadrado("50")
                botonCuadrado("100")
            }
            Row(
                modifier = Modifier
                    .padding(20.dp, 10.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(40.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                botonCuadrado("150")
                botonCuadrado("200")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SeleccionarTarjetaRecarga(){
    //variables combo box
    var tarjetasList = listOf("One","Two","Three","Four","Five")
    var isExpanded by remember { mutableStateOf(false) }
    var selectedChoice by remember { mutableStateOf(tarjetasList[0]) }

    Box (modifier = Modifier
        .border(width = 1.dp, color = Color(0xFF4FE49F))
        .fillMaxWidth()
        .height(116.dp)
    ) {
        Column (modifier = Modifier
            .padding(58.dp, 5.dp)
            .fillMaxWidth()
            .fillMaxHeight()
            .align(Alignment.Center)){
            textDescription(
                Modifier
                    .width(329.dp)
                    .height(26.dp)
                    .align(Alignment.CenterHorizontally),
                "Selecciona tu metodo de pago")
            ExposedDropdownMenuBox(expanded = isExpanded, onExpandedChange = {isExpanded  = !isExpanded}) {
                OutlinedTextField(
                    modifier = Modifier.menuAnchor(),
                    value = selectedChoice,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = {ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)},
                    shape = RoundedCornerShape(20.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        containerColor = Color.White,
                        focusedBorderColor = Color.Blue,   // Borde activo
                        unfocusedBorderColor = Color.Gray, // Borde inactivo
                    )
                )
                ExposedDropdownMenu(expanded = isExpanded, onDismissRequest = {isExpanded=false}) {
                    tarjetasList.forEachIndexed{ index,text->
                        DropdownMenuItem(
                            text = { Text(text = text) },
                            onClick = {
                                selectedChoice = tarjetasList[index]
                                isExpanded=false
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
                    .padding(horizontal = 20.dp),
                "Agregar tarjeta +"
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgegarTarjetaRecarga(){
    var numeroTarjeta by rememberSaveable { mutableStateOf("") }
    var titular by rememberSaveable { mutableStateOf("") }
    var expiracion by rememberSaveable { mutableStateOf("") }
    var cvv by rememberSaveable { mutableStateOf("") }

    //caja de rellenar tarjeta
    Box(
        Modifier
            .border(width = 1.dp, color = Color(0xFF4FE49F))
            .fillMaxWidth()
            .height(216.dp)
    ) {
        Column(modifier = Modifier
            .padding(58.dp, 20.dp)
            .fillMaxWidth()
            .fillMaxHeight()) {
            Row {
                //numero de tarjeta
                OutlinedTextField(
                    modifier = Modifier
                        .padding(1.dp)
                        .width(192.dp)
                        .height(45.dp),
                    value = numeroTarjeta,
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
                Image(modifier = Modifier
                    .width(42.dp)
                    .height(26.dp)
                    .padding(end = 2.dp)
                    .align(Alignment.CenterVertically),
                    painter = painterResource(id = R.drawable.visa_logo),
                    contentDescription = "image description",
                    contentScale = ContentScale.Fit
                )

                Image(modifier = Modifier
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
                onClick = { /*TODO*/ },
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

@Composable
fun MostrarMontosRecarga(){
    // precios
    var subtotal by remember { mutableStateOf(0) }
    var impuesto by remember { mutableStateOf(0) }

    // Muestras de pago
    Box(
        Modifier
            .border(width = 1.dp, color = Color(0xFF4FE49F))
            .fillMaxWidth()
            .height(100.dp)
    ){
        Column (modifier = Modifier.padding(58.dp,20.dp)){
            Row (modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween) {
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
            Row (modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween) {
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
            Row (modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween){
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

    MiTarjetaTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroud)
                .padding(top = 40.dp)
        ) {
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 80.dp)
                    .align(Alignment.Center)
                    .verticalScroll(rememberScrollState())
            ) {
                textTittle(
                    Modifier
                        .width(160.dp)
                        .height(52.dp)
                        .align(Alignment.CenterHorizontally),
                    "Recargas"
                )
                textDescription(
                    Modifier
                        .width(329.dp)
                        .height(26.dp)
                        .align(Alignment.CenterHorizontally),
                    "Proporciona los siguientes datos",
                )
                ReadOnlyTextField("12345678")

                SeleccionMontoRecargas()

                SeleccionarTarjetaRecarga()

                AgegarTarjetaRecarga()

                MostrarMontosRecarga()

                Button(modifier = Modifier
                    .shadow(elevation = 4.dp, spotColor = Color(0x40000000), ambientColor = Color(0x40000000))
                    .width(301.dp)
                    .height(42.dp)
                    .align(Alignment.CenterHorizontally),
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFE9762B)
                    ),
                    onClick = {/*TODO*/}) {
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
                menuView()
            }
        }
    }
}
