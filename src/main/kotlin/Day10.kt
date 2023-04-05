class Day10(inputs: List<String>) {
    private val commandSets = parse(inputs)

    private fun parse(inputs: List<String>): List<CommandSet> {
        return inputs.asSequence().map { line ->
            line.split(" ").let {
                CommandSet(Command.valueOf(it[0].uppercase()), it.getOrElse(1) { "0" }.toInt())
            }
        }.toList()
    }

    enum class Command() {
        ADDX {
            override fun getCycle() = 2
        },
        NOOP {
            override fun getCycle() = 1
        };

        abstract fun getCycle(): Int
    }

    data class CommandSet(val command: Command, val value: Int = 0)

    class CPU() {
        private var register = 1
        private var cycleCount = 0

        fun execute(commandSets: List<CommandSet>, callbackPerCycle: (cycleCount: Int, register: Int) -> Unit) {
            commandSets.forEach { commandSet ->
                val (command, value) = commandSet
                repeat(command.getCycle()) {
                    cycleCount++
                    callbackPerCycle(cycleCount, register)
                }
                register += value
            }
        }
    }

    fun solve1(): Int {
        var sumSignalStrength = 0
        CPU().execute(commandSets) { cycleCount: Int, register: Int ->
            if ((cycleCount + 20) % 40 == 0) {
                sumSignalStrength += cycleCount * register
            }
        }
        return sumSignalStrength
    }

    fun solve2(): List<String> {
        val output = MutableList<String>(6) { "" }
        CPU().execute(commandSets) { cycleCount: Int, register: Int ->
            val spriteIndex = (cycleCount - 1) % 40
            output[(cycleCount - 1) / 40] += if (spriteIndex >= register - 1 && spriteIndex <= register + 1) {
                "#"
            } else {
                "."
            }
        }
        return output
    }
}

fun main() {
    val obj = Day10(Resource.resourceAsListOfString("day10/input.txt"))
    println(obj.solve1())
    println(obj.solve2().joinToString("\n"))
}
