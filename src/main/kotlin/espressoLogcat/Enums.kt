package espressoLogcat

enum class Priority {
    VERBOSE,
    DEBUG,
    INFO,
    WARNING,
    ERROR,
    FATAL,
    SILENT
}

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

enum class BufferOptions {
    RADIO,
    EVENTS,
    MAIN,
    SYSTEM,
    CRASH,
    ALL,
    DEFAULT
}

data class LineData(
    val date: String,
    val pid: String,
    val tid: String,
    val level: String,
    val tag: String,
    val coreData: String
)