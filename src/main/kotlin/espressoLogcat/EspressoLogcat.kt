package espressoLogcat

import espressoLogcat.Formatters.Companion.createLineDataMap
import espressoLogcat.Formatters.Companion.formatLineData
import espressoLogcat.Formatters.Companion.getUnmatchedLineData
import espressoLogcat.OutputFormat.*
import java.time.format.DateTimeFormatter
import kotlin.math.log

class EspressoLogcat(private val logcatExecutor: LogcatExecutor = LogcatExecutor()) {
    /**
     * Settings Class is used to set static variables that are used when getting the logcat
     */
    class Settings {
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

    val settings = Settings()

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
        vararg options: String = arrayOf()
    ): MutableCollection<String> {
        val lineDataValues = this.getLogcatLikeLineData(
            tag = tag,
            priority = priority,
            regex = regex,
            options = options
        )
        return formatLineData(
            lineDataValues = lineDataValues,
            outputFormat = outputFormat,
            filename = this.settings.filename
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
        vararg options: String = arrayOf()
    ): MutableCollection<LineData> {
        // uses the above parameters to construct a string array
        val constructedOptions = LogcatOptions.formatMaxLinesAndSinceTime(
            maxLines = this.settings.maxLines,
            sinceTime = this.settings.sinceTime,
        )
        // formatted tag:priority string array
        val tagPriority = LogcatOptions.getTagPriority(tag = tag, priority = priority)
        val lines = logcatExecutor.getLogcat(
            tagPriority = tagPriority,
            constructedOptions = constructedOptions,
            bufferedReaderSize = this.settings.bufferedReaderSize,
            options = options
        )
        val lineDataMap = createLineDataMap(lines = lines, dateTimeFormatter = this.settings.dateTimeFormatter)
        if (regex != null) {
            val unmatched = getUnmatchedLineData(regex = regex, data = lineDataMap)
            unmatched.forEach {
                lineDataMap.remove(it)
            }
        }

        return lineDataMap.values
    }

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
        vararg options: String = arrayOf()
    ): MutableCollection<String> {
        return this.getLogCat(
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
        vararg options: String = arrayOf()
    ): MutableCollection<String> {
        return this.getLogCat(
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
        vararg options: String = arrayOf()
    ): MutableCollection<String> {
        return this.getLogCat(
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
        vararg options: String = arrayOf()
    ): MutableCollection<String> {
        return this.getLogCat(
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
        vararg options: String = arrayOf()
    ): MutableCollection<String> {
        return this.getLogCat(
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
        vararg options: String = arrayOf()
    ): MutableCollection<String> {
        return this.getLogCat(
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
        vararg options: String = arrayOf()
    ): MutableCollection<String> {
        return this.getLogCat(
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
        vararg options: String = arrayOf()
    ): MutableCollection<String> {
        return this.getLogCat(
            tag = tag,
            priority = priority,
            regex = regex,
            outputFormat = TIME,
            options = options
        )
    }

    /**
     * Clears the logcat
     */
    fun clearLogcat() {
        logcatExecutor.clearLogcat()
    }
}