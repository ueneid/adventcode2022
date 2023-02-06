class Day10 {

    fun getInputs(): List<List<String>> {
        val input = mutableListOf<List<String>>()
        try {
            while (true) {
                input.add(IO.readStrings())
            }
        } catch (e: RuntimeException) {
            e.printStackTrace()
        }
        return input
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

        fun execute(commandSet: CommandSet, callbackPerCycle: (cycleCount: Int, register: Int) -> Unit) {
            val (command, value) = commandSet
            repeat(command.getCycle()) {
                cycleCount++
                callbackPerCycle(cycleCount, register)
            }
            register += value
            println("$cycleCount: $register")
        }
    }

    fun solve1(input: List<List<String>>): Int {
        var sumSignalStrength = 0
        val cpu = CPU()
        var callback = fun(cycleCount: Int, register: Int) {
            if ((cycleCount + 20) % 40 == 0) {
                sumSignalStrength += cycleCount * register
            }
        }
        input.map { CommandSet(Command.valueOf(it[0].uppercase()), it.getOrElse(1) { "0" }.toInt()) }.forEach {
            cpu.execute(it, callback)
        }
        return sumSignalStrength
    }

    fun solve2(input: List<List<String>>): List<String> {
        val lit = "#"
        val dark = "."
        val cpu = CPU()
        val output = MutableList<String>(6) { "" }
        val callback = fun(cycleCount: Int, register: Int) {
            val spriteIndex = (cycleCount - 1) % 40
            output[(cycleCount - 1) / 40] += if (spriteIndex >= register - 1 && spriteIndex <= register + 1) {
                lit
            } else {
                dark
            }
        }
        input.map { CommandSet(Command.valueOf(it[0].uppercase()), it.getOrElse(1) { "0" }.toInt()) }.forEach {
            cpu.execute(it, callback)
        }
        return output
    }
}

fun main() {
    val obj = Day10()
    val input = obj.getInputs()
//    println(obj.solve1(input))
    println(obj.solve2(input).joinToString("\n"))
}
