class Day11(inputs: List<String>) {
    private val monkeys = parse(inputs)

    class Monkey(
        var itemList: ArrayDeque<Long>,
        private var operationPair: Pair<String, String>,
        var divisible: Long,
        private var destMonkeys: List<Int>,
    ) {
        var inspectCount = 0L

        fun calcNewWorryLevel(worryLevel: Long): Long {
            val (op, opValue) = operationPair
            val v = if (opValue == "old") worryLevel else opValue.toLong()
            return when (op) {
                "+" -> worryLevel + v
                "-" -> worryLevel - v
                "*" -> worryLevel * v
                "/" -> worryLevel / v
                else -> worryLevel
            }
        }

        private fun isDivisible(worryLevel: Long): Boolean {
            return worryLevel % divisible == 0L
        }

        fun getNextMonkeyIndex(worryLevel: Long): Int {
            return if (this.isDivisible(worryLevel)) {
                this.destMonkeys[0]
            } else {
                this.destMonkeys[1]
            }
        }
    }

    private fun parse(inputs: List<String>): List<Monkey> {
        val pat = """
Monkey \d+:
  Starting items: ([0-9, ]+)
  Operation: new = old (.+) (\d+|old)
  Test: divisible by (\d+)
    If true: throw to monkey (\d+)
    If false: throw to monkey (\d+)
        """.trim().toRegex()
        return inputs.map { bunch ->
            pat.matchEntire(bunch)!!.destructured.let {
                Monkey(
                    ArrayDeque(it.component1().split(", ").map(String::toLong)),
                    Pair(it.component2(), it.component3()),
                    it.component4().toLong(),
                    listOf(it.component5().toInt(), it.component6().toInt())
                )
            }
        }
    }

    fun solve1(): Long {
        repeat(20) {
            monkeys.forEach { monkey ->
                while (monkey.itemList.isNotEmpty()) {
                    monkey.inspectCount++
                    val item = monkey.itemList.removeFirst()
                    val newWorryLevel = monkey.calcNewWorryLevel(item) / 3
                    monkeys[monkey.getNextMonkeyIndex(newWorryLevel)].itemList.add(newWorryLevel)
                }
            }
        }
        return monkeys.map { it.inspectCount }.sorted().takeLast(2).reduce(Long::times)
    }

    fun solve2(): Long {
        // I totally don't understand the purpose of this part, so referred https://yonatankarp.com/advent-of-code-2022-day-11-kotlin-edition
        val commonMultiple = monkeys.map { monkey -> monkey.divisible }.reduce(Long::times)
        repeat(10000) {
            monkeys.forEach { monkey ->
                while (monkey.itemList.isNotEmpty()) {
                    monkey.inspectCount++
                    val item = monkey.itemList.removeFirst()
                    val newWorryLevel = monkey.calcNewWorryLevel(item) % commonMultiple
                    monkeys[monkey.getNextMonkeyIndex(newWorryLevel)].itemList.add(newWorryLevel)
                }
            }
        }
        return monkeys.map { it.inspectCount }.sorted().takeLast(2).reduce(Long::times)
    }
}

fun main() {
    val obj = Day11(Resource.resourceAsListOfBunchOfString("day11/input.txt"))
    println(obj.solve1())
    println(obj.solve2())
}
