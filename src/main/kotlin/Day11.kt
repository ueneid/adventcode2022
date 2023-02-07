class Day11 {

    class Monkey(
        var itemList: MutableList<Int>,
        var operationPair: Pair<Char, String>,
        var divible: Int,
        var destMonkeys: List<Int>,
    ) {
        var inspectCount = 0

        fun operate(worryLevel: Int): Int {
            val (op, opValue) = operationPair
            val v = if (opValue == "old") worryLevel else opValue.toInt()
            return when (op) {
                '+' -> worryLevel + v
                '-' -> worryLevel - v
                '*' -> worryLevel * v
                '/' -> worryLevel / v
                else -> worryLevel
            }
        }

        fun isDivisible(worryLevel: Int): Boolean {
            return worryLevel % divible == 0
        }
    }

    fun getInputs(): List<Monkey> {
        val input = mutableListOf<Monkey>()
        val regexList = listOf<Regex>(
//            """Monkey (\d+)""".toRegex(),
            """Starting items: ([0-9, ]+)""".toRegex(),
            """Operation: new = old (.+) (\d+|old)""".toRegex(),
            """Test: divisible by (\d+)""".toRegex(),
            """If true: throw to monkey (\d+)""".toRegex(),
            """If false: throw to monkey (\d+)""".toRegex(),
        )
        try {
            while (true) {
                IO.readStr()
                val lines = listOf(
                    IO.readStr(), IO.readStr(), IO.readStr(), IO.readStr(), IO.readStr()
                ).map { it.trim() }
                val itemList = regexList[0].matchEntire(lines[0])?.groups?.get(1)?.value?.split(", ")?.map { it.toInt() }?.toMutableList()
                    ?: throw IllegalArgumentException()
                val (op, value) = regexList[1].matchEntire(lines[1])?.destructured
                    ?: throw IllegalArgumentException()
                val divValue = regexList[2].matchEntire(lines[2])?.groups?.get(1)?.value?.toInt()
                    ?: throw IllegalArgumentException()
                val trueMonkey = regexList[3].matchEntire(lines[3])?.groups?.get(1)?.value?.toInt()
                    ?: throw IllegalArgumentException()
                val falseMonkey = regexList[4].matchEntire(lines[4])?.groups?.get(1)?.value?.toInt()
                    ?: throw IllegalArgumentException()
                val monkey = Monkey(itemList, Pair(op[0], value), divValue, listOf(trueMonkey, falseMonkey))
                input.add(monkey)
                IO.readStr()
            }
        } catch (e: RuntimeException) {
            e.printStackTrace()
        }
        return input
    }

    fun solve1(input: List<Monkey>): Int {
        repeat(20) {
            for ((i, monkey) in input.withIndex()) {
                while (monkey.itemList.isNotEmpty()) {
                    val item = monkey.itemList.removeFirst()
                    monkey.inspectCount++
                    val multiplied = monkey.operate(item)
                    val bored = multiplied / 3
                    if (monkey.isDivisible(bored)) {
                        input[monkey.destMonkeys[0]].itemList.add(bored)
                    } else {
                        input[monkey.destMonkeys[1]].itemList.add(bored)
                    }
                }
            }
        }
        val top2 = input.sortedByDescending { it.inspectCount }.take(2).map { it.inspectCount }
        return top2[0] * top2[1]
    }

    fun solve2(input: List<Monkey>): Int {
        return 1
    }
}

fun main() {
    val obj = Day11()
    val input = obj.getInputs()
    println(obj.solve1(input))
//    println(obj.solve2(input).joinToString("\n"))
}
