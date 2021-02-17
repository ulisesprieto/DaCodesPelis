package omar.dguez.dacodesmovies.Fragments.RecyclerFragment

import omar.dguez.dacodesmovies.Client.RestClient
import omar.dguez.dacodesmovies.Models.MovieObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecyclerFragmentPresenter(private val recyclerFragment: RecyclerFragmentView) :
    Callback<MovieObject> {

    private var fromSwipe: Boolean = false;

    fun getData(fromSwipe: Boolean, page: Int = 1) {
        this.fromSwipe = fromSwipe
        RestClient.instance.getMovies("a28c4bc831b590dc669ef8a459fdbff7", "es-MX", page)
            .enqueue(this)
    }

    override fun onResponse(call: Call<MovieObject>, response: Response<MovieObject>) {
        if (response.isSuccessful) {
            recyclerFragment.fillData(response.body()!!.result, fromSwipe)
        }
    }

    override fun onFailure(call: Call<MovieObject>, t: Throwable) {
        recyclerFragment.failure(t.message.toString())
    }
}