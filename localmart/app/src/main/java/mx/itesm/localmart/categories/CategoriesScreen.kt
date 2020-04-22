package mx.itesm.localmart.categories

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_categories_screen.*
import mx.itesm.localmart.R

class CategoriesScreen : AppCompatActivity() {

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




}
