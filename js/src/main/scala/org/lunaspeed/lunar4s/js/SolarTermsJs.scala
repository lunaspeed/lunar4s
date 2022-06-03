package org.lunaspeed.lunar4s.js

import org.lunaspeed.lunar4s.SolarTerms._

import java.time.ZoneId
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSExport, JSExportAll, JSExportTopLevel}


@JSExportTopLevel("SolarTerms")
object SolarTermsJs {


  @JSExportAll
  case class SolarTerm(date: js.Date, name: String)

  private val termMap = Map(
    "SpringCommences" -> (SpringCommences, "立春"),
    "SpringShowers" -> (SpringShowers, "雨水"),
    "InsectsWaken" -> (InsectsWaken, "驚蟄"),
    "VernalEquinox" -> (VernalEquinox, "春分"),
    "BrightAndClean" -> (BrightAndClean, "清明"),
    "CornRain" -> (CornRain, "穀雨"),
    "SummerCommences" -> (SummerCommences, "立夏"),
    "CornForms" -> (CornForms, "小滿"),
    "CornOnEar" -> (CornOnEar, "芒種"),
    "SummerSolstice" -> (SummerSolstice, "夏至"),
    "ModerateHeat" -> (ModerateHeat, "小暑"),
    "GreatHeat" -> (GreatHeat, "大暑"),
    "AutumnCommences" -> (AutumnCommences, "立秋"),
    "EndOfHeat" -> (EndOfHeat, "處暑"),
    "WhiteDew" -> (WhiteDew, "白露"),
    "AutumnalEquinox" -> (AutumnalEquinox, "秋分"),
    "ColdDew" -> (ColdDew, "寒露"),
    "Frost" -> (Frost, "霜降"),
    "WinterCommences" -> (WinterCommences, "立冬"),
    "LightSnow" -> (LightSnow, "小雪"),
    "HeavySnow" -> (HeavySnow, "大雪"),
    "WinterSolstice" -> (WinterSolstice, "冬至"),
    "ModerateCold" -> (ModerateCold, "小寒"),
    "SevereCold" -> (SevereCold, "大寒")
  )

  @JSExport
  def getTermDate(year: Int, term: String): SolarTerm =
    (for {
      t <- termMap.get(term)
      date <- termAsDate(year, t._1)
    } yield {

      val instant = date.atStartOfDay(ZoneId.systemDefault).toInstant
      SolarTerm(new js.Date(instant.toEpochMilli), t._2)
    }).getOrElse(null)

  @JSExport
  def getYearTerms(year: Int): js.Dictionary[SolarTerm] = {
    val result = js.Dictionary.empty[SolarTerm]
    termMap.transform((k, _) => getTermDate(year, k)).foreach { case (key, value) => result(key) = value }
    result
  }

  // need scalajs 2.13
  //  def getYearTerms(year: Int): js.Dictionary[SolarTerm] = termMap.transform((k, _) => getTermDate(year, k)).toJSDictionary

}
