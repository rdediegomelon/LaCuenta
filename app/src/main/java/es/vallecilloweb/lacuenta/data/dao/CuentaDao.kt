package es.vallecilloweb.lacuenta.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import es.vallecilloweb.lacuenta.data.entities.ConsumicionModel
import es.vallecilloweb.lacuenta.data.entities.CuentaModel

@Dao
interface CuentaDao {

    @Query("SELECT * FROM cuentamodel")
    fun getAll(): MutableList<CuentaModel>


    @Insert
    fun insertAll(vararg cuenta:CuentaModel)

    @Insert
    fun insert(cuenta:CuentaModel):Long
}