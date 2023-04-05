fun <T> List<T>.toPair(): Pair<T, T> {
    if (this.size != 2) {
        throw IllegalArgumentException("List size must be 2!")
    }
    return Pair(this[0], this[1])
}

fun <T> List<List<T>>.transpose(): List<List<T>> {
    return List(this.first().size) { index -> this.map { row -> row[index] } }
}
