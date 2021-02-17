package omar.dguez.dacodesmovies.Fragments.RecyclerFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import omar.dguez.dacodesmovies.Models.Movie
import omar.dguez.dacodesmovies.R
import omar.dguez.dacodesmovies.Utils.MovieAdapter


class RecyclerFragment : Fragment(), RecyclerFragmentView {

    private val presenter: RecyclerFragmentPresenter = RecyclerFragmentPresenter(this)
    private var swipe: SwipeRefreshLayout? = null
    private var recycler: RecyclerView? = null
    private val adapt = MovieAdapter(null)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
        swipe?.setOnRefreshListener {
            presenter.getData(true)
        }
    }

    override fun failure(msg: String) {
        Toast.makeText(activity!!.applicationContext, "Error $msg", Toast.LENGTH_SHORT).show();
    }

    override fun fillData(movieList: List<Movie>, fromSwipe: Boolean) {
        adapt.update(movieList)
        adapt.notifyDataSetChanged()
        if (!fromSwipe) {
            recycler?.apply {
                layoutManager = GridLayoutManager(activity, 2)
                adapter = adapt
                hasFixedSize()
            }
        } else {
            swipe?.isRefreshing = false
        }

    }


}