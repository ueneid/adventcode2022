import kotlin.math.abs
import kotlin.math.sign

class Day9(inputs: List<String>) {
    val commands = parse(inputs)

    private fun parse(inputs: List<String>): List<Pair<String, Int>> {
        return inputs.asSequence().map { line ->
            val commands = line.split(" ")
            Pair(commands[0], commands[1].toInt())
        }.toList()
    }

    enum class Direction {
        L, R, U, D
    }

    class Rope(
        knotsNumber: Int,
        n: Int,
        m: Int,
        private val startCell: Cell,
    ) {
        private var knots = mutableListOf<Cell>()
        private var grid = mutableListOf<MutableList<Char>>()
        private var tailVisited = mutableSetOf<Cell>()

        init {
            repeat(knotsNumber) {
                knots.add(startCell.copy())
            }
            for (i in 0 until n) {
                val row = ".".repeat(m)
                grid.add(row.toMutableList())
            }
//            println("== Initial State ==")
//            println(this)
            tailVisited.add(knots.last().copy())
        }

        override fun toString(): String {
            val newGrid = grid.map { row -> row.map { it }.toMutableList() }.toMutableList()
            for (i in 0 until knots.size) {
                if (newGrid[knots[i].y][knots[i].x] == '.') {
                    newGrid[knots[i].y][knots[i].x] = i.toString().single()
                }
            }
            return newGrid.joinToString("\n") { it.joinToString("") } + "\n"
        }

        // I gave up the part2 and then referring to https://gist.github.com/xzec/76bcfd20688ff34407c95c2f4e8acb8c
        fun move(direction: Direction, times: Int) {
            val vec = when (direction) {
                Direction.L -> Vector(-1, 0)
                Direction.R -> Vector(1, 0)
                Direction.U -> Vector(0, -1)
                Direction.D -> Vector(0, 1)
            }

//            println("== ${direction.toString()}$times ==")
            val head = knots.first()
            val tail = knots.last()
            repeat(times) {
                head.move(vec)
                for (i in 1 until knots.size) {
                    val knot = knots[i]
                    val prev = knots[i - 1]
                    if (knot.isAdjacent(prev)) {
                        break
                    }
                    val distance = prev - knot
                    knot.move(distance.sign())
                }
                tailVisited.add(tail.copy())
//                println(this)
            }
        }

        fun getTailVisitedNum(): Int {
            return tailVisited.size
        }
    }

    data class Vector(val x: Int, val y: Int) {
        operator fun plus(vec: Vector): Vector {
            return Vector(x + vec.x, y + vec.y)
        }

        operator fun times(i: Int): Vector {
            return Vector(x * i, y * i)
        }

        fun sign(): Vector {
            return Vector(x.sign, y.sign)
        }
    }

    data class Cell(var x: Int, var y: Int) {
        fun move(vec: Vector) {
            x += vec.x
            y += vec.y
        }

        fun isAdjacent(cell: Cell): Boolean {
            return abs(x - cell.x) in 0..1 && abs(y - cell.y) in 0..1
        }

        operator fun minus(cell: Cell): Vector {
            return Vector(x - cell.x, y - cell.y)
        }
    }

    fun solve1(): Int {
        val rope = Rope(2, 6, 6, Cell(0, 5))
        for ((direction, times) in commands) {
            rope.move(Direction.valueOf(direction), times)
        }
        return rope.getTailVisitedNum()
    }

    fun solve2(): Int {
//        val rope = Rope(10, 6, 6, Cell(0, 5))
        val rope = Rope(10, 22, 27, Cell(12, 15))
        for ((direction, times) in commands) {
            rope.move(Direction.valueOf(direction), times)
        }
        return rope.getTailVisitedNum()
    }
}

fun main() {
    val obj = Day9(Resource.resourceAsListOfString("day9/input.txt"))
    println(obj.solve1())
    println(obj.solve2())
}
