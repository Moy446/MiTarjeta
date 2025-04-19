package com.bocchi.mitarjeta.database

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

object CRUDUsers {
    fun signInWithCurp(curp: String, password: String, callback: (Boolean, String?) -> Unit) {
        val db = FirebaseFirestore.getInstance()
        val auth = FirebaseAuth.getInstance()

        // Buscar el email relacionado con el CURP
        db.collection("Usuarios")
            .whereEqualTo("curp", curp)
            .get()
            .addOnSuccessListener { documents ->
                if (!documents.isEmpty) {
                    val userDoc = documents.first()
                    val email = userDoc.getString("email")

                    if (email != null) {
                        // Hacer login con el email y la contraseña
                        auth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    Log.d("Auth", "signInWithCurp:success")
                                    callback(true, null)
                                } else {
                                    Log.w("Auth", "signInWithCurp:failure", task.exception)
                                    callback(false, "Contraseña incorrecta o cuenta inválida")
                                }
                            }
                    } else {
                        callback(false, "No se encontró email para este CURP")
                    }
                } else {
                    callback(false, "CURP no registrado")
                }
            }
            .addOnFailureListener { e ->
                Log.e("Firestore", "Error buscando CURP", e)
                callback(false, "Error al buscar CURP: ${e.message}")
            }
    }


    fun registerUserWithCurp(
        curp: String,
        email: String,
        password: String,
        callback: (Boolean, String?) -> Unit
    ) {
        val auth = FirebaseAuth.getInstance()
        val db = FirebaseFirestore.getInstance()

        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener { result ->
                val uid = result.user?.uid
                if (uid != null) {
                    val userData = mapOf(
                        "curp" to curp,
                        "email" to email
                    )

                    db.collection("Usuarios").document(uid)
                        .set(userData)
                        .addOnSuccessListener { task ->
                            Log.d("Auth", "Usuario registrado con CURP: $curp")
                            callback(true, null)
                        }
                        .addOnFailureListener { e ->
                            Log.d("Auth", "Error al guardar datos del usuario: ${e.message}")
                            callback(false, "Error al guardar datos: ${e.message}")
                        }
                } else {
                    callback(false, "No se pudo obtener el UID del usuario")
                }
            }
            .addOnFailureListener { e ->
                callback(false, "Error al crear usuario: ${e.message}")
            }
    }


}
