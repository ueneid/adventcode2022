class Util {
    companion object {
        fun <T> transpose(list: List<List<T>>): List<List<T>> {
            return List(list.first().size) { index -> list.map { row -> row[index] } }
        }
    }
}
