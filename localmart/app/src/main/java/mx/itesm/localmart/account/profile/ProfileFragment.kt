package mx.itesm.localmart.account.profile

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_account.*
import kotlinx.android.synthetic.main.fragment_account.textViewUserEmail
import kotlinx.android.synthetic.main.fragment_account.textViewUserFullname
import kotlinx.android.synthetic.main.fragment_profile.*

import mx.itesm.localmart.R


class ProfileFragment : Fragment() {

    var navControler: NavController? = null
    var prefs: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.toolbar?.title = "Account information"
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navControler = Navigation.findNavController(view)
        prefs = activity?.getSharedPreferences("pref", Context.MODE_PRIVATE)
        textViewUserFullname.text = prefs?.getString("fullname", "")
        textViewUserEmail.text = prefs?.getString("email", "")
        textViewUserPhone.text = prefs?.getString("phone", "No phone number")

    }

}
