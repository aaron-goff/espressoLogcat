package espressoLogcat

class LogcatOptions {
    fun getOptions(
        tCount: Int? = null,
        tTime: String? = null,
        pid: Int? = null,
    ): Array<String> {
        val options = mutableListOf<String>()

        if (tCount != null && tTime != null) {
            throw Exception("Error! Can only set tCount or tTime, but both are set.")
        } else if (tCount != null) {
            options.add("-T")
            options.add(tCount.toString())
        } else if (tTime != null) {
            options.add("-T")
            options.add(tTime)
        }

        if (pid != null) {
            options.add("--pid=$pid")
        }

        return options.toTypedArray()
    }

    fun getTagPriority(tag: String = "*", priority: Priority? = null): Array<String> {
        return if (priority == null) {
            if (tag.startsWith("*")) {
                arrayOf("")
            } else {
                arrayOf(tag)
            }
        } else {
            arrayOf("$tag:${priority.abbr}", "-s")
        }
    }
}