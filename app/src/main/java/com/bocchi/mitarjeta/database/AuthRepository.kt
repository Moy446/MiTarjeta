package com.bocchi.mitarjeta.database

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

object AuthRepository {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun signIn(email: String, password: String, callback: (Boolean, String?) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("Auth", "signInWithEmail:success")
                    callback(true, null)
                } else {
                    Log.w("Auth", "signInWithEmail:failure", task.exception)
                    callback(false, task.exception?.message ?: "Error desconocido")
                }
            }
    }

    fun createAccount(email: String, password: String, callback: (Boolean, String?) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("Auth", "createUserWithEmail:success")
                    callback(true, null)
                } else {
                    Log.w("Auth", "createUserWithEmail:failure", task.exception)
                    callback(false, task.exception?.message ?: "Error desconocido")
                }
            }
    }

    fun getCurrentUser(): FirebaseUser? {
        return auth.currentUser
    }

    fun signOut() {
        auth.signOut()
    }
}
