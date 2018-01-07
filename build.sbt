name := "TODO"
organization := "edge.labs"
version := "0.1.0-SNAPSHOT"
scalaVersion := "2.12.4"

javacOptions ++= Seq("-source", "1.8", "-target", "1.8")
scalacOptions ++= Seq(
    "-encoding","UTF-8",
    "-target:jvm-1.8",
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

libraryDependencies += "io.gatling.highcharts" % "gatling-charts-highcharts" % "2.3.0" % Test
libraryDependencies += "io.gatling" % "gatling-test-framework" % "2.3.0" % Test

lazy val GatlingTest = config("gatling") extend Test
lazy val root = (project in file("."))
  .enablePlugins(GatlingPlugin)
  .configs(GatlingTest)
  .settings(inConfig(GatlingTest)(Defaults.testSettings): _*)
  .settings(
    scalaSource in GatlingTest := baseDirectory.value / "/gatling/simulation"
  )
  
// Documentation for this project:
//    sbt "project docs" "~ paradox"
//    open docs/target/paradox/site/index.html
lazy val docs = (project in file("docs")).enablePlugins(ParadoxPlugin)