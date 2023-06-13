import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import java.util.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class Day12Test {
    @Test
    fun solve1() {
        val obj = Day12(Resource.resourceAsListOfString("day12/sample.txt"))
        assertEquals(31, obj.solve1())
    }

    @Test
    fun solve2() {
        val obj = Day12(Resource.resourceAsListOfString("day12/sample.txt"))
        assertEquals(29, obj.solve2())

    }
}
