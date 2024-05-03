package es.vallecilloweb.lacuenta.domain

import es.vallecilloweb.lacuenta.data.CuentaModel
import es.vallecilloweb.lacuenta.data.CuentaRepository

class GetAllCuentasUseCase {

    private val _repository=CuentaRepository()

    fun getAllCuentas():List<CuentaModel> = _repository.getAllCuentas()
}