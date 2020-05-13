package mx.itesm.localmart.search

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_search.*
import mx.itesm.localmart.R
import mx.itesm.localmart.categories.CategoriesScreen
import mx.itesm.localmart.product.AddProductActivity
import mx.itesm.localmart.product.ProductListScreen




class SearchFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.toolbar?.title = "Search"
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnCategories.setOnClickListener(View.OnClickListener {
            startActivity(
                Intent(
                    activity,
                    AddProductActivity::class.java
                    //CategoriesScreen::class.java
                )
            )
        })

        btnProductList.setOnClickListener(View.OnClickListener {
            startActivity(
                Intent(
                    activity,
                    ProductListScreen::class.java
                )
            )

        })
        
        }

    }

