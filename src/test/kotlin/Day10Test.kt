import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import java.util.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class Day10Test {
    @Test
    fun solve1() {
        val obj = Day10(Resource.resourceAsListOfString("day10/sample.txt"))
        assertEquals(13140, obj.solve1())
    }

    @Test
    fun solve2() {
        val obj = Day10(Resource.resourceAsListOfString("day10/sample.txt"))
        val output = """
            ##..##..##..##..##..##..##..##..##..##..
            ###...###...###...###...###...###...###.
            ####....####....####....####....####....
            #####.....#####.....#####.....#####.....
            ######......######......######......####
            #######.......#######.......#######.....
        """.trimIndent().split("\n")
        assertEquals(output, obj.solve2())
    }
}
