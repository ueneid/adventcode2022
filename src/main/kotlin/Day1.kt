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

    fun solve1(inputs: ArrayList<ArrayList<Int>>): Int {
        return inputs.maxOfOrNull { it.sum() }!!
    }

    fun solve2(inputs: ArrayList<ArrayList<Int>>): Int {
        return inputs.map { it.sum() }.sortedDescending().take(3).sum()
    }
}

fun main() {
    val obj = Day1()
    val inputs = obj.getInputs()
    println(obj.solve2(inputs))
}
