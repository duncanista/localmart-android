package mx.itesm.localmart.product

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.category_item.view.*
import kotlinx.android.synthetic.main.product_list_item.view.*
import mx.itesm.localmart.ListenerRecycler
import mx.itesm.localmart.R

class ProductAdapter (private val context: Context, var arrProducts: Array<Product>) :
    RecyclerView.Adapter<ProductAdapter.ProductItem>()
{

    var storage = FirebaseStorage.getInstance()

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
        holder.rowView.setOnClickListener{
            Toast.makeText(context, "CLICKASTE ${product.name}", Toast.LENGTH_SHORT)
        }

        println(product.imageUri)
        if (product.imageUri != "no image") {
            val gsReference = storage.getReferenceFromUrl(product.imageUri)
            gsReference.downloadUrl.addOnCompleteListener { Uri ->
                val imgUrl = Uri.toString()
                val imgView = holder.rowView.imgProduct
                Glide.with(holder.rowView)
                    .load(imgUrl)
                    .into(imgView)
            }
        }

        holder.rowView.setOnClickListener{
            listener?.itemClicked(position)
        }

    }


}