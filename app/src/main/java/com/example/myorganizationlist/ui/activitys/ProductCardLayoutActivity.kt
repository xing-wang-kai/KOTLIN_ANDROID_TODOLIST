package com.example.myorganizationlist.ui.activitys

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.myorganizationlist.R
import com.example.myorganizationlist.database.AppDatabase
import com.example.myorganizationlist.databinding.FormCardLayoutBinding
import com.example.myorganizationlist.extensions.tryToLoadImage
import com.example.myorganizationlist.model.Product
import kotlinx.coroutines.launch
import java.util.Locale

class ProductCardLayoutActivity : AppCompatActivity(R.layout.form_card_layout) {

    private var productId: Long = 0L
    private val binding by lazy {
        FormCardLayoutBinding.inflate(layoutInflater)
    }

    private val produtoDao by lazy {
        AppDatabase.instancia(this).produtoDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        this.updateActivityProducts()
        title = "Produto"

    }

    override fun onResume() {
        super.onResume()
        this.updateActivityProducts()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_card_product, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {


        when(item.itemId){
            R.id.menu_card_product_edite -> {

                lifecycleScope.launch {
                    val product = loadProduct()
                    val editIntent = Intent(this@ProductCardLayoutActivity, FormProductActivity::class.java).apply {
                        putExtra("PRODUCT_ID", product.id)
                    }
                    startActivity(editIntent)
                }
            }

            R.id.menu_card_product_delete -> {
                lifecycleScope.launch {
                    val product = loadProduct()
                    produtoDao.delete(product)
                    finish()
                }

            }
        }

        return super.onOptionsItemSelected(item)
    }

    private suspend fun loadProduct(): Product{
        this.productId = intent.getLongExtra("PRODUCT_ID", 0L)
        return this.produtoDao.findByid(this.productId)
    }

    private fun updateActivityProducts(){

        lifecycleScope.launch {
            val product = loadProduct()
            product.let {
                binding.productCardLayoutTitle.text = product.title
                binding.productCardLayoutDescriptions.text = product.description
                binding.productCardLayoutPrice.text = String.format(Locale.getDefault(), "%.2f", product.price)
                binding.productCardLayoutImage.tryToLoadImage(product.imgUrl)
            }?: finish()
        }
    }
}