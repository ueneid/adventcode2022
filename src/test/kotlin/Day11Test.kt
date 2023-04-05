import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import java.util.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class Day11Test {
    @Test
    fun solve1() {
        val obj = Day11(Resource.resourceAsListOfBunchOfString("day11/sample.txt"))
        assertEquals(10605, obj.solve1())
    }

    @Test
    fun solve2() {
        val obj = Day11(Resource.resourceAsListOfBunchOfString("day11/sample.txt"))
        assertEquals(2713310158, obj.solve2())

    }
}
