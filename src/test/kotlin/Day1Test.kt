

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.spy

internal class Day1Test {

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
            1000
            2000
            3000
            
            4000
            
            5000
            6000
            
            7000
            8000
            9000
            
            10000
        """.trimIndent()
        input.inputln(tmp)
        val obj = Day1()
        val inputs = obj.getInputs()
        assertEquals(5, inputs.size)
        for ((i, v) in inputs.withIndex()) {
            when (i) {
                0 -> assertEquals(arrayListOf(1000, 2000, 3000), v)
                1 -> assertEquals(arrayListOf(4000), v)
                2 -> assertEquals(arrayListOf(5000, 6000), v)
                3 -> assertEquals(arrayListOf(7000, 8000, 9000), v)
                4 -> assertEquals(arrayListOf(10000), v)
            }
        }
    }

    @Test
    fun solve1() {
        val obj = spy(Day1())
        val expected =
            arrayListOf<ArrayList<Int>>(
                arrayListOf(1000, 2000, 3000), arrayListOf(4000), arrayListOf(5000, 6000),
                arrayListOf(7000, 8000, 9000), arrayListOf(10000)
            )
        doReturn(expected).`when`(obj).getInputs()
        assertEquals(24000, obj.solve1())
    }

    @Test
    fun solve2() {
        val obj = spy(Day1())
        val expected =
            arrayListOf<ArrayList<Int>>(
                arrayListOf(1000, 2000, 3000), arrayListOf(4000), arrayListOf(5000, 6000),
                arrayListOf(7000, 8000, 9000), arrayListOf(10000)
            )
        doReturn(expected).`when`(obj).getInputs()
        assertEquals(45000, obj.solve2())
    }
}
