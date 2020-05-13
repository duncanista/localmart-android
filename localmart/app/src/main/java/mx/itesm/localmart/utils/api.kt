package mx.itesm.localmart.utils

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.functions.FirebaseFunctions
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.collections.HashMap


interface idd {
    val idd: String
}

object Api{
    val firestore = FirebaseFirestore.getInstance()
    val Functions = FirebaseFunctions.getInstance()
    var Auth = FirebaseAuth.getInstance()


    object UserApi {
        // Cloud Functions
        val addUser = Functions.getHttpsCallable("addUser")
        val updateUser = Functions.getHttpsCallable("updateUser")
        val deleteUser = Functions.getHttpsCallable("deleteUser")
        // Cloud Firestore instance
        val users = firestore.collection("users")


        fun create(user: User, password: String){
            val email = user.email
            Auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if(task.isSuccessful){
                    var uid = Auth.currentUser?.uid.toString()
                    firestore.collection("users").document(uid).set(user).addOnSuccessListener {
                        Log.d("TAG", "user created fooooo" + uid)

                    }.addOnFailureListener{e ->
                        Log.d("ERROR", "wack bro" + e.toString())
                    }
                }


            }
        }
    }
}

