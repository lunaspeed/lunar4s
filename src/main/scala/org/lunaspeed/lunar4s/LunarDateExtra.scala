package org.lunaspeed.lunar4s

object LunarDateExtra {

  object Branches {

    sealed trait Branch

    case object Jia extends Branch
    case object Yi extends Branch
    case object Bing extends Branch
    case object Ding extends Branch
    case object Wu extends Branch
    case object Ji extends Branch
    case object Geng extends Branch
    case object Xin extends Branch
    case object Ren extends Branch
    case object Gui extends Branch

    final val branches = Seq(Jia, Yi, Bing, Ding, Wu, Ji, Geng, Xin, Ren, Gui)

    /**
      * Get the Year Branch using branch index in Lunar Date
      * @param branchIndex index of Branch
      * @return Branch
      */
    def getYearBranch(branchIndex: Int): Branch = branches(branchIndex % 10)
  }

  object Stems {
    sealed trait Stem
    case object Zi extends Stem
    case object Chou extends Stem
    case object Yin extends Stem
    case object Mao extends Stem
    case object Chen extends Stem
    case object Si extends Stem
    case object Wu extends Stem
    case object Wei extends Stem
    case object Shen extends Stem
    case object You extends Stem
    case object Xu extends Stem
    case object Hai extends Stem

    final val stems = Seq(Zi, Chou, Yin, Mao, Chen, Si, Wu, Wei, Shen, You, Xu, Hai)

    /**
      * Get the Year Stem using stem index in Lunar Date
      * @param stemIndex index of Stem
      * @return Stem
      */
    def getYearStem(stemIndex: Int): Stem = stems(stemIndex % 12)

    /**
      * Get the Hour Stem using lunar hour in Lunar Date. Does not distinguish between early Zi (早子) and late Zi (晚子) .
      * @param lunarHour index of Stem
      * @return Stem
      */
    def getHourStem(lunarHour: Int): Stem = stems(lunarHour % 12)
  }


}
