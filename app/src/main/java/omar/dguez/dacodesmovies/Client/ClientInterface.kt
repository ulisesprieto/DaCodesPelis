package omar.dguez.dacodesmovies.Client

import omar.dguez.dacodesmovies.Models.Movie
import omar.dguez.dacodesmovies.Models.MovieObject
import retrofit2.Call
import retrofit2.http.GET

interface ClientInterface {
    /**
     * Get Movies
     * @see getMovies
     */
    @GET("now_playing?api_key=a28c4bc831b590dc669ef8a459fdbff7&language=es-MX")
    fun getMovies(): Call<MovieObject>
}