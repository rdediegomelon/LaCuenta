package es.vallecilloweb.lacuenta.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.vallecilloweb.lacuenta.model.CuentaModel
import es.vallecilloweb.lacuenta.model.CuentaProvider

class CuentaViewModel:ViewModel() {

    private val _cuentas = MutableLiveData<MutableList<CuentaModel>>()
    var cuentas: LiveData<MutableList<CuentaModel>> = this._cuentas

    init {
        CuentaProvider.load()
        _cuentas.value=CuentaProvider.cuentas
    }

}