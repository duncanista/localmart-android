package mx.itesm.localmart.home

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.fragment_account.*
import kotlinx.android.synthetic.main.fragment_home.*
import mx.itesm.localmart.MainActivity
import mx.itesm.localmart.R
import mx.itesm.localmart.auth.Auth
import mx.itesm.localmart.auth.LoginScreen
import mx.itesm.localmart.product.AddProductActivity
import mx.itesm.localmart.product.YourProductsActivity

class HomeFragment : Fragment() {
    private val Auth: Auth = Auth()
    var prefs: SharedPreferences? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.toolbar?.title = "Home"
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prefs = activity?.getSharedPreferences("pref", Context.MODE_PRIVATE)
        tvUserName.text = prefs?.getString("fullname", "") + "!"
        if (tvUserName.text == "!") {
            tvUserName.text = ""
            tvWelcome.text = "Welcome!"
        }


        buttonLogout.setOnClickListener(View.OnClickListener {
            signOut()
            startActivity(Intent(activity, LoginScreen::class.java))
            activity?.finish()
        })

        btnAddProduct.setOnClickListener{
            startActivity(
                Intent(
                    activity,
                    AddProductActivity::class.java
                )
            )

        }

        btnYourProducts.setOnClickListener {
            startActivity(
                Intent(
                    activity,
                    YourProductsActivity::class.java
                )
            )
        }
        
    }

    private fun signOut(){
        Auth.fbAuth?.signOut()
    }

}
