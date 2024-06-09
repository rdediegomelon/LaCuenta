package es.vallecilloweb.lacuenta.data.dao

import androidx.room.Dao
import es.vallecilloweb.lacuenta.data.entities.ConsumicionModel

@Dao
interface ConsumicionDao {

    fun getAllConsumicionesForCuenta(cuentaID:Int):List<ConsumicionModel>

}