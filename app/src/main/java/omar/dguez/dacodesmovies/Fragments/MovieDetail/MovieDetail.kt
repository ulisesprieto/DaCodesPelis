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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailImg = activity!!.findViewById(R.id.detailImg)
        detailTitle = activity!!.findViewById(R.id.detailTitle)
        detailDuration = activity!!.findViewById(R.id.detailDuration)
        detailDate = activity!!.findViewById(R.id.detailDate)
        detailClass = activity!!.findViewById(R.id.detailClass)
        detailGenre = activity!!.findViewById(R.id.detailGenre)
        detailDescr = activity!!.findViewById(R.id.detailDescr)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                activity!!.onBackPressed()
                return true
            }
        }
        return false
    }

    override fun failure(msg: String) {
        Toast.makeText(activity!!.applicationContext, msg, Toast.LENGTH_SHORT).show()
    }


    override fun fetchMovie(movie: MovieSummary) {
        val ab = (activity as AppCompatActivity?)!!.supportActionBar
        if (ab !== null) {
            ab.setDisplayHomeAsUpEnabled(true)
            ab.title = "Detail Movie"
        }
        var genres = ""
        val mins = " minutos"
        Picasso.get().load(url + movie.backdrop_path).into(detailImg)
        detailTitle?.text = movie.title
        detailDuration?.text = (movie.runtime.toString() + mins)
        detailDate?.text = movie.release_date
        detailClass?.text = movie.vote_average.toString()
        for (item in movie.genres) {
            genres += "${item.name}   "
        }
        detailGenre?.text = genres
        detailDescr?.text = movie.overview
    }

    fun fetchingId() {
        val value = this.viewPresenter.getMovieId()
        if (value !== null) {
            this.presenter.fetchMovie(value)
        }
    }
}