import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day5Test {
    @Test
    fun solve1() {
        val obj = Day5(Resource.resourceAsText("day5/sample.txt"))
        assertEquals("CMZ", obj.solve1())
    }

    @Test
    fun solve2() {
        val obj = Day5(Resource.resourceAsText("day5/sample.txt"))
        assertEquals("MCD", obj.solve2())
    }
}
