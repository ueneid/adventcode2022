import kotlin.math.max

class Day8(inputs: List<String>) {
    private val treeGrid = parse(inputs)

    private fun parse(inputs: List<String>): List<List<Int>> {
        return inputs.map { it.toList().map { height -> height.digitToInt() } }
    }

    private fun isVisible(input: List<List<Int>>, i: Int, j: Int): Boolean {
        return input[i].slice(0 until j).all { it < input[i][j] }
                || input[i].slice(j + 1 until input[i].size).all { it < input[i][j] }
    }

    private fun calcScenicScore(input: List<List<Int>>, i: Int, j: Int): Int {
        return listOf(j - 1 downTo 0, j + 1 until input[i].size).fold(1) { acc, range ->
            var c = 0
            for (el in input[i].slice(range)) {
                c++
                if (el >= input[i][j]) {
                    break
                }
            }
            acc * c
        }
    }

    fun solve1(): Int {
        val transposedGrid = treeGrid.transpose()
        var count = treeGrid.size * treeGrid[0].size
        for (i in 1 until treeGrid.size - 1) {
            for (j in 1 until treeGrid[i].size - 1) {
                if (!isVisible(treeGrid, i, j) && !isVisible(transposedGrid, j, i)) {
                    count -= 1
                }
            }
        }
        return count
    }

    fun solve2(): Int {
        val transposedGrid = treeGrid.transpose()
        var maxScore = 0
        for (i in 1 until treeGrid.size - 1) {
            for (j in 1 until treeGrid[i].size - 1) {
                maxScore = max(maxScore, calcScenicScore(treeGrid, i, j) * calcScenicScore(transposedGrid, j, i))
            }
        }
        return maxScore
    }
}

fun main() {
    val obj = Day8(Resource.resourceAsListOfString("day8/input.txt"))
    println(obj.solve1())
    println(obj.solve2())
}
