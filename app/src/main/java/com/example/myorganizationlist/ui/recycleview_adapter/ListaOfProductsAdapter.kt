package com.example.myorganizationlist.ui.recycleview_adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.myorganizationlist.R
import com.example.myorganizationlist.databinding.ActivityFormProductBinding
import com.example.myorganizationlist.databinding.ProdutoItemBinding
import com.example.myorganizationlist.model.Product
import java.text.NumberFormat
import java.util.Locale

class ListaOfProductsAdapter(
    private val context: Context,
    products: List<Product>,

) : RecyclerView.Adapter<ListaOfProductsAdapter.ViewHolder>() {

    private var datasets = products.toMutableList()

    class ViewHolder(private val binding: ProdutoItemBinding): RecyclerView.ViewHolder(binding.root) {


        fun bindThis(product: Product) {


            val title = binding.title
            val description = binding.descriptions
            val price = binding.price
            val imagem = binding.produtoItemImagemView

//            var visibility = if(product.imgUrl != null){
//                View.VISIBLE
//            }else{
//                View.INVISIBLE
//            }

            imagem.load(product.imgUrl){
                fallback(R.drawable.erro)
                error(R.drawable.erro)
            }

            title.text = product.title
            description.text = product.description

            val currencyInstance: NumberFormat = NumberFormat.getCurrencyInstance(Locale("pt", "br"))

            price.text = currencyInstance.format(product.price)

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
        holder.bindThis(product)
    }

    fun upgrade(products: List<Product>) {
        datasets.clear()
        datasets.addAll(products)
        notifyDataSetChanged()
    }
}
