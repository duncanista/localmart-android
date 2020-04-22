package mx.itesm.localmart.product

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_product_list_screen.*
import mx.itesm.localmart.R

class ProductListScreen : AppCompatActivity() {

    var productAdapter: ProductAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list_screen)


        var layout = GridLayoutManager(this, 3)
        recyclerProductList.layoutManager = layout
        productAdapter = ProductAdapter(
            this,
            Product.arrProducts
        )
        recyclerProductList.adapter = productAdapter

    }




}
