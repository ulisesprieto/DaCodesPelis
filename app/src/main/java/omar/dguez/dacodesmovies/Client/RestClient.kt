package omar.dguez.dacodesmovies.Client

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RestClient {
    private const val BASE_URL =
        "https://api.themoviedb.org/3/movie/"
    private val client = OkHttpClient.Builder().build()
    val instance: ClientInterface by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        retrofit.create(ClientInterface::class.java)
    }
}