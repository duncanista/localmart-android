package mx.itesm.localmart.categories

class Category (val name: String) : Comparable<Category>
{
    override fun compareTo(other: Category): Int {
        return name.compareTo(other.name)
    }

    companion object {
        val arrCategories = arrayOf(
            Category("Arts & Crafts"),
            Category("Clothing and Accessories"),
            Category("Electronics"),
            Category("Food"),
            Category("Services"),
            Category("Video games"),
            Category("Other")
        )
    }
}