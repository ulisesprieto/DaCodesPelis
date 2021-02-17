package omar.dguez.dacodesmovies.Fragments.MovieDetail


import omar.dguez.dacodesmovies.Models.MovieSummary

/**
 * MovieDetailView
 */
interface MovieDetailView {
    fun failure(msg: String)
    fun fetchMovie(movie: MovieSummary)
}