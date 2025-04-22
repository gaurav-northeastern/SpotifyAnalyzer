cat > src/main/scala/SpotifyPlaylistAnalyzer.scala <<EOF
import requests._
import ujson._

object SpotifyPlaylistAnalyzer {
  val accessToken = "BQCNim2v7cl3L_w-l5gJiFii3-tAwF9a3YunHO2vRvXZR9xu3pLo5zoa2dZO5okL40pzgJ7Kld_NNuZlLHaczmjx2rlk-f8zO2p1QWlnsiatH5Gexuhv5ZxhEUgY35ZCYYnad90H-2-ea8MUPBqY6yKKsIuIZUmVmsidu84ymAMzR0Q0wtacfSZD4MmRa6l5HS7SxFN8B0HKAIJ94qPo_YP4pLhAg0XGQxTPjDpTjGzAmDULt7KVF7z7OPlnx9ymHGi_NCDBTpkcYjZUgUBLpbLhdwwP0oSCIF0HLF_p317qwtKkD72Shjd7Rkdk"
  val playlistId = "37i9dQZF1DX4JAvHpjipBk"

  def main(args: Array[String]): Unit = {
    println("✅ Hello from Spotify analyzer!")
  }
}
EOF
