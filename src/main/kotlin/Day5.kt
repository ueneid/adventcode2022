class Day5(inputs: String) {
    private val parsedInput = parse(inputs)

    data class Command(val value: Int, val from: Int, val to: Int)

    data class Input(val stackList: List<ArrayDeque<Char>>, val commandList: List<Command>)

    private fun parse(inputs: String): Input {
        val stackRegex = """(\[[A-Z]\]|\s{3})(?:\s|$)""".toRegex()
        val stackCrateRegex = """^\[[A-Z]\]$""".toRegex()
        return inputs.split("\n\n").let { it ->
            val stackList = it[0].split("\n").let { lines ->
                val stackNumber = """(\d+)""".toRegex().findAll(lines.last()).count()
                val stacks = List<ArrayDeque<Char>>(stackNumber) { ArrayDeque() }
                lines.dropLast(1).forEach { line ->
                    val matches = stackRegex.findAll(line)
                    if (matches.count() == 0) {
                        throw IllegalArgumentException("Invalid format for stacks: $matches")
                    }
                    matches.forEachIndexed { index, matchResult ->
                        if (stackCrateRegex.matches(matchResult.groupValues[1])) {
                            stacks[index].addFirst(matchResult.groupValues[1][1])
                        }
                    }
                }
                stacks
            }

            val commandRegex = """^move (\d+) from (\d+) to (\d+)$""".toRegex()
            val commandList = it[1].split("\n").map { line ->
                val (value, from, to) = commandRegex.matchEntire(line)!!.destructured
                Command(value.toInt(), from.toInt(), to.toInt())
            }

            Input(stackList, commandList)
        }
    }

    private fun clone(): Input {
        val stackList = mutableListOf<ArrayDeque<Char>>().apply {
            addAll(parsedInput.stackList.map { chars ->
                val charQueue = ArrayDeque<Char>()
                chars.forEach { charQueue.add(it) }
                charQueue
            })
        }
        return Input(stackList, parsedInput.commandList)
    }

    fun solve1(): String {
        val (stackList, commandList) = clone()
        commandList.forEachIndexed { _, (value, from, to) ->
            repeat(value) {
                stackList[to - 1].add(stackList[from - 1].removeLast())
            }
        }
        return stackList.map {
            it.removeLast()
        }.joinToString("")
    }

    fun solve2(): String {
        val (stackList, commandList) = clone()
        commandList.forEachIndexed { _, (value, from, to) ->
            stackList[to - 1] += (0 until value).map { stackList[from - 1].removeLast() }.reversed()
        }
        return stackList.map { it.removeLast() }.joinToString("")
    }
}

fun main() {
    val obj = Day5(Resource.resourceAsText("day5/input.txt"))
    println(obj.solve1())
    println(obj.solve2())
}
