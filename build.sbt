ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

libraryDependencies += "com.nylas.sdk" % "nylas-java-sdk" % "1.11.2"

lazy val root = (project in file("."))
  .settings(
    name := "NylasPocSignature2"
  )

