class Day6 {

    data class Command(val value: Int, val from: Int, val to: Int)

    data class Input(val stacks: List<ArrayDeque<Char>>, val commands: List<Command>)

    fun getInputs(): String {
        return IO.readStr()
    }

    fun solve1(input: String): Int {
        val makerLength = 4
        return input.windowedSequence(makerLength) { it.toSet() }.indexOfFirst { it.size == makerLength } + makerLength
    }

    fun solve2(input: String): Int {
        val makerLength = 14
        return input.windowedSequence(makerLength) { it.toSet() }.indexOfFirst { it.size == makerLength } + makerLength}
}

fun main() {
    val obj = Day6()
    val input = obj.getInputs()
    println(obj.solve1(input))
    println(obj.solve2(input))
}
