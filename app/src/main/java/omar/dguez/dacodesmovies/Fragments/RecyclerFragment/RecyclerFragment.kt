package omar.dguez.dacodesmovies.Fragments.RecyclerFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import omar.dguez.dacodesmovies.Models.Movie
import omar.dguez.dacodesmovies.R
import omar.dguez.dacodesmovies.Utils.MovieAdapter


class RecyclerFragment : Fragment(), RecyclerFragmentView {

    private val presenter = RecyclerFragmentPresenter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recycler, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.getData()
    }

    override fun failure(msg: String) {
        Toast.makeText(activity!!.applicationContext, "Error $msg", Toast.LENGTH_SHORT).show();
    }

    override fun fillData(movieList: List<Movie>) {
        activity!!.findViewById<RecyclerView>(R.id.recyclerView).apply {
            layoutManager = GridLayoutManager(activity, 2)
            adapter = MovieAdapter(movieList)
            hasFixedSize()
        }
    }


}