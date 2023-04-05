class Day7(inputs: List<String>) {
    private val root = parse(inputs).apply { calcSize() }

    enum class FileType(val type: String) {
        FILE("file"), DIR("DIR"), DUMMY("DUMMY")
    }

    private class FileTree(val type: FileType, val name: String, var size: Int, val children: MutableList<FileTree>) {
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

    private fun parse(input: List<String>): FileTree {
        val fileTree = FileTree(FileType.DUMMY)
        val dirStack = ArrayDeque<FileTree>()
        dirStack.add(fileTree)
        var cur = fileTree
        input
            .asSequence()
            .map { it.split(" ") }
            .filterNot { it[0] == "dir" || it[1] == "ls" }
            .forEach { commands ->
                if (commands[0] == "$" && commands[1] == "cd") {
                    if (commands[2] == "..") {
                        dirStack.removeLast()
                    } else {
                        val d = FileTree(FileType.DIR, commands[2])
                        dirStack.last().children.add(d)
                        dirStack.add(d)
                    }
                    cur = dirStack.last()
                } else if (commands[0].matches("""^\d+$""".toRegex())) {
                    cur.children.add(FileTree(FileType.FILE, commands[1], commands[0].toInt()))
                }
            }
        return fileTree.children[0]
    }

    fun solve1(): Int {
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

    fun solve2(): Int {
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
    val obj = Day7(Resource.resourceAsListOfString("day7/input.txt"))
    println(obj.solve1())
    println(obj.solve2())
}
