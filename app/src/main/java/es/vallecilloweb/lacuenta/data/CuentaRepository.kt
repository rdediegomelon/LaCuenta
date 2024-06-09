package es.vallecilloweb.lacuenta.data

import android.app.Application
import es.vallecilloweb.lacuenta.data.entities.ConsumicionModel
import es.vallecilloweb.lacuenta.data.entities.CuentaModel

class CuentaRepository(application:Application) {

    val provider:CuentaProvider= CuentaProvider(application)

    fun getAllCuentas():List<CuentaModel>{
        return provider.getAllCuentas()
    }

    fun getAllCuentasWithConsumiciones():List<CuentaModel>{

        val cuentas:List<CuentaModel> = provider.getAllCuentas()
        //Por cada cuenta, saco sus consumiciones
        for (cuenta in cuentas){

            var consumiciones:MutableList<ConsumicionModel> = provider.getConsumicionesInCuenta(cuenta)

            for (consumicion in consumiciones){
                cuenta.addConsumicion(person = consumicion.person, consumicion = consumicion)
            }
        }

        return cuentas
    }

    fun addCuenta(cuenta: CuentaModel){
        provider.addCuenta(cuenta)
    }

    fun saveConsumicion(consumicion:ConsumicionModel){
        provider.saveConsumicion(consumicion)
    }
}