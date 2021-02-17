package omar.dguez.dacodesmovies.Fragments.MovieDetail


import omar.dguez.dacodesmovies.Models.MovieSummary

interface MovieDetailView {
    fun failure(msg: String)
    fun fetchMovie(movie: MovieSummary)
}