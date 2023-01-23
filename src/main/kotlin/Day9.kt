import kotlin.math.abs

class Day9 {

    enum class Direction {
        L, R, U, D
    }

    class Rope(private var head: Cell, private var tail: Cell) {
        private val tailVisited = mutableSetOf<Cell>()

        init {
            this.tailVisited.add(tail.copy())
        }

        fun move(direction: Direction, times: Int) {
            val (diff, follow) = when (direction) {
                Direction.L -> Pair(-1, 0) to Pair(1, 0)
                Direction.R -> Pair(1, 0) to Pair(-1, 0)
                Direction.U -> Pair(0, -1) to Pair(0, 1)
                Direction.D -> Pair(0, 1) to Pair(0, -1)
            }
            repeat(times) {
                head.move(diff)
                if (!tail.isAdjacent(head)) {
                    tail.moveTo(Pair(head.x + follow.first, head.y + follow.second))
                }
                tailVisited.add(tail.copy())
            }
        }

        fun getTailVisitedNum(): Int {
            return tailVisited.size
        }
    }

    data class Cell(var x: Int, var y: Int) {
        fun move(value: Pair<Int, Int>) {
            this.x += value.first
            this.y += value.second
        }

        fun moveTo(value: Pair<Int, Int>) {
            this.x = value.first
            this.y = value.second
        }

        fun isAdjacent(cell: Cell): Boolean {
            return abs(this.x - cell.x) in 0..1 && abs(this.y - cell.y) in 0..1
        }
    }

    fun getInputs(): List<Pair<String, Int>> {
        val input = mutableListOf<Pair<String, Int>>()
        try {
            while (true) {
                input.add(IO.readStrings().zipWithNext { a, b -> Pair(a, b.toInt()) }.single())
            }
        } catch (e: RuntimeException) {
            e.printStackTrace()
        }
        return input
    }

    fun solve1(input: List<Pair<String, Int>>): Int {
        val rope = Rope(Cell(0, 5), Cell(0, 5))
        for ((direction, times) in input) {
            rope.move(Direction.valueOf(direction), times)
        }
        return rope.getTailVisitedNum()
    }

    fun solve2(input: List<Pair<String, Int>>): Int {
        return 1
    }
}

fun main() {
    val obj = Day9()
    val input = obj.getInputs()
    println(obj.solve1(input))
//    println(obj.solve2(input))
}
