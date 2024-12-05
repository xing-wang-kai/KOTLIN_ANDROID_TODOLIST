package com.example.myorganizationlist.ui.activitys

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.myorganizationlist.DAO.ProductDAO
import com.example.myorganizationlist.R
import com.example.myorganizationlist.model.Product
import java.math.BigDecimal

class FormProductActivity : AppCompatActivity(
    R.layout.activity_form_product
) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val submit = findViewById<Button>(R.id.submit_button)

        submit.setOnClickListener {
            val title_imput = findViewById<EditText>(R.id.title_imput)
            val description_imput = findViewById<EditText>(R.id.descriptions_imput)
            val price_imput = findViewById<EditText>(R.id.price_imput)

            val title = title_imput.text.toString()
            val description = description_imput.text.toString()
            val price = price_imput.text.toString()

            val price_decimal = if(price.isBlank()) {
                BigDecimal.ZERO
            }else{
                BigDecimal(price)
            }

            val newProduct = Product(
                title = title,
                description = description,
                price = price_decimal
            )

            Log.i("FormProducts", "OnCreated: $newProduct")

            val productDAO = ProductDAO()
                productDAO.add(newProduct)
            val productsDAO = productDAO.findAll()

            Log.i("productsLIst", "OnCreate: ${productsDAO.size}")

            finish()
        }

    }
}