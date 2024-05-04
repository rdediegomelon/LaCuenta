package es.vallecilloweb.lacuenta.data

class CuentaRepository() {

    fun getAllCuentas():List<CuentaModel>{
        return CuentaProvider.cuentas
    }

    fun addCuenta(cuenta:CuentaModel){
        CuentaProvider.cuentas.add(cuenta)
    }
}