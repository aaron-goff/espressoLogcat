package espressoLogcat

import espressoLogcat.OutputFormat.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.time.format.DateTimeFormatter

class EspressoLogcat {
    /**
     * Settings Class is used to set static variables that are used when getting the logcat
     */
    class Settings {
        companion object {
            /**
             * Buffered Reader size when parsing the logcat
             */
            var bufferedReaderSize: Int = 4 * 1024

            /**
             * dateTimeFormatter is used format the date as a string
             */
            var dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME

            /**
             * If set, will only return strings since a certain time.
             * Should be a String in ISO_LOCAL_DATE_TIME format -- 'YYYY-MM-ddTHH:mm:ss.SSS'
             * Exclusive of maxLines
             */
            var sinceTime: String? = null

            /**
             * If set, will only return the most recent X lines
             * Excluse of sinceTime
             */
            var maxLines: Int? = null

            /**
             * Filename for the logcat output to be stored in.
             * Note: If used with Espresso, this file will be on the device Espresso is executed on.
             */
            var filename: String? = null
        }
    }

    companion object {
        /**
         * Returns the logcat in a Collection<String> formatted as if `-v brief` was added to the logcat query
         * @param tag String used to match the tag of the application
         * @param priority determines the log level assigned to the tag
         * @param regex Regex matcher used after logs are returned on the string data
         * @param options used to pass in additional strings to the logcat command
         *      **note**: using this may cause exceptions or have no effect, since EspressoLogcat parses the response from querying logcat
         */
        fun getLogcatLikeBrief(
            tag: String = "*",
            priority: Priority? = null,
            regex: Regex? = null,
            vararg options: String? = arrayOf()
        ): MutableCollection<String> {
            return EspressoLogcat().getLogCat(
                tag = tag,
                priority = priority,
                regex = regex,
                outputFormat = BRIEF,
                options = options
            )
        }

        /**
         * Returns the logcat in a Collection<String> formatted as if `-v long` was added to the logcat query
         * @param tag String used to match the tag of the application
         * @param priority determines the log level assigned to the tag
         * @param regex Regex matcher used after logs are returned on the string data
         * @param options used to pass in additional strings to the logcat command
         *      **note**: using this may cause exceptions or have no effect, since EspressoLogcat parses the response from querying logcat
         */
        fun getLogcatLikeLong(
            tag: String = "*",
            priority: Priority? = null,
            regex: Regex? = null,
            vararg options: String? = arrayOf()
        ): MutableCollection<String> {
            return EspressoLogcat().getLogCat(
                tag = tag,
                priority = priority,
                regex = regex,
                outputFormat = LONG,
                options = options
            )
        }

        /**
         * Returns the logcat in a Collection<String> formatted as if `-v process` was added to the logcat query
         * @param tag String used to match the tag of the application
         * @param priority determines the log level assigned to the tag
         * @param regex Regex matcher used after logs are returned on the string data
         * @param options used to pass in additional strings to the logcat command
         *      **note**: using this may cause exceptions or have no effect, since EspressoLogcat parses the response from querying logcat
         */
        fun getLogcatLikeProcess(
            tag: String = "*",
            priority: Priority? = null,
            regex: Regex? = null,
            vararg options: String? = arrayOf()
        ): MutableCollection<String> {
            return EspressoLogcat().getLogCat(
                tag = tag,
                priority = priority,
                regex = regex,
                outputFormat = PROCESS,
                options = options
            )
        }

        /**
         * Returns the logcat in a Collection<String> formatted as if `-v raw` was added to the logcat query
         * @param tag String used to match the tag of the application
         * @param priority determines the log level assigned to the tag
         * @param regex Regex matcher used after logs are returned on the string data
         * @param options used to pass in additional strings to the logcat command
         *      **note**: using this may cause exceptions or have no effect, since EspressoLogcat parses the response from querying logcat
         */
        fun getLogcatLikeRaw(
            tag: String = "*",
            priority: Priority? = null,
            regex: Regex? = null,
            vararg options: String? = arrayOf()
        ): MutableCollection<String> {
            return EspressoLogcat().getLogCat(
                tag = tag,
                priority = priority,
                regex = regex,
                outputFormat = RAW,
                options = options
            )
        }

        /**
         * Returns the logcat in a Collection<String> formatted as if `-v tag` was added to the logcat query
         * @param tag String used to match the tag of the application
         * @param priority determines the log level assigned to the tag
         * @param regex Regex matcher used after logs are returned on the string data
         * @param options used to pass in additional strings to the logcat command
         *      **note**: using this may cause exceptions or have no effect, since EspressoLogcat parses the response from querying logcat
         */
        fun getLogcatLikeTag(
            tag: String = "*",
            priority: Priority? = null,
            regex: Regex? = null,
            vararg options: String? = arrayOf()
        ): MutableCollection<String> {
            return EspressoLogcat().getLogCat(
                tag = tag,
                priority = priority,
                regex = regex,
                outputFormat = TAG,
                options = options
            )
        }

        /**
         * Returns the logcat in a Collection<String> formatted as if `-v thread` was added to the logcat query
         * @param tag String used to match the tag of the application
         * @param priority determines the log level assigned to the tag
         * @param regex Regex matcher used after logs are returned on the string data
         * @param options used to pass in additional strings to the logcat command
         *      **note**: using this may cause exceptions or have no effect, since EspressoLogcat parses the response from querying logcat
         */
        fun getLogcatLikeThread(
            tag: String = "*",
            priority: Priority? = null,
            regex: Regex? = null,
            vararg options: String? = arrayOf()
        ): MutableCollection<String> {
            return EspressoLogcat().getLogCat(
                tag = tag,
                priority = priority,
                regex = regex,
                outputFormat = THREAD,
                options = options
            )
        }

        /**
         * Returns the logcat in a Collection<String> formatted as if `-v threadtime` was added to the logcat query
         * @param tag String used to match the tag of the application
         * @param priority determines the log level assigned to the tag
         * @param regex Regex matcher used after logs are returned on the string data
         * @param options used to pass in additional strings to the logcat command
         *      **note**: using this may cause exceptions or have no effect, since EspressoLogcat parses the response from querying logcat
         */
        fun getLogcatLikeThreadtime(
            tag: String = "*",
            priority: Priority? = null,
            regex: Regex? = null,
            vararg options: String? = arrayOf()
        ): MutableCollection<String> {
            return EspressoLogcat().getLogCat(
                tag = tag,
                priority = priority,
                regex = regex,
                outputFormat = THREADTIME,
                options = options
            )
        }

        /**
         * Returns the logcat in a Collection<String> formatted as if `-v time` was added to the logcat query
         * @param tag String used to match the tag of the application
         * @param priority determines the log level assigned to the tag
         * @param regex Regex matcher used after logs are returned on the string data
         * @param options used to pass in additional strings to the logcat command
         *      **note**: using this may cause exceptions or have no effect, since EspressoLogcat parses the response from querying logcat
         */
        fun getLogcatLikeTime(
            tag: String = "*",
            priority: Priority? = null,
            regex: Regex? = null,
            vararg options: String? = arrayOf()
        ): MutableCollection<String> {
            return EspressoLogcat().getLogCat(
                tag = tag,
                priority = priority,
                regex = regex,
                outputFormat = TIME,
                options = options
            )
        }

        /**
         * Returns the logcat in a Collection<LineData>
         * @param tag String used to match the tag of the application
         * @param priority determines the log level assigned to the tag
         * @param regex Regex matcher used after logs are returned on the string data
         * @param options used to pass in additional strings to the logcat command
         *      **note**: using this may cause exceptions or have no effect, since EspressoLogcat parses the response from querying logcat
         */
        fun getLogcatLikeLineData(
            tag: String = "*",
            priority: Priority? = null,
            regex: Regex? = null,
            vararg options: String? = arrayOf()
        ): MutableCollection<LineData> {
            // uses the above parameters to construct a string array
            val constructedOptions = LogcatOptions().formatMaxLinesAndSinceTime(
                maxLines = Settings.maxLines,
                sinceTime = Settings.sinceTime,
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
                val br = BufferedReader(InputStreamReader(logcat.inputStream), Settings.bufferedReaderSize)
                val lines = mutableListOf<String>()
                br.forEachLine {
                    if (!it.startsWith("----")) {
                        lines.add(it)
                    }
                }

                val lineDataMap = mutableMapOf<String, LineData>()
                lines.forEach {
                    val lineData = Formatters().getLineData(data = it, dateTimeFormatter = Settings.dateTimeFormatter)
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

    /**
     * Returns the logcat in a Collection<String> formatted as if `-v {outputFormat}` was added to the logcat query
     * @param tag String used to match the tag of the application
     * @param priority determines the log level assigned to the tag
     * @param regex Regex matcher used after logs are returned on the string data
     * @param outputFormat enum to determine how the output of each string line is formatted
     * @param options used to pass in additional strings to the logcat command
     *      **note**: using this may cause exceptions or have no effect, since EspressoLogcat parses the response from querying logcat
     */
    private fun getLogCat(
        tag: String = "*",
        priority: Priority? = null,
        regex: Regex? = null,
        outputFormat: OutputFormat,
        vararg options: String? = arrayOf()
    ): MutableCollection<String> {
        val lineDataValues = getLogcatLikeLineData(
            tag = tag,
            priority = priority,
            regex = regex,
            options = options
        )
        return Formatters().formatLineData(
            lineDataValues = lineDataValues,
            outputFormat = outputFormat,
            filename = Settings.filename
        )
    }
}
