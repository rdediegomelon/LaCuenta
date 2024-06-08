package es.vallecilloweb.lacuenta.domain

import android.app.Application
import es.vallecilloweb.lacuenta.data.entities.CuentaModel
import es.vallecilloweb.lacuenta.data.CuentaRepository

class AddCuentaUseCase(application: Application) {

    private val _repository= CuentaRepository(application)

    fun addCuenta(name:String) {
        val newcuenta= CuentaModel(name)
        _repository.addCuenta(newcuenta)
    }

}