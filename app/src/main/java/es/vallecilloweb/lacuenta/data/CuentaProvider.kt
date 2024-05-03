package es.vallecilloweb.lacuenta.data

class CuentaProvider {
    companion object {
        var cuentas:List<CuentaModel> = listOf()

        init {
            for (i in 1..5) {
                cuentas = cuentas + (CuentaModel("Cuenta $i"))
            }
        }
    }
}