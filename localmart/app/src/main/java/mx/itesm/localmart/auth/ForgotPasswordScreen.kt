package mx.itesm.localmart.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_forgot_password.*
import kotlinx.android.synthetic.main.activity_forgot_password.textInputEmail
import kotlinx.android.synthetic.main.activity_forgot_password.textViewEmailError
import mx.itesm.localmart.R
import mx.itesm.localmart.utils.Api
import mx.itesm.localmart.utils.Validation

class ForgotPasswordScreen : AppCompatActivity() {

    private val Auth: Auth = Auth()
    private val Validate: Validation = Validation()
    private lateinit var email: String
    val firestore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        buttonRecoverPassword.setOnClickListener {
            email = textInputEmail.text.toString()

            checkErrors()

            if (email.isNotEmpty() && Validate.isEmail(email)){
                containerForgot.visibility = View.INVISIBLE
                containerProgress.visibility = View.VISIBLE
                Api.Auth.sendPasswordResetEmail(email).addOnCompleteListener { task ->
                    if(task.isSuccessful) {
                        textViewPasswordRecovered.visibility = View.VISIBLE
                        containerForgot.visibility = View.VISIBLE
                        containerProgress.visibility = View.INVISIBLE
                    }
                }
            }
        }

    }

    private fun resetErrors(){
        textViewEmailError.text = ""
    }
    private fun checkErrors(){
        resetErrors()
        if(email.isEmpty()) textViewEmailError.text = "Email can not be empty"
        else if(!Validate.isEmail(email)) textViewEmailError.text = "Invalid email"

    }
}
