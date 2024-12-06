package com.example.myorganizationlist.ui.recycleview_adapter

import android.content.Context
import android.icu.text.DecimalFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myorganizationlist.R
import com.example.myorganizationlist.model.Product

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

            title.text = product.title
            description.text = product.description
            price.text = "R$ ${product.price.toPlainString()}"

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
