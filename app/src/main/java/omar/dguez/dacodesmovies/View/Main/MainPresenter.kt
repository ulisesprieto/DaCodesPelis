package omar.dguez.dacodesmovies.View.Main

/**
 * Class MainPresenter
 * @param view Triggers in view the interface methods
 * @param movieId To get/set a movieId from selected movie
 */
class MainPresenter(private val view: MainView, private var movieId: Int) {
    /**
     * Handles the change of
     * fragments in runtime
     */
    fun switchFragment(tagOne: String, tagTwo: String, movieId: Int) {
        this.view.changeFragment(tagOne, tagTwo, movieId)
    }

    /**
     * Set the movieId
     */
    fun setMovieId(movieId: Int) {
        this.movieId = movieId
    }

    /**
     * Get the movieId
     */
    fun getMovieId(): Int = movieId
}