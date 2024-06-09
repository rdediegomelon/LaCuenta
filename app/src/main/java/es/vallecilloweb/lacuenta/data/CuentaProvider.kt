package es.vallecilloweb.lacuenta.data

import android.app.Application
import es.vallecilloweb.lacuenta.data.entities.ConsumicionModel
import es.vallecilloweb.lacuenta.data.entities.CuentaModel

class CuentaProvider (application: Application) {

    val db:CuentaDatabase= CuentaDatabase.getDatabase(application)
    private var cuentas: MutableList<CuentaModel> = db.cuentaDao().getAll()


    public fun getAllCuentas():MutableList<CuentaModel>{
        return cuentas;
    }

    public fun addCuenta(cuenta:CuentaModel){
        val id=db.cuentaDao().insert(cuenta)
        cuenta.uid=id.toInt()
        cuentas.add(cuenta)
    }

    public fun saveConsumicion(cosumicion:ConsumicionModel){
        db.consumicionDao().insertAll(cosumicion)
    }

    public fun getConsumicionesInCuenta(cuenta: CuentaModel):MutableList<ConsumicionModel> = db.consumicionDao().getConsumicionesInCuenta(cuenta.uid)

}