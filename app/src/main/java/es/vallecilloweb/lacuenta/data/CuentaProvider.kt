package es.vallecilloweb.lacuenta.data

class CuentaProvider {
    companion object {
        var cuentas:MutableList<CuentaModel> = mutableListOf()

        init {
            //TEMPORAL, para llenar la lista de cuentas
            for (i in 1..5) {
                //Creo una nueva cuenta con un número consecutivo
                var cuenta:CuentaModel=CuentaModel("Cuenta $i")

                //Añado dos personas a la cuenta
                cuenta.addPerson("Rodrigo")
                cuenta.addPerson("Antonia")

                //Añado consumiciones a la cuenta
                var consumicion:ConsumicionModel = ConsumicionModel("Coca-Cola", person = "Rodrigo", cost = 2.90f, quantity = 2)
                cuenta.addConsumicion(consumicion)
                consumicion=ConsumicionModel("Cerveza", person = "Antonia", cost = 3.3f, quantity = 1)
                cuenta.addConsumicion(consumicion)

                cuentas.add(cuenta)
            }
        }
    }
}