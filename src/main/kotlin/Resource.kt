import java.io.File
import java.net.URI

internal object Resource {
    fun resourceAsText(fileName: String): String =
        File(fileName.toURI()).readText().trim()

    fun resourceAsListOfString(fileName: String): List<String> =
        File(fileName.toURI()).readLines()

    fun resourceAsListOfBunchOfString(fileName: String, separator: String = "\n\n"): List<String> =
        resourceAsText(fileName).split(separator)

    fun resourceAsListOfInt(fileName: String): List<Int> =
        resourceAsListOfString(fileName).map { it.toInt() }

    private fun String.toURI(): URI =
        Resource.javaClass.classLoader.getResource(this)?.toURI()
            ?: throw IllegalArgumentException("Cannot find Resource: $this")
}
