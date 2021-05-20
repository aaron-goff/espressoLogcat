package espressoLogcat

data class LineData(
    val date: String,
    val pid: String,
    val tid: String,
    val level: String,
    val tag: String,
    val coreData: String
)
