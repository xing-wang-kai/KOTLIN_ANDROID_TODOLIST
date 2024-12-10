package com.example.myorganizationlist.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class User(
    @PrimaryKey(autoGenerate = true) var id: Long = 0L,
    @ColumnInfo(name="email") var email: String,
    @ColumnInfo(name="name") var name: String,
    @ColumnInfo(name="password") var password: String,
) {
    override fun toString(): String {
        return """
            - ID: ${this.id}, -email ${this.email} - NAME: ${this.name}, - PASSWORD: ${this.password}   
        """.trimIndent()
    }
}