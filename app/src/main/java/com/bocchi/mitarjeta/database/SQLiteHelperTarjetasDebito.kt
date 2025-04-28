package com.bocchi.mitarjeta.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.bocchi.mitarjeta.Cita
import com.bocchi.mitarjeta.TarjetasDebito

class SQLiteHelperTarjetasDebito(context: Context): SQLiteOpenHelper(context,"miTarjeta.db",null,1)  {
    override fun onCreate(db: SQLiteDatabase?) {
        val query = "CREATE TABLE tarjetasDebito(_id INTERGER PRIMARY KEY AUTOINCREMENT, " +
                "numero VARCHAR(255)," +
                "titular VARCHAR(255)," +
                "expiracion VARCHAR(8)," +
                "cvv VARCHAR(5))"
        db!!.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun insertTarjetaDebito(tarjetasDebito: TarjetasDebito){
        val db = this.writableDatabase
        var valores = ContentValues()
        valores.put("numeroTarjeta",tarjetasDebito.numeroTarjeta)
        valores.put("titularTarjeta",tarjetasDebito.titularTarjeta)
        valores.put("expiracionTarjeta",tarjetasDebito.numeroExpiracion)
        valores.put("cvvTarjeta",tarjetasDebito.cvvTarjeta)
        db.insert("tarjetasDebito",null,valores)
        db.close()
    }

    @SuppressLint("Range")
    fun getTarjetas():MutableList<TarjetasDebito>{
        var tarjetas:MutableList<TarjetasDebito> = ArrayList()
        val db = this.readableDatabase
        val query = "SELECT * FROM tarjetasDebito"
        val resultados  = db.rawQuery(query,null)
        if (resultados.moveToFirst()){
            do{
                var numero = resultados.getString(resultados.getColumnIndex("numeroTarjeta"))
                var titular = resultados.getString(resultados.getColumnIndex("titularTarjeta"))
                var expiracion = resultados.getString(resultados.getColumnIndex("expiracionTarjeta"))
                var cvv = resultados.getString(resultados.getColumnIndex("cvvTarjeta"))
                tarjetas.add(TarjetasDebito(numero,expiracion,titular,cvv))
            }while (resultados.moveToNext())
        }
        resultados.close()
        db.close()
        return tarjetas
    }
}