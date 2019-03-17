# Lunar4s

Scala library for converting AD date (西元) to Lunar date (農曆) and back.

Fully converted from [JavaChineseCalendar](https://github.com/magiclen/JavaChineseCalendar) and tested against it.
Modified to extend available years range to 1900 to 2099.

Install: Add to build.sbt

```sbtshell
libraryDependencies += "org.lunaspeed" %% "lunar4s" % "1.0.0"
```

Usage:
```scala

import java.time.LocalDateTime
import org.lunaspeed.lunar4s.LunarDate._

val dt = LocalDateTime.now().withYear(2024).withMonth(11).withDayOfMonth(1).withHour(15)

//to lunar date
val lunarDate = dt.toLunar() match {
  case Right(d) => d
  case Left(e) => 
    println("something went wrong when converting to lunar date, ") 
    throw e
}

//if don't want to worry about exception
val unsafeLunarDate = dt.toLunarUnsafe()

//back to LocalDate
val localDate = lunarDate.toLocalDate() match {
  case Right(d) => d
  case Left(e) =>
    println("something went wrong when converting to AD date, ") 
    throw e  
}

```

### TODO

* Add i18n for Stem and Branch
* Cross compile for 2.11