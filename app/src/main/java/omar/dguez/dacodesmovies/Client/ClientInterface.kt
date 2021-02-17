package omar.dguez.dacodesmovies.Client


import omar.dguez.dacodesmovies.Models.MovieSummary
import omar.dguez.dacodesmovies.Models.MovieObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ClientInterface {
    /**
     * Get Movies
     * @see getMovies
     */
    @GET("now_playing?api_key=a28c4bc831b590dc669ef8a459fdbff7&language=es-MX")
    fun getMovies(): Call<MovieObject>
    /**
     * Get Movie
     * @see getMovie
     */
    @GET("{id}?api_key=a28c4bc831b590dc669ef8a459fdbff7&language=es-MX")
    fun getMovie(
        @Path("id") id: Int
    ): Call<MovieSummary>
}