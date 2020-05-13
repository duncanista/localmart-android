package mx.itesm.localmart.categories

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_categories_screen.*
import kotlinx.android.synthetic.main.activity_main.*
import mx.itesm.localmart.ListenerRecycler
import mx.itesm.localmart.R


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
        recyclerCategory.adapter = categoryAdapter

        val divisor = DividerItemDecoration(activity, layout.orientation)
        recyclerCategory.addItemDecoration(divisor)
    }


    override fun itemClicked(position: Int) {
        TODO("Not yet implemented")
    }

}
