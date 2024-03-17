package es.vallecilloweb.lacuenta.model

class CuentaProvider {

    companion object {

        var cuentas:MutableList<CuentaModel> = mutableListOf()

        fun load() {
            for (i in 1..16) {
                cuentas.add(CuentaModel("Cuenta $i"))
            }
        }

    }
}