name := "lunar4s"

organization := "org.lunaspeed"

version := "1.0.0"

libraryDependencies ++= {
  Seq(
    "org.scalatest" %% "scalatest" % "3.0.5" % "test"
  )
}

scalacOptions += "-Ypartial-unification"

javacOptions ++= Seq("-encoding", "UTF-8")
