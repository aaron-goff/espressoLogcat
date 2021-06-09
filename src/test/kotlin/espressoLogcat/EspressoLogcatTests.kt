package espressoLogcat

import org.junit.Before
import java.time.format.DateTimeFormatter
import kotlin.test.Test
import kotlin.test.assertEquals

class EspressoLogcatTests : BaseTests() {
    private var mockLogcatExecutor: MockLogcatExecutor? = null

    @Before
    fun setup() {
        lineDataValues = Formatters.createLineDataMap(
            lines = dataList,
            dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME
        ).values

         mockLogcatExecutor = MockLogcatExecutor(dataList = dataList)
    }

    @Test
    fun testGetLogcatLikeBrief() {
        val expectedList = generateExpectedList(OutputFormat.BRIEF)
        val el = EspressoLogcat(logcatExecutor = mockLogcatExecutor!!)
        val brief = el.getLogcatLikeBrief(tag = testTag)
        assertEquals(expected = expectedList, actual = brief)
    }

    @Test
    fun testGetLogcatLikeLong() {
        val expectedList = generateExpectedList(OutputFormat.LONG)
        val el = EspressoLogcat(logcatExecutor = mockLogcatExecutor!!)
        val long = el.getLogcatLikeLong(tag = testTag)
        assertEquals(expected = expectedList, actual = long)
    }
    
    @Test
    fun testGetLogcatLikeProcess() {
        val expectedList = generateExpectedList(OutputFormat.PROCESS)
        val el = EspressoLogcat(logcatExecutor = mockLogcatExecutor!!)
        val process = el.getLogcatLikeProcess(tag = testTag)
        assertEquals(expected = expectedList, actual = process)
    }

    @Test
    fun testGetLogcatLikeRaw() {
        val expectedList = generateExpectedList(OutputFormat.RAW)
        val el = EspressoLogcat(logcatExecutor = mockLogcatExecutor!!)
        val raw = el.getLogcatLikeRaw(tag = testTag)
        assertEquals(expected = expectedList, actual = raw)
    }

    @Test
    fun testGetLogcatLikeTag() {
        val expectedList = generateExpectedList(OutputFormat.TAG)
        val el = EspressoLogcat(logcatExecutor = mockLogcatExecutor!!)
        val tag = el.getLogcatLikeTag(tag = testTag)
        assertEquals(expected = expectedList, actual = tag)
    }

    @Test
    fun testGetLogcatLikeThread() {
        val expectedList = generateExpectedList(OutputFormat.THREAD)
        val el = EspressoLogcat(logcatExecutor = mockLogcatExecutor!!)
        val thread = el.getLogcatLikeThread(tag = testTag)
        assertEquals(expected = expectedList, actual = thread)
    }

    @Test
    fun testGetLogcatLikeThreadtime() {
        val expectedList = generateExpectedList(OutputFormat.THREADTIME)
        val el = EspressoLogcat(logcatExecutor = mockLogcatExecutor!!)
        val threadtime = el.getLogcatLikeThreadtime(tag = testTag)
        assertEquals(expected = expectedList, actual = threadtime)
    }

    @Test
    fun testGetLogcatLikeTime() {
        val expectedList = generateExpectedList(OutputFormat.TIME)
        val el = EspressoLogcat(logcatExecutor = mockLogcatExecutor!!)
        val time = el.getLogcatLikeTime(tag = testTag)
        assertEquals(expected = expectedList, actual = time)
    }
}
