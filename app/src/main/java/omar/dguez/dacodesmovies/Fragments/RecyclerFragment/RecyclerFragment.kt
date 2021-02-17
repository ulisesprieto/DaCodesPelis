package omar.dguez.dacodesmovies.Fragments.RecyclerFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import omar.dguez.dacodesmovies.Models.Movie
import omar.dguez.dacodesmovies.R
import omar.dguez.dacodesmovies.Utils.MovieAdapter
import omar.dguez.dacodesmovies.View.Main.MainPresenter

/**
 * RecyclerFragment
 * @param viewPresenter from MainActivity
 */
class RecyclerFragment(viewPresenter: MainPresenter) : Fragment(),
    RecyclerFragmentView {
    /**
     * The presenter triggers the retrofit functions and
     * handles the async tasks.
     */
    private val presenter: RecyclerFragmentPresenter = RecyclerFragmentPresenter(this)
    private val adapt = MovieAdapter(null, viewPresenter)
    private var swipe: SwipeRefreshLayout? = null
    private var recycler: RecyclerView? = null

    /**
     * I ensure with the onReturn that the
     * actionBar only will have the MovieApp title
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        onReturn()
        return inflater.inflate(R.layout.fragment_recycler, container, false)
    }

    /**
     * Tells the presenter to fetch the first
     * page, and that it's not coming from swap
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.presenter.getData(false)
    }

    /**
     * When the view it's created, we assign the
     * view instances, configurations and the event listeners
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.swipe = view.findViewById(R.id.swipeRefresh)
        this.recycler = view.findViewById(R.id.recyclerView)
        this.recycler?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    val layoutManager = recyclerView.layoutManager as GridLayoutManager
                    val lastItem = layoutManager.findLastCompletelyVisibleItemPosition()
                    if (layoutManager.itemCount <= lastItem + 2) {
                        presenter.getData(fromSwipe = false, nextPage = true)
                    }
                }
            }
        })
        this.recycler?.apply {
            layoutManager = GridLayoutManager(activity!!.applicationContext, 2)
            adapter = adapt
            setHasFixedSize(true)
        }
        this.swipe?.setOnRefreshListener {
            this.presenter.getData(true)
        }
    }

    /**
     * Determines if the newList needs to be renewed or
     * joined with the old one.
     */
    override fun fillData(current: Int, last: Int, movieList: List<Movie>, fromSwipe: Boolean) {
        if (fromSwipe) {
            this.swipe?.isRefreshing = false
        }
        if (current == 1) {
            this.adapt.update(movieList)
            this.adapt.notifyDataSetChanged()
        } else {
            this.adapt.joinLists(movieList)
            this.adapt.notifyDataSetChanged()
        }
    }

    /**
     * Handles the actionBar behavior in this
     * view
     */
    fun onReturn() {
        val ab: ActionBar? = (activity as AppCompatActivity?)!!.supportActionBar
        setHasOptionsMenu(false)
        if (ab !== null) {
            ab.setDisplayHomeAsUpEnabled(false)
            ab.title = "Movie App"
        }
    }

    /**
     * Handler for failures
     */
    override fun failure(msg: String) {
        //
    }
}