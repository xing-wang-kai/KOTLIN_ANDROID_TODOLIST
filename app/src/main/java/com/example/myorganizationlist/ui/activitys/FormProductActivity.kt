package com.example.myorganizationlist.ui.activitys

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.myorganizationlist.R
import com.example.myorganizationlist.database.AppDatabase
import com.example.myorganizationlist.databinding.ActivityFormProductBinding
import com.example.myorganizationlist.extensions.tryToLoadImage
import com.example.myorganizationlist.model.Product
import com.example.myorganizationlist.ui.dialog.FormImageDialog
import kotlinx.coroutines.launch
import java.math.BigDecimal

class FormProductActivity : AppCompatActivity(
    R.layout.activity_form_product
) {

    private var url: String? = null
    private var currentProduct: Product? = null
    private var idProduct: Long = 0L
    private val binding by lazy {
        ActivityFormProductBinding.inflate(layoutInflater)
    }
    private val productDao by lazy{
        AppDatabase.instancia(this).produtoDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(this.binding.root)
        configSaveButton()
        title = "Cadastrar Produtos"

        binding.focusImagem.setOnClickListener {
            FormImageDialog(this).show(this.url) { imagem: String ->
                this.url = imagem
                binding.focusImagem.tryToLoadImage(this.url)
            }
        }

        this.idProduct = intent.getLongExtra("PRODUCT_ID", 0L)

        if(this.idProduct != 0L){

            lifecycleScope.launch {
                currentProduct = loadProduct(idProduct)

                title = "Editar Produto: ${currentProduct?.title}"
                binding.titleImput.setText(currentProduct?.title)
                binding.descriptionsImput.setText(currentProduct?.description)
                binding.priceImput.setText(currentProduct?.price.toString())
                binding.focusImagem.tryToLoadImage(currentProduct?.imgUrl)
                url = currentProduct?.imgUrl
            }

        }

    }

    private fun configSaveButton() {
        val submit = binding.submitButton

        submit.setOnClickListener {
            if (this.idProduct != 0L) {
                lifecycleScope.launch {
                    val newProduct = createNewProduct()
                    newProduct.id = idProduct
                    updateProduct(newProduct)
                    finish()
                }

            } else {

                lifecycleScope.launch{
                    val newProduct = createNewProduct()
                    productDao.insertAll(newProduct)
                    finish()
                }

            }
        }
    }

    private fun createNewProduct(): Product {
        binding.activityFormProductProgressBar?.show()
        val titleField = binding.titleImput
        val descriptionField = binding.descriptionsImput
        val priceField = binding.priceImput
        val title = titleField.text.toString()
        val description = descriptionField.text.toString()
        val price = priceField.text.toString()
        val imgUrl = this.url
        val priceDecimalValue = if (price.isBlank()) {
            BigDecimal.ZERO
        } else {
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

    private suspend fun loadProduct(id: Long): Product {
            return this.productDao.findByid(id)
    }

    private suspend fun updateProduct(product: Product) {
        this.productDao.update(product)
    }
}
