package com.example.myorganizationlist.ui.recycleview_adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myorganizationlist.databinding.ProdutoItemBinding
import com.example.myorganizationlist.extensions.tryToLoadImage
import com.example.myorganizationlist.model.Product
import com.example.myorganizationlist.ui.activitys.ProductCardLayoutActivity
import java.text.NumberFormat
import java.util.Locale

class ListaOfProductsAdapter(
    private val context: Context,
    products: List<Product>
) : RecyclerView.Adapter<ListaOfProductsAdapter.ViewHolder>() {

    private var datasets = products.toMutableList()

    class ViewHolder(private val binding: ProdutoItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bindThis(product: Product, context: Context) {

            val title = binding.title
            val description = binding.descriptions
            val price = binding.price
            val imagem = binding.produtoItemImagemView

            imagem.tryToLoadImage(product.imgUrl)

            title.text = product.title
            description.text = product.description

            val currencyInstance: NumberFormat = NumberFormat.getCurrencyInstance(Locale("pt", "br"))

            price.text = currencyInstance.format(product.price)

            binding.productItemCard.setOnClickListener {
                val intent = Intent(context, ProductCardLayoutActivity::class.java)
                intent.putExtra("PRODUCT_TITLE", product.title)
                intent.putExtra("PRODUCT_DESCRITION", product.description)
                intent.putExtra("PRODUCT_PRICE", product.price.toString())
                intent.putExtra("PRODUCT_IMGURL", product.imgUrl)

                context.startActivity(intent)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater = LayoutInflater.from(this.context)
        val binding = ProdutoItemBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = datasets.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = datasets[position]
        holder.bindThis(product, context)
    }

    fun upgrade(products: List<Product>) {
        datasets.clear()
        datasets.addAll(products)
        notifyDataSetChanged()
    }
}
