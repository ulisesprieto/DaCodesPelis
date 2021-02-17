package omar.dguez.dacodesmovies.Models

import com.google.gson.annotations.SerializedName

data class MovieObject(
    @SerializedName("date") val date: DateServer,
    @SerializedName("page") val page: Int,
    @SerializedName("results") val result: List<Movie>,
    @SerializedName("total_pages") val total_pages: Int,
    @SerializedName("total_results") val total_results: Int,
)