import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day2Test {
    @Test
    fun solve1() {
        val inputs = Resource.resourceAsListOfString("day2/sample.txt")
        val obj = Day2(inputs)
        assertEquals(15, obj.solve1())
    }

    @Test
    fun solve2() {
        val inputs = Resource.resourceAsListOfString("day2/sample.txt")
        val obj = Day2(inputs)
        assertEquals(12, obj.solve2())
    }
}
