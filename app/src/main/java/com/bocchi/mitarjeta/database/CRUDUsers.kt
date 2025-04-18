package com.bocchi.mitarjeta.database

import com.google.firebase.firestore.FirebaseFirestore


//fun setUser(
//    userId: String,
//    name: String,
//    email: String,
//    phone: String,
//    address: String,
//    callback: (Boolean, String?) -> Unit
//) {
//    val user = User(userId, name, email, phone, address)
//    val db = FirebaseFirestore.getInstance()
//    db.collection("users").document(userId).set(user)
//        .addOnSuccessListener {
//            callback(true, null)
//        }
//        .addOnFailureListener { e ->
//            callback(false, e.message)
//        }
//}