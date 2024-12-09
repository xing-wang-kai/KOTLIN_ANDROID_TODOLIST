package com.example.myorganizationlist.ui.activitys

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.myorganizationlist.DAO.ProductDAO
import com.example.myorganizationlist.R
import com.example.myorganizationlist.databinding.ActivityFormProductBinding
import com.example.myorganizationlist.extensions.tryToLoadImage
import com.example.myorganizationlist.model.Product
import com.example.myorganizationlist.ui.dialog.FormImageDialog
import java.math.BigDecimal

/**
 * Represeta o objeto do Formulário de produtos
 *
 * @property activity_form_product é a view do formulário de produto
 * @property productDAO é a classe que representa Data Access Object
 * @property url é a url que usamos para acessar a imagem no formulário
 *
 * **/
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

        setContentView(binding.root)
        this.configSaveButton()

        title = "Cadastrar Produtos"

        binding.focusImagem.setOnClickListener {
            FormImageDialog(this).show(this.url){ imagem: String ->
                this.url = imagem
                binding.focusImagem.tryToLoadImage(url)
            }
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

        binding.activityFormProductProgressBar?.show()

        val titleField = binding.titleImput
        val descriptionField = binding.descriptionsImput
        val priceField = binding.priceImput

        val title = titleField.text.toString()
        val description = descriptionField.text.toString()
        val price = priceField.text.toString()
        val imgUrl = url

        val priceDecimalValue = if(price.isBlank()) {
            BigDecimal.ZERO
        }else{
            BigDecimal(price)
        }

        binding.activityFormProductProgressBar?.hide()
        return Product(
            title = title,
            description = description,
            price = priceDecimalValue,
            imgUrl = imgUrl
        )
    }
}