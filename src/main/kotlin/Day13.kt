class Day13(inputs: List<String>) {
    private sealed class Packet : Comparable<Packet> {
        companion object {
            fun of(input: String): Packet {
                var listInput = mutableListOf<String>()
                var tempNumber = ""
                for (c in input) {
                    if (c.isDigit()) {
                        tempNumber += c
                        continue
                    }
                    if (tempNumber.isNotEmpty()) {
                        listInput.add(tempNumber)
                        tempNumber = ""
                    }
                    if (c == ',') {
                        continue
                    }
                    listInput.add(c.toString())
                }
                return of(listInput.iterator())
            }

            private fun of(input: Iterator<String>): Packet {
                val packets = mutableListOf<Packet>()
                while (input.hasNext()) {
                    when (val symbol = input.next()) {
                        "]" -> return ListPacket(packets)
                        "[" -> packets.add(of(input))
                        else -> packets.add(IntPacket(symbol.toInt()))
                    }
                }
                return ListPacket(packets)
            }
        }
    }

    private class IntPacket(val amount: Int) : Packet() {
        fun asList(): Packet = ListPacket(listOf(this))

        override fun compareTo(other: Packet): Int =
            when (other) {
                is IntPacket -> amount.compareTo(other.amount)
                is ListPacket -> asList().compareTo(other)
            }
    }

    private class ListPacket(val subPackets: List<Packet>) : Packet() {
        override fun compareTo(other: Packet): Int =
            when (other) {
                is IntPacket -> compareTo(other.asList())
                is ListPacket -> subPackets.zip(other.subPackets)
                    .map { it.first.compareTo(it.second) }
                    .firstOrNull { it != 0 } ?: subPackets.size.compareTo(other.subPackets.size)
            }
    }

    private val packets = inputs.filter { it.isNotBlank() }.map { Packet.of(it) }

    fun solve1(): Int {
        return packets.chunked(2).mapIndexed { index, pair ->
            if (pair.first() < pair.last()) index + 1 else 0
        }.sum()
    }

    fun solve2(): Int {
        val divider1 = Packet.of("[[2]]")
        val divider2 = Packet.of("[[6]]")
        var key = 1
        (packets + divider1 + divider2).sorted().forEachIndexed { index, packet ->
            if (packet == divider1 || packet == divider2) {
                key *= index + 1
            }
        }
        return key
    }
}

fun main() {
    val obj = Day13(Resource.resourceAsListOfString("day13/input.txt"))
    println(obj.solve1())
    println(obj.solve2())
}
