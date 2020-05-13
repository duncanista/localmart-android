package mx.itesm.localmart.account.communities

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.community_item.view.*
import mx.itesm.localmart.ListenerRecycler
import mx.itesm.localmart.R

class CommunityAdapter(private val context: Context, var arrCommunities: Array<Community>) :
    RecyclerView.Adapter<CommunityAdapter.CommunityItem>()
{

    var listener: ListenerRecycler? = null

    inner class CommunityItem(var rowView: View) : RecyclerView.ViewHolder(rowView)
    {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommunityItem {
        val view = LayoutInflater.from(context).inflate(R.layout.community_item, parent, false)
        return CommunityItem(view)
    }


    override fun getItemCount(): Int {
        return arrCommunities.size
    }

    override fun onBindViewHolder(holder: CommunityItem, position: Int) {
        val community = arrCommunities[position]
        holder.rowView.tvCommunity.text = "${community.name}"

        holder.rowView.setOnClickListener {
            listener?.itemClicked(position)
        }
    }

}