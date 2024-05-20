package es.vallecilloweb.lacuenta.domain

import es.vallecilloweb.lacuenta.data.ConsumicionModel
import es.vallecilloweb.lacuenta.data.CuentaModel

class AddConsumicionUseCase {

    fun addConsumicion(cuenta:CuentaModel, person:String, name:String, cost:Float, quantity:Int){
        var consumicion: ConsumicionModel = ConsumicionModel(name,cost,quantity)
        cuenta.addConsumicion(person,consumicion)
    }

}