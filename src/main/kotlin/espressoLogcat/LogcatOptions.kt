package espressoLogcat

import espressoLogcat.BufferOptions.*
import espressoLogcat.OutputFormat.*

class LogcatOptions {
    fun getOptions(outputFormat: OutputFormat = THREADTIME,
                   regExp: Regex? = null,
                   maxLineCount: Int? = null,
                   filename: String? = null,
                   maxRotatedLogCount: Int? = null,
                   dividers: Boolean = false,
                   tCount: Int? = null,
                   tTime: String? = null,
                   pid: Int? = null,
                   outputModifiers: Array<OutputModifier> = arrayOf(),
                   vararg buffer: BufferOptions = arrayOf(DEFAULT)): Array<String> {
        val options = mutableListOf<String>()

        // Add output format
        options.add("-v")
        options.add(outputFormat.toString().toLowerCase())

        for (modifier in outputModifiers) {
            options.add("-v")
            options.add(modifier.toString().toLowerCase())
        }

        // Add regexp
        if (regExp != null) {
            options.add("-e")
            options.add(regExp.pattern)
        }

        if (maxLineCount != null) {
            options.add("-m")
            options.add(maxLineCount.toString())
        }

        if (filename != null) {
            options.add("-f")
            options.add(filename)
        }

        if (maxRotatedLogCount != null) {
            options.add("-n")
            options.add(maxRotatedLogCount.toString())
        }

        if (dividers) {
            options.add("-D")
        }

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