package omar.dguez.dacodesmovies.Models

import com.google.gson.annotations.SerializedName

data class DateServer(
    @SerializedName("maximum") val maximum: String,
    @SerializedName("minimum") val minimum: String
)
