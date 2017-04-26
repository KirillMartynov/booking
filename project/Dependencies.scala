import sbt._

object Dependencies {
  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.0.1"
  lazy val scalaMock = "org.scalamock" %% "scalamock-scalatest-support" % "3.5.0"
  lazy val springAutoConfig = "org.springframework.boot" % "spring-boot-autoconfigure" % "1.5.1.RELEASE"
  lazy val springStarterWeb = "org.springframework.boot" % "spring-boot-starter-web" % "1.5.1.RELEASE"
}
