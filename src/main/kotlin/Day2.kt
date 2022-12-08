class Day2 {
    fun getInputs(): ArrayList<Pair<String, String>> {
        val matches = ArrayList<Pair<String, String>>()
        try {
            while (true) {
                val (first, second) = IO.readStrings()
                matches.add(Pair(first, second))
            }
        } catch (e: RuntimeException) { // EOF
            println(e.toString())
        } catch (e: Exception) {
            println(e.toString())
        }
        return matches
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

    fun solve1(matches: ArrayList<Pair<String, String>>): Int {
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

    fun solve2(matches: ArrayList<Pair<String, String>>): Int {
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
    val obj = Day2()
    println(obj.solve2(obj.getInputs()))
}
