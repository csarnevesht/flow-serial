name := """flow-serial"""

version := "1.0"

scalaVersion := "2.11.7"

//add resolver to Bintray's jcenter
resolvers += Resolver.jcenterRepo

libraryDependencies ++= Seq(
  "com.github.jodersky" % "flow-core_2.11" % "2.5.0-SNAPSHOT",
  "com.github.jodersky" % "flow-native" % "2.5.0-RC1" % "runtime",
  "org.scalatest" %% "scalatest" % "2.2.4" % "test",
  "junit" % "junit" % "4.12" % "test",
  "com.novocode" % "junit-interface" % "0.11" % "test"
)


testOptions += Tests.Argument(TestFrameworks.JUnit, "-v")

fork in run := true