package es.vallecilloweb.lacuenta.domain

import android.app.Application
import es.vallecilloweb.lacuenta.data.entities.CuentaModel
import es.vallecilloweb.lacuenta.data.CuentaRepository

class GetAllCuentasUseCase(application: Application) {

    private val _repository=CuentaRepository(application)

    fun getAllCuentas():List<CuentaModel> = _repository.getAllCuentas()
}