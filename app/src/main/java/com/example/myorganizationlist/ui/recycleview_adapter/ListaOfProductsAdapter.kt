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
import com.example.myorganizationlist.model.Product
import java.text.NumberFormat
import java.util.Locale

class ListaOfProductsAdapter(
    private val context: Context,
    products: List<Product>

) : RecyclerView.Adapter<ListaOfProductsAdapter.ViewHolder>() {

    private val datasets = products.toMutableList()

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        fun bindThis(product: Product) {
            val title = itemView.findViewById<TextView>(R.id.title)
            val description = itemView.findViewById<TextView>(R.id.descriptions)
            val price = itemView.findViewById<TextView>(R.id.price)
            val imagem = itemView.findViewById<ImageView>(R.id.produto_item_imagem_view)

            imagem.load(product.imgUrl)

            title.text = product.title
            description.text = product.description

            val currencyInstance: NumberFormat = NumberFormat.getCurrencyInstance(Locale("pt", "br"))

            price.text = currencyInstance.format(product.price)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater = LayoutInflater.from(this.context)
        val view = inflater.inflate(R.layout.produto_item, parent, false)

        return ViewHolder(view)
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
