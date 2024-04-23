package es.vallecilloweb.lacuenta.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.vallecilloweb.lacuenta.model.CuentaModel
import es.vallecilloweb.lacuenta.model.CuentaProvider

class CuentaViewModel:ViewModel() {

    private val _cuentas = mutableStateListOf<CuentaModel>()
    var cuentas: List<CuentaModel> = this._cuentas

    init {
        CuentaProvider.load()

        val it=CuentaProvider.cuentas.iterator()

        while (it.hasNext()) {
            _cuentas.add(it.next())
        }
    }

    public fun onClickAddCuentaButton (){
        _cuentas.add(CuentaModel("Cuenta ${_cuentas.size+1}"))
    }

}