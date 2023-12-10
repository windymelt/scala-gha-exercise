import scala.sys.process.Process

val scala3Version = "3.3.1"

lazy val root = project
  .in(file("."))
  .enablePlugins(
    ScalaJSPlugin,
    ScalablyTypedConverterExternalNpmPlugin
  )
  .settings(
    name := "scala-gha-exercise",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := scala3Version,
    scalaJSUseMainModuleInitializer := true,
    scalaJSLinkerConfig ~= {
      _.withModuleKind(ModuleKind.CommonJSModule)
    },
    Compile / scalaJSMainModuleInitializer ~= {
      _.map(_.withModuleID("index"))
    },
    externalNpm := {
      Process(Seq("yarn"), baseDirectory.value).!
      baseDirectory.value
    },
    stOutputPackage := "npm",
    Compile / stMinimize := Selection.AllExcept(
      "@actions/core",
      "@actions/github"
    ),
    libraryDependencies += "org.scalameta" %% "munit" % "0.7.29" % Test
  )
