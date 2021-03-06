package mx.itesm.localmart.utils

import androidx.core.text.isDigitsOnly

class Validation {
    fun isEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun atLeastOfSize(toCheck: String, size: Int) : Boolean {
        return toCheck.length >= size
    }

    fun isPhone(phone: String): Boolean {
        return ("[0-9]+").toRegex().matches(phone)
    }

    fun nameContainsLastname(name: String): Boolean{
        return name.contains(" ")
    }


}