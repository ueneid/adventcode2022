import java.io.InputStream

class StandardInputStream : InputStream() {
    private val sb = StringBuilder()
    private val lf = System.getProperty("line.separator")

    fun inputln(str: String?) {
        sb.append(str).append(lf)
    }

    override fun read(): Int {
        if (sb.isEmpty()) return -1
        val result = sb[0].code
        sb.deleteCharAt(0)
        return result
    }
}
