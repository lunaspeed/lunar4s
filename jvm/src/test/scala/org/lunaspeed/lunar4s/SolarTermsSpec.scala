package org.lunaspeed.lunar4s

import org.scalatest.flatspec._
import org.scalatest.matchers._
import SolarTerms._

import java.time.LocalDate

class SolarTermsSpec extends AnyFlatSpec with should.Matchers {

  "立春" should "have date" in {
    val term = SpringCommences
    assertResult(termDate(2058, term), "date does not match")(Some(3))

    assertResult(Some(LocalDate.of(2017, 2,3)), "date does not match")(termAsDate(2017, term))
    assertResult(Some(LocalDate.of(2021, 2,3)), "date does not match")(termAsDate(2021, term))
    assertResult(Some(LocalDate.of(2022, 2,4)), "date does not match")(termAsDate(2022, term))
    assertResult(Some(LocalDate.of(2023, 2,4)), "date does not match")(termAsDate(2023, term))
    assertResult(Some(LocalDate.of(2024, 2,4)), "date does not match")(termAsDate(2024, term))
    assertResult(Some(LocalDate.of(2025, 2,3)), "date does not match")(termAsDate(2025, term))
    assertResult(Some(LocalDate.of(2026, 2,4)), "date does not match")(termAsDate(2026, term))
    assertResult(Some(LocalDate.of(2058, 2,3)), "date does not match")(termAsDate(2058, term))
    assertResult(Some(LocalDate.of(2088, 2,4)), "date does not match")(termAsDate(2088, term))
  }

  "雨水" should "have date" in {
    val term = SpringShowers
    assertResult(Some(LocalDate.of(2008, 2,19)), "date does not match")(termAsDate(2008, term))
    assertResult(Some(LocalDate.of(2022, 2,19)), "date does not match")(termAsDate(2022, term))
    assertResult(Some(LocalDate.of(2023, 2,19)), "date does not match")(termAsDate(2023, term))
    assertResult(Some(LocalDate.of(2026, 2,18)), "date does not match")(termAsDate(2026, term))

  }

  "驚蟄" should "have date" in {
    val term = InsectsWaken
    assertResult(Some(LocalDate.of(2022, 3,5)), "date does not match")(termAsDate(2022, term))
    assertResult(Some(LocalDate.of(2023, 3,6)), "date does not match")(termAsDate(2023, term))
  }

  "春分" should "have date" in {
    val term = VernalEquinox
    assertResult(Some(LocalDate.of(2022, 3,20)), "date does not match")(termAsDate(2022, term))
    assertResult(Some(LocalDate.of(2023, 3,21)), "date does not match")(termAsDate(2023, term))
  }

  "清明" should "have date" in {
    val term = BrightAndClean
    assertResult(Some(LocalDate.of(2022, 4,5)), "date does not match")(termAsDate(2022, term))
    assertResult(Some(LocalDate.of(2023, 4,5)), "date does not match")(termAsDate(2023, term))
  }

  "穀雨" should "have date" in {
    val term = CornRain
    assertResult(Some(LocalDate.of(2022, 4,20)), "date does not match")(termAsDate(2022, term))
    assertResult(Some(LocalDate.of(2023, 4,20)), "date does not match")(termAsDate(2023, term))
  }

  "立夏" should "have date" in {
    val term = SummerCommences
    assertResult(Some(LocalDate.of(2022, 5,5)), "date does not match")(termAsDate(2022, term))
    assertResult(Some(LocalDate.of(2023, 5,6)), "date does not match")(termAsDate(2023, term))
  }

  "小滿" should "have date" in {
    val term = CornForms
    assertResult(Some(LocalDate.of(2022, 5,21)), "date does not match")(termAsDate(2022, term))
    assertResult(Some(LocalDate.of(2023, 5,21)), "date does not match")(termAsDate(2023, term))
  }

  "芒種" should "have date" in {
    val term = CornOnEar
    assertResult(Some(LocalDate.of(2022, 6,6)), "date does not match")(termAsDate(2022, term))
    assertResult(Some(LocalDate.of(2023, 6,6)), "date does not match")(termAsDate(2023, term))
  }

  "夏至" should "have date" in {
    val term = SummerSolstice
    assertResult(Some(LocalDate.of(2022, 6,21)), "date does not match")(termAsDate(2022, term))
    assertResult(Some(LocalDate.of(2023, 6,21)), "date does not match")(termAsDate(2023, term))
    assertResult(Some(LocalDate.of(2088, 6,20)), "date does not match")(termAsDate(2088, term))
  }

  "小暑" should "have date" in {
    val term = ModerateHeat
    assertResult(Some(LocalDate.of(2022, 7,7)), "date does not match")(termAsDate(2022, term))
    assertResult(Some(LocalDate.of(2023, 7,7)), "date does not match")(termAsDate(2023, term))
  }

  "大暑" should "have date" in {
    val term = GreatHeat
    assertResult(Some(LocalDate.of(2022, 7,23)), "date does not match")(termAsDate(2022, term))
    assertResult(Some(LocalDate.of(2023, 7,23)), "date does not match")(termAsDate(2023, term))
  }

  "立秋" should "have date" in {
    val term = AutumnCommences
    assertResult(Some(LocalDate.of(2022, 8,7)), "date does not match")(termAsDate(2022, term))
    assertResult(Some(LocalDate.of(2023, 8,8)), "date does not match")(termAsDate(2023, term))
  }

  "處暑" should "have date" in {
    val term = EndOfHeat
    assertResult(Some(LocalDate.of(2022, 8,23)), "date does not match")(termAsDate(2022, term))
    assertResult(Some(LocalDate.of(2023, 8,23)), "date does not match")(termAsDate(2023, term))
  }

  "白露" should "have date" in {
    val term = WhiteDew
    assertResult(Some(LocalDate.of(2022, 9,7)), "date does not match")(termAsDate(2022, term))
    assertResult(Some(LocalDate.of(2023, 9,8)), "date does not match")(termAsDate(2023, term))
  }

  "秋分" should "have date" in {
    val term = AutumnalEquinox
    assertResult(Some(LocalDate.of(2022, 9,23)), "date does not match")(termAsDate(2022, term))
    assertResult(Some(LocalDate.of(2023, 9,23)), "date does not match")(termAsDate(2023, term))
  }

  "寒露" should "have date" in {
    val term = ColdDew
    assertResult(Some(LocalDate.of(2022, 10,8)), "date does not match")(termAsDate(2022, term))
    assertResult(Some(LocalDate.of(2023, 10,8)), "date does not match")(termAsDate(2023, term))
  }

  "霜降" should "have date" in {
    val term = Frost
    assertResult(Some(LocalDate.of(2022, 10,23)), "date does not match")(termAsDate(2022, term))
    assertResult(Some(LocalDate.of(2023, 10,24)), "date does not match")(termAsDate(2023, term))
  }

  "立冬" should "have date" in {
    val term = WinterCommences
    assertResult(Some(LocalDate.of(2022, 11,7)), "date does not match")(termAsDate(2022, term))
    assertResult(Some(LocalDate.of(2023, 11,8)), "date does not match")(termAsDate(2023, term))
  }

  "小雪" should "have date" in {
    val term = LightSnow
    assertResult(Some(LocalDate.of(2022, 11,22)), "date does not match")(termAsDate(2022, term))
    assertResult(Some(LocalDate.of(2023, 11,22)), "date does not match")(termAsDate(2023, term))
  }

  "大雪" should "have date" in {
    val term = HeavySnow
    assertResult(Some(LocalDate.of(2022, 12,7)), "date does not match")(termAsDate(2022, term))
    assertResult(Some(LocalDate.of(2023, 12,7)), "date does not match")(termAsDate(2023, term))
  }

  "冬至" should "have date" in {
    val term = WinterSolstice
    assertResult(Some(LocalDate.of(2022, 12,22)), "date does not match")(termAsDate(2022, term))
    assertResult(Some(LocalDate.of(2023, 12,22)), "date does not match")(termAsDate(2023, term))
  }

  "小寒" should "have date" in {
    val term = ModerateCold
    assertResult(Some(LocalDate.of(2022, 1,5)), "date does not match")(termAsDate(2022, term))
    assertResult(Some(LocalDate.of(2023, 1,5)), "date does not match")(termAsDate(2023, term))
  }

  "大寒" should "have date" in {
    val term = SevereCold
    assertResult(None, "date is not empty")(termAsDate(2000, term))
    assertResult(Some(LocalDate.of(2022, 1,20)), "date does not match")(termAsDate(2022, term))
    assertResult(Some(LocalDate.of(2023, 1,20)), "date does not match")(termAsDate(2023, term))
  }
}
