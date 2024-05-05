package es.vallecilloweb.lacuenta.data

class CuentaProvider {
    companion object {
        var cuentas:MutableList<CuentaModel> = mutableListOf()

        init {
            //TEMPORAL, para llenar la lista de cuentas
            for (i in 1..5) {
                //Creo una nueva cuenta con un número consecutivo
                var cuenta:CuentaModel=CuentaModel("Cuenta $i")

                //Añado consumiciones a la cuenta
                var consumicion:ConsumicionModel = ConsumicionModel("Coca-Cola", cost = 2.90f, quantity = 2)
                cuenta.addConsumicion("Rodrigo",consumicion)
                consumicion=ConsumicionModel("Agua", cost = 1.8f, quantity = 2)
                cuenta.addConsumicion("Rodrigo",consumicion)
                consumicion=ConsumicionModel("Cerveza", cost = 3.3f, quantity = 2)
                cuenta.addConsumicion("Antonia",consumicion)

                cuentas.add(cuenta)
            }
        }
    }
}