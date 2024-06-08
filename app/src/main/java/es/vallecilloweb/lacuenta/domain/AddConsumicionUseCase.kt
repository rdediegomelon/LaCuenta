package es.vallecilloweb.lacuenta.domain

import es.vallecilloweb.lacuenta.data.entities.ConsumicionModel
import es.vallecilloweb.lacuenta.data.entities.CuentaModel

class AddConsumicionUseCase {

    fun addConsumicion(cuenta: CuentaModel, person:String, name:String, cost:Float, quantity:Int){
        var consumicion: ConsumicionModel = ConsumicionModel(name,cost,quantity)
        cuenta.addConsumicion(person,consumicion)
    }

}