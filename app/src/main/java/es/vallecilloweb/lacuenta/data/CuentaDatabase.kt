package es.vallecilloweb.lacuenta.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import es.vallecilloweb.lacuenta.data.dao.ConsumicionDao
import es.vallecilloweb.lacuenta.data.dao.CuentaDao
import es.vallecilloweb.lacuenta.data.entities.ConsumicionModel
import es.vallecilloweb.lacuenta.data.entities.CuentaModel

@Database(entities = [CuentaModel::class,ConsumicionModel::class],version=2)
@TypeConverters(Converters::class)
abstract class CuentaDatabase:RoomDatabase() {
    abstract fun cuentaDao():CuentaDao
    abstract fun consumicionDao():ConsumicionDao

    companion object {
        var db:CuentaDatabase?=null

        fun getDatabase(context: Context):CuentaDatabase{

            if (db==null){
                db = Room.databaseBuilder(context,CuentaDatabase::class.java,"lacuenta").allowMainThreadQueries().build()
            }
            return db!!
        }
    }

}