# Lunar4s

Scala library for converting AD date (西元) to Lunar date (農曆) and back.

Fully converted from [JavaChineseCalendar](https://github.com/magiclen/JavaChineseCalendar) and tested against it.
Modified to extend available years range to 1900 to 2099.

Available for 2.11, 2.12 and 2.13.
Support ScalaJs for 2.12 and 2.13

Add to build.sbt

```sbtshell
//scala
libraryDependencies += "org.lunaspeed" %% "lunar4s" % "1.1.1"

//scalajs
libraryDependencies += "org.lunaspeed" %%% "lunar4s" % "1.1.1"
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

24 Solar Terms (24節氣)

Currently, only support 21st century.

```scala

import org.lunaspeed.lunar4s.SolarTerms

SolarTerms.termAsDate(2017, SolarTerms.SpringCommences)
// Some(LocalDate.of(2017, 2,3))


//get all Solar Terms in one year
SolarTerms.getYearTerms(2017): Map[SolarTerm, LocalDate]

```

JavaScript

```javascript
import { LunarDate } from 'lunar4s-opt'

const lunarDate = LunarDate.toLunar(new Date())
```

### TODO

* Add i18n for Stem and Branch
* ~~Cross compile for 2.11~~