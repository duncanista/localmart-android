package mx.itesm.localmart.product

class Product (val name: String, val price: String) : Comparable<Product>
{
    override fun compareTo(other: Product): Int {
        return name.compareTo(other.name)
    }

    companion object {
        val arrProducts = arrayOf(
            Product("iPhone 11 Pro Max", "$1,099.99"),
            Product("iPhone 11", "$799.99"),
            Product("test", "test"),
            Product("test", "test"),
            Product("test", "test"),
            Product("test", "test"),
            Product("test", "test"),
            Product("test", "test"),
            Product("test", "test"),
            Product("test", "test"),
            Product("test", "test"),
            Product("test", "test"),
            Product("test", "test"),
            Product("test", "test"),
            Product("test", "test"),
            Product("test", "test"),
            Product("test", "test"),
            Product("test", "test")

            )
    }
}