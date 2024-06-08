package es.vallecilloweb.lacuenta.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ConsumicionModel(
    @ColumnInfo(name="name") val name:String,
    @ColumnInfo(name="cost") val cost:Float,
    @ColumnInfo(name="quantity") var quantity:Int){

    @PrimaryKey(autoGenerate = true) val uid:Int = 0
}
