lazy val scala212 = "2.12.8"
lazy val scala211 = "2.11.12"
lazy val supportedScalaVersions = Seq(scala212, scala211)

organization := "org.lunaspeed"

scalaVersion := scala212


name := "lunar4s"
crossScalaVersions := supportedScalaVersions

libraryDependencies ++= {
  Seq(
    "org.scalatest" %% "scalatest" % "3.0.5" % "test"
  )
}

javacOptions ++= Seq("-encoding", "UTF-8")
scalacOptions ++= Seq(
  "-Ypartial-unification",
  "-deprecation",
  "-unchecked",
  "-Xfatal-warnings",
  "-feature",
  "-language:_"
)

  


