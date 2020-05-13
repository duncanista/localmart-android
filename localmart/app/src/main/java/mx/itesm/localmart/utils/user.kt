package mx.itesm.localmart.utils

data class User(var name: String="", var lastname: String="", var email: String="", var phone: String="", var admin: Boolean=false, var communities: List<String>){
    constructor() : this("", "", "", "", false, listOf<String>())
}