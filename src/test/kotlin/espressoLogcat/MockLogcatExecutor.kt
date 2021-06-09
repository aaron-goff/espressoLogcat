package espressoLogcat

// Mock class to return whatever MutableList of strings is passed in
class MockLogcatExecutor(private val dataList: MutableList<String>) : LogcatExecutor {
    override fun getLogcat(
        tagPriority: MutableList<String>,
        constructedOptions: MutableList<String>,
        bufferedReaderSize: Int,
        vararg options: String
    ): MutableList<String> {
        return this.dataList
    }
}