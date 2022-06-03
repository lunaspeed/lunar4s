import sbt.Keys.{scalaVersion, scalacOptions}
//enablePlugins(ScalaJSPlugin)

lazy val scala213 = "2.13.10"
lazy val scala212 = "2.12.17"
lazy val scala211 = "2.11.12"
lazy val supportedScalaVersions = Seq(scala213, scala212, scala211)


lazy val root = project.in(file("."))
  .settings(
    publish := {},
    publishLocal := {},
    publishArtifact := false
  )
  .aggregate(lunar.js, lunar.jvm)


lazy val lunar = crossProject(JSPlatform, JVMPlatform).in(file(".")).
    settings(
      name := "lunar",
//      version := "1.1.2-SNAPSHOT",
      organization := "org.lunaspeed",
      scalaVersion := scala213,
      name := "lunar4s",
    ).
  jvmSettings(
    // Add JVM-specific settings here
    crossScalaVersions := supportedScalaVersions,
    libraryDependencies ++= {
      Seq(
        "org.scala-js" %% "scalajs-stubs" % "1.0.0" % "provided",
        "org.scalatest" %% "scalatest" % "3.2.10" % "test"
      )
    },
    javacOptions ++= Seq("-encoding", "UTF-8"),
    scalacOptions ++= Seq(
      //  "-Ypartial-unification",
      "-deprecation",
      "-unchecked",
      "-Xfatal-warnings",
      "-feature",
      "-language:_"
    )
  ).
  jsSettings(
    // Add JS-specific settings here
    crossScalaVersions := Seq(scala213, scala212),
    scalaJSUseMainModuleInitializer := false,
    libraryDependencies ++= Seq(
      "io.github.cquiroz" %%% "scala-java-time" % "2.3.0",
      "io.github.cquiroz" %%% "scala-java-time-tzdb" % "2.3.0"),
    scalaJSLinkerConfig ~= { c =>
      //disable optimization
      //c.withOptimizer(false)
      // ECMAScript
      c.withModuleKind(ModuleKind.ESModule)
    }

    // CommonJS
    // scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.CommonJSModule) }

  )

crossScalaVersions := supportedScalaVersions






