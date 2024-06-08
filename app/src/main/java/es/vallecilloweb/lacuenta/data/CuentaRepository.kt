package es.vallecilloweb.lacuenta.data

import android.app.Application
import es.vallecilloweb.lacuenta.data.entities.CuentaModel

class CuentaRepository(application:Application) {

    val provider:CuentaProvider= CuentaProvider(application)

    fun getAllCuentas():List<CuentaModel>{
        return provider.cuentas
    }

    fun addCuenta(cuenta: CuentaModel){
        provider.cuentas.add(cuenta)
    }
}