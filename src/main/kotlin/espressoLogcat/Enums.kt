package espressoLogcat

/**
 * Priority, also known as level, to be used when querying logcat
 * `abbr` is the first character of the string
 */
enum class Priority(val abbr: String) {
    VERBOSE(abbr = "V"),
    DEBUG(abbr = "D"),
    INFO(abbr = "I"),
    WARNING(abbr = "W"),
    ERROR(abbr = "E"),
    FATAL(abbr = "F"),
    SILENT(abbr = "S")
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