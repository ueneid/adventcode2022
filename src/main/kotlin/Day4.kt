class Day4 {
    fun getInputs(): ArrayList<String> {
        val inputs = arrayListOf<String>()
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

    fun solve1(inputs: List<String>): Int {
        return inputs.asSequence()
            .map { line ->
                line.split(",")
                    .flatMap { it.split("-") }
                    .map { it.toInt() }
            }
            .count { it[0] <= it[2] && it[3] <= it[1] || it[2] <= it[0] && it[1] <= it[3] }
    }

    fun solve2(inputs: List<String>): Int {
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
    val obj = Day4()
    val inputs = obj.getInputs()
    println(obj.solve1(inputs))
    println(obj.solve2(inputs))
}
