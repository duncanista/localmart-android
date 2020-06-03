package mx.itesm.localmart.product

import android.content.Intent
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
import mx.itesm.localmart.ListenerRecycler
import mx.itesm.localmart.ProductDescriptionFragment
import mx.itesm.localmart.R
import mx.itesm.localmart.auth.Auth

class ProductListScreen : AppCompatActivity(), ListenerRecycler {

    var productAdapter: ProductAdapter? = null
    val array = mutableListOf<Product>()
    var selectedCategory: String = ""
    var searchValue: String = ""





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list_screen)

        selectedCategory = intent.getStringExtra("Category")
        searchValue = intent.getStringExtra("Search")
        tvSelectedCategory.text = selectedCategory

    }

    override fun onStart() {
        super.onStart()
        getProducts()

    }
    private fun getProducts() {

        var firestore = FirebaseFirestore.getInstance()
        firestore.collection("products")
            .get()
            .addOnCompleteListener { task->
                    if (task.isSuccessful) {
                        array.clear()
                        val documents = task.result
                        for (document in documents!!){
                            val name = document.data["name"].toString()
                            val image = document.data["image"].toString()
                            val seller = document.data["seller"].toString()
                            val description = document.data["description"].toString()
                            val sold = document.data["sold"].toString().toBoolean()
                            val price = "$ %.2f".format(document.data["price"].toString().toDouble())
                            val category = document.data["category"].toString()
                            var product = Product(name, price, image, description, seller, sold, category)
                            if ((category == selectedCategory || selectedCategory == "All") &&
                                (searchValue == "" || searchValue.toLowerCase().replace("\\s".toRegex(), "") in name.toLowerCase().replace("\\s".toRegex(), "")))
                            {array.add(product)}
                        }

                        configureRecycler()

                    }
                }
    }

    fun configureRecycler(){
        var layout = GridLayoutManager(this, 3)
        recyclerProductList.layoutManager = layout

        productAdapter = ProductAdapter(
            this,
            array.toTypedArray()
        )
        productAdapter?.listener = this
        recyclerProductList.adapter = productAdapter

    }

    override fun itemClicked(position: Int){
        println("ITEM CLICKED:")
        println(position.toString())
        val intProductDescription = Intent(this, ProductDescriptionActivity::class.java)
        val clickedProduct = array[position]
        intProductDescription.putExtra("Product", clickedProduct)
        startActivity(intProductDescription)
    }


}
