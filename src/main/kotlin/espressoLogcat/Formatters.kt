package espressoLogcat

import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Formatters {
    companion object {
        fun createLineDataMap(lines: MutableList<String>, dateTimeFormatter: DateTimeFormatter): MutableMap<String, LineData> {
            val lineDataMap = mutableMapOf<String, LineData>()
            lines.forEach {
                val lineData =
                    getLineData(data = it, dateTimeFormatter = dateTimeFormatter)
                if (lineDataMap.containsKey(lineData.date)) {
                    val newCoreData = lineDataMap[lineData.date]!!.coreData + lineData.coreData
                    lineDataMap[lineData.date] =
                        lineDataMap[lineData.date]!!.copy(coreData = newCoreData)
                } else {
                    lineDataMap[lineData.date] = lineData
                }
            }
            return lineDataMap
        }

        fun getUnmatchedLineData(regex: Regex, data: MutableMap<String, LineData>): MutableList<String> {
            val unmatched = mutableListOf<String>()
            data.forEach {
                if (!it.value.coreData.matches(regex)) {
                    unmatched.add(it.key)
                }
            }
            return unmatched
        }

        private fun getLineData(data: String, dateTimeFormatter: DateTimeFormatter): LineData {
            val date = LocalDateTime.parse(data.substring(0, 23).replace(" ", "T")).format(dateTimeFormatter)
            val pid = data.substring(23, 29).trim()
            val tid = data.substring(29, 35).trim()
            val level = data.substring(35, 37).trim()
            val tagAndData = data.substring(37).split(":", limit = 2)
            val newTag = tagAndData[0].trim()
            val coreData = tagAndData[1].trim()

            return LineData(date = date, level = level, tag = newTag, pid = pid, tid = tid, coreData = coreData)
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
                        stringLineDataList.add("${it.level}/${it.tag}(${it.pid}): ${it.coreData}")
                    }
                    OutputFormat.LONG -> {
                        stringLineDataList.add("[ ${it.date} ${it.pid}: ${it.tid} ${it.level}/${it.tag} ]\n${it.coreData}")
                    }
                    OutputFormat.PROCESS -> {
                        stringLineDataList.add("${it.level}(${it.pid}) ${it.coreData} (${it.tag})")
                    }
                    OutputFormat.RAW -> {
                        stringLineDataList.add(it.coreData)
                    }
                    OutputFormat.TAG -> {
                        stringLineDataList.add("${it.level}/${it.tag}: ${it.coreData}")
                    }
                    OutputFormat.THREAD -> {
                        stringLineDataList.add("${it.level}(${it.pid}: ${it.tid}) ${it.coreData}")
                    }
                    OutputFormat.THREADTIME -> {
                        stringLineDataList.add("${it.date} ${it.pid} ${it.tid} ${it.level} ${it.tag}: ${it.coreData}")
                    }
                    OutputFormat.TIME -> {
                        stringLineDataList.add("${it.date} ${it.level}/${it.tag}(${it.pid}): ${it.coreData}")
                    }
                }
            }

            if (filename != null) {
                writeToFile(filename = filename, data = stringLineDataList)
            }

            return stringLineDataList
        }

        private fun writeToFile(filename: String, data: MutableList<String>) {
            val file = File(filename)
            if (!file.exists()) {
                file.createNewFile()
            }

            file.bufferedWriter().use { out ->
                data.forEach {
                    out.appendLine(it)
                }
            }
        }
    }
}