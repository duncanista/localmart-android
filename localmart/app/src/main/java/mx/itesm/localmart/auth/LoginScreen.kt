package mx.itesm.localmart.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login_screen.*
import mx.itesm.localmart.MainActivity
import mx.itesm.localmart.R
import mx.itesm.localmart.utils.Validation

class LoginScreen : AppCompatActivity() {

    private val Auth: Auth = Auth()
    private lateinit var email: String
    private lateinit var password: String

    public override fun onStart() {
        super.onStart()
        val currentUser = Auth.fbAuth?.currentUser
        launchApp(currentUser)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)

        buttonLogin.setOnClickListener {
            email = textInputEmail.text.toString()
            password = textInputPassword.text.toString()
            if(!Auth.checkLogInFields(email, password)){

                containerLogin.visibility = View.INVISIBLE
                containerProgress.visibility = View.VISIBLE

                Auth.fbAuth?.signInWithEmailAndPassword(email, password)?.addOnCompleteListener {
                    if(it.isSuccessful){
                        val currentUser = Auth.fbAuth?.currentUser
                        launchApp(currentUser)
                    }else{
                        println("hubo problemas en el login")
                    }
                }
            }else{
                println("hubo problemas")
                println(email)
                println(password)
            }
        }

        textViewCreateAccount.setOnClickListener{
            startActivity(Intent(this, SignupScreen::class.java))
        }
    }

    private fun launchApp(currentUser: FirebaseUser?){
        if(currentUser != null){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }else{
            println("usuario nulo wei")
        }

    }
}
