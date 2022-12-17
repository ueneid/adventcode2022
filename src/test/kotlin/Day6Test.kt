import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource
import java.util.*
import java.util.stream.Stream
import kotlin.collections.ArrayDeque

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class Day6Test {

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

    fun _generateInput(expected: List<Int>): Stream<Arguments> {
        val strings = listOf<String>(
        "mjqjpqmgbljsphdztnvjfqwrcgsmlb",
        "bvwbjplbgvbhsrlpgdmjqwftvncz",
        "nppdvjthqldpwncqszvftbrmjlhg",
        "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg",
        "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw",
        )
        return strings.zip(expected).map { (str, exp) ->
            Arguments.arguments(str, exp)
        }.stream()
    }

    private fun generateInput1(): Stream<Arguments> {
        return _generateInput(listOf(7,5,6,10,11))
    }

    private fun generateInput2(): Stream<Arguments> {
        return _generateInput(listOf(19,23,23,29,26))
    }

    @ParameterizedTest
    @MethodSource("generateInput1")
    fun solve1(input: String, expected: Int) {
        val obj = Day6()
        assertEquals(expected, obj.solve1(input))
    }

    @ParameterizedTest
    @MethodSource("generateInput2")
    fun solve2(input: String, expected: Int) {
        val obj = Day6()
        assertEquals(expected, obj.solve2(input))
    }
}
