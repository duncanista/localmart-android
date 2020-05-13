package mx.itesm.localmart.utils

class Validation {
    fun isEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun atLeastOfSize6(toCheck: String) : Boolean {
        return toCheck.length >= 6
    }


}