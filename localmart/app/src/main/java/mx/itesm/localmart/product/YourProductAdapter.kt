package mx.itesm.localmart.product

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.category_item.view.*
import kotlinx.android.synthetic.main.product_list_item.view.*
import kotlinx.android.synthetic.main.your_product_item.view.*
import mx.itesm.localmart.ListenerRecycler
import mx.itesm.localmart.R
import mx.itesm.localmart.auth.Auth

class YourProductAdapter (private val context: Context, var arrProducts: Array<Product>) :
    RecyclerView.Adapter<YourProductAdapter.YourProductItem>()
{


    var storage = FirebaseStorage.getInstance()

    var listener: ListenerRecycler? = null

    inner class YourProductItem(var rowView: View) : RecyclerView.ViewHolder(rowView)
    {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YourProductItem {
        val view = LayoutInflater.from(context).inflate(R.layout.your_product_item, parent, false)
        return YourProductItem(view)
    }


    override fun getItemCount(): Int {
        return arrProducts.size
    }

    override fun onBindViewHolder(holder: YourProductItem, position: Int) {
        val product = arrProducts[position]
        holder.rowView.tv_yourProduct.text = "${product.name}"

        if (product.imageUri != "no image") {
            val gsReference = storage.getReferenceFromUrl(product.imageUri)
            gsReference.downloadUrl.addOnSuccessListener { Uri ->
                val imgUrl = Uri.toString()
                val imgView = holder.rowView.img_yourProduct
                Glide.with(holder.rowView)
                    .load(imgUrl)
                    .into(imgView)
            }
        }

        holder.rowView.setOnClickListener {
            listener?.itemClicked(position)
        }
    }

}