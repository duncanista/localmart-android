package mx.itesm.localmart.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_signup_screen.*
import mx.itesm.localmart.MainActivity
import mx.itesm.localmart.R
import mx.itesm.localmart.utils.User
import mx.itesm.localmart.utils.Api.UserApi

class SignupScreen : AppCompatActivity() {

    private val Auth: Auth = Auth()
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var confirmPassword: String
    private lateinit var nameInput: String
    private lateinit var phone: String

    public override fun onStart() {
        super.onStart()
        val currentUser = Auth.fbAuth?.currentUser
        launchApp(currentUser)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_screen)

        buttonSignUp.setOnClickListener {
            email = textInputEmail.text.toString()
            password = textInputPassword.text.toString()
            confirmPassword = textInputConfirmPassword.text.toString()
            nameInput = textInputConfirmPassword.text.toString()
            print(email)
            print(password)
            print(confirmPassword)
            print(nameInput)
            if(Auth.checkSignUpFields(email, password, confirmPassword, phone, nameInput)){
                //containerSignUp.visibility = View.INVISIBLE
                //containerProgress.visibility = View.VISIBLE
                val fullname = nameInput.split(" ")
                val name = fullname[0]
                val lastname = fullname[1]
                val communities = listOf<String>()
                val user = User(name=name, lastname=lastname, email=email, admin=false, communities=communities)
                print(name)
                print(lastname)
                /*if(UserApi.create(user, password)){
                    val currentUser = Auth.fbAuth?.currentUser
                    launchApp(currentUser)
                }*/
            }
        }

        textViewLogIn.setOnClickListener{
            startActivity(Intent(this, LoginScreen::class.java))
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
