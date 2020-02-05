name := "magnolia-example-atto"

version := "0.1"

scalaVersion := "2.13.1"

lazy val macros = project.settings(
  libraryDependencies ++= List(
    "com.propensive" %% "magnolia" % "0.10.0",
    "org.tpolecat" %% "atto-core" % "0.7.2"
  )
)

lazy val example = project.dependsOn(macros)