package es.vallecilloweb.lacuenta.domain

import es.vallecilloweb.lacuenta.data.CuentaModel
import es.vallecilloweb.lacuenta.data.CuentaRepository

class AddCuentaUseCase {

    private val _repository= CuentaRepository()

    fun addCuenta(name:String) {
        val newcuenta=CuentaModel(name)
        _repository.addCuenta(newcuenta)
    }

}