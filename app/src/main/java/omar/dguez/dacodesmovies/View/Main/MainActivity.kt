package omar.dguez.dacodesmovies.View.Main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import omar.dguez.dacodesmovies.Fragments.MovieDetail.MovieDetail
import omar.dguez.dacodesmovies.Fragments.RecyclerFragment.RecyclerFragment
import omar.dguez.dacodesmovies.R

class MainActivity : AppCompatActivity(), MainView {

    private val manager: FragmentManager = supportFragmentManager
    private val presenter: MainPresenter = MainPresenter(this)

    private val fragmentOne = RecyclerFragment(presenter)
    private val fragmentTwo = MovieDetail(presenter)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        manager
            .beginTransaction()
            .add(R.id.mainContainer, fragmentOne, "recycler")
            .add(R.id.mainContainer, fragmentTwo, "details")
            .hide(fragmentTwo)
            .commit()
    }

    override fun changeFragment(tagOne: String, tagTwo: String, movieId: Int) {
        if (tagOne == "details") {
            presenter.fetchMovieId(movieId)
            fragmentTwo.fetchingId()
        } else {
            fragmentOne.onReturn()
        }
        manager.beginTransaction()
            .show(manager.findFragmentByTag(tagOne)!!)
            .hide(manager.findFragmentByTag(tagTwo)!!)
            .commit()
    }

    override fun onBackPressed() {
        if (!fragmentOne.isHidden) {
            super.onBackPressed()
        } else {
            changeFragment("recycler", "details", -1)
        }
    }

}