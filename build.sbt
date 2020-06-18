name := "TODO"
organization := "edge.labs"
version := "0.1.0-SNAPSHOT"
scalaVersion := "2.13.1"

scalacOptions ++= Seq(
    "-encoding","UTF-8",
    "-unchecked",
    "-deprecation",
    "-feature",
    "-language:existentials",
    "-language:higherKinds",
    "-language:implicitConversions",
    "-language:postfixOps",
    "-Ywarn-dead-code",
    "-Ywarn-infer-any",
    "-Ywarn-unused-import",
    "-Xfatal-warnings",
    "-Xlint"
)

libraryDependencies += "io.gatling.highcharts" % "gatling-charts-highcharts" % "3.3.1" % Test
libraryDependencies += "io.gatling" % "gatling-test-framework" % "3.3.1" % Test

lazy val GatlingTest = config("gatling") extend Test
lazy val root = (project in file("."))
  .enablePlugins(GatlingPlugin)
  .configs(GatlingTest)
  .settings(inConfig(GatlingTest)(Defaults.testSettings): _*)
  .settings(
    scalaSource in GatlingTest := baseDirectory.value / "/gatling/simulation"
  )
