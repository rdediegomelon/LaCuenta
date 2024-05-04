package es.vallecilloweb.lacuenta.data

import java.time.LocalDateTime

data class CuentaModel(var name:String) {

    val dateCreated: LocalDateTime? = LocalDateTime.now()
    val people:MutableList<String> = mutableListOf()
    val consumiciones:MutableList<ConsumicionModel> = mutableListOf()

    fun addPerson(name:String){
        people.add(name)
    }

    fun addConsumicion(consumicion:ConsumicionModel){
        consumiciones.add(consumicion)
    }

    fun calculateTotal():Float{
        var total:Float=0f;
        for (consumicion in consumiciones){
            total+=consumicion.cost*consumicion.quantity
        }
        return total
    }
}