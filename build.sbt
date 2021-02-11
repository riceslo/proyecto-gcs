
lazy val gcsApp = (project in file("gcs-app"))
  .enablePlugins(PlayScala)
  .settings(
    name := """gcs-app""",
    version := "2.8.x",
    scalaVersion := "2.13.1",
    libraryDependencies ++= Seq(
      guice,
      "com.typesafe.play" %% "play-slick" % "5.0.0",
      "com.typesafe.play" %% "play-slick-evolutions" % "5.0.0",
      "org.postgresql" % "postgresql" % "9.4.1209",
      specs2 % Test,
    ),
    scalacOptions ++= Seq(
      "-feature",
      "-deprecation",
      "-Xfatal-warnings"
    )
  )

lazy val gcsAppIT = (project in file("gcs-it"))
  .enablePlugins(PlayScala)
  .settings(
    name := """gcp-it""",
    version := "2.8.x",
    scalaVersion := "2.13.1",
    libraryDependencies ++= Seq(
      guice,
      "com.typesafe.play" %% "play-slick" % "5.0.0",
      "com.typesafe.play" %% "play-slick-evolutions" % "5.0.0",
      "org.postgresql" % "postgresql" % "9.4.1209",
      "org.postgresql" % "postgresql" % "9.3-1103-jdbc41",
      specs2 % Test
    ),
    scalacOptions ++= Seq(
      "-feature",
      "-deprecation",
      "-Xfatal-warnings"
    )
  ).dependsOn(gcsApp)


onLoad in Global := (Command.process("project gcsApp", _: State)) compose (onLoad in Global).value
