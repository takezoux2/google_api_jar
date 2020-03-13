import Dependencies._

ThisBuild / scalaVersion     := "2.12.8"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.takezoux2"
ThisBuild / organizationName := "googleapijar"

val GoogleAPIVersion = "1.30.4"

lazy val root = (project in file("."))
  .settings(
    name := "google_api_jar",
    libraryDependencies ++= Seq(
      "com.google.api-client" % "google-api-client" % GoogleAPIVersion,
      "com.google.oauth-client" % "google-oauth-client-jetty" % GoogleAPIVersion,
      "com.google.apis" % "google-api-services-drive" % "v3-rev173-1.25.0",
      "com.google.apis" % "google-api-services-cloudresourcemanager" % "v1-rev563-1.25.0",
      scalaTest % Test
    ),
    mainClass in assembly := Some("Main")
  )

// Uncomment the following for publishing to Sonatype.
// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for more detail.

// ThisBuild / description := "Some descripiton about your project."
// ThisBuild / licenses    := List("Apache 2" -> new URL("http://www.apache.org/licenses/LICENSE-2.0.txt"))
// ThisBuild / homepage    := Some(url("https://github.com/example/project"))
// ThisBuild / scmInfo := Some(
//   ScmInfo(
//     url("https://github.com/your-account/your-project"),
//     "scm:git@github.com:your-account/your-project.git"
//   )
// )
// ThisBuild / developers := List(
//   Developer(
//     id    = "Your identifier",
//     name  = "Your Name",
//     email = "your@email",
//     url   = url("http://your.url")
//   )
// )
// ThisBuild / pomIncludeRepository := { _ => false }
// ThisBuild / publishTo := {
//   val nexus = "https://oss.sonatype.org/"
//   if (isSnapshot.value) Some("snapshots" at nexus + "content/repositories/snapshots")
//   else Some("releases" at nexus + "service/local/staging/deploy/maven2")
// }
// ThisBuild / publishMavenStyle := true
