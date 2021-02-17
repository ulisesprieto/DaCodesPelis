package omar.dguez.dacodesmovies.Fragments.MovieDetail

import android.util.Log
import omar.dguez.dacodesmovies.Client.RestClient
import omar.dguez.dacodesmovies.Models.MovieSummary
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailPresenter(private val movieDetail: MovieDetailView) : Callback<MovieSummary> {

    fun fetchMovie(movieId: Int) {
        RestClient.instance.getMovie(movieId,"a28c4bc831b590dc669ef8a459fdbff7","es-MX").enqueue(this)
    }

    override fun onResponse(call: Call<MovieSummary>, response: Response<MovieSummary>) {
        if (response.isSuccessful) {
            movieDetail.fetchMovie(response.body()!!)
        }
    }

    override fun onFailure(call: Call<MovieSummary>, t: Throwable) {
        Log.d("RA","${t.message}")
    }

}
