ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.9.0"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.20"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.20" % "test"

lazy val root = (project in file("."))
  .settings(
    name := "k8s-portfolio-app"
  )
