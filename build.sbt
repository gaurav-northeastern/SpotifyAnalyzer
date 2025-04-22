name := "SpotifyPlaylistAnalyzer"

version := "1.0"

scalaVersion := "2.13.8"

libraryDependencies ++= Seq(
  "com.lihaoyi" %% "ujson" % "1.4.0",
  "com.lihaoyi" %% "requests" % "0.6.9"
)

Compile / run / mainClass := Some("SpotifyPlaylistAnalyzer")

resolvers += Resolver.sonatypeRepo("releases")
