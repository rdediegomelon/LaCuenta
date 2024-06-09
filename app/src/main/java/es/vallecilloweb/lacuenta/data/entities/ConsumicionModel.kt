package es.vallecilloweb.lacuenta.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity
data class ConsumicionModel(
    @ColumnInfo(name="name") var name:String,
    @ColumnInfo(name="cost") var cost:Float,
    @ColumnInfo(name="quantity") var quantity:Int){

    @PrimaryKey(autoGenerate = true) var uid:Int = 0

    @ColumnInfo(name="cuentaID") var cuentaId:Int=0
    @ColumnInfo(name="person") var person:String=""
}
