package com.example.myorganizationlist.ui.activitys

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myorganizationlist.R
import com.example.myorganizationlist.databinding.FormCardLayoutBinding
import com.example.myorganizationlist.extensions.tryToLoadImage

class ProductCardLayoutActivity : AppCompatActivity(R.layout.form_card_layout) {

    private lateinit var binding: FormCardLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FormCardLayoutBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val productTitle = intent.getStringExtra("PRODUCT_TITLE")
        val productDescription = intent.getStringExtra("PRODUCT_DESCRITION")
        val productPrice = intent.getStringExtra("PRODUCT_PRICE")
        val productImgUrl = intent.getStringExtra("PRODUCT_IMGURL")

        binding.productCardLayoutTitle.text = productTitle?: "Product não encontrado"
        binding.productCardLayoutDescriptions.text = productDescription?: "Product não encontrado"
        binding.productCardLayoutPrice.text = productPrice?: "Product não encontrado"
        binding.productCardLayoutImage.tryToLoadImage(productImgUrl)

    }
}