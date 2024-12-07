package com.example.myorganizationlist.ui.activitys

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myorganizationlist.DAO.ProductDAO
import com.example.myorganizationlist.R
import com.example.myorganizationlist.databinding.ActivityMainBinding
import com.example.myorganizationlist.databinding.ImageFormBinding
import com.example.myorganizationlist.ui.recycleview_adapter.ListaOfProductsAdapter

class ListProductsActivity: AppCompatActivity(R.layout.activity_main) {

    private val productDao = ProductDAO()
    private val adapter = ListaOfProductsAdapter(context = this, products = productDao.findAll() )
    private lateinit var binding: ActivityMainBinding
    private lateinit var imageFormBinding: ImageFormBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        imageFormBinding = ImageFormBinding.inflate(layoutInflater)

        setContentView(binding.root)
        configureRecycleView()

    }

    override fun onResume() {
        super.onResume()
        adapter.upgrade(productDao.findAll())
        configureFab()
    }

    private fun configureRecycleView(){


        //val recycleView: RecyclerView = findViewById<RecyclerView>(R.id.list_of_products)

        val recycleView: RecyclerView = binding.listOfProducts

        recycleView.adapter = this.adapter
        recycleView.layoutManager = LinearLayoutManager(this)
    }

    private fun configureFab(){

        val findViewById = binding.floatingActionButton
        //val findViewById = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        findViewById.setOnClickListener{
            val intent = Intent(this, FormProductActivity::class.java)
            startActivity(intent)
        }
    }

}