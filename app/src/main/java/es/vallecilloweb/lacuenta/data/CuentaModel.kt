package es.vallecilloweb.lacuenta.data

import java.time.LocalDateTime

data class CuentaModel(var name:String) {

    val dateCreated: LocalDateTime? = LocalDateTime.now()
    val consumiciones:MutableMap<String,MutableList<ConsumicionModel>> = mutableMapOf()

    fun addConsumicion(person:String,consumicion:ConsumicionModel){
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