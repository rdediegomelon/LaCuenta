package es.vallecilloweb.lacuenta.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import es.vallecilloweb.lacuenta.data.entities.ConsumicionModel

@Dao
interface ConsumicionDao {

    @Query("SELECT * FROM consumicionmodel where cuentaID=:cuentaID")
    fun getConsumicionesInCuenta(cuentaID:Int): MutableList<ConsumicionModel>

    @Insert
    fun insertAll(vararg consumicion:ConsumicionModel)

}