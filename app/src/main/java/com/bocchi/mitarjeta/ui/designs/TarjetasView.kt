package com.bocchi.mitarjeta.ui.designs

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
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
import androidx.navigation.compose.rememberNavController
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
    Tarjetas("12345678","50 $"),
    Tarjetas("12345679","150 $"),
    Tarjetas("12345677","200 $"),
    Tarjetas("12345676","258 $"),
    Tarjetas("12345675","10 $"),
    Tarjetas("12345675", "80 $"),
)



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TarjetasView (navController: NavController){
    Scaffold (bottomBar = {
        //Impresion del Menu
        menuView()
    },floatingActionButton = {
        botonQR()
    }) {
        Box(modifier = Modifier.fillMaxSize().background(backgroud).padding(0.dp,40.dp,0.dp,0.dp)){
            botonBack(Modifier
                .padding(30.dp,15.dp)
                .clickable {
                    navController.popBackStack()
                })
            Column ( modifier = Modifier.fillMaxSize().padding(bottom = 80.dp)){
                textTittle(Modifier.width(145.dp).height(52.dp).align(Alignment.CenterHorizontally),"Tarjetas")
                textDescription(Modifier.width(329.dp).height(26.dp).align(Alignment.CenterHorizontally),"Selecciona una tarjeta para recargar")
                rcvTarjeta(tarjetasList,navController)
            }
        }
    }
}



@Preview(showSystemUi = true)
@Composable
fun PreviewTarjetas(){
}
