import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day4Test {
    @Test
    fun solve1() {
        val obj = Day4(Resource.resourceAsListOfString("day4/sample.txt"))
        assertEquals(2, obj.solve1())
    }

    @Test
    fun solve2() {
        val obj = Day4(Resource.resourceAsListOfString("day4/sample.txt"))
        assertEquals(4, obj.solve2())
    }
}
