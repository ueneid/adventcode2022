import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import java.util.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class Day8Test {
    @Test
    fun solve1() {
        val obj = Day8(Resource.resourceAsListOfString("day8/sample.txt"))
        assertEquals(21, obj.solve1())
    }

    @Test
    fun solve2() {
        val obj = Day8(Resource.resourceAsListOfString("day8/sample.txt"))
        assertEquals(8, obj.solve2())
    }
}
