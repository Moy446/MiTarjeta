package com.bocchi.mitarjeta.ui.designs

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bocchi.mitarjeta.R
import com.bocchi.mitarjeta.Tarjetas
import com.bocchi.mitarjeta.ui.theme.Titulos
import com.google.relay.compose.BoxScopeInstanceImpl.align

@Composable
fun textTittle(modifier: Modifier, text: String) {
    Text(
        text, modifier, style = TextStyle(
            fontSize = 40.sp,
            fontWeight = FontWeight(500),
            color = Color(0xFF3058B6),
        )
    )
}

@Composable
fun textDescription(modifier: Modifier, text: String) {
    Text(
        text, modifier, style = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight(500),
            color = Color(0xFF29536D),
        )
    )
}

@Composable
fun botonCuadrado(value: String) {
    Box(
        modifier = Modifier
            .width(100.dp)
            .height(50.dp)
            .align(Alignment.Center)
            .background(color = Color(0xFF57AD79))
    ) {
        Text(
            "$ $value",
            modifier = Modifier
                .fillMaxWidth()
                .height(35.dp)
                .align(Alignment.Center),
            style = TextStyle(
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.relay_niramit_medium)),
                fontWeight = FontWeight(400),
                color = Color(0xFF212020)
            )
        )
    }
}

@Composable
fun tarjetaView(uid: String, saldo: String) {
    Box(
        modifier = Modifier
            .shadow(
                elevation = 10.dp,
                spotColor = Color(0x40000000),
                ambientColor = Color(0x40000000)
            )
            .fillMaxWidth()
            .padding(30.dp, 10.dp)
            .height(136.dp)
            .background(color = Color(0xFF39C3CB), shape = RoundedCornerShape(size = 20.dp))
    ) {

        //UID de la tarjeta
        Text(
            text = uid,
            modifier = Modifier
                .graphicsLayer { rotationZ = -90f }
                .align(Alignment.CenterStart),
            style = TextStyle(
                fontSize = 10.sp,
                fontFamily = FontFamily(Font(R.font.relay_niramit_medium)),
                color = Color(0xFF262626)
            )
        )

        Row(modifier = Modifier
            .align(Alignment.CenterStart)
            .padding(start = 55.dp)) {

            //Imagen de la tarjeta
            Image(
                modifier = Modifier
                    .shadow(
                        elevation = 4.dp,
                        spotColor = Color(0x40000000),
                        ambientColor = Color(0x40000000)
                    )
                    .padding(end = 20.dp)
                    .width(132.dp)
                    .height(84.dp),
                painter = painterResource(id = R.drawable.tarjetas1_imagen_2025_03_10_195357111),
                contentDescription = "image description",
                contentScale = ContentScale.FillBounds
            )

            Column {
                textDescription(
                    Modifier
                        .width(59.dp)
                        .height(33.dp), "Saldo")
                textDescription(
                    Modifier
                        .width(59.dp)
                        .height(26.dp), "$ $saldo")
            }
        }
        //boton de desvincular
        Box(modifier = Modifier
            .align(Alignment.BottomEnd)
            .padding(10.dp)) {
            Text(
                text = "Desvincular",
                style = TextStyle(
                    fontSize = 15.sp,
                    fontFamily = FontFamily(Font(R.font.relay_niramit_medium)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFFFF4848),

                    )
            )
        }
    }
}

@Composable
fun rcvTarjeta(tarjetas: List<Tarjetas>) {
    LazyColumn {
        items(tarjetas) { tarjeta ->
            tarjetaView(tarjeta.uid, tarjeta.saldo)
        }
    }
}

@Composable
fun menuView() {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(106.dp)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(76.dp)
                .align(Alignment.BottomCenter)
                .background(color = Color(0xFF57A8AD))
        ) {
            Row(modifier = Modifier.align(Alignment.Center)) {

                //cuadro seleccionado
                Box(
                    modifier = Modifier
                        .size(70.dp)
                        .clip(CircleShape)
                        .background(color = Color(0xFF57AD96))
                ) {
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .clip(CircleShape)
                            .align(Alignment.Center)
                            .background(color = Color(0xFFE9762B))
                    ) {
                        Image(
                            modifier = Modifier
                                .width(90.dp)
                                .height(76.dp)
                                .background(color = Color(0x00FFFFFF)),
                            painter = painterResource(id = R.drawable.menu_vinculation_img),
                            contentDescription = "image description",
                            contentScale = ContentScale.None
                        )
                    }
                }

                //resto de opciones
                Image(
                    modifier = Modifier
                        .width(90.dp)
                        .height(76.dp)
                        .background(color = Color(0x00FFFFFF)),
                    painter = painterResource(id = R.drawable.menu_home_img),
                    contentDescription = "image description",
                    contentScale = ContentScale.None
                )

                Image(
                    modifier = Modifier
                        .width(90.dp)
                        .height(76.dp)
                        .background(color = Color(0x00FFFFFF)),
                    painter = painterResource(id = R.drawable.menu_citas_img),
                    contentDescription = "image description",
                    contentScale = ContentScale.None
                )

                Image(
                    modifier = Modifier
                        .width(90.dp)
                        .height(76.dp)
                        .background(color = Color(0x00FFFFFF)),
                    painter = painterResource(id = R.drawable.menu_close_img),
                    contentDescription = "image description",
                    contentScale = ContentScale.None
                )
            }
        }
    }
}


@Composable
fun ReadOnlyTextField(uid: String) {
    var text by remember { mutableStateOf(uid) }
    TextField(
        value = text,
        onValueChange = { },
        enabled = false,
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp,10.dp),
        shape = RoundedCornerShape(20.dp)

    )
}

@Preview
@Composable
fun PreviewComponent() {

}