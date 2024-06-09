package es.vallecilloweb.lacuenta.domain

import android.app.Application
import es.vallecilloweb.lacuenta.data.entities.CuentaModel
import es.vallecilloweb.lacuenta.data.CuentaRepository

class AddCuentaUseCase(val repository: CuentaRepository) {

    fun addCuenta(name:String) {
        val newcuenta= CuentaModel(name)
        repository.addCuenta(newcuenta)
    }

}