package org.lunaspeed.lunar4s

import java.time.LocalDate

/**
  * Currently only support 21st century.
  */
object SolarTerms {

  //計算方式 https://blog.csdn.net/qq_20490553/article/details/119039040
  //對著網站 https://destiny.to/app/calendar/SolarTerms
  private final val D: Double = 0.2422

  sealed trait SolarTerm {
    protected val centuryMap: Map[Int, Double]
    protected val exceptionOffSetMap: Map[Int, Int] = Map.empty

    def getC(year: Int): Option[Double] = {
      val century = if (year % 100 == 0) {
        year / 100
      } else {
        (year / 100) + 1
      }
      centuryMap.get(century)
    }

    def getExceptionOffset(year: Int): Int =
      exceptionOffSetMap.getOrElse(year, 0)

    def month: Int

    def leapYearOffset = 0
  }

  //  春

  /**
    * 立春
    */
  case object SpringCommences extends SolarTerm {
    protected val centuryMap: Map[Int, Double] = Map(21 -> 3.87D, 22 -> 4.15D)
    override val leapYearOffset = 1
    val month: Int = 2
  }

  /**
    * 雨水
    */
  case object SpringShowers extends SolarTerm {
    protected val centuryMap: Map[Int, Double] = Map(21 -> 18.73)
    override protected val exceptionOffSetMap: Map[Int, Int] = Map(2026 -> -1)
    override val leapYearOffset = 1
    val month: Int = 2
  }

  /**
    * 驚蟄
    */
  case object InsectsWaken extends SolarTerm {
    protected val centuryMap: Map[Int, Double] = Map(21 -> 5.63D)

    val month: Int = 3
  }

  /**
    *  春分
    */
  case object VernalEquinox extends SolarTerm {
    protected val centuryMap: Map[Int, Double] = Map(21 -> 20.646D)
    override protected val exceptionOffSetMap: Map[Int, Int] = Map(2084 -> 1)
    val month: Int = 3
  }

  /**
    * 清明
    */
  case object BrightAndClean extends SolarTerm {
    protected val centuryMap: Map[Int, Double] = Map(21 -> 4.81D)

    val month: Int = 4
  }

  case object CornRain extends SolarTerm { // 穀雨 Corn Rain
    protected val centuryMap: Map[Int, Double] = Map(21 -> 20.1D)

    val month: Int = 4
  }

  //  夏

  /**
    * 立夏
    */
  case object SummerCommences extends SolarTerm {
    protected val centuryMap: Map[Int, Double] = Map(21 -> 5.52D)
    val month: Int = 5
  }

  /**
    * 小滿
    */
  case object CornForms extends SolarTerm {
    protected val centuryMap: Map[Int, Double] = Map(21 -> 21.04D)
    override protected val exceptionOffSetMap: Map[Int, Int] = Map(2008 -> 1)
    val month: Int = 5
  }

  /**
    * 芒種
    */
  case object CornOnEar extends SolarTerm {
    protected val centuryMap: Map[Int, Double] = Map(21 -> 5.678D)
    val month: Int = 6
  }

  /**
    * 夏至
    */
  case object SummerSolstice extends SolarTerm {
    protected val centuryMap: Map[Int, Double] = Map(21 -> 21.37D)
    val month: Int = 6
  }

  /**
    * 小暑
    */
  case object ModerateHeat extends SolarTerm {
    protected val centuryMap: Map[Int, Double] = Map(21 -> 7.108D)
    val month: Int = 7
  }

  /**
    * 大暑
    */
  case object GreatHeat extends SolarTerm {
    protected val centuryMap: Map[Int, Double] = Map(21 -> 22.83D)
    val month: Int = 7
  }

  //  秋
  /**
    * 立秋
    */
  case object AutumnCommences extends SolarTerm {
    protected val centuryMap: Map[Int, Double] = Map(21 -> 7.5D)
    override protected val exceptionOffSetMap: Map[Int, Int] = Map(2002 -> 1)
    val month: Int = 8
  }

  /**
    * 處暑
    */
  case object EndOfHeat extends SolarTerm {
    protected val centuryMap: Map[Int, Double] = Map(21 -> 23.13D)
    val month: Int = 8
  }

  /**
    * 白露
    */
  case object WhiteDew extends SolarTerm {
    protected val centuryMap: Map[Int, Double] = Map(21 -> 7.646D)
    val month: Int = 9
  }

  /**
    * 秋分
    */
  case object AutumnalEquinox extends SolarTerm {
    protected val centuryMap: Map[Int, Double] = Map(21 -> 23.042D)
    val month: Int = 9
  }

  /**
    * 寒露
    */
  case object ColdDew extends SolarTerm {
    protected val centuryMap: Map[Int, Double] = Map(21 -> 8.318D)
    val month: Int = 10
  }

  /**
    * 霜降
    */
  case object Frost extends SolarTerm {
    protected val centuryMap: Map[Int, Double] = Map(21 -> 23.438D)
    val month: Int = 10
  }

  //  冬

  /**
    * 立冬
    */
  case object WinterCommences extends SolarTerm {
    protected val centuryMap: Map[Int, Double] = Map(21 -> 7.438D)
    override protected val exceptionOffSetMap: Map[Int, Int] = Map(2089 -> 1)
    val month: Int = 11
  }

  /**
    * 小雪
    */
  case object LightSnow extends SolarTerm {
    protected val centuryMap: Map[Int, Double] = Map(21 -> 22.36D)
    val month: Int = 11
  }

  /**
    * 大雪
    */
  case object HeavySnow extends SolarTerm {
    protected val centuryMap: Map[Int, Double] = Map(21 -> 7.18D)
    val month: Int = 12
  }

  /**
    * 冬至
    */
  case object WinterSolstice extends SolarTerm {
    protected val centuryMap: Map[Int, Double] = Map(21 -> 21.94D)
    override protected val exceptionOffSetMap: Map[Int, Int] = Map(2021 -> -1) //1918年和2021年的计算结果减1日。
    val month: Int = 12
  }

  /**
    * 小寒
    */
  case object ModerateCold extends SolarTerm {
    protected val centuryMap: Map[Int, Double] = Map(21 -> 5.4055D)
    override protected val exceptionOffSetMap: Map[Int, Int] = Map(2019 -> -1) //1982年计算结果加1日，2019年减1日。
    override val leapYearOffset = 1
    val month: Int = 1
  }

  /**
    * 大寒
    */
  case object SevereCold extends SolarTerm {
    protected val centuryMap: Map[Int, Double] = Map(21 -> 20.12D)
    override protected val exceptionOffSetMap: Map[Int, Int] = Map(2082 -> 1)
    override val leapYearOffset = 1
    val month: Int = 1
  }

  private val termMap: Map[SolarTerm, String] = Map(
    SpringCommences -> "立春",
    SpringShowers -> "雨水",
    InsectsWaken -> "驚蟄",
    VernalEquinox -> "春分",
    BrightAndClean -> "清明",
    CornRain -> "穀雨",
    SummerCommences -> "立夏",
    CornForms -> "小滿",
    CornOnEar -> "芒種",
    SummerSolstice -> "夏至",
    ModerateHeat -> "小暑",
    GreatHeat -> "大暑",
    AutumnCommences -> "立秋",
    EndOfHeat -> "處暑",
    WhiteDew -> "白露",
    AutumnalEquinox -> "秋分",
    ColdDew -> "寒露",
    Frost -> "霜降",
    WinterCommences -> "立冬",
    LightSnow -> "小雪",
    HeavySnow -> "大雪",
    WinterSolstice -> "冬至",
    ModerateCold -> "小寒",
    SevereCold -> "大寒"
  )

  /**
    * get date of month for the SolarTerm in the year
    * @param year
    * @param term
    * @return
    */
  def termDate(year: Int, term: SolarTerm): Option[Int] = {

    //[YD+C]-L
    val y = year % 100
    term.getC(year).map(c => {
      val ydc = (y * D) + c
      val l = (y - term.leapYearOffset) / 4
      val exceptionOffset = term.getExceptionOffset(year)
      (ydc - l + exceptionOffset).toInt
    })
  }

  /**
    * get date for the SolarTerm in the year
    * @param year
    * @param term
    * @return
    */
  def termAsDate(year: Int, term: SolarTerm): Option[LocalDate] =
    termDate(year, term).map(d => LocalDate.of(year, term.month, d))

  /**
    * get SolarTerm Chinese name
    * @param term
    * @return
    */
  def toChinese(term: SolarTerm): String = termMap(term)

  /**
    * get SolarTerms for the whole year
    * @param year
    * @return
    */
  def getYearTerms(year: Int): Map[SolarTerm, LocalDate] = {
    termMap.transform((term, _) => termAsDate(year, term))
      .collect({ case (term, Some(d)) => (term, d) })
  }

}

