package omar.dguez.dacodesmovies.View.Main

import omar.dguez.dacodesmovies.Models.Movie

class MainPresenter(private val view: MainView) {
    private var movieId: Int? = null

    fun switchFragment(tagOne: String, tagTwo: String, movieId: Int) {
        view.changeFragment(tagOne, tagTwo, movieId)
    }

    fun fetchMovieId(movieId: Int) {
        this.movieId = movieId
    }

    fun getMovieId(): Int? = movieId
}