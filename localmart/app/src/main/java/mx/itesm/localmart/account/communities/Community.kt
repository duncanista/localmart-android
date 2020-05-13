package mx.itesm.localmart.account.communities

class Community(val name: String) : Comparable<Community>
{
    override fun compareTo(other: Community): Int {
        return name.compareTo(other.name)
    }

    companion object {
        val arrCommunities = arrayOf(
            Community("ITESM CEM"),
            Community("Tesla"),
            Community("Arboledas"),
            Community("UVM Lomas Verdes"),
            Community("Zona Esmeralda"),
            Community("Prepa Tec CEM"),
            Community("Test"),
            Community("Test"),
            Community("Test"),
            Community("Test"),
            Community("Test"),
            Community("Test"),
            Community("Test")
        )
    }
}