package es.vallecilloweb.lacuenta.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import es.vallecilloweb.lacuenta.data.dao.CuentaDao
import es.vallecilloweb.lacuenta.data.entities.CuentaModel

@Database(entities = [CuentaModel::class],version=1)
abstract class CuentaDatabase:RoomDatabase() {
    abstract fun cuentaDao():CuentaDao

    companion object {
        var db:CuentaDatabase?=null

        fun getDatabase(context: Context):CuentaDatabase{

            if (db==null){
                db = Room.databaseBuilder(context,CuentaDatabase::class.java,"lacuenta").build()
            }
            return db!!
        }
    }

}