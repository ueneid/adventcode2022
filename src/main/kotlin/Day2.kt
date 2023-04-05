class Day2(inputs: List<String>) {
    private val matches = parse(inputs)

    private fun parse(inputs: List<String>): List<Pair<String, String>> {
        return inputs.map { it.split(" ").toPair() }
    }

    enum class Choice(s: String, val point: Int) {
        A("Rock", 1) {
            override fun judgeAgainst(c: Choice?): Result {
                return when (c) {
                    A -> Result.DRAW
                    C -> Result.WIN
                    else -> Result.LOSE
                }
            }

            override fun getChoiceByResult(s: Result): Choice {
                return when (s) {
                    Result.WIN -> B
                    Result.DRAW -> A
                    Result.LOSE -> C
                }
            }
        },
        B("Paper", 2) {
            override fun judgeAgainst(c: Choice?): Result {
                return when (c) {
                    B -> Result.DRAW
                    A -> Result.WIN
                    else -> Result.LOSE
                }
            }

            override fun getChoiceByResult(s: Result): Choice {
                return when (s) {
                    Result.WIN -> C
                    Result.DRAW -> B
                    Result.LOSE -> A
                }
            }
        },
        C("Scissors", 3) {
            override fun judgeAgainst(c: Choice?): Result {
                return when (c) {
                    C -> Result.DRAW
                    B -> Result.WIN
                    else -> Result.LOSE
                }
            }

            override fun getChoiceByResult(s: Result): Choice {
                return when (s) {
                    Result.WIN -> A
                    Result.DRAW -> C
                    Result.LOSE -> B
                }
            }
        };

        abstract fun judgeAgainst(c: Choice?): Result
        abstract fun getChoiceByResult(s: Result): Choice
    }

    enum class Result(val point: Int) {
        WIN(6), LOSE(0), DRAW(3);
    }

    fun solve1(): Int {
        val choiceMap = mapOf<String, Choice>(
            "X" to Choice.A, "Y" to Choice.B, "Z" to Choice.C
        )
        var score = 0
        for ((a, b) in matches) {
            val firstChoice = Choice.valueOf(a)
            val secondChoice = choiceMap[b]
            val ret = firstChoice.judgeAgainst(secondChoice)
            score += ret.point + (secondChoice?.point ?: 0)
        }
        return score
    }

    fun solve2(): Int {
        var score = 0
        val retMap = mapOf<String, Result>(
            "X" to Result.LOSE, "Y" to Result.DRAW, "Z" to Result.WIN
        )
        for ((a, b) in matches) {
            val firstChoice = Choice.valueOf(a)
            val ret = retMap.getOrDefault(b, Result.LOSE)
            score += ret.point + firstChoice.getChoiceByResult(ret).point
        }
        return score
    }
}

fun main() {
    val inputs = Resource.resourceAsListOfString("day2/input.txt")
    val obj = Day2(inputs)
    println(obj.solve1())
    println(obj.solve2())
}
