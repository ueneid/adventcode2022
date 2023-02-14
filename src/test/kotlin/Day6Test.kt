import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.*
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class Day6Test {
    private fun _generateInput(expected: List<Int>): Stream<Arguments> {
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
        return _generateInput(listOf(7, 5, 6, 10, 11))
    }

    private fun generateInput2(): Stream<Arguments> {
        return _generateInput(listOf(19, 23, 23, 29, 26))
    }

    @ParameterizedTest
    @MethodSource("generateInput1")
    fun solve1(input: String, expected: Int) {
        val obj = Day6(input)
        assertEquals(expected, obj.solve1())
    }

    @ParameterizedTest
    @MethodSource("generateInput2")
    fun solve2(input: String, expected: Int) {
        val obj = Day6(input)
        assertEquals(expected, obj.solve2())
    }
}
