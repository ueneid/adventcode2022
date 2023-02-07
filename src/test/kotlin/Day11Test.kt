import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import java.util.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class Day11Test {

    private val input = StandardInputStream()
    private val output = StandardOutputStream()
    private val inputs = listOf<Day11.Monkey>(
        Day11.Monkey(mutableListOf(79, 98), Pair('*', "19"), 23, listOf(2, 3)),
        Day11.Monkey(mutableListOf(54, 65, 75, 74), Pair('+', "6"), 19, listOf(2, 0)),
        Day11.Monkey(mutableListOf(79, 60, 97), Pair('*', "old"), 13, listOf(1, 3)),
        Day11.Monkey(mutableListOf(74), Pair('+', "3"), 17, listOf(0, 1)),

    )

    @BeforeEach
    fun setUp() {
        System.setIn(input)
        System.setOut(output)
    }

    @AfterEach
    fun tearDown() {
        System.setIn(null)
        System.setOut(null)
    }

    @Test
    fun getInputs() {
        val lines = """
            Monkey 0:
              Starting items: 79, 98
              Operation: new = old * 19
              Test: divisible by 23
                If true: throw to monkey 2
                If false: throw to monkey 3
            
            Monkey 1:
              Starting items: 54, 65, 75, 74
              Operation: new = old + 6
              Test: divisible by 19
                If true: throw to monkey 2
                If false: throw to monkey 0
            
            Monkey 2:
              Starting items: 79, 60, 97
              Operation: new = old * old
              Test: divisible by 13
                If true: throw to monkey 1
                If false: throw to monkey 3
            
            Monkey 3:
              Starting items: 74
              Operation: new = old + 3
              Test: divisible by 17
                If true: throw to monkey 0
                If false: throw to monkey 1
        """.trimIndent()
        input.inputln(lines)
        val obj = Day11()
        val out = obj.getInputs()
        out.zip(inputs) { a, b ->
            assertEquals(a, b)
        }
    }

    @Test
    fun solve1() {
        val obj = Day11()
        assertEquals(10605, obj.solve1(inputs))
    }

    @Test
    fun solve2() {
        val obj = Day11()
        val output = """
            ##..##..##..##..##..##..##..##..##..##..
            ###...###...###...###...###...###...###.
            ####....####....####....####....####....
            #####.....#####.....#####.....#####.....
            ######......######......######......####
            #######.......#######.......#######.....
        """.trimIndent().split("\n")
        assertEquals(output, obj.solve2(inputs))
    }
}
