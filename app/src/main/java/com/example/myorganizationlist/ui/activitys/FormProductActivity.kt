package com.example.myorganizationlist.ui.activitys

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.example.myorganizationlist.DAO.ProductDAO
import com.example.myorganizationlist.R
import com.example.myorganizationlist.databinding.ActivityFormProductBinding
import com.example.myorganizationlist.databinding.ActivityMainBinding
import com.example.myorganizationlist.databinding.ImageFormBinding
import com.example.myorganizationlist.model.Product
import java.math.BigDecimal

class FormProductActivity : AppCompatActivity(
    R.layout.activity_form_product
) {

    private val productDAO = ProductDAO()
    private lateinit var binding: ActivityFormProductBinding

    private var url: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityFormProductBinding.inflate(layoutInflater)

        val bindingImageForm = ImageFormBinding.inflate(layoutInflater)
        bindingImageForm.imageFormButtomLoad.setOnClickListener{
            this.url = bindingImageForm.imageFormImputImgUrl.text.toString()
            bindingImageForm.imageFormImageView.load(this.url)

        }

        setContentView(binding.root)
        this.configSaveButton()

        binding.focusImagem.setOnClickListener{
            AlertDialog.Builder(this)
                .setView(bindingImageForm.root)
                .setPositiveButton("Confirmar") {_, _ ->
                    binding.focusImagem.load(this.url)
                }
                .setNegativeButton("Cancelar"){_,_->}
                .show()
        }

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
        val imgUrlField = binding.focusImagem

        //val titleField = findViewById<EditText>(R.id.title_imput)
        //val descriptionField = findViewById<EditText>(R.id.descriptions_imput)
        //val priceField = findViewById<EditText>(R.id.price_imput)

        val title = titleField.text.toString()
        val description = descriptionField.text.toString()
        val price = priceField.text.toString()
        val imgUrl = url

        val priceDecimalValue = if(price.isBlank()) {
            BigDecimal.ZERO
        }else{
            BigDecimal(price)
        }

        return Product(
            title = title,
            description = description,
            price = priceDecimalValue,
            imgUrl = imgUrl
        )
    }
}