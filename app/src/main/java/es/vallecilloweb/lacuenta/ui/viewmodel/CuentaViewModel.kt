package es.vallecilloweb.lacuenta.ui.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import es.vallecilloweb.lacuenta.data.ConsumicionModel
import es.vallecilloweb.lacuenta.data.CuentaModel
import es.vallecilloweb.lacuenta.data.CuentaRepository
import es.vallecilloweb.lacuenta.domain.AddCuentaUseCase
import es.vallecilloweb.lacuenta.domain.GetAllCuentasUseCase

class CuentaViewModel:ViewModel() {

    private var _cuentas:SnapshotStateList<CuentaModel> = mutableStateListOf<CuentaModel>()
    var cuentas: List<CuentaModel> = this._cuentas

    private val getAllCuentasUseCase=GetAllCuentasUseCase()
    private val addCuentasUseCase=AddCuentaUseCase()

    lateinit var cuentaDetail:CuentaModel

    init {
        load()
    }

    private fun load(){
        val allcuentas:List<CuentaModel> = getAllCuentasUseCase.getAllCuentas()
        _cuentas.clear()
        _cuentas.addAll(allcuentas)
    }

    public fun onClickAddCuentaButton (){
        addCuentasUseCase.addCuenta("Cuenta ${_cuentas.size+1}")
        load()
    }

    public fun onClickAddConsumicionButton(){
        var consumicion: ConsumicionModel = ConsumicionModel("Vermú", cost = 3f, quantity = 1)
        cuentaDetail.addConsumicion("Boton",consumicion)
    }
}