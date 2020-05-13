package mx.itesm.localmart.utils

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


interface idd {
    val idd: String
}

class Api{
    private var firestore = Firebase.firestore
    fun <T> getCollIddDocs(path: String): List<(T,idd)> {
        val coll = firestore.collection(path).get()
        val result: List<(T, idd)> = []
    }
}