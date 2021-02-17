package omar.dguez.dacodesmovies.Fragments.RecyclerFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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


class RecyclerFragment(viewPresenter: MainPresenter) : Fragment(),
    RecyclerFragmentView {

    private val presenter: RecyclerFragmentPresenter = RecyclerFragmentPresenter(this)
    private val adapt = MovieAdapter(null, viewPresenter)
    private var swipe: SwipeRefreshLayout? = null
    private var recycler: RecyclerView? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        onReturn()
        return inflater.inflate(R.layout.fragment_recycler, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.getData(false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipe = view.findViewById(R.id.swipeRefresh)
        recycler = view.findViewById(R.id.recyclerView)
        recycler?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) { // only when scrolling up
                    val visibleThreshold = 2
                    val layoutManager = recycler?.layoutManager as GridLayoutManager
                    val lastItem = layoutManager.findLastCompletelyVisibleItemPosition()
                    val currentTotalCount = layoutManager.itemCount
                    if (currentTotalCount <= lastItem + visibleThreshold) {
                        presenter.getData(fromSwipe = false, nextPage = true)
                    }
                }
            }
        })
        recycler?.apply {
            layoutManager = GridLayoutManager(activity!!.applicationContext, 2)
            adapter = adapt
            setHasFixedSize(true)
        }
        swipe?.setOnRefreshListener {
            presenter.getData(true)
        }
    }

    override fun failure(msg: String) {
        Toast.makeText(activity!!.applicationContext, "Error $msg", Toast.LENGTH_SHORT).show();
    }

    override fun fillData(current: Int, last: Int, movieList: List<Movie>, fromSwipe: Boolean) {
        if (fromSwipe) {
            swipe?.isRefreshing = false
        }
        if (current == 1) {
            adapt.update(movieList)
            adapt.notifyDataSetChanged()
        } else {
            adapt.joinLists(movieList)
            adapt.notifyDataSetChanged()
        }
    }

    fun onReturn() {
        val ab: ActionBar? = (activity as AppCompatActivity?)!!.supportActionBar
        setHasOptionsMenu(false)
        if (ab !== null) {
            ab.setDisplayHomeAsUpEnabled(false)
            ab.title = "Movie App"
        }
    }

}