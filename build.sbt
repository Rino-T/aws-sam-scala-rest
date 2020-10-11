import sbt.Keys._

lazy val helloWorldFunction = (project in file("HelloWorld"))
  .settings(
    name := "HelloWorld",
    version := "0.1",
    scalaVersion := "2.13.3",
    libraryDependencies ++= Seq(
      "com.google.inject" % "guice" % "4.2.3",
      "com.amazonaws" % "aws-lambda-java-core" % "1.2.1",
      "com.amazonaws" % "aws-lambda-java-events" % "3.4.0"
      ),
    scalacOptions ++= Seq(
      "-feature",
      "-deprecation",
      "-Xfatal-warnings"
      ),
    assemblyDefaultJarName in assembly := "HelloWorldFunction.jar"
    )
