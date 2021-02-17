package omar.dguez.dacodesmovies.Fragments.MovieDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import omar.dguez.dacodesmovies.Models.MovieSummary
import omar.dguez.dacodesmovies.R
import omar.dguez.dacodesmovies.View.Main.MainPresenter

/**
 * MovieDetail
 * @param viewPresenter from MainActivity to handle fragment visibility changes
 */
class MovieDetail(private val viewPresenter: MainPresenter) : Fragment(), MovieDetailView {

    private val presenter: MovieDetailPresenter = MovieDetailPresenter(this)
    private var detailImg: ImageView? = null
    private var detailTitle: TextView? = null
    private var detailDuration: TextView? = null
    private var detailDate: TextView? = null
    private var detailClass: TextView? = null
    private var detailGenre: TextView? = null
    private var detailDescr: TextView? = null
    private val url = "https://image.tmdb.org/t/p/w500/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    /**
     * Generates the values for each view instance
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity !== null) {
            this.detailImg = activity?.findViewById(R.id.detailImg)
            this.detailTitle = activity?.findViewById(R.id.detailTitle)
            this.detailDuration = activity?.findViewById(R.id.detailDuration)
            this.detailDate = activity?.findViewById(R.id.detailDate)
            this.detailClass = activity?.findViewById(R.id.detailClass)
            this.detailGenre = activity?.findViewById(R.id.detailGenre)
            this.detailDescr = activity?.findViewById(R.id.detailDescr)
        }
    }

    /**
     * Behavior of go to the main fragment
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                if (activity !== null) {
                    activity?.onBackPressed()
                }
                return true
            }
        }
        return false
    }

    /**
     * Triggered after a call from MainActivity the fetchId method,
     * will render all the data, and set the actionBar to a proper config
     * @param movie It's the summary of the movie
     */
    override fun fetchMovie(movie: MovieSummary) {
        val ab = (activity as AppCompatActivity?)!!.supportActionBar
        if (ab !== null) {
            ab.setDisplayHomeAsUpEnabled(true)
            ab.title = "Detail Movie"
        }
        var genres = ""
        val mins = " minutos"
        Picasso.get().load(this.url + movie.backdrop_path).into(this.detailImg)
        this.detailTitle?.text = movie.title
        this.detailDuration?.text = (movie.runtime.toString() + mins)
        this.detailDate?.text = movie.release_date
        this.detailClass?.text = movie.vote_average.toString()
        for (item in movie.genres) {
            genres += "${item.name}   "
        }
        this.detailGenre?.text = genres
        this.detailDescr?.text = movie.overview
    }

    /**
     * Triggered from the MainActivity
     * to receive the event listener movieId
     */
    fun fetchingId() {
        val value = this.viewPresenter.getMovieId()
        this.presenter.fetchMovie(value)
    }

    override fun failure(msg: String) {
        Toast.makeText(activity!!.applicationContext, msg, Toast.LENGTH_SHORT).show()
    }

}