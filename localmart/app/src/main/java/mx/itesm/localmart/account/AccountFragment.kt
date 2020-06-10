package mx.itesm.localmart.account

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.preference.PreferenceManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_account.*
import kotlinx.android.synthetic.main.fragment_search.*

import mx.itesm.localmart.R
import mx.itesm.localmart.product.AddProductActivity
import mx.itesm.localmart.product.ProductListScreen
import mx.itesm.localmart.product.YourProductsActivity

class AccountFragment : Fragment(), View.OnClickListener {

    var navControler: NavController? = null
    var prefs: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.toolbar?.title = "Account"
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navControler = Navigation.findNavController(view)
        prefs = activity?.getSharedPreferences("pref", Context.MODE_PRIVATE)
        textViewUserEmail.text = prefs?.getString("email", "")
        textViewUserFullname.text = prefs?.getString("fullname", "")
        view.findViewById<LinearLayout>(R.id.linearLayoutUserProfile).setOnClickListener(this)
        view.findViewById<Button>(R.id.buttonAccountInformation).setOnClickListener(this)




    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.linearLayoutUserProfile -> navControler!!.navigate(R.id.action_accountFragment_to_profileFragment)
            R.id.buttonAccountInformation -> navControler!!.navigate(R.id.action_accountFragment_to_profileFragment)
        }
    }

}
