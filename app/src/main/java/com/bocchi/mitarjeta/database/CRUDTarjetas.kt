package com.bocchi.mitarjeta.database

import android.util.Log
import com.bocchi.mitarjeta.Tarjetas
import com.google.firebase.firestore.FirebaseFirestore

fun getTarjetas(curp:String,onResult:(List<Tarjetas>) -> Unit){
    val db = FirebaseFirestore.getInstance()
    var tarjetasList = mutableListOf<Tarjetas>()
    db.collection("Usuarios").document(curp).collection("tarjetas").get().addOnSuccessListener{result ->
        for (tarjeta in result){
            val uid = tarjeta.id
            val saldo = tarjeta.data["saldo"].toString()?:"0.00"
            tarjetasList.add(Tarjetas(uid,saldo))
        }
        onResult(tarjetasList)
    }.addOnFailureListener { e->
        Log.e("Firebase", "Error obteniendo tarjetas", e)
        onResult(emptyList())
    }


}

fun deleteTarjeta(curp: String, uid:String,onResult:(List<Tarjetas>)->Unit){
    val db = FirebaseFirestore.getInstance()
    var tarjetasList = mutableListOf<Tarjetas>()
    db.collection("Usuarios").document(curp).collection("tarjetas").document(uid).delete()
    db.collection("Usuarios").document(curp).collection("tarjetas").get().addOnSuccessListener{result ->
        for (tarjeta in result){
            val uid = tarjeta.id
            val saldo = tarjeta.data["saldo"].toString()?:"0.00"
            tarjetasList.add(Tarjetas(uid,saldo))
        }
        onResult(tarjetasList)
    }
}

fun updateTarjeta(curp: String,uid: String,saldo: String,onResult: (List<Tarjetas>) -> Unit){
    val db = FirebaseFirestore.getInstance()
    var tarjetasList = mutableListOf<Tarjetas>()
    db.collection("Usuarios").document(curp).collection("tarjetas").document(uid).update("saldo",saldo)
    db.collection("Usuarios").document(curp).collection("tarjetas").get().addOnSuccessListener{result ->
        for (tarjeta in result){
            val uidActual = tarjeta.id
            val saldoActual = tarjeta.data["saldo"].toString()?:"0.00"
            tarjetasList.add(Tarjetas(uidActual,saldoActual))
        }
        onResult(tarjetasList)
    }
}

fun setTarjeta(curp: String,uid: String,saldo:String){
    val db = FirebaseFirestore.getInstance()
    val tarjeta = hashMapOf("saldo" to saldo)
    db.collection("Usuarios").document(curp).collection("tarjetas").document(uid).set(tarjeta)
}
