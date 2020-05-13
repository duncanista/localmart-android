package mx.itesm.localmart.product

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_product_description.*
import kotlinx.android.synthetic.main.product_list_item.view.*
import mx.itesm.localmart.R

class ProductDescriptionActivity : AppCompatActivity() {

    var storage = FirebaseStorage.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_description)

        val product = intent.getSerializableExtra("Product") as? Product

        tvProductName.text = product?.name
        tvDescription.text = product?.description
        tvPrice.text = product?.price

        val gsReference = storage.getReferenceFromUrl(product!!.imageUri)
        gsReference.downloadUrl.addOnCompleteListener{Uri->
            val imgUrl = Uri.toString()
            val imgView = imgProduct
            Glide.with(this)
                .load(imgUrl)
                .into(imgView)
        }
    }
}
