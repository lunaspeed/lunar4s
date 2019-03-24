package org.lunaspeed.lunar4s

import java.time.{LocalDate, LocalDateTime}
import java.util.Date

import org.lunaspeed.lunar4s.LunarDateExtra.Branches.Branch
import org.lunaspeed.lunar4s.LunarDateExtra.{Branches, Stems}
import org.lunaspeed.lunar4s.LunarDateExtra.Stems.Stem

/**
  * Constructor.
  * @param year year of Lunar date
  * @param month month of Lunar date
  * @param date date of Lunar date
  * @param hour hour of original DateTime
  * @param lunarHour lunar hour in terms of Stem(天) or so called 時辰, value from 0 ~ 12.
  * @param isLeap is the month a leap month （閏月）
  */
case class LunarDate(year: Int, month: Int, date: Int, hour: Int, lunarHour: Int, val isLeap: Boolean) {

  import LunarDate.LunarResult

  /**
    * Convert to {@code java.time.LocalDate} in AD.
    * @return equivalent date in LocalDate
    */
  def toLocalDate(): LunarResult[LocalDate] = LunarDate.fromLunar(year, month, date, isLeap)

  /**
    * Convert to {@code java.util.Date} in AD.
    * @return equivalent date in Date
    */
  def toDate(): LunarResult[Date] = LunarDate.fromLunar(year, month, date, isLeap) match {
    case Right(d) => Right(java.sql.Date.valueOf(d))
    case Left(e) => Left(e)
  }

  /**
    * Convert to {@code java.time.LocalDate} in AD.
    * @return equivalent date in Date
    */
  def toLocalDateTime(): LunarResult[LocalDateTime] = LunarDate.fromLunar(year, month, date, isLeap, hour)

  /**
    * Stem (天干) of the year, in form of index starting from 0.
    * @return stem index of the year
    */
  def yearStemIndex(): Int = LunarDate.yearStemIndex(year)

  /**
    * Stem (天干) of the year.
    * @return stem of the year
    * @see [[org.lunaspeed.lunar4s.LunarDateExtra.Stems#getYearStem]]
    */
  def yearStem(): Stem = Stems.getYearStem(yearStemIndex())


  /**
    * Branch (地支) of th year, in form of index starting from 0.
    * @return branch index of the year
    */
  def yearBranchIndex(): Int = LunarDate.yearBranchIndex(year)

  /**
    * Branch (地支) of th year.
    * @return branch of the year
    * @see [[org.lunaspeed.lunar4s.LunarDateExtra.Branches#getYearBranch]]
    */
  def yearBranch(): Branch = Branches.getYearBranch(yearBranchIndex())

  /**
    * Stem of the Lunar hour (時辰).
    * @return hour stem
    * @see [[org.lunaspeed.lunar4s.LunarDateExtra.Stems#getHourStem]]
    */
  def hourStem(): Stem = Stems.getHourStem(lunarHour)
}


class LunarDateArgumentException(message: String) extends RuntimeException(message)
case object YearOutOfRangeException extends LunarDateArgumentException(s"year is out of range $BASE_YEAR - $MAX_YEAR")
case object MonthOutOfRangeException extends LunarDateArgumentException(s"month is out of range 1 - 12")
case object LunarDateOutOfRangeException extends LunarDateArgumentException(s"lunar date is out of range 1 - 30")

object LunarDate {

  type LunarResult[A] = Either[LunarDateArgumentException, A]

  /**
    * Convert from AD date to Lunar date using {@code java.util.Date}.
    * @param date date to convert
    * @return lunar date representation of {@code date} or {@link LunarDateArgumentException} if year is not supported
    */
  def toLunar(date: Date): LunarResult[LunarDate] = toLunar(new java.sql.Timestamp(date.getTime()).toLocalDateTime())

  /**
    * Convert from AD date to Lunar date using {@code java.time.LocalDate} with time at midnight.
    * @param date date to convert
    * @return lunar date representation of {@code date} or {@link LunarDateArgumentException} if year is not supported
    */
  def toLunar(date: LocalDate): LunarResult[LunarDate] = toLunar(date.atStartOfDay())

  /**
    * Convert from AD date to Lunar date using {@code java.time.LocalDateTime}.
    * @param date date to convert
    * @return lunar date representation of {@code date} or {@link LunarDateArgumentException} if year is not supported
    */
  def toLunar(date: LocalDateTime): LunarResult[LunarDate] = {

    val year = date.getYear()
    if (year < BASE_YEAR || year > MAX_YEAR) { //若年份超出範圍
      Left(YearOutOfRangeException)
    }
    else {

      val lunarYear = calculateLunarYear(date)
      var lunarMonth = 0
      var lunarDate = 0
      var isLeap = false
      var dateDiff = daysYearToDate(date)
      var tempLeapMonth = leapMonth(year)
      var lMonth = tempLeapMonth

      val newYearDif = chineseNewYearOffset(year)

      if (dateDiff < newYearDif) {
        tempLeapMonth = leapMonth(year - 1)
        lMonth = tempLeapMonth

        dateDiff = newYearDif - dateDiff
        lunarMonth = 12

        var continue = true
        while (continue) {
          if (lMonth == lunarMonth) {
            val leapMonthDays = daysOfLeapMonth(year)
            if (dateDiff > leapMonthDays) {
              dateDiff = dateDiff - leapMonthDays
            } else {
              isLeap = true
            }
          }
          val lunarMonthDays = daysInMonth(lunarYear, lunarMonth, false)
          if (dateDiff > lunarMonthDays) {
            dateDiff = dateDiff - lunarMonthDays
            isLeap = false
            lunarMonth = lunarMonth - 1
          } else {
            continue = false
          }
        }

        if (dateDiff == 0) {
          lunarDate = 1
          lunarMonth = lunarMonth + 1
        } else {
          lunarDate = daysInMonth(lunarYear, lunarMonth, isLeap) - dateDiff + 1
        }
      }
      else {

        dateDiff = dateDiff - chineseNewYearOffset(year)
        lunarMonth = 1

        var cont = true
        while (cont) {
          val lunarMonthDays = daysInMonth(lunarYear, lunarMonth, false)
          if (dateDiff >= lunarMonthDays) {
            dateDiff = dateDiff - lunarMonthDays
            if (lMonth == lunarMonth) {
              val leapMonthDays = daysOfLeapMonth(year)
              if (dateDiff >= leapMonthDays) {
                dateDiff = dateDiff - leapMonthDays
                lunarMonth = lunarMonth + 1
              } else {
                isLeap = true
                cont = false
              }
            }
            else {
              lunarMonth = lunarMonth + 1
            }

          } else {
            cont = false
          }
        }

        lunarDate = dateDiff + 1
      }
      Right(LunarDate(lunarYear, lunarMonth, lunarDate, date.getHour(), Math.ceil(date.getHour().toDouble / 2D).toInt, isLeap))
    }
  }

  /**
    * Convert Lunar date to AD date.
    * @param lunarYear lunar year
    * @param lunarMonth lunar month
    * @param lunarDate lunar date
    * @param isLeap is the lunar month a leap month
    * @return {@code java.time.LocalDate} representation of Lunar date in AD or {@link LunarDateArgumentException} if parameter is not accepted.
    */
  def fromLunar(lunarYear: Int, lunarMonth: Int, lunarDate: Int, isLeap: Boolean): LunarResult[LocalDate] = fromLunar(lunarYear, lunarMonth, lunarDate, isLeap, 0) match {
    case Right(d) => Right(d.toLocalDate)
    case Left(e) => Left(e)
  }

  /**
    * Convert Lunar date to AD date.
    * @param lunarYear lunar year
    * @param lunarMonth lunar month
    * @param lunarDate lunar date
    * @param isLeap is the lunar month a leap month
    * @param hour the hour of date, default 0
    * @return {@code java.time.LocalDateTime} representation of Lunar date in AD or {@link LunarDateArgumentException} if parameter is not accepted.
    */
  def fromLunar(lunarYear: Int, lunarMonth: Int, lunarDate: Int, isLeap: Boolean, hour: Int = 0): LunarResult[LocalDateTime] = {

    if (lunarYear < BASE_YEAR || lunarYear > MAX_YEAR) { //若年份超出範圍
      Left(YearOutOfRangeException)
    }
    else if (lunarMonth < 1 || lunarMonth > 12) {
      Left(MonthOutOfRangeException)
    }
    else if (lunarDate < 1 || lunarDate > 30) {
      Left(LunarDateOutOfRangeException)
    }
    else if (leapMonth(lunarYear) == 0 && isLeap) {
      Left(new LunarDateArgumentException("no leap month in specified year but isLeap is true"))
    }
    else {
      var year = 0
      var month = 1
      var date = 0
      val lytd = daysLunarYearToDate(lunarYear, lunarMonth, lunarDate, isLeap)

      var dateDiff = lytd - 1 + chineseNewYearOffset(lunarYear)

      val diy = daysInYear(lunarYear)
      if (dateDiff >= diy) {
        dateDiff = dateDiff - diy
        year = lunarYear + 1
      }
      else {
        year = lunarYear
      }
      while (dateDiff >= daysInMonth(year, month)) {

        dateDiff = dateDiff - daysInMonth(year, month)
        month = month + 1
      }

      date = dateDiff + 1

      Right(LocalDateTime.of(year, month, date, hour, 0))
    }
  }

  private def calculateLunarYear(date: LocalDateTime): Int = {
    val year = date.getYear()
    val dateDiff = daysYearToDate(date)
    val newYearDif = chineseNewYearOffset(year)
    if (dateDiff < newYearDif) {
      year - 1
    }
    else {
      year
    }
  }

  private def isLeap(year: Int): Boolean = LocalDate.now().withYear(year).isLeapYear

  private def yearStemIndex(year: Int): Int = (6 + (year - 1900)) % 10

  private def yearBranchIndex(year: Int): Int = (year - 1900) % 12

  private def daysInYear(year: Int): Int = if (isLeap(year)) 366 else 365

  private def daysYearToDate(dt: LocalDateTime): Int = dt.getDayOfYear() - 1

  private def daysLunarYearToDate(lunarYear: Int, lunarMonth: Int, lunarDate: Int, isLeap: Boolean): Int = {

    val lMonth = leapMonth(lunarYear)
    val total = (1 until (if (isLeap) lunarMonth + 1 else lunarMonth)).foldLeft(lunarDate)((s, m) => {
      val offset = if (lMonth != 0 && m > lMonth) 1 else 0
      s + daysInLunarMonth(lunarYear, m - 1 + offset)
    })

    lMonth match {
      case m if m != 0 && lunarMonth > m => total + daysOfLeapMonth(lunarYear)
      case m if m == 0 && isLeap => 0 //something is wrong
      case _ => total
    }

  }

  private def leapMonth(year: Int): Int = (LUNAR_CONSTANTS(year - BASE_YEAR) >> 20)

  private def daysOfLeapMonth(year: Int): Int = daysInLunarMonth(year, leapMonth(year))

  /**
    *
    *
    * @param year
    * @param monthIndex
    * 1月 => 0
    * 12月 & 無閏月 => 11
    * 12月 & 閏3月 => 12
    * @return
    */
  private def isBigMonth(year: Int, monthIndex: Int): Boolean = ((LUNAR_CONSTANTS(year - BASE_YEAR)) & (0x80000 >> monthIndex)) != 0


  //  11010110101011100111101
  //  10
  /**
    *
    *
    * @param year
    * @param monthIndex
    * 1月 => 0
    * 12月 & 無閏月 => 11
    * 12月 & 閏3月 => 12
    * @return
    */
  private def daysInLunarMonth(year: Int, monthIndex: Int): Int =
    if (isBigMonth(year, monthIndex)) {
      30
    }
    else {
      29
    }

  private def daysInMonth(year: Int, month: Int): Int =
    if (month % 2 == (if (month < 8) 1 else 0)) {
      31 //大月31天
    } else if (month == 2) {
      if (isLeap(year)) 29 else 28 //閏年2月29天，非閏年2月28天
    } else {
      30 //小月30天
    }


  private def chineseNewYearOffset(year: Int): Int = {
    val lc = LUNAR_CONSTANTS(year - BASE_YEAR)
    val date = lc % 0x20
    val month = (lc >> 5) % 4
    (if (month > 1) date + 31
    else date) - 1
  }

  private def daysInMonth(year: Int, month: Int, isLeap: Boolean): Int = {

    var m = month
    val lMonth = leapMonth(year)

    if (isLeap) {
      if (m != lMonth) { //防呆
        0
      }
      else {
        //此為閏月需往計算之後一個月的天數
        daysInLunarMonth(year, lMonth)
      }
    }
    else {
      if ((lMonth > 0) && (m > lMonth)) { //若今年有閏月，且該西曆月應在閏月之後再加一個月
        m = m + 1
      }
      daysInLunarMonth(year, m - 1)
    }
  }

  implicit class LocalDateTimeConvert(date: LocalDateTime) {
    def toLunar(): LunarResult[LunarDate] = LunarDate.toLunar(date)
    @throws("LunarDateArgumentException")
    def toLunarUnsafe(): LunarDate = LunarDate.toLunar(date) match {
      case Right(d) => d
      case Left(e) => throw e
    }
  }

  implicit class LocalDateConvert(date: LocalDate) {
    def toLunar(): LunarResult[LunarDate] = LunarDate.toLunar(date)
    @throws("LunarDateArgumentException")
    def toLunarUnsafe(): LunarDate = LunarDate.toLunar(date) match {
      case Right(d) => d
      case Left(e) => throw e
    }
  }

  implicit class UtilDateConvert(date: Date) {
    def toLunar(): LunarResult[LunarDate] = LunarDate.toLunar(date)
    @throws("LunarDateArgumentException")
    def toLunarUnsafe(): LunarDate = LunarDate.toLunar(date) match {
      case Right(d) => d
      case Left(e) => throw e
    }
  }



}
