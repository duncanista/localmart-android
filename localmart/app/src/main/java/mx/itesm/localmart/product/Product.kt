package mx.itesm.localmart.product

import java.io.Serializable

class Product (var name: String, var price: String, var imageUri: String, var description: String,
               var seller: String, var sold: Boolean, var category: String, var productId: String) : Comparable<Product>, Serializable
{
    override fun compareTo(other: Product): Int {
        return name.compareTo(other.name)
    }

    companion object {
        val arrProducts = arrayOf(
            Product("iPhone 11 Pro Max", "$1,099.99", "empty", "empty", "none", false, "Electronics", "00"),
            Product("iPhone 11", "$799.99", "empty", "empty", "none", false, "Electronics", "00")
            )
    }
}