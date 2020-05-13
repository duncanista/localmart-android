package mx.itesm.localmart.auth

import com.google.firebase.auth.FirebaseAuth
import mx.itesm.localmart.utils.Validation

class Auth{
    var fbAuth: FirebaseAuth? = FirebaseAuth.getInstance()
    private val Validate: Validation = Validation()

    fun checkLogInFields(email: String, password: String): Boolean{
        if(email.isEmpty() || password.isEmpty() || !Validate.atLeastOfSize6(password) || !Validate.isEmail(email)) return false
        return true
    }

}