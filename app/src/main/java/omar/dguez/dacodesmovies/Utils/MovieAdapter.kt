package omar.dguez.dacodesmovies.Utils


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import omar.dguez.dacodesmovies.Models.Movie
import omar.dguez.dacodesmovies.R
import omar.dguez.dacodesmovies.View.Main.MainPresenter

/**
 * MovieAdapter
 * @param movieList comes from updates/joins and starts in null
 * @param viewPresenter required to trigger the onClick listener over the MainActivity switch fragment logic
 */
class MovieAdapter(private var movieList: List<Movie>?, private val viewPresenter: MainPresenter) :
    RecyclerView.Adapter<MovieAdapter.MyViewHolder>() {
    /**
     * Inflates the viewHolder
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MyViewHolder(inflater, parent, viewPresenter)
    }

    /**
     * Triggers the viewHolder bind for render
     * and event listener purposes, one movie at a time
     */
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if (movieList !== null) {
            val movie: Movie = movieList!![position]
            holder.bind(movie)
        }
    }

    override fun getItemCount(): Int {
        return if (movieList !== null) {
            movieList!!.size
        } else {
            -1
        }
    }

    fun update(newList: List<Movie>) {
        this.movieList = newList;
    }

    fun joinLists(newList: List<Movie>) {
        this.movieList = this.movieList?.plus(newList)
    }

    /**
     * MyViewHolder
     */
    class MyViewHolder(inflater: LayoutInflater, parent: ViewGroup, viewPresenter: MainPresenter) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.movie, parent, false)) {
        private val url = "https://image.tmdb.org/t/p/w500/"
        private val presenter: MainPresenter = viewPresenter;
        private var movieName: TextView? = null
        private var movieDate: TextView? = null
        private var movieRate: TextView? = null
        private var movieImg: ImageView? = null

        init {
            movieName = itemView.findViewById(R.id.movieName)
            movieDate = itemView.findViewById(R.id.movieDate)
            movieRate = itemView.findViewById(R.id.movieRate)
            movieImg = itemView.findViewById(R.id.movieImg)
        }

        fun bind(movie: Movie) {
            movieName?.text = movie.title
            movieDate?.text = movie.release_date
            movieRate?.text = movie.vote_average.toString()
            Picasso.get().load(url + movie.poster_path).into(movieImg)
            movieImg?.setOnClickListener {
                presenter.switchFragment("details", "recycler", movie.id)
            }
        }
    }
}