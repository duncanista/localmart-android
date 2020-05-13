package mx.itesm.localmart.product

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import kotlinx.android.synthetic.main.activity_add_product.*
import kotlinx.android.synthetic.main.activity_product_list_screen.*
import kotlinx.coroutines.awaitAll
import mx.itesm.localmart.R
import mx.itesm.localmart.auth.Auth

class ProductListScreen : AppCompatActivity() {

    var productAdapter: ProductAdapter? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list_screen)


    }

    override fun onStart() {
        super.onStart()
        getProducts()



}


    private fun getProducts() {

        var firestore = FirebaseFirestore.getInstance()
        val array = mutableListOf<Product>()
        firestore.collection("products")
            .get()
            .addOnCompleteListener { task->
                    if (task.isSuccessful) {
                        val documents = task.result
                        for (document in documents!!){
                            val name = document.data["name"].toString()
                            val image = document.data["image"].toString()
                            val seller = document.data["seller"].toString()
                            val description = document.data["description"].toString()
                            val sold = document.data["sold"].toString()
                            val price = document.data["price"].toString()
                            var product = Product(name, price, image)
                            array.add(product)
                        }

                        var layout = GridLayoutManager(this, 3)
                        recyclerProductList.layoutManager = layout

                        productAdapter = ProductAdapter(
                            this,
                            array.toTypedArray()
                        )
                        recyclerProductList.adapter = productAdapter
                    }
                }


    }


}
