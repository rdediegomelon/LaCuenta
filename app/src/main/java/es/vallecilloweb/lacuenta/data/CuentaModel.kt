package es.vallecilloweb.lacuenta.data

import java.time.LocalDateTime

data class CuentaModel(var name:String) {

    val dateCreated: LocalDateTime? = LocalDateTime.now();
    val people:List<String> = listOf()

}