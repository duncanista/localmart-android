package mx.itesm.localmart.utils

data class User(var id: String, var name: String, var lastname: String, var email: String, var admin: Boolean, var communities: Collection<String>)