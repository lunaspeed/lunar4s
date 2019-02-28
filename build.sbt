name := "lunar4s"

version := "1.0.0"

organization := "org.lunary" 

scalaVersion := "2.12.8"

libraryDependencies ++= {
  Seq(
    "org.scalatest" %% "scalatest" % "3.0.5" % "test"
  )
}

scalacOptions += "-Ypartial-unification"

javacOptions ++= Seq("-encoding", "UTF-8")

