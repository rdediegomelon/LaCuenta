package es.vallecilloweb.lacuenta.model

class CuentaProvider {

    companion object {

        var cuentas:List<CuentaModel> = listOf()

        fun load() {
            for (i in 1..5) {
                cuentas=cuentas + (CuentaModel("Cuenta $i"))
            }
        }

    }
}