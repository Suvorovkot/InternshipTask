name := "InternshipTask"

version := "0.1"

scalaVersion := "2.12.8"

libraryDependencies ++= Seq(
  "org.json4s" %% "json4s-jackson" % "3.6.5",
  "com.typesafe.akka" %% "akka-actor" % "2.5.21",
  "com.typesafe.akka" %% "akka-stream" % "2.5.21",
  "com.typesafe.akka" %% "akka-http" % "10.1.7"
)

libraryDependencies ++= Seq(
  "org.postgresql" % "postgresql" % "9.3-1100-jdbc4",
  "com.typesafe.slick" %% "slick" % "2.1.0",
  "org.slf4j" % "slf4j-nop" % "1.6.4",
  "org.scalatest" %% "scalatest" % "3.0.5" % Test,
  "org.scalamock" %% "scalamock" % "4.1.0" % Test,
  "com.typesafe.akka" %% "akka-stream-testkit" % "2.5.21" % Test,
  "com.typesafe.akka" %% "akka-http-testkit" % "10.1.7" % Test,
  "com.typesafe.akka" %% "akka-testkit" % "2.5.21" % Test
)

//enablePlugins(DockerPlugin, JavaAppPackaging)
//
//dockerImageCreationTask := (publishLocal in Docker).value