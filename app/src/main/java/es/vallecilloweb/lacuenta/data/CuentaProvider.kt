package es.vallecilloweb.lacuenta.data

import android.app.Application
import es.vallecilloweb.lacuenta.data.entities.CuentaModel

class CuentaProvider (application: Application) {

    val db:CuentaDatabase= CuentaDatabase.getDatabase(application)
    private var cuentas: MutableList<CuentaModel> = db.cuentaDao().getAll()


    public fun getAllCuentas():MutableList<CuentaModel>{
        return cuentas;
    }

    public fun addCuente(cuenta:CuentaModel){
        cuentas.add(cuenta)
        db.cuentaDao().insertAll(cuenta)
    }

}