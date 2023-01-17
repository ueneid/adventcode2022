import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.*
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class Day8Test {

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

    private fun _generateInput(expected: Int): Stream<Arguments> {
        val input = """
            30373
            25512
            65332
            33549
            35390
        """.trimIndent().split("\n").map {
            it.toList().map { c -> c.digitToInt() }
        }
        return Stream.of(Arguments.arguments(input, expected))
    }

    private fun generateInput1(): Stream<Arguments> {
        return _generateInput(21)
    }

    private fun generateInput2(): Stream<Arguments> {
        return _generateInput(8)
    }

    @ParameterizedTest
    @MethodSource("generateInput1")
    fun solve1(input: List<List<Int>>, expected: Int) {
        val obj = Day8()
        assertEquals(expected, obj.solve1(input))
    }

    @ParameterizedTest
    @MethodSource("generateInput2")
    fun solve2(input: List<List<Int>>, expected: Int) {
        val obj = Day8()
        assertEquals(expected, obj.solve2(input))
    }
}
