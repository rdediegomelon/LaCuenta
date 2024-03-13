package es.vallecilloweb.lacuenta.model

import java.time.LocalDateTime

class CuentaModel(var name:String) {

    val dateCreated: LocalDateTime? = LocalDateTime.now();

}