package es.vallecilloweb.lacuenta.domain

import android.app.Application
import es.vallecilloweb.lacuenta.data.entities.CuentaModel
import es.vallecilloweb.lacuenta.data.CuentaRepository

class GetAllCuentasUseCase(val repository: CuentaRepository) {

    fun getAllCuentas():List<CuentaModel> = repository.getAllCuentasWithConsumiciones()

}