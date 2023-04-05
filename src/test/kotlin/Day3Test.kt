import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day3Test {
    @Test
    fun solve1() {
        val obj = Day3(Resource.resourceAsListOfString("day3/sample.txt"))
        assertEquals(157, obj.solve1())
    }

    @Test
    fun solve2() {
        val obj = Day3(Resource.resourceAsListOfString("day3/sample.txt"))
        assertEquals(70, obj.solve2())
    }
}
