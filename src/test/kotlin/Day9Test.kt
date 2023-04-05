import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import java.util.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class Day9Test {
    private val inputList = Resource.resourceAsListOfBunchOfString("day9/sample.txt")
    @Test
    fun solve1() {
        val obj = Day9(inputList[0].split("\n"))
        assertEquals(13, obj.solve1())
    }

    @Test
    fun solve2() {
        val obj = Day9(inputList[1].split("\n"))
        assertEquals(36, obj.solve2())
    }
}
