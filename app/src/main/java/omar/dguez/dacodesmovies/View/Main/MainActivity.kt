package omar.dguez.dacodesmovies.View.Main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import omar.dguez.dacodesmovies.Fragments.MovieDetail.MovieDetail
import omar.dguez.dacodesmovies.Fragments.RecyclerFragment.RecyclerFragment
import omar.dguez.dacodesmovies.R

/**
 * Class MainActivity
 */
class MainActivity : AppCompatActivity(), MainView {
    /**
     * The presenter handles the trigger methods,
     * data communication and events in 2 or more fragments
     */
    private val presenter: MainPresenter = MainPresenter(this,-1)
    private val manager: FragmentManager = supportFragmentManager
    private val fragmentOne = RecyclerFragment(presenter)
    private val fragmentTwo = MovieDetail(presenter)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.fragmentBasis()
    }

    /**
     * Handles the fragment additions and hides
     * the ones whom not required.
     */
    private fun fragmentBasis() {
        this.manager
            .beginTransaction()
            .add(R.id.mainContainer, this.fragmentOne, "recycler")
            .add(R.id.mainContainer, this.fragmentTwo, "details")
            .hide(this.fragmentTwo)
            .commit()
    }

    /**
     * Changes the visibility of the fragments
     * @param tagOne Fragment Tag Target
     * @param tagTwo Fragment Tag Destiny
     * @param movieId Required to communicate from Recycler to Details the movieId
     */
    override fun changeFragment(tagOne: String, tagTwo: String, movieId: Int) {
        if (tagOne == "details") {
            this.presenter
            this.fragmentTwo.fetchingId()
        } else {
            this.fragmentOne.onReturn()
        }
        this.manager.beginTransaction()
            .show(this.manager.findFragmentByTag(tagOne)!!)
            .hide(this.manager.findFragmentByTag(tagTwo)!!)
            .commit()
    }

    /**
     * Overwritten to handle the
     * events of the actionBar in the second fragment
     */
    override fun onBackPressed() {
        if (!this.fragmentOne.isHidden) {
            super.onBackPressed()
        } else {
            this.changeFragment("recycler", "details", -1)
        }
    }

}