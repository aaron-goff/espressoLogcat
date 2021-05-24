package espressoLogcat

import org.junit.Before
import org.junit.Test
import java.io.File
import java.time.format.DateTimeFormatter
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FormattersTests: BaseTests() {

    @Before
    fun setup() {
        dataList = getFileAsMutableList(filename = testDataFilename)
    }

    @Test
    fun testLineDataMapIsCreated() {
        val lineData =
            Formatters.createLineDataMap(lines = dataList, dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        for (key in lineData.keys) {
            assertEquals(expected = lineDataMap[key], actual = lineData[key])
        }
    }

    @Test
    fun testUnmatchedLineDataIsRemovedFromMap() {
        val unmatchedLineData =
            Formatters.getUnmatchedLineData(regex = Regex(".*name: Lifecycle.*"), data = lineDataMap)
        assertEquals(expected = 1, unmatchedLineData.size)
    }

    @Test
    fun testBriefStringOutput() {
        val outputString = Formatters.formatLineData(
            lineDataValues = outputTestingData,
            outputFormat = OutputFormat.BRIEF,
            filename = null
        )
        assertEquals(expected = briefString, actual = outputString)
    }

    @Test
    fun testLongStringOutput() {
        val outputString = Formatters.formatLineData(
            lineDataValues = outputTestingData,
            outputFormat = OutputFormat.LONG,
            filename = null
        )
        assertEquals(expected = longString, actual = outputString)
    }

    @Test
    fun testProcessStringOutput() {
        val outputString = Formatters.formatLineData(
            lineDataValues = outputTestingData,
            outputFormat = OutputFormat.PROCESS,
            filename = null
        )
        assertEquals(expected = processString, actual = outputString)
    }

    @Test
    fun testRawStringOutput() {
        val outputString = Formatters.formatLineData(
            lineDataValues = outputTestingData,
            outputFormat = OutputFormat.RAW,
            filename = null
        )
        assertEquals(expected = rawString, actual = outputString)
    }

    @Test
    fun testTagStringOutput() {
        val outputString = Formatters.formatLineData(
            lineDataValues = outputTestingData,
            outputFormat = OutputFormat.TAG,
            filename = null
        )
        assertEquals(expected = tagString, actual = outputString)
    }

    @Test
    fun testThreadStringOutput() {
        val outputString = Formatters.formatLineData(
            lineDataValues = outputTestingData,
            outputFormat = OutputFormat.THREAD,
            filename = null
        )
        assertEquals(expected = threadString, actual = outputString)
    }

    @Test
    fun testThreadtimeStringOutput() {
        val outputString = Formatters.formatLineData(
            lineDataValues = outputTestingData,
            outputFormat = OutputFormat.THREADTIME,
            filename = null
        )
        assertEquals(expected = threadtimeString, actual = outputString)
    }

    @Test
    fun testTimeStringOutput() {
        val outputString = Formatters.formatLineData(
            lineDataValues = outputTestingData,
            outputFormat = OutputFormat.TIME,
            filename = null
        )
        assertEquals(expected = timeString, actual = outputString)
    }

    @Test
    fun testFileCreation() {
        val filename = "test.txt"
        val file = File(filename)
        Formatters.formatLineData(
            lineDataValues = outputTestingData,
            outputFormat = OutputFormat.TIME,
            filename = filename
        )
        if (file.exists()) {
            file.forEachLine {
                // File should only be one line long
                assertEquals(expected = timeString[0], actual = it)
            }
            // If test is successful, delete the file
            file.delete()
        } else {
            // If file isn't found, fail test
            assertTrue(file.exists())
        }
    }
}