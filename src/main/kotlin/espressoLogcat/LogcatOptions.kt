package espressoLogcat

class LogcatOptions {
    companion object {
        /**
         * Returns a string array of the correct format for either maxLines or sinceTime
         * @param maxLines only return the most recent X lines
         * @param sinceTime only return lines since a certain time.
         *      Should be a String in ISO_LOCAL_DATE_TIME format -- 'YYYY-MM-ddTHH:mm:ss.SSS'
         */
        fun formatMaxLinesAndSinceTime(
            maxLines: Int? = null,
            sinceTime: String? = null,
        ): MutableList<String> {
            val options = mutableListOf<String>()

            if (maxLines != null && sinceTime != null) {
                throw Exception("Error! Can only set maxLines or sinceTime, but both are set.")
            } else if (maxLines != null) {
                options.add("-T")
                options.add(maxLines.toString())
            } else if (sinceTime != null) {
                options.add("-T")
                options.add(sinceTime)
            }

            return options
        }

        /**
         * Returns a string array to be used as the main search in the logcat query.
         *      Will resemble {tag}:{priority.levelFlag} if priority is not null or {tag} if priority is null
         *      `-s` is added to the return array to remove all logs not matching the `{tag}:{priority}`
         * @param tag String used to match the tag of the application
         * @param priority determines the log level assigned to the tag
         */
        fun getTagPriority(tag: String, priority: Priority?): MutableList<String> {
            return if (priority == null) {
                if (tag.startsWith("*")) {
                    mutableListOf("")
                } else {
                    mutableListOf(tag, "-s")
                }
            } else {
                mutableListOf("$tag:${priority.levelFlag}", "-s")
            }
        }
    }
}