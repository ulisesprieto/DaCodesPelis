package omar.dguez.dacodesmovies.Utils


import android.R.attr
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import omar.dguez.dacodesmovies.Models.Movie
import omar.dguez.dacodesmovies.R


class MovieAdapter(private val movieList: List<Movie>) :
    RecyclerView.Adapter<MovieAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MyViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie: Movie = movieList[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = movieList.size;


    class MyViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.movie, parent, false)) {
        private val context = parent.context;
        private val url = "https://image.tmdb.org/t/p/w500/"
        private var movieName: TextView? = null
        private var movieDate: TextView? = null
        private var movieRate: TextView? = null
        private var movieImg: ConstraintLayout? = null

        init {
            movieName = itemView.findViewById(R.id.movieName)
            movieDate = itemView.findViewById(R.id.movieDate)
            movieRate = itemView.findViewById(R.id.movieRate)
            movieImg = itemView.findViewById(R.id.cardContainer)
        }

        fun bind(movie: Movie) {
            movieName?.text = movie.original_title
            movieDate?.text = movie.release_date
            movieRate?.text = movie.vote_average.toString()
            //do a image
            Picasso.get().load(url + movie.poster_path).into(object : Target {
                override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                    movieImg!!.background = BitmapDrawable(context.resources, bitmap)
                }

                override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                    Log.d("TAG", "FAILED");
                }

                override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                    Log.d("TAG", "Prepare Load");
                }
            })
        }
    }
}