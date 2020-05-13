package mx.itesm.localmart.utils

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*


interface idd {
    val idd: String
}

class Api{
    private var firestore = FirebaseFirestore.getInstance()
    fun <T> getCollIddDocs(path: String) where T : idd {
        val result: MutableList<T>
        val coll = firestore.collection(path).get()
            .addOnSuccessListener { docs ->
                for(doc in docs){
                    val data = { doc.data }

                }
            }
    }
}