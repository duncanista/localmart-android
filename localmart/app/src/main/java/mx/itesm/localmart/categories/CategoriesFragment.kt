package mx.itesm.localmart.categories

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_categories_screen.recyclerCategory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_categories.*
import mx.itesm.localmart.ListenerRecycler
import mx.itesm.localmart.R
import mx.itesm.localmart.product.ProductListScreen


/**
 * A simple [Fragment] subclass.
 */
class CategoriesFragment : Fragment(), ListenerRecycler {

    var categoryAdapter: CategoryAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.toolbar?.title = "Categories"
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var layout = LinearLayoutManager(activity)
        layout.orientation = LinearLayoutManager.VERTICAL
        recyclerCategory.layoutManager = layout
        categoryAdapter = CategoryAdapter(
            activity!!,
            Category.arrCategories
        )
        categoryAdapter?.listener = this
        recyclerCategory.adapter = categoryAdapter

        val divisor = DividerItemDecoration(activity, layout.orientation)
        recyclerCategory.addItemDecoration(divisor)

        btnSearch.setOnClickListener(View.OnClickListener {
            val searchValue = etSearchProduct.text.toString()
            if (searchValue != ""){
                val intProductList = Intent(activity, ProductListScreen::class.java)
                intProductList.putExtra("Search", searchValue)
                intProductList.putExtra("Category", "All")
                startActivity(intProductList)
            }

            etSearchProduct.setText("")
        })
    }



    override fun itemClicked(position: Int) {
        println("ITEM CLICKED:")
        println(position.toString())
        val intProductList = Intent(activity, ProductListScreen::class.java)
        val clickedCategory = Category.arrCategories[position].name
        intProductList.putExtra("Category", clickedCategory)
        val searchValue = etSearchProduct.text.toString()
        intProductList.putExtra("Search", searchValue)
        startActivity(intProductList)
    }



}
