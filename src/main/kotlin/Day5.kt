class Day5 {

    data class Command(val value: Int, val from: Int, val to: Int)

    data class Input(val stacks: List<ArrayDeque<Char>>, val commands: List<Command>)

    fun getInputs(): Input {
        val inputs = arrayListOf<String>()
        val stacks = arrayListOf<ArrayDeque<Char>>()
        val stackRegex = """(\[[A-Z]\]|\s{3})(?:\s|$)""".toRegex()
        try {
            while (true) {
                val line = IO.readStr()
                if (line.isBlank()) {
                    break
                }
                val matches = stackRegex.findAll(line)
                if (matches.count() == 0) {
                    continue
                }
                if (stacks.size == 0) {
                    for (i in 0 until matches.count()) {
                        stacks.add(ArrayDeque<Char>())
                    }
                }
                matches.forEachIndexed { i, match ->
                    if (match.value.contains("""[A-Z]""".toRegex())) {
                        stacks[i].addFirst(match.value[1])
                    }
                }
            }
        } catch (e: RuntimeException) { // EOF
            println(e.toString())
        }

        val commands = ArrayList<Command>()
        val commandRegex = """^move (\d+) from (\d+) to (\d+)$""".toRegex()
        try {
            while (true) {
                val line = IO.readStr()
                val (value, from, to) = commandRegex.matchEntire(line)?.destructured?.toList()?.map { it.toInt() }
                    ?: throw IllegalArgumentException("Incorrect input line $line")
                commands.add(Command(value, from, to))
            }
        } catch (e: RuntimeException) {
            println(e.toString())
        }

        return Input(stacks, commands)
    }

    fun solve1(stacks: List<ArrayDeque<Char>>, commands: List<Command>): String {
        commands.forEachIndexed { _, (value, from, to) ->
            repeat(value) {
                stacks[to - 1].add(stacks[from - 1].removeLast())
            }
        }
        return stacks.map {
            it.removeLast()
        }.joinToString("")
    }

    fun solve2(stacks: List<ArrayDeque<Char>>, commands: List<Command>): String {
        commands.forEachIndexed { _, (value, from, to) ->
            val removed = (0 until value).map {_ ->
                stacks[from - 1].removeLast()
            }
            stacks[to - 1] += removed.reversed()
        }
        return stacks.map {
            it.removeLast()
        }.joinToString("")
    }
}

fun main() {
    val obj = Day5()
    val (stacks, commands) = obj.getInputs()
//    println(obj.solve1(stacks, commands))
    println(obj.solve2(stacks, commands))
}
