package com.bocchi.mitarjeta.ui.designs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bocchi.mitarjeta.Tarjetas
import com.bocchi.mitarjeta.ui.theme.backgroud
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun UnaTarjetaView(navController: NavController,uid: String){
    val tarjetaState = remember { mutableStateOf<Tarjetas?>(null) }
    LaunchedEffect(uid) {
        val db = FirebaseFirestore.getInstance()
        db.collection("Tarjetas").document(uid).get().addOnSuccessListener { result ->
            val saldo = result.data?.get("saldo")?.toString() ?: "0.00"
            tarjetaState.value = Tarjetas(uid, saldo)
        }
    }
    Box(modifier = Modifier.fillMaxSize().background(backgroud).padding(top = 40.dp, bottom = 40.dp)) {
        botonBack(
            Modifier
                .padding(30.dp, 15.dp)
                .clickable {
                    navController.popBackStack()
                }
        )
        Column(modifier = Modifier.fillMaxSize().padding(bottom = 80.dp)) {
            textTittle(
                Modifier.width(145.dp).height(52.dp).align(Alignment.CenterHorizontally),
                "Tarjeta"
            )
            val tarjeta = tarjetaState.value
            if (tarjeta != null) {
                tarjetaView(tarjeta.uid, tarjeta.saldo)
            } else {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun UnaTarjetaPreview(){
    UnaTarjetaView(rememberNavController(),"123456789")
}