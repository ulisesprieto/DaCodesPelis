package omar.dguez.dacodesmovies.Fragments.RecyclerFragment

import omar.dguez.dacodesmovies.Models.Movie

interface RecyclerFragmentView {
    fun failure(msg: String)
    fun fillData(movieList: List<Movie>)
}