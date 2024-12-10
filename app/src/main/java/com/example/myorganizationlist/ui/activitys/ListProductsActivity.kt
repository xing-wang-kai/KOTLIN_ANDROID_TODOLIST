package com.example.myorganizationlist.ui.activitys

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myorganizationlist.R
import com.example.myorganizationlist.database.AppDatabase
import com.example.myorganizationlist.databinding.ActivityMainBinding
import com.example.myorganizationlist.databinding.ImageFormBinding
import com.example.myorganizationlist.model.Product
import com.example.myorganizationlist.ui.recycleview_adapter.ListaOfProductsAdapter
import kotlinx.coroutines.launch

class ListProductsActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var imageFormBinding: ImageFormBinding
    private val adapter = ListaOfProductsAdapter(context = this)

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val productDao by lazy{
        AppDatabase.instancia(this).produtoDao()
    }

    private var ordenadProducts: List<Product> = emptyList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        imageFormBinding = ImageFormBinding.inflate(layoutInflater)

        setContentView(this.binding.root)

        configureRecycleView()
        configureFab()

        binding.activityMainSwipeRefresh?.setOnRefreshListener {
            lifecycleScope.launch{
                productDao.getAll().collect{ product ->
                    adapter.upgrade(product)
                }
            }
            binding.activityMainSwipeRefresh?.isRefreshing = false
        }

        lifecycleScope.launch{
            productDao.getAll().collect{ product ->
                adapter.upgrade(product)
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.ordem_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            R.id.ordem_menu_title_asc -> {

                lifecycleScope.launch {
                    productDao.getAllSorted("title", "asc").collect{
                        product ->
                        adapter.upgrade(product)
                    }

                }
            }

            R.id.ordem_menu_title_desc -> {

                lifecycleScope.launch {
                    productDao.getAllSorted("title", "desc").collect{
                            product ->
                        adapter.upgrade(product)
                    }

                }

            }

            R.id.ordem_menu_description_asc -> {

                lifecycleScope.launch {
                    productDao.getAllSorted("description", "asc").collect{
                            product ->
                        adapter.upgrade(product)
                    }

                }

            }

            R.id.ordem_menu_description_desc -> {

                lifecycleScope.launch {
                    productDao.getAllSorted("description", "desc").collect{
                            product ->
                        adapter.upgrade(product)
                    }

                }

            }

            R.id.ordem_menu_price_asc -> {
                lifecycleScope.launch {
                    productDao.getAllSorted("price", "asc").collect{
                            product ->
                        adapter.upgrade(product)
                    }

                }
            }

            R.id.ordem_menu_price_desc -> {
                lifecycleScope.launch {
                    productDao.getAllSorted("price", "desc").collect{
                            product ->
                        adapter.upgrade(product)
                    }
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun configureRecycleView() {
        val recycleView: RecyclerView = binding.listOfProducts
        recycleView.adapter = this.adapter
        recycleView.layoutManager = LinearLayoutManager(this)
    }

    private fun configureFab() {
        val floatingButton = binding.floatingActionButton
        floatingButton.setOnClickListener {
            redirectToProductForm()
        }
    }

    private fun redirectToProductForm() {
        val intent = Intent(this, FormProductActivity::class.java)
        startActivity(intent)
    }

}
