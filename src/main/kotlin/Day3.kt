class Day3 {
    fun getInputs(): ArrayList<String> {
        val inputs = ArrayList<String>()
        try {
            while (true) {
                inputs.add(IO.readStr())
            }
        } catch (e: RuntimeException) { // EOF
            println(e.toString())
        } catch (e: Exception) {
            println(e.toString())
        }
        return inputs
    }

    fun getPriority(c: Char): Int {
        return if (c.isUpperCase()) {
            c.lowercaseChar() - 'a' + 1 + 26
        } else {
            c - 'a' + 1
        }
    }

    fun solve1(inputs: List<String>): Int {
        return inputs.asSequence()
            .map { line -> line.chunked(line.length / 2) }
            .map { it.map { s -> s.toSet() } }
            .map { it[0].intersect(it[1]) }
            .sumOf { getPriority(it.first()) }
    }

    fun solve2(inputs: List<String>): Int {
        return inputs.asSequence().chunked(3)
            .map { it.map { s -> s.toSet() } }
            .map { it[0].intersect(it[1]).intersect(it[2]) }
            .sumOf { getPriority(it.first()) }
    }
}

fun main() {
    val obj = Day3()
    val inputs = obj.getInputs()
    println(obj.solve1(inputs))
    println(obj.solve2(inputs))
}
