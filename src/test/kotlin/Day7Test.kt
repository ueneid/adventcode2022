import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day7Test {
    @Test
    fun solve1() {
        val obj = Day7(Resource.resourceAsListOfString("day7/sample.txt"))
        assertEquals(95437, obj.solve1())
    }

    @Test
    fun solve2() {
        val obj = Day7(Resource.resourceAsListOfString("day7/sample.txt"))
        assertEquals(24933642, obj.solve2())
    }
}
