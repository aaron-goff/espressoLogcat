# espressoLogcat

Helper library to access logcat during espresso tests

## Installing - tbd

## Using

All functionality to be used is included as a companion object on the `EspressoLogcat` class.

The data can be returned as a Collection of `LineData`, a custom data class. A LineData class contains 6 constructors:

- date: a string-ified date. The format is determined by the `dateTimeFormatter` in Settings. Defaults to `ISO_LOCAL_DATE_TIME`
- pid: process id
- tid: thread id
- level: the log level, specified in a single string character
- tag: the associated tag with the event
- coreData: the data associated with the event

Using `getLogcatLikeLineData()`, you can return the collection of LineData and use it as needed.

```kotlin
val lineData: Collection<LineData> = getLogcatLikeLineData(tag = "MyTag")
val firstLineData = lineData[0]
// firstLineData.date = "2021-05-14T13:26:12.108"
// firstLineData.pid = "1234"
// firstLineData.tid = "2345"
// firstLineData.tag = "MyTag"
// firstLineData.level = "V"
// firstLineData.coreData = "Here's my logged data!"
```

If you'd prefer to already have the output in formatted strings, there are functions that already return
a `Collection<String>`, equal to what the `OutputFormat` would be if you queried logcat with `-v format`

```kotlin
// All examples use the same `firstLineData` object as above

val briefLogcat = EspressoLogcat().getLogcatLikeBrief(tag = "MyTag")
// equal to the output of `-v brief`
// "V/MyTag( 1234): Here's my logged data!"

val longLogcat = EspressoLogcat.getLogcatLikeLong(tag = "MyTag")
// equal to the output of `-v long`
// "[ 2021-05-14T13:26:12.108 1234: 2345 V/MyTag ]\n Here's my logged data!"

val processLogcat = EspressoLogcat.getLogcatLikeProcess(tag = "MyTag")
// equal to the output of `-v process`
// "V( 1234) Here's my logged data!"

val rawLogcat = EspressoLogcat.getLogcatLikeRaw(tag = "MyTag")
// equal to the output of `-v raw`
// "Here's my logged data!"

val tagLogcat = EspressoLogcat.getLogcatLikeTag(tag = "MyTag")
// equal to the output of `-v tag`
// "V/MyTag: Here's my logged data!"

val threadLogcat = EspressoLogcat.getLogcatLikeThread(tag = "MyTag")
// equal to the output of `-v thread`
// "V( 1234: 2345) Here's my logged data!"

val threadtimeLogcat = EspressoLogcat.getLogcatLikeThreadTime(tag = "MyTag")
// equal to the output of `-v threadtime`
// "2021-05-14T13:26:12.108  1234  2345 V MyTag: Here's my logged data!"

val timeLogcat = EspressoLogcat.getLogcatLikeTime(tag = "MyTag")
// equal to the output of `-v time`
// "2021-05-14T13:26:12.108 V/MyTag( 1234): Here's my logged data!"
```

Additionally, there are a number of parameters that can be used. When passed in, these mimic adding them to
the `adb logcat` command line

### Logcat Modifiers

_**tag**_: string used to search, equal to just passing in a string to the command line

```kotlin
EspressoLogcat.getLogcatLikeLineData(tag = "MyTag")
// adb logcat -d -s MyTag -v threadtime
```

_**priority**_: used to set the search level for a tag

```kotlin
EspressoLogcat.getLogcatLikeLineData(tag = "MyTag", priority = Priority.VERBOSE)
// adb logcat -d -s MyTag:V
```

_**regex**_: A regex matcher to filter results after they are returned from Logcat
(_Note_: This filters after the response from Logcat, and checks for matches on the `coreData` string.)

```kotlin
EspressoLogcat.getLogcatLikLineData(regex = Regex(".*MyData.*"))
```

_**options**_: vararg strings to pass in any other logcat options
(_Note_: Because the core functionality of espressoLogcat involves parsing string responses, it is very possible that
adding extra options that affect the logcat output will cause exceptions.)

```kotlin
EspressoLogcat.getLogcatLikeLineData(options = arrayOf("-v", "descriptive", "AnotherString"))
// adb logcat -d -s -v descriptive AnotherString
```

### Settings variables

There are additional variables that can be used to modify the response.
_**dateTimeFormatter**_: A date time formatter to format the `date` value of the LineData

```kotlin
EspressoLogcat.Settings.dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME
```

_**bufferedReaderSize**_: An optional parameter to change the size of the buffered reader that processes the logcat

```kotlin
EspressoLogcat.Settings.bufferedReaderSize = 1024 * 8
```

_**filename**_: string used to determine where to store the logcat output
(_Note_: Using this in Espresso will save the file to the emulator)

```kotlin
EspressoLogcat.Settings.filename = "/my/path/to/file.txt"
```

_**sinceTime**_: If set, will only return strings since a certain time. Should be a String in ISO_LOCAL_DATE_TIME format
-- 'YYYY-MM-ddTHH:mm:ss.SSS' (_Note_: Exclusive of maxLines)

```kotlin
EspressoLogcat.Settings.sinceTime = "2021-05-14T13:26:12.108"
```

_**maxLines**_: If set, will only return the most recent X lines (_Note_: Excluse of sinceTime)
```kotlin
EspressoLogcat.Settings.maxLines = 10
```

### Common Logcat Functionality Excluded

- Most custom options passed in via the formatting command (`-v`) are not supported and could cause exceptions if used
- Usually, you would combine both the Regex matching (`-e`) with the Max Line flag (`-m`). Instead of using
  Logcat's `-e` flag, when matching on Regex, EspressoLogcat filters after the responses are returned. This means that
  it isn't reasonable to use the `-m` flag.