package mx.itesm.localmart.utils

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
    val functions = FirebaseFunctions.getInstance()


    object UserApi {
        // Cloud Functions
        val addUser = functions.getHttpsCallable("addUser")
        val updateUser = functions.getHttpsCallable("updateUser")
        val deleteUser = functions.getHttpsCallable("deleteUser")
        // Cloud Firestore instance
        val users = firestore.collection("users")

        fun create(user: User, password: String): Boolean{
            val email = user.email
            val data = hashMapOf("email" to email, "password" to password)
            var result = false
           addUser.call(data).addOnCompleteListener { userCredentials ->
                users.document(userCredentials.toString()).set(user).addOnCompleteListener {
                    if(it.isSuccessful){
                        result = true
                    }else{
                        print("hubo problemas aqui")
                    }
                }
           }
            return result
        }
    }
}