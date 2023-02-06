class Day11 {

    class Monkey(
        var itemList: MutableList<Int>,
        var operationFunction: (Int) -> Int,
        var divideFunction: (Int) -> Int,
        var next: List<Int>,
    ) {
        var inspectCount = 0
    }

    fun getInputs(): List<Monkey> {
        val input = mutableListOf<Monkey>()
        var count = 0
        val operationFunction = { op: Char, value: String ->
            { old: Int ->
                val v = if (value == "old") old else value.toInt()
                when (op) {
                    '+' -> old + v
                    '-' -> old - v
                    '*' -> old * v
                    '/' -> old / v
                    else -> old
                }
            }
        }
        val devideFunction = { value: Int ->
            { level: Int -> level % value }
        }
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
                val monkey = Monkey(itemList, operationFunction(op[0], value), devideFunction(divValue), listOf(trueMonkey, falseMonkey))
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
//                println("Monkey $i")
                while (true) {
                    if (monkey.itemList.isEmpty()) {
                        break
                    }
                    val item = monkey.itemList.removeFirst()
                    monkey.inspectCount++
                    val multiplied = monkey.operationFunction(item)
                    val bored = multiplied / 3
//                    println("  Monkey inspects an item with a worry level of $item")
//                    println("    Worry level is multiplied to $multiplied")
//                    println("    Monkey level is divided by 3 to $bored")
                    if (monkey.divideFunction(bored) == 0) {
//                        println("    Current worry level is divisible, throw to monkey ${monkey.next[0]}")
                        input[monkey.next[0]].itemList.add(bored)
                    } else {
//                        println("    Current worry level is not divisible, throw to monkey ${monkey.next[1]}")
                        input[monkey.next[1]].itemList.add(bored)
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
