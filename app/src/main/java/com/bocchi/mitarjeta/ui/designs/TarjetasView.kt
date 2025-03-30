package com.bocchi.mitarjeta.ui.designs

import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bocchi.mitarjeta.Tarjetas
import com.bocchi.mitarjeta.btnqr.BtnQr
import com.bocchi.mitarjeta.menu.Menu
import com.bocchi.mitarjeta.menu.Property1
import com.bocchi.mitarjeta.tarjetas1.Tarjetas1
import com.bocchi.mitarjeta.ui.theme.MiTarjetaTheme
import com.bocchi.mitarjeta.ui.theme.backgroud
import com.google.relay.compose.BoxScopeInstanceImpl.align
import com.google.relay.compose.ColumnScopeInstanceImpl.align
import com.google.relay.compose.EmptyPainter


private var tarjetasList:List<Tarjetas> = listOf(
    Tarjetas("12345678", EmptyPainter(),"50 $"),
    Tarjetas("12345679", EmptyPainter(),"150 $"),
    Tarjetas("12345677", EmptyPainter(),"200 $"),
    Tarjetas("12345676", EmptyPainter(),"258 $"),
    Tarjetas("12345675", EmptyPainter(),"10 $"),
    Tarjetas("12345675", EmptyPainter(),"80 $"),
)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TarjetasView (navController: NavController, tarjetas: List<Tarjetas>){
    tarjetasList = tarjetas
    MiTarjetaTheme() {
        Box(modifier = Modifier.fillMaxSize().background(backgroud).padding(0.dp,40.dp,0.dp,0.dp)){
            Column ( modifier = Modifier.fillMaxSize().padding(bottom = 80.dp)){
                text(Modifier.width(145.dp).height(52.dp).align(Alignment.CenterHorizontally),"Tarjetas","titulo")
                text(Modifier.width(329.dp).height(26.dp).align(Alignment.CenterHorizontally),"Selecciona una tarjeta para recargar","texto")
                misTarjetas(tarjetasList)
            }
            Box(modifier = Modifier.fillMaxWidth().height(106.dp).align(Alignment.BottomCenter)) {
                menuView()
            }
        }
    }
}

@Composable
fun text(modifier: Modifier, text:String, type:String){
    var style = TextStyle()
    style = if (type == "titulo"){
        TextStyle(
            fontSize = 40.sp,
            fontWeight = FontWeight(500),
            color = Color(0xFF3058B6),
        )
    }else{
        TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight(500),
            color = Color(0xFF29536D),
        )
    }
    Text(text,modifier, style = style)
}

@Composable
fun tarjetaView(image: Painter, saldo:String, uid:String){
    Tarjetas1(
        Modifier
        .shadow(elevation = 10.dp, spotColor = Color(0x40000000), ambientColor = Color(0x40000000))
        .padding(30.dp,10.dp)
        .fillMaxWidth()
        .height(136.dp)
        .background(color = Color(0xFF39C3CB), shape = RoundedCornerShape(size = 20.dp)),
        image, saldo,uid)
}

@Composable
fun misTarjetas(tarjetas:List<Tarjetas>){
    LazyColumn {
        items(tarjetas) {tarjeta->
            tarjetaView(tarjeta.img,tarjeta.saldo,tarjeta.uid)
        }
    }
}

@Composable
fun menuView(){
    Menu(Modifier.fillMaxWidth().height(106.dp),Property1.Home)
}

@Preview(showSystemUi = true)
@Composable
fun PreviewTarjetas(){
    MiTarjetaTheme() {
        Box(modifier = Modifier.fillMaxSize().background(backgroud).padding(0.dp,40.dp,0.dp,0.dp)){
            Column ( modifier = Modifier.fillMaxSize().padding(bottom = 80.dp)){
                text(Modifier.width(145.dp).height(52.dp).align(Alignment.CenterHorizontally),"Tarjetas","titulo")
                text(Modifier.width(329.dp).height(26.dp).align(Alignment.CenterHorizontally),"Selecciona una tarjeta para recargar","texto")
                misTarjetas(tarjetasList)
            }
            //Impresion del Menu
            Box(modifier = Modifier.fillMaxWidth().height(106.dp).align(Alignment.BottomCenter)) {
                menuView()
            }

            //Impresion del QR
            Box(modifier = Modifier.align(Alignment.BottomEnd).padding(0.dp,0.dp,10.dp,65.dp,)) {
                BtnQr(modifier = Modifier.align(Alignment.BottomEnd).padding(bottom =20.dp))
            }
        }
    }
}
