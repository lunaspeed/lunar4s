package org.lunaspeed.lunar4s.js

import org.lunaspeed.lunar4s.LunarDate

import java.time.ZoneId
import java.util.Date
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("LunarDate")
object LunarDateJs {

  @JSExport
  def toLunar(date: js.Date): LunarDate = {
    LunarDate.toLunar(new Date(date.getTime().toLong)) match {
      case Right(ld) => ld
      case Left(e) => throw e
    }
  }

  @JSExport
  def fromLunar(lunarYear: Int, lunarMonth: Int, lunarDate: Int, isLeap: Boolean): js.Date = {
    LunarDate.fromLunar(lunarYear, lunarMonth, lunarDate, isLeap) match {
      case Right(ld) => new js.Date(ld.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli().toDouble)
      case Left(e) => throw e
    }
  }

  @JSExport
  def daysInMonth(year: Int, month: Int, isLeap: Boolean): Int = LunarDate.daysInMonth(year, month, isLeap)

}
