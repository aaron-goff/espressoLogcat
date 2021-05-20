package espressoLogcat

import espressoLogcat.OutputFormat.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.time.format.DateTimeFormatter

class EspressoLogcat {
    companion object {
        fun getLogcatLikeBrief(
            tag: String = "*",
            priority: Priority? = null,
            bufferedReaderSize: Int = 4 * 1024,
            dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME,
            regex: Regex? = null,
            filename: String? = null,
            tCount: Int? = null,
            tTime: String? = null,
            pid: Int? = null,
            vararg options: String? = arrayOf()
        ): MutableCollection<String> {
            return EspressoLogcat().getLogCat(
                tag = tag,
                priority = priority,
                bufferedReaderSize = bufferedReaderSize,
                dateTimeFormatter = dateTimeFormatter,
                regex = regex,
                filename = filename,
                tCount = tCount,
                tTime = tTime,
                pid = pid,
                outputFormat = BRIEF,
                options = options
            )
        }

        fun getLogcatLikeLong(
            tag: String = "*",
            priority: Priority? = null,
            bufferedReaderSize: Int = 4 * 1024,
            dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME,
            regex: Regex? = null,
            filename: String? = null,
            tCount: Int? = null,
            tTime: String? = null,
            pid: Int? = null,
            vararg options: String? = arrayOf()
        ): MutableCollection<String> {
            return EspressoLogcat().getLogCat(
                tag = tag,
                priority = priority,
                bufferedReaderSize = bufferedReaderSize,
                dateTimeFormatter = dateTimeFormatter,
                regex = regex,
                filename = filename,
                tCount = tCount,
                tTime = tTime,
                pid = pid,
                outputFormat = LONG,
                options = options
            )
        }

        fun getLogcatLikeProcess(
            tag: String = "*",
            priority: Priority? = null,
            bufferedReaderSize: Int = 4 * 1024,
            dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME,
            regex: Regex? = null,
            filename: String? = null,
            tCount: Int? = null,
            tTime: String? = null,
            pid: Int? = null,
            vararg options: String? = arrayOf()
        ): MutableCollection<String> {
            return EspressoLogcat().getLogCat(
                tag = tag,
                priority = priority,
                bufferedReaderSize = bufferedReaderSize,
                dateTimeFormatter = dateTimeFormatter,
                regex = regex,
                filename = filename,
                tCount = tCount,
                tTime = tTime,
                pid = pid,
                outputFormat = PROCESS,
                options = options
            )
        }

        fun getLogcatLikeRaw(
            tag: String = "*",
            priority: Priority? = null,
            bufferedReaderSize: Int = 4 * 1024,
            dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME,
            regex: Regex? = null,
            filename: String? = null,
            tCount: Int? = null,
            tTime: String? = null,
            pid: Int? = null,
            vararg options: String? = arrayOf()
        ): MutableCollection<String> {
            return EspressoLogcat().getLogCat(
                tag = tag,
                priority = priority,
                bufferedReaderSize = bufferedReaderSize,
                dateTimeFormatter = dateTimeFormatter,
                regex = regex,
                filename = filename,
                tCount = tCount,
                tTime = tTime,
                pid = pid,
                outputFormat = RAW,
                options = options
            )
        }

        fun getLogcatLikeTag(
            tag: String = "*",
            priority: Priority? = null,
            bufferedReaderSize: Int = 4 * 1024,
            dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME,
            regex: Regex? = null,
            filename: String? = null,
            tCount: Int? = null,
            tTime: String? = null,
            pid: Int? = null,
            vararg options: String? = arrayOf()
        ): MutableCollection<String> {
            return EspressoLogcat().getLogCat(
                tag = tag,
                priority = priority,
                bufferedReaderSize = bufferedReaderSize,
                dateTimeFormatter = dateTimeFormatter,
                regex = regex,
                filename = filename,
                tCount = tCount,
                tTime = tTime,
                pid = pid,
                outputFormat = TAG,
                options = options
            )
        }

        fun getLogcatLikeThread(
            tag: String = "*",
            priority: Priority? = null,
            bufferedReaderSize: Int = 4 * 1024,
            dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME,
            regex: Regex? = null,
            filename: String? = null,
            tCount: Int? = null,
            tTime: String? = null,
            pid: Int? = null,
            vararg options: String? = arrayOf()
        ): MutableCollection<String> {
            return EspressoLogcat().getLogCat(
                tag = tag,
                priority = priority,
                bufferedReaderSize = bufferedReaderSize,
                dateTimeFormatter = dateTimeFormatter,
                regex = regex,
                filename = filename,
                tCount = tCount,
                tTime = tTime,
                pid = pid,
                outputFormat = THREAD,
                options = options
            )
        }

        fun getLogcatLikeThreadtime(
            tag: String = "*",
            priority: Priority? = null,
            bufferedReaderSize: Int = 4 * 1024,
            dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME,
            regex: Regex? = null,
            filename: String? = null,
            tCount: Int? = null,
            tTime: String? = null,
            pid: Int? = null,
            vararg options: String? = arrayOf()
        ): MutableCollection<String> {
            return EspressoLogcat().getLogCat(
                tag = tag,
                priority = priority,
                bufferedReaderSize = bufferedReaderSize,
                dateTimeFormatter = dateTimeFormatter,
                regex = regex,
                filename = filename,
                tCount = tCount,
                tTime = tTime,
                pid = pid,
                options = options
            )
        }

        fun getLogcatLikeTime(
            tag: String = "*",
            priority: Priority? = null,
            bufferedReaderSize: Int = 4 * 1024,
            dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME,
            regex: Regex? = null,
            filename: String? = null,
            tCount: Int? = null,
            tTime: String? = null,
            pid: Int? = null,
            vararg options: String? = arrayOf()
        ): MutableCollection<String> {
            return EspressoLogcat().getLogCat(
                tag = tag,
                priority = priority,
                bufferedReaderSize = bufferedReaderSize,
                dateTimeFormatter = dateTimeFormatter,
                regex = regex,
                filename = filename,
                tCount = tCount,
                tTime = tTime,
                pid = pid,
                outputFormat = TIME,
                options = options
            )
        }

        /**
         * Function that returns the specified logcat in an array of strings
         *
         * @param tag the tag to search for
         * @param priority the priority to apply on the tag
         * @param bufferedReaderSize size for the buffered reader
         * @param dateTimeFormatter formatter for the date, requires withMetadata = true
         * @param regex matcher for filtering logcat, accompanies the `-e` flag
         *        that `-m` should be paired with the `-e` flag, but will work on its own
         * @param tCount Integer for only printing the most recent number of lines, accompanies the `-t` flag.
         *        exclusive with `tTime`
         * @param tTime string for only printing the lines since the provided time, accompanies the `-t` flag.
         *        exclusive with `tCount`
         * @param pid allows filtering by a specific pid, accompanies the `--pid` flag.
         * @param options an additional string array to include any logcat options not provided for by the other parameters.
         */
        fun getLogcatLikeLineData(
            tag: String = "*",
            priority: Priority? = null,
            bufferedReaderSize: Int = 4 * 1024,
            dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME,
            regex: Regex? = null,
            tCount: Int? = null,
            tTime: String? = null,
            pid: Int? = null,
            vararg options: String? = arrayOf()
        ): MutableCollection<LineData> {
            // uses the above parameters to construct a string array
            val constructedOptions = LogcatOptions().getOptions(
                tCount = tCount,
                tTime = tTime,
                pid = pid,
            )
            // formatted tag:priority string array
            val tagPriority = LogcatOptions().getTagPriority(tag = tag, priority = priority)
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
                            *tagPriority,
                            *constructedOptions,
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

                val lineDataMap = mutableMapOf<String, LineData>()
                lines.forEach {
                    val lineData = Formatters().getLineData(data = it, dateTimeFormatter = dateTimeFormatter)
                    if (lineDataMap.containsKey(lineData.date)) {
                        val newCoreData = lineDataMap[lineData.date]!!.coreData + lineData.coreData
                        lineDataMap[lineData.date] =
                            lineDataMap[lineData.date]!!.copy(coreData = newCoreData)
                    } else {
                        lineDataMap[lineData.date] = lineData
                    }
                }

                if (regex != null) {
                    val lineDataToExclude = mutableListOf<String>()
                    lineDataMap.forEach {
                        if (!it.value.coreData.matches(regex)) {
                            lineDataToExclude.add(it.key)
                        }
                    }
                    lineDataToExclude.forEach {
                        lineDataMap.remove(it)
                    }
                }

                return lineDataMap.values
            } catch (exception: Exception) {
                throw exception
            }
        }

        fun clearLogcat() {
            try {
                Runtime.getRuntime().exec(arrayOf("logcat", "-c"))
            } catch (exception: Exception) {
                throw exception
            }
        }
    }

    private fun getLogCat(
        tag: String = "*",
        priority: Priority? = null,
        bufferedReaderSize: Int = 4 * 1024,
        dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME,
        regex: Regex? = null,
        filename: String? = null,
        tCount: Int? = null,
        tTime: String? = null,
        pid: Int? = null,
        outputFormat: OutputFormat = THREADTIME,
        vararg options: String? = arrayOf()
    ): MutableCollection<String> {
        val lineDataValues = getLogcatLikeLineData(
            tag = tag,
            priority = priority,
            bufferedReaderSize = bufferedReaderSize,
            dateTimeFormatter = dateTimeFormatter,
            regex = regex,
            tCount = tCount,
            tTime = tTime,
            pid = pid,
            options = options
        )
        return Formatters().formatLineData(lineDataValues, outputFormat, filename = filename)
    }
}
