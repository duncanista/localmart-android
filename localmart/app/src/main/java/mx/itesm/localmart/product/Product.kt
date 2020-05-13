package mx.itesm.localmart.product

class Product (var name: String, var price: String, var imageUri: String) : Comparable<Product>
{
    override fun compareTo(other: Product): Int {
        return name.compareTo(other.name)
    }

    companion object {
        val arrProducts = arrayOf(
            Product("iPhone 11 Pro Max", "$1,099.99", "empty"),
            Product("iPhone 11", "$799.99", "empty")
            )
    }
}