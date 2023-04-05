import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day1Test {
    @Test
    fun solve1() {
        val obj = Day1(Resource.resourceAsListOfBunchOfString("day1/sample.txt"))
        assertEquals(24000, obj.solve1())
    }

    @Test
    fun solve2() {
        val obj = Day1(Resource.resourceAsListOfBunchOfString("day1/sample.txt"))
        assertEquals(45000, obj.solve2())
    }
}
