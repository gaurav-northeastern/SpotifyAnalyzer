import requests._
import ujson._

object SpotifyPlaylistAnalyzer {
  val accessToken = "BQCNim2v7cl3L_w-l5gJiFii3-tAwF9a3YunHO2vRvXZR9xu3pLo5zoa2dZO5okL40pzgJ7Kld_NNuZlLHaczmjx2rlk-f8zO2p1QWlnsiatH5Gexuhv5ZxhEUgY35ZCYYnad90H-2-ea8MUPBqY6yKKsIuIZUmVmsidu84ymAMzR0Q0wtacfSZD4MmRa6l5HS7SxFN8B0HKAIJ94qPo_YP4pLhAg0XGQxTPjDpTjGzAmDULt7KVF7z7OPlnx9ymHGi_NCDBTpkcYjZUgUBLpbLhdwwP0oSCIF0HLF_p317qwtKkD72Shjd7Rkdk"
  val playlistId = "5Rrf7mqN8uus2AaQQQNdc1"

  def getPlaylistData(): ujson.Value = {
    val response = requests.get(
      s"https://api.spotify.com/v1/playlists/$playlistId",
      headers = Map("Authorization" -> s"Bearer $accessToken")
    )
    ujson.read(response.text())
  }

  def getTop10LongestSongs(playlistData: ujson.Value): List[(String, Int, List[String])] = {
    val tracks = playlistData("tracks")("items").arr
    val songData = tracks.map { track =>
      val name = track("track")("name").str
      val duration = track("track")("duration_ms").num.toInt
      val artistIds = track("track")("artists").arr.map(_("id").str).toList
      (name, duration, artistIds)
    }
    songData.sortBy(-_._2).take(10).toList
  }

  def getArtistDetails(artistId: String): (String, Int) = {
    val response = requests.get(
      s"https://api.spotify.com/v1/artists/$artistId",
      headers = Map("Authorization" -> s"Bearer $accessToken")
    )
    val json = ujson.read(response.text())
    (json("name").str, json("followers")("total").num.toInt)
  }

  def main(args: Array[String]): Unit = {
    println("🎧 Fetching playlist data...")

    val playlistData = getPlaylistData()
    val top10Songs = getTop10LongestSongs(playlistData)

    println("\n🎵 Top 10 Longest Songs:")
    top10Songs.foreach { case (name, duration, _) =>
      println(s"$name, $duration ms")
    }

    println("\n👥 Artist Follower Counts:")
    val artistFollowerCounts = top10Songs
      .flatMap(_._3)
      .distinct
      .map(getArtistDetails)
      .sortBy(-_._2)

    artistFollowerCounts.foreach { case (artist, followers) =>
      println(s"$artist : $followers followers")
    }
  }
}
