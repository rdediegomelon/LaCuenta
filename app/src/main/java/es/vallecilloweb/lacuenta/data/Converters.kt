package es.vallecilloweb.lacuenta.data

import androidx.room.TypeConverter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Converters {

    @TypeConverter
    fun fromLocalDateTimeToString(date:LocalDateTime):String {
        return date.toString()
    }

    @TypeConverter
    fun fromStringToLocalDateTime(datestr:String):LocalDateTime = LocalDateTime.parse(datestr)


}