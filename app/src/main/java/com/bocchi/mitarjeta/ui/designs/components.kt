package com.bocchi.mitarjeta.ui.designs

import android.graphics.drawable.Icon
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.appcompat.app.AlertDialog
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bocchi.mitarjeta.CaptureActivityPortrait
import com.bocchi.mitarjeta.R
import com.bocchi.mitarjeta.Tarjetas
import com.bocchi.mitarjeta.database.deleteTarjeta
import com.bocchi.mitarjeta.navigation.NavItem
import com.bocchi.mitarjeta.ui.theme.Titulos
import com.google.firebase.firestore.FirebaseFirestore
import com.google.relay.compose.BoxScopeInstanceImpl.align
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions

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
fun botonCuadrado(value: String,isSelected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .width(100.dp)
            .height(50.dp)
            .align(Alignment.Center)
            .background(color = if (isSelected) Color(0xFFE9762B) else Color(0xFF57AD79))
            .clickable { onClick() }
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
fun tarjetaView(uid: String, saldo: String,curp:String,navController: NavController,onUpdate:(List<Tarjetas>)->Unit) {
    val openAlertDialog = remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp, 10.dp)
            .height(136.dp)
            .background(color = Color(0xFF39C3CB), shape = RoundedCornerShape(size = 20.dp))
            .clickable {
                navController.navigate("recargas/${uid}")
            }
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
                        .fillMaxWidth()
                        .height(26.dp), "$ $saldo")
            }
        }
        //boton de desvincular
        Box(modifier = Modifier
            .align(Alignment.BottomEnd)
            .padding(10.dp)
            .clickable {
                openAlertDialog.value = true
            }) {
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
    if(openAlertDialog.value){
        AlertDialog(
            icon = R.drawable.ic_desvincular,
            dialogTitle = "Desvincular",
            dialogText = "¿Seguro que quieres desvincular tu tarjeta de tu cuenta?",
            dialogConfirm = "Desvincular",
            showDialog = openAlertDialog.value,
            onAccept = {
                deleteTarjeta(curp,uid,{update->
                    onUpdate(update)
                })
                openAlertDialog.value = false
            },
            onDismiss = { openAlertDialog.value = false }
        )
    }
}

@Composable
fun tarjetaView(uid:String, saldo: String){
    Box(
        modifier = Modifier
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

        Row(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 55.dp)
        ) {

            //Imagen de la tarjeta
            Image(
                modifier = Modifier
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
                        .height(33.dp), "Saldo"
                )
                textDescription(
                    Modifier
                        .fillMaxWidth()
                        .height(26.dp), "$ $saldo"
                )
            }
        }
    }
}

@Composable
fun rcvTarjeta(tarjetas: List<Tarjetas>,curp:String,navController: NavController,onUpdate:(List<Tarjetas>)->Unit) {
    LazyColumn {
        items(tarjetas) { tarjeta ->
            tarjetaView(tarjeta.uid, tarjeta.saldo, curp,navController,onUpdate)
        }
    }
}

@Composable

fun menuView(navItemList: List<NavItem>, selectedView:String, onItemSelected:(String) -> Unit){
    NavigationBar(containerColor = Color(0xFF57A8AD)) {
        navItemList.forEachIndexed{index, navItem->
            NavigationBarItem(
                selected = selectedView == navItem.titulo,
                onClick = { onItemSelected(navItem.titulo) },
                icon = {
                    Icon(
                        ImageVector.vectorResource(navItem.icono),
                        contentDescription = "Icono",
                        tint =Color(0xFF29536D)
                    )
                },
                colors =NavigationBarItemDefaults.colors(
                    indicatorColor = Color(0xFFE9762B)
                )
            )

        }
    }
}

@Composable
fun ReadOnlyTextField(uid: String?) {
    var text by remember { mutableStateOf(uid) }
    text?.let {
        TextField(
            value = it,
            onValueChange = { },
            enabled = false,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp, 10.dp),
            shape = RoundedCornerShape(20.dp)

        )
    }
}



@Composable
fun AlertDialog(icon: Int, dialogTitle: String, dialogText: String, dialogConfirm:String, showDialog: Boolean, onAccept: ()->Unit, onDismiss: () -> Unit){

    if(showDialog){
        AlertDialog(
            icon = {
                Icon(imageVector = ImageVector.vectorResource(icon), contentDescription = "Icono alert Dialog")
            },
            title = {
                Text(text = dialogTitle)
            },
            text = {
                Text(text = dialogText)
            },
            onDismissRequest = {
                onDismiss()
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onAccept()
                    }
                ) {
                    Text(dialogConfirm,
                        style = TextStyle(
                            color =  Color(0xFFFF4848)))
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        onDismiss()
                    }
                ) {
                    Text("Cancelar")
                }
            }

        )
    }
}

@Composable
fun botonQR(navController: NavController){
    //variables para inicio del escaner
    val scanLauncher = rememberLauncherForActivityResult(
        contract = ScanContract(),
        onResult = { result ->
            val aux = result.contents ?: "" // respuesta del escaner
            if (aux.isNotEmpty()){
                navController.navigate("tarjeta/${aux}")
            }
        })

    Box(
        modifier = Modifier
            .size(60.dp)
            .clip(CircleShape)
            .align(Alignment.Center)
            .background(color = Color(0xFFE9762B))
            .clickable {
                val scanOption = ScanOptions()
                scanOption.setBeepEnabled(true)
                scanOption.setCaptureActivity(CaptureActivityPortrait::class.java)
                scanOption.setOrientationLocked(false)
                scanLauncher.launch(scanOption)
            },
    ) {
        Image(
            modifier = Modifier
                .width(90.dp)
                .height(76.dp)
                .background(color = Color(0x00FFFFFF)),
            painter = painterResource(id = R.drawable.btn_qr_vector),
            contentDescription = "boton QR",
            contentScale = ContentScale.None
        )
    }
}

@Composable
fun botonBack(modifier: Modifier){
    Icon(imageVector = Icons.Default.ArrowBack,
        contentDescription = "Arrow back",
        tint = Color.White,
        modifier = modifier)
}
@Preview
@Composable
fun PreviewComponent() {

}