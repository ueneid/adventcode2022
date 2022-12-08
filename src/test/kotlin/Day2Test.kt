import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.spy

internal class Day2Test {

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
            A Y
            B X
            C Z
        """.trimIndent()
        input.inputln(tmp)
        val obj = Day2()
        val matches = obj.getInputs()
        assertEquals(3, matches.size)
        assertEquals(Pair("A", "Y"), matches[0])
        assertEquals(Pair("B", "X"), matches[1])
        assertEquals(Pair("C", "Z"), matches[2])
    }

    @Test
    fun solve1() {
        val obj = Day2()
        val ret = arrayListOf<Pair<String, String>>(
            Pair("A", "Y"), Pair("B", "X"), Pair("C", "Z")
        )
        assertEquals(15, obj.solve1(ret))
    }

    @Test
    fun solve2() {
        val obj = Day2()
        val ret = arrayListOf<Pair<String, String>>(
            Pair("A", "Y"), Pair("B", "X"), Pair("C", "Z")
        )
        assertEquals(12, obj.solve2(ret))
    }
}
