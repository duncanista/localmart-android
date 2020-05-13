package mx.itesm.localmart.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
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

            if(!Auth.checkSignUpFields(email, password, confirmPassword, nameInput, phone)){
                containerSignUp.visibility = View.INVISIBLE
                containerProgress.visibility = View.VISIBLE
                val fullname = nameInput.split(" ").toTypedArray()
                val name = fullname[0]
                val lastname = fullname[1]
                val communities = listOf<String>()
                val user = User(name=name, lastname=lastname, email=email, admin=false, communities=communities)
                val email = user.email

                Api.Auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    if(task.isSuccessful) {
                        val uid = Api.Auth.currentUser?.uid.toString()
                        println(uid)
                        val currentUser = Api.Auth.currentUser
                        println("---------- A USER WAS CREATED $uid")

                        firestore.collection("users").document(uid).set(user).addOnSuccessListener {
                            println("-------- Succesfully created a fucking user")
                        }.addOnFailureListener {
                            println("-------- user wasnt fucking created ma nigga")
                        }

                        launchApp(currentUser)
                    }
                }



            }else{
                println("no jala tu porkeria")
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

/*
var data = hashMapOf(
                            "name" to name,
                            "lastname" to lastname,
                            "phone" to phone,
                            "email" to email,
                            "communities" to communities
                        )
                        Api.firestore.collection("users").document(uid).set(data).addOnSuccessListener {
                            Log.d("TAG", "user created fooooo" + uid)
                            val currentUser = Auth.fbAuth?.currentUser
                            launchApp(currentUser)

                        }.addOnFailureListener{e ->
                            Log.d("ERROR", "wack bro" + e.toString())
                        }
* */