package com.example.myorganizationlist.database

import androidx.room.TypeConverter
import java.math.BigDecimal

class ConvertorTypes {

    @TypeConverter
    fun fromDouble(valor: Double?): BigDecimal{
        return valor?.let{BigDecimal(valor.toString())} ?: BigDecimal.ZERO
    }

    @TypeConverter
    fun BigDecimalForDouble(valor: BigDecimal?): Double {
        return valor?.let{ valor.toDouble()} ?: Double.NaN
    }
}