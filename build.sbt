name := "lunar4s"

version := "1.0.0"

organization := "org.lunaspeed" 

libraryDependencies ++= {
  Seq(
    "org.scalatest" %% "scalatest" % "3.0.5" % "test"
  )
}

scalacOptions += "-Ypartial-unification"

//scalacOptions ++= Seq("-groups", "-implicits", "-external-urls:[https://docs.oracle.com/en/java/javase/11/docs/api]")

javacOptions ++= Seq("-encoding", "UTF-8")
