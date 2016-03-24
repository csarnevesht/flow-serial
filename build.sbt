name := """flow-serial"""

version := "1.0"

scalaVersion := "2.11.7"


resolvers := Seq(
  "Scalaz Bintray Repo" at "http://dl.bintray.com/scalaz/releases",
  "typesafe" at "http://repo.typesafe.com/typesafe/releases",
  "Bintray" at "https://jcenter.bintray.com/"
)
//add resolver to Bintray's jcenter
resolvers += Resolver.jcenterRepo


libraryDependencies ++= Seq(
  "org.scalatest" % "scalatest_2.11" % "2.2.4",
  "org.json4s" %% "json4s-native" % "3.2.11",
  "org.specs2" %% "specs2-core" % "2.4.15",
  "com.typesafe" % "config" % "1.2.1",
  "com.typesafe.play" %% "play-ws" % "2.4.0-M2",
  "ch.qos.logback" % "logback-classic" % "1.1.2",
  "com.github.jodersky" % "flow-core_2.11" % "2.5.0-SNAPSHOT",
  "com.github.jodersky" % "flow-native" % "2.5.0-RC1" % "runtime",
  "junit" % "junit" % "4.12" % "test",
  "com.novocode" % "junit-interface" % "0.11" % "test",
  "joda-time" % "joda-time" % "2.7",
  "org.pegdown" % "pegdown" % "1.5.0",
  "info.folone" %% "poi-scala" % "0.15"
)




testOptions += Tests.Argument(TestFrameworks.JUnit, "-v")

fork in run := true