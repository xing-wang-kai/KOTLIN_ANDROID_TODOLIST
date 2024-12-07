package com.example.myorganizationlist.model

import android.widget.ImageView
import java.math.BigDecimal

class Product(
    val title: String,
    val description: String,
    val price: BigDecimal,
    val imgUrl: String? = null
) {



    override fun toString(): String {
        return """
            - TITLE: ${this.title}, - DESCRIPTION: ${this.description}, - PRICE: ${this.price}, -imgUrl: ${this.imgUrl}   
        """.trimIndent()
    }

}
