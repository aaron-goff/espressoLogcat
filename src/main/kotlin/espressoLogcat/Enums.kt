package espressoLogcat

/**
 * Priority, also known as level, to be used when querying logcat
 * `levelFlag` is the first character of the string, which maps to a priority appended to a `tag` string during query
 */
enum class Priority(internal val levelFlag: String) {
    VERBOSE(levelFlag = "V"),
    DEBUG(levelFlag = "D"),
    INFO(levelFlag = "I"),
    WARNING(levelFlag = "W"),
    ERROR(levelFlag = "E"),
    FATAL(levelFlag = "F"),
    SILENT(levelFlag = "S")
}

/**
 * Various output formats. These are equivalent to passing in `-v {OutputFormat}` when querying logcat.
 * However, EspressoLogcat only queries logcat with `threadtime` and then formats the response to match
 */
enum class OutputFormat {
    BRIEF,
    LONG,
    PROCESS,
    RAW,
    TAG,
    THREAD,
    THREADTIME,
    TIME
}