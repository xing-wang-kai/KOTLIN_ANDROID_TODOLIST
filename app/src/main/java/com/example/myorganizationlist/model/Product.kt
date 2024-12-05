package com.example.myorganizationlist.model

import java.math.BigDecimal

class Product(
    val title: String,
    val description: String,
    val price: BigDecimal
) {

    override fun toString(): String {
        return """
            - TITLE: ${this.title}, - DESCRIPTION: ${this.description}, - PRICE: ${this.price}
        """.trimIndent()
    }

}
