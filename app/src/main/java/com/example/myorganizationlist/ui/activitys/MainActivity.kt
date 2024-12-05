package com.example.myorganizationlist.ui.activitys

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myorganizationlist.DAO.ProductDAO
import com.example.myorganizationlist.R
import com.example.myorganizationlist.model.Product
import com.example.myorganizationlist.ui.recycleview_adapter.ListaOfProductsAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.math.BigDecimal

class MainActivity: AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onResume() {
        super.onResume()
        val recycleView: RecyclerView = findViewById<RecyclerView>(R.id.list_of_products)

        val productDao = ProductDAO()
        val products = productDao.findAll()

        recycleView.adapter = ListaOfProductsAdapter(context = this, products = products )
        recycleView.layoutManager = LinearLayoutManager(this)

        val findViewById = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        findViewById.setOnClickListener{
            val intent = Intent(this, FormProductActivity::class.java)
            startActivity(intent)
        }
    }
}