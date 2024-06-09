package es.vallecilloweb.lacuenta.ui.viewmodel

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavHostController
import es.vallecilloweb.lacuenta.data.CuentaRepository
import es.vallecilloweb.lacuenta.data.entities.CuentaModel
import es.vallecilloweb.lacuenta.domain.AddConsumicionUseCase
import es.vallecilloweb.lacuenta.domain.AddCuentaUseCase
import es.vallecilloweb.lacuenta.domain.GetAllCuentasUseCase
import es.vallecilloweb.lacuenta.ui.navigation.AppScreens

class CuentaViewModel(application: Application): AndroidViewModel(application) {

    private var _cuentas:SnapshotStateList<CuentaModel> = mutableStateListOf<CuentaModel>()
    var cuentas: List<CuentaModel> = this._cuentas

    private var _cuentaDetail = MutableLiveData<CuentaModel>()
    var cuentaDetail:LiveData<CuentaModel> = _cuentaDetail

    private val _repository= CuentaRepository(application)

    private val getAllCuentasUseCase=GetAllCuentasUseCase(_repository)
    private val addCuentasUseCase=AddCuentaUseCase(_repository)
    private val addConsumicionUseCase=AddConsumicionUseCase(_repository)



    init {
        load()
    }

    private fun load(){
        val allcuentas:List<CuentaModel> = getAllCuentasUseCase.getAllCuentas()
        _cuentas.clear()
        _cuentas.addAll(allcuentas)
    }

    fun onClickAddCuentaButton (navController: NavHostController){
        navController.navigate(AppScreens.AddCuentaScreen.route)
    }

    fun onClickNewCuentaButton(navController: NavHostController,name:String){
        addCuentasUseCase.addCuenta(name)
        load()
        //Se vuelve a la pantalla anterior una vez se han realizado las operaciones, no se navega a esa pantalla para evitar que al dar hacia atrás aparezca de nuevo el formulario
        navController.popBackStack()
    }

    fun onClickAddConsumicionButton(navController: NavHostController){
        navController.navigate(AppScreens.AddConsumicionScreen.route)
    }

    fun onClickNewConsumicionButton(navController: NavHostController,person:String,product:String,cost:String,quantity:String){
        addConsumicionUseCase.addConsumicion(_cuentaDetail.value!!,person,product,cost.toFloat(),quantity.toInt())
        //Se vuelve a la pantalla anterior una vez se han realizado las operaciones, no se navega a esa pantalla para evitar que al dar hacia atrás aparezca de nuevo el formulario
        navController.popBackStack()
    }

    fun onClickCuentaDetail(navController: NavHostController, cuenta: CuentaModel){
        _cuentaDetail.value=cuenta
        navController.navigate(AppScreens.CuentaDetailScreen.route)
    }

}