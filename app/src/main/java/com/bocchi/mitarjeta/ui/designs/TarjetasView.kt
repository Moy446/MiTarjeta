package com.bocchi.mitarjeta.ui.designs

import android.annotation.SuppressLint
import android.util.Log
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.bocchi.mitarjeta.R
import com.bocchi.mitarjeta.Tarjetas
import com.bocchi.mitarjeta.btnqr.BtnQr
import com.bocchi.mitarjeta.database.getTarjetas
import com.bocchi.mitarjeta.menu.Menu
import com.bocchi.mitarjeta.menu.Property1
import com.bocchi.mitarjeta.navigation.NavItemList
import com.bocchi.mitarjeta.navigation.handleNavigationWithLogout
import com.bocchi.mitarjeta.tarjetas1.Tarjetas1
import com.bocchi.mitarjeta.ui.theme.MiTarjetaTheme
import com.bocchi.mitarjeta.ui.theme.backgroud
import com.bocchi.mitarjeta.views.getUser
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.relay.compose.BoxScopeInstanceImpl.align
import com.google.relay.compose.ColumnScopeInstanceImpl.align
import com.google.relay.compose.EmptyPainter
import kotlinx.coroutines.tasks.await


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TarjetasView (navController: NavController){
    var curp = remember { mutableStateOf(getUser()) }
    var selectedRoute = remember { mutableStateOf("home") }
    //obtener las tarjetas almacenadas
    val tarjetasList = remember { mutableStateListOf<Tarjetas>() }

    //variable para el dialog
    var openDialogClose = remember { mutableStateOf (false) }

    LaunchedEffect(key1 = curp) {
        if (curp != null) {
            getTarjetas(curp.value) { tarjetas ->
                tarjetasList.clear()
                tarjetasList.addAll(tarjetas)
            }
        }
    }

    Scaffold (bottomBar = {
            //Impresion del Menu
            menuView(
                navItemList = NavItemList.navItemList,
                selectedView = selectedRoute.value,
                onItemSelected = { titulo ->
                    handleNavigationWithLogout(titulo,navController,selectedRoute,openDialogClose)
                },
            )
    },floatingActionButton = {
        botonQR()
    }) {
        Box(modifier = Modifier.fillMaxSize().background(backgroud).padding(top = 40.dp, bottom = 40.dp)){
            botonBack(Modifier
                .padding(30.dp,15.dp)
                .clickable {
                    navController.popBackStack()
                })
            Column ( modifier = Modifier.fillMaxSize().padding(bottom = 80.dp)){
                textTittle(Modifier.width(145.dp).height(52.dp).align(Alignment.CenterHorizontally),"Tarjetas")
                textDescription(Modifier.width(329.dp).height(26.dp).align(Alignment.CenterHorizontally),"Selecciona una tarjeta para recargar")
                rcvTarjeta(tarjetasList,curp.value,navController,{ onUpdate->
                    tarjetasList.clear()
                    tarjetasList.addAll(onUpdate)
                })
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

@Preview(showSystemUi = true)
@Composable
fun PreviewTarjetas(){
}
