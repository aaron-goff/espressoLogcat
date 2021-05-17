package espressoLogcat

import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Formatters {

    fun getLineData(data: String, dateTimeFormatter: DateTimeFormatter): LineData {
        val splitDataTwoSpaces = data.split("  ", limit = 3)
        val splitDataOneSpace = splitDataTwoSpaces[2].split(" ", limit = 4)
        val date = LocalDateTime.parse(splitDataTwoSpaces[0].replace(" ", "T")).format(dateTimeFormatter)
        val pid = splitDataTwoSpaces[1]
        val subPid = splitDataOneSpace[0]
        val level = splitDataOneSpace[1]
        val newTag = splitDataOneSpace[2].split(":")[0]
        val coreData = splitDataOneSpace[3]

        return LineData(date = date, level = level, tag = newTag, pid = pid, tid = subPid, coreData = coreData)
    }

    fun formatLineData(
        lineDataValues: MutableCollection<LineData>,
        outputFormat: OutputFormat,
        filename: String?
    ): MutableCollection<String> {
        val stringLineDataList = mutableListOf<String>()
        lineDataValues.forEach {
            when (outputFormat) {
                OutputFormat.BRIEF -> {
                    stringLineDataList.add("${it.level}/${it.tag}( ${it.pid}): ${it.coreData}")
                }
                OutputFormat.LONG -> {
                    stringLineDataList.add("[ ${it.date}  ${it.pid}: ${it.tid} ${it.level}/${it.tag} ]\n${it.coreData}")
                }
                OutputFormat.PROCESS -> {
                    stringLineDataList.add("${it.level}( ${it.pid}) ${it.coreData} (${it.tag})")
                }
                OutputFormat.RAW -> {
                    stringLineDataList.add(it.coreData)
                }
                OutputFormat.TAG -> {
                    stringLineDataList.add("${it.level}/${it.tag}: ${it.coreData}")
                }
                OutputFormat.THREAD -> {
                    stringLineDataList.add("${it.level}( ${it.pid}: ${it.tid}) ${it.coreData}")
                }
                OutputFormat.THREADTIME -> {
                    stringLineDataList.add("${it.date}  ${it.pid}  ${it.tid} ${it.level} ${it.tag}: ${it.coreData}")
                }
                OutputFormat.TIME -> {
                    stringLineDataList.add("${it.date} ${it.level}/${it.tag}( ${it.pid}): ${it.coreData}")
                }
            }
        }

        if (filename != null) {
            val file = File(filename)
            if (!file.exists()) {
                file.createNewFile()
            }

            file.bufferedWriter().use { out ->
               stringLineDataList.forEach {
                   out.appendLine(it)
               }
            }
        }
        return stringLineDataList
    }
}