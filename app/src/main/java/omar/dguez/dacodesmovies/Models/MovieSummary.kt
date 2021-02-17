package omar.dguez.dacodesmovies.Models

import com.google.gson.annotations.SerializedName

data class MovieSummary(
    @SerializedName("adult") val adult: Boolean,
    @SerializedName("backdrop_path") val backdrop_path: String,
    @SerializedName("budget") val budget: Int,
    @SerializedName("genres") val genres: List<Genres>,
    @SerializedName("homepage") val homepage: String,
    @SerializedName("id") val id: Int,
    @SerializedName("imdb_id") val imdb_id: String,
    @SerializedName("original_language") val original_language: String,
    @SerializedName("original_title") val original_title: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("popularity") val popularity: Double,
    @SerializedName("poster_path") val poster_path: String,
    @SerializedName("release_date") val release_date: String,
    @SerializedName("title") val title: String,
    @SerializedName("runtime") val runtime: Int,
    @SerializedName("vote_average") val vote_average: Double,
)


