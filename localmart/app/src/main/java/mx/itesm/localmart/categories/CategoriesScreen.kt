
package mx.itesm.localmart.categories

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_categories_screen.*
import mx.itesm.localmart.ListenerRecycler
import mx.itesm.localmart.R
import mx.itesm.localmart.product.ProductListScreen

class CategoriesScreen : AppCompatActivity(), ListenerRecycler {

    var categoryAdapter: CategoryAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories_screen)


        var layout = LinearLayoutManager(this)
        layout.orientation = LinearLayoutManager.VERTICAL
        recyclerCategory.layoutManager = layout
        categoryAdapter = CategoryAdapter(
            this,
            Category.arrCategories
        )
        recyclerCategory.adapter = categoryAdapter

        val divisor = DividerItemDecoration(this, layout.orientation)
        recyclerCategory.addItemDecoration(divisor)
    }

    override fun itemClicked(position: Int) {
        //Move to product list screen

        //val nombre = categoryAdapter?.arrCategories?.get(position)?.name
        //TODO make each category button take you to corresponding products (we need DB connection for this)
        val intProductList = Intent(this, ProductListScreen::class.java)
        startActivity(intProductList)
    }

}
