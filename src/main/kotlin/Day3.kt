class Day3(val inputs: List<String>) {
    private fun getPriority(c: Char): Int {
        return if (c.isUpperCase()) {
            c.lowercaseChar() - 'a' + 1 + 26
        } else {
            c - 'a' + 1
        }
    }

    fun solve1(): Int {
        return inputs.asSequence()
            .map { line -> line.chunked(line.length / 2) }
            .map { it.map { s -> s.toSet() } }
            .map { it[0].intersect(it[1]) }
            .sumOf { getPriority(it.first()) }
    }

    fun solve2(): Int {
        return inputs.asSequence().chunked(3)
            .map { it.map { s -> s.toSet() } }
            .map { it[0].intersect(it[1]).intersect(it[2]) }
            .sumOf { getPriority(it.first()) }
    }
}

fun main() {
    val obj = Day3(Resource.resourceAsListOfString("day3/input.txt"))
    println(obj.solve1())
    println(obj.solve2())
}
