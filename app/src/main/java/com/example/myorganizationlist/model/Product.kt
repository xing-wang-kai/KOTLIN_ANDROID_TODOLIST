package com.example.myorganizationlist.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal

@Entity
class Product(
    @PrimaryKey(autoGenerate = true) var id: Long = 0L,
    @ColumnInfo(name="title") var title: String,
    @ColumnInfo(name="description") var description: String,
    @ColumnInfo(name="price") var price: BigDecimal,
    @ColumnInfo(name="img_url") var imgUrl: String?,
    @ColumnInfo(name="user_id") var userId: Long? = 0L
) {

    override fun toString(): String {
        return """
            - TITLE: ${this.title}, 
            -ID ${this.id} 
            - DESCRIPTION: ${this.description}, 
            - PRICE: ${this.price}, 
            -imgUrl: ${this.imgUrl} , 
            -USER ${this.userId}  
        """.trimIndent()
    }

}
