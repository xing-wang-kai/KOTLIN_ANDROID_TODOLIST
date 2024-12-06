package com.example.myorganizationlist.DAO

import com.example.myorganizationlist.model.Product
import java.math.BigDecimal

class ProductDAO {

    fun add(product: Product){
        Companion.products.add(product)
    }

    fun findAll(): List<Product>{
        return Companion.products.toList()
    }

    companion object {
        private val products = mutableListOf<Product>(
            Product(
                title = "Salada de Morangos",
                description = "Èssa é uma deliciosa salada de morangos, você vai amar a explosão de sabores citricos desses morangos.",
                price = BigDecimal("22.33")
            )
        )
    }
}