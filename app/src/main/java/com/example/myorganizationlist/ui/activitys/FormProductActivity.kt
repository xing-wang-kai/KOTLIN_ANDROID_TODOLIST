package com.example.myorganizationlist.ui.activitys

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.myorganizationlist.DAO.ProductDAO
import com.example.myorganizationlist.R
import com.example.myorganizationlist.databinding.ActivityFormProductBinding
import com.example.myorganizationlist.databinding.ActivityMainBinding
import com.example.myorganizationlist.model.Product
import java.math.BigDecimal

class FormProductActivity : AppCompatActivity(
    R.layout.activity_form_product
) {

    private val productDAO = ProductDAO()
    private lateinit var binding: ActivityFormProductBinding


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityFormProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.configSaveButton()

    }

    private fun configSaveButton(){
        val submit = binding.submitButton
        //val submit = findViewById<Button>(R.id.submit_button)
        submit.setOnClickListener {
            val newProduct = this.createNewProduct()
            productDAO.add(newProduct)
            finish()
        }

    }

    private fun createNewProduct(): Product{

        val titleField = binding.titleImput
        val descriptionField = binding.descriptionsImput
        val priceField = binding.priceImput

        //val titleField = findViewById<EditText>(R.id.title_imput)
        //val descriptionField = findViewById<EditText>(R.id.descriptions_imput)
        //val priceField = findViewById<EditText>(R.id.price_imput)

        val title = titleField.text.toString()
        val description = descriptionField.text.toString()
        val price = priceField.text.toString()

        val priceDecimalValue = if(price.isBlank()) {
            BigDecimal.ZERO
        }else{
            BigDecimal(price)
        }

        return Product(
            title = title,
            description = description,
            price = priceDecimalValue
        )
    }
}