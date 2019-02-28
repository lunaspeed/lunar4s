package org.lunary.lunar4s

import java.time.LocalDateTime

import org.scalatest.FlatSpec
import LunarDate._

class LunarDateSpec extends FlatSpec {

  "lunar dates" should "match java and convert back" in {

    val based = LocalDateTime.now().withYear(2024).withMonth(11).withDayOfMonth(1).withHour(15)
    for (i <- 0 to 3600) {
      val date = based.plusDays(i)
      date.toLunar() match {
        case Right(lunarDate) =>

          println(s"year: ${date.getYear()}, month: ${date.getMonthValue()}, day: ${date.getDayOfMonth()}")
          val 農曆日期 = 農曆.建立(date.getYear(), date.getMonthValue(), date.getDayOfMonth())

          assertResult((農曆日期.取得農曆月().ordinal() + 1) + "/" + (農曆日期.取得農曆日().ordinal() + 1), "lunar month/date does not match Java library")(lunarDate.month + "/" + lunarDate.getDate)
          assertResult(農曆日期.是否為閏月(), "lunar month lear does not match java library")(lunarDate.isLeap)

          println("------------")
          LunarDate.fromLunar(lunarDate.year, lunarDate.month, lunarDate.getDate, lunarDate.isLeap) match {
            case Right(convertedDate) =>
              val 日期 = 農曆.建立(lunarDate.year, 農曆.月.values()(lunarDate.month - 1), lunarDate.isLeap, 農曆.日.values()(lunarDate.getDate - 1))

              println(s"date: ${convertedDate}, lunar: ${lunarDate.year}/${lunarDate.month}/${lunarDate.getDate}, t2: $date")
              println("============")
              assertResult(日期.取得西曆年(), "converted year does not match java library")(convertedDate.getYear())
              assertResult(日期.取得西曆月(), "converted month does not match java library")(convertedDate.getMonthValue())
              assertResult(日期.取得西曆日(), "converted date does not match java library")(convertedDate.getDayOfMonth())
              assertResult(date.getYear(), "converted year does not match original")(convertedDate.getYear())
              assertResult(date.getMonth(), "converted month does not match original")(convertedDate.getMonth())
              assertResult(date.getDayOfMonth(), "converted date does not match original")(convertedDate.getDayOfMonth())
            case Left(e) => fail(e)
          }
        case Left(e) => fail(e)
      }
    }

  }

}
