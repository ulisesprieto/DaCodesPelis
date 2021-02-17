package omar.dguez.dacodesmovies.Fragments.MovieDetail

import android.util.Log
import omar.dguez.dacodesmovies.Client.RestClient
import omar.dguez.dacodesmovies.Models.MovieSummary
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * MovieDetailPresenter
 * @param movieDetail view instance
 */
class MovieDetailPresenter(private val movieDetail: MovieDetailView) : Callback<MovieSummary> {
    /**
     * When a movie it's triggered from the adapter, the client
     * will fetch the movie.
     */
    fun fetchMovie(movieId: Int) {
        RestClient.instance.getMovie(movieId, "a28c4bc831b590dc669ef8a459fdbff7", "es-MX")
            .enqueue(this)
    }

    /**
     * Triggers to the view all the details from
     * the movie selected.
     */
    override fun onResponse(call: Call<MovieSummary>, response: Response<MovieSummary>) {
        if (response.isSuccessful && response.body() !== null) {
            movieDetail.fetchMovie(response.body()!!)
        }
    }

    /**
     * onFailure server Handler
     */
    override fun onFailure(call: Call<MovieSummary>, t: Throwable) {
        Log.d("RA", "${t.message}")
    }

}
