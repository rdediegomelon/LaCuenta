package es.vallecilloweb.lacuenta.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import es.vallecilloweb.lacuenta.data.entities.ConsumicionModel
import java.time.LocalDateTime

@Entity
data class CuentaModel(@ColumnInfo(name="name") var name:String) {

    @PrimaryKey(autoGenerate = true) var uid:Int = 0

    @ColumnInfo(name="created") var dateCreated: LocalDateTime? = LocalDateTime.now()
    @Ignore var consumiciones:MutableMap<String,MutableList<ConsumicionModel>> = mutableMapOf()

    fun addConsumicion(person:String,consumicion: ConsumicionModel){
        //Si la persona ya existe añado la consumicion a su lista, si no creo la lista de nuevo
        if (consumiciones.containsKey(person)){
            consumiciones.get(person)?.add(consumicion)
        }
        else {
            var newlist:MutableList<ConsumicionModel> = mutableListOf(consumicion)
            consumiciones.put(person,newlist)
        }
    }

    fun calculateTotal():Float{
        var total:Float=0f
        //Saco la lista de consumiciones de cada persona y por cada lista hago el calculo del total
        for (lista in consumiciones.values) {
            for (consumicion in lista) {
                total += consumicion.cost * consumicion.quantity
            }
        }
        return total
    }

    fun calculateTotal(person:String):Float{
        var total:Float=0f
        if (consumiciones.containsKey(person)){
            for (consumicion in consumiciones.get(person)!!){
                total+=consumicion.cost*consumicion.quantity
            }
        }
        return total
    }
}