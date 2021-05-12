package espressoLogcat

enum class StringPosition {
    FIRST,
    LAST,
    NONE
}

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

enum class OutputModifier {
    COLOR,
    DESCRIPTIVE,
    EPOCH,
    MONOTONIC,
    PRINTABLE,
    UID,
    USEC,
    UTC,
    YEAR,
    ZONE
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