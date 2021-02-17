package omar.dguez.dacodesmovies.Client


import omar.dguez.dacodesmovies.Models.MovieSummary
import omar.dguez.dacodesmovies.Models.MovieObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ClientInterface {
    /**
     * Get Movies
     * @see getMovies
     */
    @GET("now_playing")
    fun getMovies(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") page: Int,
    ): Call<MovieObject>

    /**
     * Get Movie
     * @see getMovie
     */
    @GET("{id}")
    fun getMovie(
        @Path("id") id: Int,
        @Query("api_key") api_key: String,
        @Query("language") language: String,
    ): Call<MovieSummary>
}