class Day4(val inputs: List<String>) {
    fun solve1(): Int {
        return inputs.asSequence()
            .map { line ->
                line.split(",")
                    .flatMap { it.split("-") }
                    .map { it.toInt() }
            }
            .count { it[0] <= it[2] && it[3] <= it[1] || it[2] <= it[0] && it[1] <= it[3] }
    }

    fun solve2(): Int {
        return inputs.asSequence()
            .map { line ->
                line.split(",")
                    .map { it.split("-").map { s -> s.toInt() } }
                    .map { (it[0]..it[1]).toSet() }
            }
            .map { it[0].intersect(it[1]) }
            .count { it.isNotEmpty() }
    }
}

fun main() {
    val obj = Day4(Resource.resourceAsListOfString("day4/input.txt"))
    println(obj.solve1())
    println(obj.solve2())
}
