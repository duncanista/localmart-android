package mx.itesm.localmart.product

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_categories_screen.*
import kotlinx.android.synthetic.main.activity_product_list_screen.*
import kotlinx.android.synthetic.main.activity_your_products.*
import mx.itesm.localmart.ListenerRecycler
import mx.itesm.localmart.R
import mx.itesm.localmart.auth.Auth
import mx.itesm.localmart.categories.Category
import mx.itesm.localmart.categories.CategoryAdapter

class YourProductsActivity : AppCompatActivity(), ListenerRecycler {

    var yourProductAdapter: YourProductAdapter? = null


    val array = mutableListOf<Product>()

    //Firebase
    private var firestore = FirebaseFirestore.getInstance()
    private val Auth: Auth = Auth()

    internal var filepath: Uri? = null
    var storage = FirebaseStorage.getInstance()
    var storageReference = storage!!.reference

    val currentUser = Auth.fbAuth?.currentUser
    val userUid = currentUser?.uid!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_your_products)

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
                    val documents = task.result
                    for (document in documents!!){
                        val name = document.data["name"].toString()
                        val image = document.data["image"].toString()
                        val seller = document.data["seller"].toString()
                        val description = document.data["description"].toString()
                        val sold = document.data["sold"].toString().toBoolean()
                        val price = "$ %.2f".format(document.data["price"].toString().toDouble())
                        val category = document.data["category"].toString()
                        val id = document.id
                        var product = Product(name, price, image, description, seller, sold, category, id)

                        if (seller == userUid)
                        {array.add(product)}
                    }

                    configureRecycler()

                }
            }
    }

    fun configureRecycler(){
        var layout = LinearLayoutManager(this)
        recyclerYourProducts.layoutManager = layout
        layout.orientation = LinearLayoutManager.VERTICAL
        yourProductAdapter = YourProductAdapter(
            this,
                array.toTypedArray())


        yourProductAdapter?.listener = this
        recyclerYourProducts.adapter = yourProductAdapter

        val divisor = DividerItemDecoration(this, layout.orientation)
        recyclerYourProducts.addItemDecoration(divisor)

        if (array.toTypedArray().size == 0){
            tvYourProducts.text = "You have no products"
        }

    }

    override fun itemClicked(position: Int) {
        var productId = array[position].productId
        var productName = array[position].name

        println("clickkkk")

        var builder = AlertDialog.Builder(this)
        builder.setTitle("Delete product")
        builder.setMessage("Do you want to delete ${productName}?")
        builder.setPositiveButton("YES"){dialog, which ->
            // Do something when user press the positive button
            firestore.collection("products")
                .document(productId)
                .delete()
            Toast.makeText(applicationContext,"Product deleted",Toast.LENGTH_SHORT).show()

        }
        // Display a negative button on alert dialog
        builder.setNegativeButton("No"){dialog,which ->
            Toast.makeText(applicationContext,"Cancelling deletion",Toast.LENGTH_SHORT).show()
        }

        builder.show()




    }



}
