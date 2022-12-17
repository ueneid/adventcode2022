import java.io.File

class Day7 {

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

    enum class FileType(val type: String) {
        FILE("file"), DIR("DIR")
    }

    class FileSystem(private val type: FileType, private val size: Int?, private val children: List<FileSystem>?) {
        fun totalSize(): Int {
            if (type == FileType.FILE) {
                return size!!
            }
            return children?.fold(0) { acc, child ->
                    acc + child.totalSize()
            } ?: 0
        }
    }

    fun parse(input: List<String>): FileSystem {
        fun (input: List<String>, parent: FileSystem?): FileSystem {
            val cur = input.
        }
    }

    fun solve1(input: List<String>): Int {
        return 1
    }

    fun solve2(input: List<String>): Int {
        return 1
    }
}

fun main() {
    val obj = Day5()
    val (stacks, commands) = obj.getInputs()
//    println(obj.solve1(stacks, commands))
    println(obj.solve2(stacks, commands))
}
