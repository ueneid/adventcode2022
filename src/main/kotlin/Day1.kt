class Day1 {
    fun getInputs(): ArrayList<ArrayList<Int>> {
        var inputs = ArrayList<ArrayList<Int>>()
        var elfCalories = ArrayList<Int>()
        try {
            while (true) {
                val calorie = IO.readStr()
                if (calorie.isNotEmpty()) {
                    elfCalories.add(calorie.toInt())
                } else {
                    inputs.add(elfCalories)
                    elfCalories = ArrayList<Int>()
                }
            }
        } catch (e: RuntimeException) { // EOF
            inputs.add(elfCalories)
            println(e.toString())
        } catch (e: Exception) {
            println(e.toString())
        }
        return inputs
    }

    fun solve1(): Int {
        val inputs = getInputs()
        return inputs.map { it.sum() }.max()
    }

    fun solve2(): Int {
        val inputs = getInputs()
        return inputs.map { it.sum() }.sortedDescending().take(3).sum()
    }
}

fun main() {
    println(Day1().solve2())
}
