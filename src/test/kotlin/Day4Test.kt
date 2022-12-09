import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class Day4Test {

    private val input = StandardInputStream()
    private val output = StandardOutputStream()

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
        val tmp = """
            2-4,6-8
            2-3,4-5
            5-7,7-9
            2-8,3-7
            6-6,4-6
            2-6,4-8
        """.trimIndent()
        input.inputln(tmp)
        val obj = Day4()
        val inputs = obj.getInputs()
        assertEquals(6, inputs.size)
        for ((i, l) in tmp.split("\n").withIndex()) {
            assertEquals(l, inputs[i])
        }
    }

    @Test
    fun solve1() {
        val obj = Day4()
        val ret = """
            2-4,6-8
            2-3,4-5
            5-7,7-9
            2-8,3-7
            6-6,4-6
            2-6,4-8
        """.trimIndent().split("\n")
        assertEquals(2, obj.solve1(ret))
    }

    @Test
    fun solve2() {
        val obj = Day4()
        val ret = """
            2-4,6-8
            2-3,4-5
            5-7,7-9
            2-8,3-7
            6-6,4-6
            2-6,4-8
        """.trimIndent().split("\n")
        assertEquals(4, obj.solve2(ret))
    }
}
