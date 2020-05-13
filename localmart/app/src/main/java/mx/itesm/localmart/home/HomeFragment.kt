package mx.itesm.localmart.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_home.*
import mx.itesm.localmart.MainActivity
import mx.itesm.localmart.R
import mx.itesm.localmart.auth.Auth
import mx.itesm.localmart.auth.LoginScreen

class HomeFragment : Fragment() {
    private val Auth: Auth = Auth()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonLogout.setOnClickListener(View.OnClickListener {
            signOut()
        })

        Auth.fbAuth?.addAuthStateListener {
            if(Auth.fbAuth?.currentUser == null){
                startActivity(Intent(activity, LoginScreen::class.java))
                activity?.finish()
            }
        }
    }

    private fun signOut(){
        Auth.fbAuth?.signOut()
    }

}
