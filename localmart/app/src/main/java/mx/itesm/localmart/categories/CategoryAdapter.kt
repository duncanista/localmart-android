package mx.itesm.localmart.categories

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.category_item.view.*
import mx.itesm.localmart.R

class CategoryAdapter (private val context: Context, var arrCategories: Array<Category>) :
    RecyclerView.Adapter<CategoryAdapter.CategoryItem>()
{

    var listener: ListenerRecycler? = null

    inner class CategoryItem(var rowView: View) : RecyclerView.ViewHolder(rowView)
    {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryItem {
        val view = LayoutInflater.from(context).inflate(R.layout.category_item, parent, false)
        return CategoryItem(view)
    }


    override fun getItemCount(): Int {
        return arrCategories.size
    }

    override fun onBindViewHolder(holder: CategoryItem, position: Int) {
        val category = arrCategories[position]
        holder.rowView.tvProfile.text = "${category.name}"
    }


}