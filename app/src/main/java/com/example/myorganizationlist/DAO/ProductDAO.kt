package com.example.myorganizationlist.DAO

import com.example.myorganizationlist.model.Product

class ProductDAO {

    fun add(product: Product){
        Companion.products.add(product)
    }

    fun findAll(): List<Product>{
        return Companion.products.toList()
    }

    companion object {
        private val products = mutableListOf<Product>()
    }
}