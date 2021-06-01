package espressoLogcat

import org.junit.Before
import org.mockito.Mockito
import java.io.File

open class BaseTests {
    @Before
    fun init() {
        dataList = getFileAsMutableList(filename = testDataFilename)

        lineDataMap[lineDataEventOne.date] = lineDataEventOne
        lineDataMap[lineDataEventTwo.date] = lineDataEventTwo
        lineDataMap[lineDataEventThree.date] = lineDataEventThree
        lineDataMap[lineDataEventFour.date] = lineDataEventFour
    }

    protected val mockLogcatExecutorImpl: LogcatExecutorImpl = Mockito.mock(LogcatExecutorImpl::class.java)

    protected val testDataFilename = "src/test/resources/testdata.txt"
    protected fun getFileAsMutableList(filename: String): MutableList<String> {
        val list = mutableListOf<String>()
        File(filename).forEachLine {
            list.add(it)
        }
        return list
    }

    protected fun generateExpectedList(outputFormat: OutputFormat): MutableCollection<String> {
        return Formatters.formatLineData(
            lineDataValues = lineDataValues,
            outputFormat = outputFormat,
            filename = null
        )
    }

    protected var dataList = mutableListOf<String>()
    protected lateinit var lineDataValues: MutableCollection<LineData>
    protected val lineDataMap = mutableMapOf<String, LineData>()
    protected val testTag = "MyTestSDK"
    protected val lineDataEventOne = LineData(
        date = "2021-05-21T15:44:05.125",
        pid = "23871",
        tid = "23899",
        level = "V",
        tag = testTag,
        coreData = "EventBus(EventHub) - Processing event #22: {class: Event,name: CollectData,eventNumber: 22,uniqueIdentifier: c8b0b338-0366-4116-a11a-8d9273cec209,source: com.mytest.eventsource.os,type: com.mytest.eventtype.generic.data,pairId: null,responsePairId: bd636dcd-07e8-4ea7-9955-0fa47bc23f40,timestamp: 1621626245121,data: {\"args_transactions_link\" : \"/services\",\"args_transaction_id\" : \"MYTEST\",}}"
    )
    protected val lineDataEventTwo = LineData(
        date = "2021-05-21T15:44:05.129",
        pid = "23871",
        tid = "23899",
        level = "V",
        tag = testTag,
        coreData = "EventHub(AMSEventHub) - Event (0c499e29-35d6-464f-8597-56a189b66d54) #23 (LifecycleResume) resulted in 0 consequence events. Time in rules was 0 milliseconds.EventBus(EventHub) - Processing event #23: {class: Event,name: LifecycleResume,eventNumber: 23,uniqueIdentifier: 0c499e29-35d6-464f-8597-56a189b66d54,source: com.mytest.eventsource.requestcontent,type: com.mytest.eventtype.generic.lifecycle,pairId: null,responsePairId: d6a3988b-01dc-4b17-ba11-a92a33adb7b4,timestamp: 1621626245123,data: {\"action\" : \"start\",\"additionalcontextdata\" : null}}"
    )
    protected val lineDataEventThree = LineData(
        date = "2021-05-21T16:44:04.678",
        pid = "3871",
        tid = "3899",
        level = "V",
        tag = testTag,
        coreData = "EventBus(EventHub) - Processing event #21: {class: Event,name: LifecyclePause,eventNumber: 21,uniqueIdentifier: 1a515c88-2c9e-4084-8d25-e4e30e229f7b,source: com.mytest.eventsource.requestcontent,type: com.mytest.eventtype.generic.lifecycle,pairId: null,responsePairId: 7ca35071-62e1-4a6a-a90f-df5dfd39e503,timestamp: 1621626244676,data: {\"action\" : \"pause\"}}"
    )
    protected val lineDataEventFour = LineData(
        date = "2021-05-21T15:44:04.678",
        pid = "23871",
        tid = "23899",
        level = "V",
        tag = testTag,
        coreData = "EventBus(EventHub) - Processing event #21: {class: Event,name: LifecyclePause,eventNumber: 21,uniqueIdentifier: 1a515c88-2c9e-4084-8d25-e4e30e229f7b,source: com.mytest.eventsource.requestcontent,type: com.mytest.eventtype.generic.lifecycle,pairId: null,responsePairId: 7ca35071-62e1-4a6a-a90f-df5dfd39e503,timestamp: 1621626244676,data: {\"action\" : \"pause\"}}"
    )
    protected val outputTestingData = mutableListOf(lineDataEventOne)
    protected val briefString = mutableListOf("V/MyTestSDK(23871): ${lineDataEventOne.coreData}")
    protected val longString =
        mutableListOf("[ 2021-05-21T15:44:05.125 23871: 23899 V/MyTestSDK ]\n${lineDataEventOne.coreData}")
    protected val processString = mutableListOf("V(23871) ${lineDataEventOne.coreData} (MyTestSDK)")
    protected val rawString = mutableListOf(lineDataEventOne.coreData)
    protected val tagString = mutableListOf("V/MyTestSDK: ${lineDataEventOne.coreData}")
    protected val threadString = mutableListOf("V(23871: 23899) ${lineDataEventOne.coreData}")
    protected val threadtimeString =
        mutableListOf("2021-05-21T15:44:05.125 23871 23899 V MyTestSDK: ${lineDataEventOne.coreData}")
    protected val timeString = mutableListOf("2021-05-21T15:44:05.125 V/MyTestSDK(23871): ${lineDataEventOne.coreData}")
}