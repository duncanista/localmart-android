package mx.itesm.localmart.product

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.category_item.view.*
import kotlinx.android.synthetic.main.product_list_item.view.*
import mx.itesm.localmart.ListenerRecycler
import mx.itesm.localmart.R

class ProductAdapter (private val context: Context, var arrProducts: Array<Product>) :
    RecyclerView.Adapter<ProductAdapter.ProductItem>()
{

    var listener: ListenerRecycler? = null

    inner class ProductItem(var rowView: View) : RecyclerView.ViewHolder(rowView)
    {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductItem {
        val view = LayoutInflater.from(context).inflate(R.layout.product_list_item, parent, false)
        return ProductItem(view)
    }


    override fun getItemCount(): Int {
        return arrProducts.size
    }

    override fun onBindViewHolder(holder: ProductItem, position: Int) {
        val product = arrProducts[position]
        holder.rowView.tvProductListName.text = "${product.name}"
        holder.rowView.tvPrice.text = "${product.price}"
    }


}