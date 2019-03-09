name := "lunar4s"

useGpg := true

version := "1.0.0"

organization := "org.lunary" 

libraryDependencies ++= {
  Seq(
    "org.scalatest" %% "scalatest" % "3.0.5" % "test"
  )
}

scalacOptions += "-Ypartial-unification"

javacOptions ++= Seq("-encoding", "UTF-8")

pomExtra :=
  <licenses>
    <license>
      <name>MIT License</name>
      <url>http://www.opensource.org/licenses/mit-license.php</url>
      <distribution>repo</distribution>
    </license>
  </licenses>