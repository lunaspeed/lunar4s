ThisBuild / organization := "org.lunaspeed"
ThisBuild / organizationName := "lunaspeed"
ThisBuild / organizationHomepage := Some(url("https://github.com/lunaspeed"))

ThisBuild / scmInfo := Some(
  ScmInfo(
    url("https://github.com/lunaspeed/lunar4s"),
    "scm:git@github.com:lunaspeed/lunar4s.git"
  )
)
ThisBuild / developers := List(
  Developer(
    id    = "lunaspeed",
    name  = "Steven Yang",
    email = "kenshin520@gmail.com",
    url   = url("https://github.com/lunaspeed")
  )
)

ThisBuild / description := "Converting AD date to Lunar date (西元轉農曆)"

ThisBuild / licenses := List("MIT License" -> new URL("http://www.opensource.org/licenses/mit-license.php"))
ThisBuild / homepage := Some(url("https://github.com/lunaspeed/lunar4s"))


// Remove all additional repository other than Maven Central from POM
ThisBuild / pomIncludeRepository := { _ => false }
ThisBuild / publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value) Some("snapshots" at nexus + "content/repositories/snapshots")
  else Some("releases" at nexus + "service/local/staging/deploy/maven2")
}
ThisBuild / publishMavenStyle := true

import xerial.sbt.Sonatype._
sonatypeProjectHosting := Some(GitLabHosting("lunaspeed", "lunar4s", "kenshin520@gmail.com"))



