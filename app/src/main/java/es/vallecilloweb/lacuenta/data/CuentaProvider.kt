package es.vallecilloweb.lacuenta.data

import android.app.Application
import android.content.Context
import androidx.room.Room
import es.vallecilloweb.lacuenta.data.entities.CuentaModel

class CuentaProvider (application: Application) {

    val db:CuentaDatabase= CuentaDatabase.getDatabase(application)
    var cuentas: MutableList<CuentaModel> = db.cuentaDao().getAll()

}