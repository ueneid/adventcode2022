import kotlin.math.max

class Day8 {

    fun getInputs(): List<List<Int>> {
        val input = mutableListOf<List<Int>>()
        try {
            while (true) {
                input.add(IO.readStr().toList().map { it.digitToInt() })
            }
        } catch (e: RuntimeException) {
            e.printStackTrace()
        }
        return input
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

    fun solve1(input: List<List<Int>>): Int {
        val transposedInput = Util.transpose(input)
        var count = input.size * input[0].size
        for (i in 1 until input.size - 1) {
            for (j in 1 until input[i].size - 1) {
                if (!isVisible(input, i, j) && !isVisible(transposedInput, j, i)) {
                    count -= 1
                }
            }
        }
        return count
    }

    fun solve2(input: List<List<Int>>): Int {
        val transposedInput = Util.transpose(input)
        var maxScore = 0
        for (i in 1 until input.size - 1) {
            for (j in 1 until input[i].size - 1) {
                maxScore = max(maxScore, calcScenicScore(input, i, j) * calcScenicScore(transposedInput, j, i))
            }
        }
        return maxScore
    }
}

fun main() {
    val obj = Day8()
    val input = obj.getInputs()
//    println(obj.solve1(input))
    println(obj.solve2(input))
}
