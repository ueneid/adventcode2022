import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.*
import kotlin.collections.ArrayDeque

internal class Day5Test {

    private val input = StandardInputStream()
    private val output = StandardOutputStream()

    @BeforeEach
    fun setUp() {
        System.setIn(input)
        System.setOut(output)
    }

    @AfterEach
    fun tearDown() {
        System.setIn(null)
        System.setOut(null)
    }

    @Test
    fun getInputs() {
        val tmp = """
                [D]    
            [N] [C]    
            [Z] [M] [P]
             1   2   3 
            
            move 1 from 2 to 1
            move 3 from 1 to 3
            move 2 from 2 to 1
            move 1 from 1 to 2
        """.trimIndent()
        input.inputln(tmp)
        val obj = Day5()
        val (stacks, commands) = obj.getInputs()
        assertEquals(ArrayDeque(listOf('Z', 'N')), stacks[0])
        assertEquals(ArrayDeque(listOf('M', 'C', 'D')), stacks[1].toList())
        assertEquals(ArrayDeque(listOf('P')), stacks[2].toList())
        assertEquals(Day5.Command(1, 2, 1), commands[0])
        assertEquals(Day5.Command(3, 1, 3), commands[1])
        assertEquals(Day5.Command(2, 2, 1), commands[2])
        assertEquals(Day5.Command(1, 1, 2), commands[3])
    }

    @Test
    fun solve1() {
        val obj = Day5()
        val stacks = listOf(
            ArrayDeque(listOf('Z', 'N')),
            ArrayDeque(listOf('M', 'C', 'D')),
            ArrayDeque(listOf('P')),
        )
        val commands = listOf(
            Day5.Command(1, 2, 1),
            Day5.Command(3, 1, 3),
            Day5.Command(2, 2, 1),
            Day5.Command(1, 1, 2),
        )
        assertEquals("CMZ", obj.solve1(stacks, commands))
    }

    @Test
    fun solve2() {
        val obj = Day5()
        val stacks = listOf(
            ArrayDeque(listOf('Z', 'N')),
            ArrayDeque(listOf('M', 'C', 'D')),
            ArrayDeque(listOf('P')),
        )
        val commands = listOf(
            Day5.Command(1, 2, 1),
            Day5.Command(3, 1, 3),
            Day5.Command(2, 2, 1),
            Day5.Command(1, 1, 2),
        )
        assertEquals("MCD", obj.solve2(stacks, commands))}
}
