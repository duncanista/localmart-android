package mx.itesm.localmart.auth

import android.content.Intent
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import mx.itesm.localmart.MainActivity
import mx.itesm.localmart.utils.Validation

class Auth{
    var fbAuth: FirebaseAuth? = FirebaseAuth.getInstance()
    private val Validate: Validation = Validation()

    fun checkLogInFields(email: String, password: String): Boolean{
        return (email.isEmpty() || password.isEmpty() || !Validate.atLeastOfSize(password, 6) || !Validate.isEmail(email))
    }

    fun checkSignUpFields(email: String, password: String, confirmPassword: String, name: String, phone: String): Boolean{
        return (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || name.isEmpty() || phone.isEmpty()
                || !Validate.atLeastOfSize(password, 6) || !Validate.isEmail(email) || !Validate.isPhone(phone)  || !Validate.nameContainsLastname(name)
                || !checkPasswords(password, confirmPassword) || !Validate.atLeastOfSize(phone, 8))
    }

    fun checkPasswords(password: String, confirmPassword: String): Boolean{
        return password == confirmPassword
    }




}