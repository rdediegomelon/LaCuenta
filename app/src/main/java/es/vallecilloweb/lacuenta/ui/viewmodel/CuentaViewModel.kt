package es.vallecilloweb.lacuenta.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import es.vallecilloweb.lacuenta.data.ConsumicionModel
import es.vallecilloweb.lacuenta.data.CuentaModel
import es.vallecilloweb.lacuenta.data.CuentaRepository
import es.vallecilloweb.lacuenta.domain.AddConsumicionUseCase
import es.vallecilloweb.lacuenta.domain.AddCuentaUseCase
import es.vallecilloweb.lacuenta.domain.GetAllCuentasUseCase
import es.vallecilloweb.lacuenta.ui.navigation.AppScreens

class CuentaViewModel:ViewModel() {

    private var _cuentas:SnapshotStateList<CuentaModel> = mutableStateListOf<CuentaModel>()
    var cuentas: List<CuentaModel> = this._cuentas

    private var _cuentaDetail = MutableLiveData<CuentaModel>()
    var cuentaDetail:LiveData<CuentaModel> = _cuentaDetail

    private val getAllCuentasUseCase=GetAllCuentasUseCase()
    private val addCuentasUseCase=AddCuentaUseCase()
    private val addConsumicionUseCase=AddConsumicionUseCase()

    init {
        load()
    }

    private fun load(){
        val allcuentas:List<CuentaModel> = getAllCuentasUseCase.getAllCuentas()
        _cuentas.clear()
        _cuentas.addAll(allcuentas)
    }

    public fun onClickAddCuentaButton (navController: NavHostController){
        navController.navigate(AppScreens.AddCuentaScreen.route)
    }

    public fun onClickNewCuentaButton(navController: NavHostController,name:String){
        addCuentasUseCase.addCuenta(name)
        load()
        navController.navigate(AppScreens.ListCuentasScreen.route)
    }

    public fun onClickAddConsumicionButton(){
        addConsumicionUseCase.addConsumicion(_cuentaDetail.value!!,"Botón","Vermú", cost = 3f, quantity = 1)
    }

    public fun onClickCuentaDetail(navController: NavHostController, cuenta:CuentaModel){
        _cuentaDetail.value=cuenta
        navController.navigate(AppScreens.CuentaDetailScreen.route)
    }

}