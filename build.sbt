ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "3.9.0"

val Http4sVersion = "0.23.37"
val CatsEffectVersion = "3.7.1"
val Log4catsVersion = "2.8.0"
val LogbackClassicVersion = "1.6.4"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.20"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.20" % "test"

libraryDependencies ++= Seq(
  "org.http4s" %% "http4s-ember-client" % Http4sVersion,
  "org.http4s" %% "http4s-ember-server" % Http4sVersion,
  "org.http4s" %% "http4s-dsl"          % Http4sVersion,
  "org.typelevel" %% "cats-effect" % CatsEffectVersion,
  "org.typelevel" %% "log4cats-slf4j"   % Log4catsVersion,
  "ch.qos.logback" % "logback-classic" % LogbackClassicVersion
)

lazy val root = (project in file("."))
  .settings(
    name := "k8s-portfolio-app",
    Compile / unmanagedResourceDirectories += baseDirectory.value / "charts" / "files"
  )

Compile / run / fork := true