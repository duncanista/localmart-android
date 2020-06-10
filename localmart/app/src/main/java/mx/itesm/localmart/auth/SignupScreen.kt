package mx.itesm.localmart.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_signup_screen.*
import mx.itesm.localmart.MainActivity
import mx.itesm.localmart.R
import mx.itesm.localmart.utils.Api
import mx.itesm.localmart.utils.User
import mx.itesm.localmart.utils.Api.UserApi
import mx.itesm.localmart.utils.Validation

class SignupScreen : AppCompatActivity() {

    private val Auth: Auth = Auth()
    private val Validate: Validation = Validation()
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var confirmPassword: String
    private lateinit var nameInput: String
    private lateinit var phone: String
    val firestore = FirebaseFirestore.getInstance()

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
            nameInput = textInputName.text.toString()
            phone = textInputPhone.text.toString()

            checkErrors()

            if(!Auth.checkSignUpFields(email, password, confirmPassword, nameInput, phone)){
                containerSignUp.visibility = View.INVISIBLE
                containerProgress.visibility = View.VISIBLE
                val fullname = nameInput.split(" ").toTypedArray()
                val name = fullname[0]
                val lastname = fullname[1]
                val communities = listOf<String>()
                val user = User(name=name, lastname=lastname, email=email, phone=phone, admin=false, communities=communities)
                val email = user.email

                Api.Auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    if(task.isSuccessful) {
                        val uid = Api.Auth.currentUser?.uid.toString()
                        println(uid)
                        val currentUser = Api.Auth.currentUser
                        firestore.collection("users").document(uid).set(user).addOnSuccessListener {
                            println("-------- Succesfully created user")
                        }.addOnFailureListener {
                            println("-------- User wasnt created")
                        }

                        launchApp(currentUser)
                    }
                }
            }
        }

        textViewLogIn.setOnClickListener{
            finish()
        }
    }

    private fun launchApp(currentUser: FirebaseUser?){
        if(currentUser != null){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun resetErrors(){
        textViewNameError.text = ""
        textViewEmailError.text = ""
        textViewPhoneError.text = ""
        textViewPasswordError.text = ""
        textViewConfirmPasswordError.text = ""
    }
    private fun checkErrors(){
        resetErrors()
        if(email.isEmpty()) textViewEmailError.text = "Email can not be empty"
        else if(!Validate.isEmail(email)) textViewEmailError.text = "Invalid email"

        if(nameInput.isEmpty()) textViewNameError.text = "You have to write your name"
        else if(!Validate.nameContainsLastname(nameInput)) textViewNameError.text = "You should add your lastname"

        if(phone.isEmpty()) textViewPhoneError.text = "You must add your phone number"
        else if(!Validate.isPhone(phone)) textViewPhoneError.text = "Phone is invalid"
        else if(!Validate.atLeastOfSize(phone, 8)) textViewPhoneError.text = "Phone must be 8 digits long minimum"

        if(password.isEmpty()) textViewPasswordError.text = "Add a password"
        else if(!Validate.atLeastOfSize(password, 6)) textViewPasswordError.text = "Password must be at least 6 characters long"

        if(confirmPassword.isEmpty() && password.isNotEmpty()) textViewConfirmPasswordError.text = "You need to repeat the previous password"
        else if(!Auth.checkPasswords(password, confirmPassword)) textViewConfirmPasswordError.text = "Passwords do not match"

    }
}
