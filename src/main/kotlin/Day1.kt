class Day1(val inputs: List<String>) {
    private val calories = parse()

    private fun parse(): MutableList<List<Int>> {
        val calories = mutableListOf<List<Int>>()
        for (bunch in inputs) {
            calories.add(bunch.split("\n").map { it.toInt() })
        }
        return calories
    }

    fun solve1(): Int {
        return calories.maxOfOrNull { it.sum() }!!
    }

    fun solve2(): Int {
        return calories.map { it.sum() }.sortedDescending().take(3).sum()
    }
}

fun main() {
    val input = Resource.resourceAsListOfBunchOfString("day1/input.txt")
    val obj = Day1(input)
    println(obj.solve1())
    println(obj.solve2())
}
