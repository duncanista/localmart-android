package mx.itesm.localmart.product

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_product_description.*
import kotlinx.android.synthetic.main.product_list_item.view.*
import mx.itesm.localmart.ListenerRecycler
import mx.itesm.localmart.R
import mx.itesm.localmart.auth.Auth

class ProductDescriptionActivity : AppCompatActivity() {

    //Firebase
    private var firestore = FirebaseFirestore.getInstance()
    private val Auth: Auth = Auth()

    internal var filepath: Uri? = null
    var storage = FirebaseStorage.getInstance()
    var storageReference = storage!!.reference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_description)

        val product = intent.getSerializableExtra("Product") as? Product

        tvProductName.text = product?.name
        tvDescription.text = product?.description
        tvPrice.text = product?.price

        if (product!!.imageUri != "no image") {
            val gsReference = storage.getReferenceFromUrl(product!!.imageUri)
            gsReference.downloadUrl.addOnCompleteListener { Uri ->
                val imgUrl = Uri.toString()
                val imgView = imgProduct
                Glide.with(this)
                    .load(imgUrl)
                    .into(imgView)
            }
        }

        firestore.collection("users").document(product?.seller).get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val document = task.result
                    tvSellerName.text = document!!.data!!["name"].toString()
                    tvSellerEmail.text = document!!.data!!["email"].toString()
                    tvSellerPhoneNumber.text = document!!.data!!["phone"].toString()
                    }
            }

    }
}
