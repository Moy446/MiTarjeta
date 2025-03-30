package com.bocchi.mitarjeta.ui.designs

import android.util.Property
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bocchi.mitarjeta.botonescuadrados.BotonesCuadrados
import com.bocchi.mitarjeta.botonescuadrados.Property1.*
import com.bocchi.mitarjeta.formfield.FormField
import com.bocchi.mitarjeta.formfield.Property1
import com.bocchi.mitarjeta.ui.theme.MiTarjetaTheme
import com.bocchi.mitarjeta.ui.theme.backgroud
import com.google.relay.compose.BoxScopeInstanceImpl.align
import com.google.relay.compose.ColumnScopeInstanceImpl.align


@Composable
fun RecargaView(navController: NavController) {

}

@Composable
fun ReadOnlyTextField(uid:String) {
    var text by remember { mutableStateOf(uid) }
    TextField(
        value = text,
        onValueChange = { },
        enabled = false,
        modifier = Modifier.fillMaxWidth().padding(20.dp).background(color = Color(0xFFD9D9D9), shape = RoundedCornerShape(size = 60.dp))
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewRecargas() {
    MiTarjetaTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroud)
                .padding(top=40.dp)
        ) {
            Column ( modifier = Modifier.fillMaxSize().padding(bottom = 80.dp)){
                text(
                    Modifier
                        .width(145.dp)
                        .height(52.dp)
                        .align(Alignment.CenterHorizontally),
                    "Recargas",
                    "titulo"
                )
                text(
                    Modifier
                        .width(329.dp)
                        .height(26.dp)
                        .align(Alignment.CenterHorizontally),
                    "Proporciona los siguientes datos",
                    "texto"
                )
                ReadOnlyTextField("12345678")
                Box (Modifier.border(width = 1.dp, color = Color(0xFF4FE49F))){
                    Column {
                        text(Modifier
                            .width(329.dp)
                            .height(26.dp)
                            .align(Alignment.CenterHorizontally),
                            "Selecciona el monto deseado",
                            "texto")
                        Row {
                            BotonesCuadrados(Modifier, NoSeleccionado,"$50")
                            BotonesCuadrados(Modifier,NoSeleccionado,"$100")
                        }
                        Row {
                            BotonesCuadrados(Modifier.align(Alignment.Bottom), NoSeleccionado,"$150")
                            BotonesCuadrados(Modifier.align(Alignment.Bottom), NoSeleccionado,"$200")
                        }
                        text(Modifier
                            .width(329.dp)
                            .height(26.dp)
                            .align(Alignment.CenterHorizontally),
                            "Agregar tarjeta +",
                            "texto")
                    }
                }

            }


            //Impresion del Menu
            Box(modifier = Modifier.fillMaxWidth().height(106.dp).align(Alignment.BottomCenter)) {
                menuView()
            }
        }
    }
}