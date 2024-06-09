package es.vallecilloweb.lacuenta.domain

import es.vallecilloweb.lacuenta.data.CuentaRepository
import es.vallecilloweb.lacuenta.data.entities.ConsumicionModel
import es.vallecilloweb.lacuenta.data.entities.CuentaModel

class AddConsumicionUseCase(val repository: CuentaRepository) {

    fun addConsumicion(cuenta: CuentaModel, person:String, name:String, cost:Float, quantity:Int){
        var consumicion: ConsumicionModel = ConsumicionModel(name,cost,quantity)
        consumicion.cuentaId=cuenta.uid
        cuenta.addConsumicion(person,consumicion)
    }

}