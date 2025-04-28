package com.bocchi.mitarjeta.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.bocchi.mitarjeta.Cita

class SQLiteHelperCitas(context: Context): SQLiteOpenHelper(context,"miTarjeta.db",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val query = "CREATE TABLE citas(_id INTERGER PRIMARY KEY AUTOINCREMENT, " +
                "fecha DATE," +
                "hora TIME," +
                "lugar VARCHAR(255)," +
                "curp VARCHAR(16))"
        db!!.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun insertCita(cita: Cita){
        val db = this.writableDatabase
        var valores = ContentValues()
        valores.put("fecha",cita.fecha.toString())
        valores.put("hora",cita.horario)
        valores.put("lugar",cita.lugar)
        valores.put("curp",cita.curp)
        db.insert("citas",null,valores)
        db.close()
    }


    @SuppressLint("Range")
    fun getCitas():MutableList<String>{
        var citas:MutableList<String> = ArrayList()
        val db = this.readableDatabase
        val query = "SELECT * FROM citas"
        val resultados  = db.rawQuery(query,null)
        if (resultados.moveToFirst()){
            do{
                var fecha = resultados.getString(resultados.getColumnIndex("fecha"))

                citas.add(fecha)
            }while (resultados.moveToNext())
        }
        resultados.close()
        db.close()
        return citas
    }

    fun deleteCita(fecha:String){
        val db = this.writableDatabase
        db.delete("citas","fecha=?", arrayOf(fecha))
    }

}