import java.io.BufferedReader
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.io.StringReader

class StandardOutputStream : PrintStream(ByteArrayOutputStream()) {
    private var br = BufferedReader(StringReader(""))

    //出力が一行のみでいい場合
    fun readLine(): String {
        br = BufferedReader(StringReader(out.toString()))
        (out as ByteArrayOutputStream).reset()
        return br.readLine()
    }

    //複数行に渡る出力を得たい場合
    fun readLines(): List<String> {
        val lines = mutableListOf<String>()
        br = BufferedReader(StringReader(out.toString()))
        (out as ByteArrayOutputStream).reset()
        do {
            val result = br.readLine()
            if (result != null) {
                lines.add(result)
            }
        } while (result != null)
        return lines
    }
}
