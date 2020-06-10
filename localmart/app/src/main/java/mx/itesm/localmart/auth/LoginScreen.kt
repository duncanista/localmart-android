package mx.itesm.localmart.auth

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.preference.PreferenceManager
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login_screen.*
import mx.itesm.localmart.MainActivity
import mx.itesm.localmart.R
import mx.itesm.localmart.utils.Api
import mx.itesm.localmart.utils.User
import mx.itesm.localmart.utils.Validation

class LoginScreen : AppCompatActivity() {

    private val Auth: Auth = Auth()
    private val Validate: Validation = Validation()
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

            checkErrors()

            if(!Auth.checkLogInFields(email, password)){

                containerLogin.visibility = View.INVISIBLE
                containerProgress.visibility = View.VISIBLE

                Api.Auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    if(it.isSuccessful){
                        val currentUser = Api.Auth.currentUser
                        val uid = currentUser?.uid.toString()
                        val prefs = getSharedPreferences("pref", Context.MODE_PRIVATE)

                        val editor = prefs.edit()
                        editor.clear()
                        Api.UserApi.users.document(uid).get().addOnSuccessListener { document ->
                            if(document != null){
                                val user = document.toObject(User::class.java)
                                editor.putString("name", user?.name)
                                editor.putString("lastname", user?.lastname)
                                editor.putString("email", user?.email)
                                editor.putString("phone", user?.phone)
                                editor.putString("fullname", "${user?.name} ${user?.lastname}")
                                println(prefs.getString("fullname", ""))
                                println(prefs.getString("name", ""))
                                println(prefs.getString("email", ""))
                                println(prefs.getString("phone", ""))
                                editor.commit()
                            }
                        }
                        editor.putString("uid", uid)
                        editor.commit()

                        launchApp(currentUser)
                    }else{
                        textViewInvalidCredentials.text = "Invalid credentials"
                        containerLogin.visibility = View.VISIBLE
                        containerProgress.visibility = View.INVISIBLE
                    }
                }
            }
        }

        textForgotPassword.setOnClickListener{
            startActivity(Intent(this, ForgotPasswordScreen::class.java))
        }

        textViewCreateAccount.setOnClickListener{
            startActivity(Intent(this, SignupScreen::class.java))
        }
    }

    private fun launchApp(currentUser: FirebaseUser?){
        if(currentUser != null){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    fun resetErrors(){
        textViewEmailError.text = ""
        textViewInvalidCredentials.text = ""
    }

    fun checkErrors(){
        resetErrors()
        if(email.isEmpty()) textViewEmailError.text = "Email can not be empty"
        else if(!Validate.isEmail(email)) textViewEmailError.text = "Invalid email"

        if(password.isEmpty()) textViewInvalidCredentials.text = "Password can not be empty"
        else if(!Validate.atLeastOfSize(password, 6)) textViewInvalidCredentials.text = "Invalid password"
    }
}
