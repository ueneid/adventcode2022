class Day7 {

    data class Command(val value: Int, val from: Int, val to: Int)

    data class Input(val stacks: List<ArrayDeque<Char>>, val commands: List<Command>)

    fun getInputs(): ArrayDeque<String> {
        val inputs = ArrayDeque<String>()
        try {
            while (true) {
                val line = IO.readStr()
                inputs.add(line)
            }
        } catch (e: RuntimeException) {
            e.printStackTrace()
        }

        return inputs
    }

    enum class FileType(val type: String) {
        FILE("file"), DIR("DIR"), DUMMY("DUMMY")
    }

    class FileTree(val type: FileType, val name: String, var size: Int, val children: MutableList<FileTree>) {
        constructor(type: Day7.FileType) : this(type, "", 0, mutableListOf())
        constructor(type: Day7.FileType, name: String) : this(type, name, 0, mutableListOf())
        constructor(type: Day7.FileType, name: String, size: Int) : this(type, name, size, mutableListOf())

        fun calcSize(): Int {
            return when (type) {
                FileType.DIR, FileType.DUMMY -> {
                    size = children.sumOf { it.calcSize() }
                    size
                }

                FileType.FILE -> size
            }
        }
    }

    fun parse(input: List<String>): FileTree {
        val fileTree = FileTree(FileType.DUMMY)
        val dirStack = ArrayDeque<FileTree>()
        dirStack.add(fileTree)
        var cur = fileTree
        for (line in input) {
            val splitted = line.split(" ")
            when {
                splitted[0] == "$" -> {
                    when (splitted[1]) {
                        "cd" -> {
                            cur = if (splitted[2] == "..") {
                                dirStack.removeLast()
                                dirStack.last()
                            } else {
                                val d = FileTree(FileType.DIR, splitted[2])
                                dirStack.last().children.add((d))
                                dirStack.add(d)
                                dirStack.last()
                            }
                        }
                    }
                }

                splitted[0].matches("""^\d+$""".toRegex()) -> {
                    var f = FileTree(FileType.FILE, splitted[1], splitted[0].toInt())
                    cur.children.add(f)
                }
            }
        }
        return fileTree.children[0]
    }

    fun solve1(input: List<String>): Int {
        val root = parse(input)
        root.calcSize()
        val q = ArrayDeque<FileTree>()
        val lessThan10000 = mutableListOf<FileTree>()
        q.add(root)
        while (q.isNotEmpty()) {
            val ft = q.removeFirst()
            if (ft.type == FileType.DIR) {
                if (ft.size < 100_000) {
                    lessThan10000.add(ft)
                }
                ft.children.forEach {
                    if (it.type == FileType.DIR) {
                        q.add(it)
                    }
                }
            }
        }
        return lessThan10000.sumOf { it.size }
    }

    fun solve2(input: List<String>): Int {
        val root = parse(input)
        root.calcSize()
        val requiredUnusedSpace = 30_000_000 - (70_000_000 - root.size)
        val q = ArrayDeque<FileTree>()
        var candidate = FileTree(FileType.DUMMY, "dummy", 70_000_000)
        q.add(root)
        while (q.isNotEmpty()) {
            val ft = q.removeFirst()
            if (ft.type == FileType.DIR) {
                if (ft.size > requiredUnusedSpace && ft.size - requiredUnusedSpace < candidate.size - requiredUnusedSpace) {
                    candidate = ft
                }
                ft.children.forEach {
                    if (it.type == FileType.DIR) {
                        q.add(it)
                    }
                }
            }
        }
        return candidate.size
    }
}

fun main() {
    val obj = Day7()
    val input = obj.getInputs()
//    println(obj.solve1(input))
    println(obj.solve2(input))
}
