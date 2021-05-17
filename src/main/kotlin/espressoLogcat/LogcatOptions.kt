package espressoLogcat

import espressoLogcat.BufferOptions.*

class LogcatOptions {
    fun getOptions(
        tCount: Int? = null,
        tTime: String? = null,
        pid: Int? = null,
        vararg buffer: BufferOptions = arrayOf(DEFAULT)
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

        // add buffer options
        for (option in buffer) {
            options.add("-b")
            options.add(option.toString().toLowerCase())
        }

        return options.toTypedArray()
    }

    fun getTagPriority(tag: String = "*", priority: Priority? = null): Array<String> {
        val tagPriority = if (priority == null) {
            if (tag == "*") {
                ""
            } else {
                tag
            }
        } else {
            "$tag:${priority.toString()[0]}"
        }

        return if (tagPriority.startsWith("*")) {
            arrayOf(tagPriority)
        } else {
            arrayOf(tagPriority, "-s")
        }
    }
}