package espressoLogcat

import java.io.BufferedReader
import java.io.InputStreamReader

interface LogcatExecutor {
    fun getLogcat(
        tagPriority: MutableList<String>,
        constructedOptions: MutableList<String>,
        bufferedReaderSize: Int,
        vararg options: String
    ): MutableList<String>

    fun clearLogcat() {}
}

class LogcatExecutorImpl: LogcatExecutor {
    override fun getLogcat(
        tagPriority: MutableList<String>,
        constructedOptions: MutableList<String>,
        bufferedReaderSize: Int,
        vararg options: String
    ): MutableList<String> {
        try {
            val logcat = Runtime.getRuntime()
                .exec(
                    arrayOf(
                        "logcat",
                        "-d",
                        "-v",
                        "threadtime",
                        "-v",
                        "year",
                        *tagPriority.toTypedArray(),
                        *constructedOptions.toTypedArray(),
                        *options
                    )
                )
            val br = BufferedReader(InputStreamReader(logcat.inputStream), bufferedReaderSize)
            val lines = mutableListOf<String>()
            br.forEachLine {
                if (!it.startsWith("----")) {
                    lines.add(it)
                }
            }
            return lines
        } catch (exception: Exception) {
            throw exception
        }
    }

    override fun clearLogcat() {
        try {
            Runtime.getRuntime().exec(arrayOf("logcat", "-c"))
        } catch (exception: Exception) {
            throw exception
        }
    }
}