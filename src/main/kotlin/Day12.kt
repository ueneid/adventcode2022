import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.collections.set

typealias Cell = Pair<Int, Int>

class Day12(inputs: List<String>) {
    companion object {
        private val offsets = listOf<Pair<Int, Int>>(
            Pair(0, 1), Pair(0, -1), Pair(1, 0), Pair(-1, 0)
        )
        private var charValue = ('a'..'z').toList().associateWith { (it - 96).code }
    }

    private val matrix = parse(inputs)
    private val height = matrix.size
    private val width = matrix[0].size
    private lateinit var sPos: Cell
    private lateinit var ePos: Cell

    private fun parse(inputs: List<String>): List<List<Char>> {
        val matrix = inputs.mapIndexed { y, line ->
            line.toList().mapIndexed { x, c ->
                when (c) {
                    'S' -> {
                        sPos = Pair(x, y)
                        'a'
                    }

                    'E' -> {
                        ePos = Pair(x, y)
                        'z'
                    }

                    else -> c
                }
            }
        }
        return matrix
    }

    private fun getAdjacentCells(cell: Cell, visited: Map<Cell, Int>): List<Cell> {
        return offsets
            .asSequence()
            .map { Pair(cell.first + it.first, cell.second + it.second) }
            .filter { (nextX, nextY) -> nextY in 0 until height && nextX in 0 until width }
            .filter { !visited.contains(it) }
            .filter { (nextX, nextY) ->
                charValue[matrix[nextY][nextX]]!! <= charValue[matrix[cell.second][cell.first]]!! + 1
            }.toList()
    }

    private fun calcMinStep(start: Cell): Int {
        val q = ArrayDeque(listOf(start))
        val visited = mutableMapOf<Cell, Int>(start to 0)
        while (q.isNotEmpty()) {
            val cur = q.removeFirst()
            if (cur == ePos) {
                break
            }
            getAdjacentCells(cur, visited).forEach {
                visited[it] = visited.getOrDefault(cur, 0) + 1
                q.addLast(it)
            }
        }
        return visited.getOrDefault(ePos, Int.MAX_VALUE)
    }

    fun solve1(): Int {
        return calcMinStep(sPos)
    }

    private fun findA(): List<Cell> {
        return matrix
            .asSequence()
            .flatMapIndexed { y, chars ->
                chars
                    .mapIndexed { x, c -> Pair(Cell(x, y), c) }
                    .filter { it.second == 'a' }
                    .map { it.first }
            }.toList()
    }

    fun solve2(): Int {
        val buf = PriorityQueue<Int>()
        findA().asSequence().forEach { buf.add(calcMinStep(it)) }
        return buf.poll()
    }
}

fun main() {
    val obj = Day12(Resource.resourceAsListOfString("day12/input.txt"))
    println(obj.solve1())
    println(obj.solve2())
}
