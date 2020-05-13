package mx.itesm.localmart.account.communities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_communities.*
import mx.itesm.localmart.ListenerRecycler
import mx.itesm.localmart.R


class CommunitiesScreen : AppCompatActivity(), ListenerRecycler {

    var communityAdapter: CommunityAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_communities)

        var layout = LinearLayoutManager(this)
        layout.orientation = LinearLayoutManager.VERTICAL
        recyclerCommunities.layoutManager = layout
        communityAdapter = CommunityAdapter(
            this,
            Community.arrCommunities
        )
        recyclerCommunities.adapter = communityAdapter

        val divisor = DividerItemDecoration(this, layout.orientation)
        recyclerCommunities.addItemDecoration(divisor)

    }

    override fun itemClicked(position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
