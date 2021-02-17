package omar.dguez.dacodesmovies.Fragments.RecyclerFragment

import omar.dguez.dacodesmovies.Client.RestClient
import omar.dguez.dacodesmovies.Models.MovieObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecyclerFragmentPresenter(private val recyclerFragment: RecyclerFragmentView) :
    Callback<MovieObject> {
    fun getData() {
        RestClient.instance.getMovies().enqueue(this)
    }

    override fun onResponse(call: Call<MovieObject>, response: Response<MovieObject>) {
        if (response.isSuccessful) {
            recyclerFragment.fillData(response.body()!!.result)
        }
    }

    override fun onFailure(call: Call<MovieObject>, t: Throwable) {
        recyclerFragment.failure(t.message.toString())
    }
}