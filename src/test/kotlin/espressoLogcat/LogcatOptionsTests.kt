package espressoLogcat

import org.junit.Test
import kotlin.test.assertEquals

class LogcatOptionsTests: BaseTests() {
    @Test
    fun testNonNullMaxLinesAndNullSinceTime() {
        val maxLinesActual = LogcatOptions.formatMaxLinesAndSinceTime(maxLines = 10)
        val maxLinesExpected = mutableListOf("-T", "10")
        assertEquals(expected = maxLinesExpected, actual = maxLinesActual)
    }

    @Test
    fun testNullMaxLinesAndNonNullSinceTime() {
        val time = "2021-10-10T01:02:03.444"
        val sinceTimeActual = LogcatOptions.formatMaxLinesAndSinceTime(sinceTime = time)
        val sinceTimeExpected = mutableListOf("-T", time)
        assertEquals(expected = sinceTimeExpected, actual = sinceTimeActual)
    }

    @Test
    fun testNullMaxLinesAndNullSinceTime() {
        val actual = LogcatOptions.formatMaxLinesAndSinceTime()
        val expected = mutableListOf<String>()
        assertEquals(expected = expected, actual = actual)
    }

    @Test
    fun testMaxLinesAndSinceTimeAreExclusiveAndSettingBothCausesException() {
        try {
            LogcatOptions.formatMaxLinesAndSinceTime(maxLines = 1, sinceTime = "2021-10-10T01:02:03.444")
        } catch (exception: Exception) {
            val actualMessage = exception.message
            val expectedMessage = "Error! Can only set maxLines or sinceTime, but both are set."
            assertEquals(expected = expectedMessage, actual = actualMessage)
        }
    }

    @Test
    fun testNormalTagAndNullPriority() {
        val actualTagPriority = LogcatOptions.getTagPriority(tag = "tag", priority = null)
        val expectedTagPriority = mutableListOf("tag", "-s")
        assertEquals(expected = expectedTagPriority, actual = actualTagPriority)
    }

    @Test
    fun testNormalTagAndNormalPriority() {
        val actualTagPriority = LogcatOptions.getTagPriority(tag = "tag", priority = Priority.DEBUG)
        val expectedTagPriority = mutableListOf("tag:D", "-s")
        assertEquals(expected = expectedTagPriority, actual = actualTagPriority)
    }

    @Test
    fun testDefaultTagAndNormalPriority() {
        val actualTagPriority = LogcatOptions.getTagPriority(tag = "*", priority = Priority.ERROR)
        val expectedTagPriority = mutableListOf("*:E", "-s")
        assertEquals(expected = expectedTagPriority, actual = actualTagPriority)
    }

    @Test
    fun testDefaultTagAndNullPriority() {
        val actualTagPriority = LogcatOptions.getTagPriority(tag = "*", priority = null)
        val expectedTagPriority = mutableListOf("")
        assertEquals(expected = expectedTagPriority, actual = actualTagPriority)
    }
}