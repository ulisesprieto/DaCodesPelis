package omar.dguez.dacodesmovies.Fragments.RecyclerFragment

import omar.dguez.dacodesmovies.Client.RestClient
import omar.dguez.dacodesmovies.Models.MovieObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * RecyclerFragmentPresenter
 * @param recyclerFragment as a view instance
 */
class RecyclerFragmentPresenter(private val recyclerFragment: RecyclerFragmentView) :
    Callback<MovieObject> {

    private var fromSwipe: Boolean = false
    private var page: Int = 1

    /**
     * @param fromSwipe if it's called from SwipeRefreshLayout
     * @param nextPage if it's called from the Scroll Listener
     */
    fun getData(fromSwipe: Boolean = false, nextPage: Boolean = false) {
        this.fromSwipe = fromSwipe
        if (nextPage) {
            this.page++
        }
        RestClient.instance.getMovies("a28c4bc831b590dc669ef8a459fdbff7", "es-MX", this.page)
            .enqueue(this)
    }

    /**
     * Handles server response
     * @param call, @param resonse
     */
    override fun onResponse(call: Call<MovieObject>, response: Response<MovieObject>) {
        if (response.isSuccessful && response.body() !== null) {
            this.recyclerFragment.fillData(
                response.body()!!.page,
                response.body()!!.total_pages,
                response.body()!!.result,
                this.fromSwipe
            )
        }
    }

    /**
     * Error handler...
     */
    override fun onFailure(call: Call<MovieObject>, t: Throwable) {
        this.recyclerFragment.failure(t.message.toString())
    }
}