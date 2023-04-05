class Day6(val input: String) {
    fun solve1(): Int {
        val makerLength = 4
        return input.windowedSequence(makerLength) { it.toSet() }.indexOfFirst { it.size == makerLength } + makerLength
    }

    fun solve2(): Int {
        val makerLength = 14
        return input.windowedSequence(makerLength) { it.toSet() }.indexOfFirst { it.size == makerLength } + makerLength
    }
}

fun main() {
    val obj = Day6(Resource.resourceAsText("day6/input.txt"))
    println(obj.solve1())
    println(obj.solve2())
}
