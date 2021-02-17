package omar.dguez.dacodesmovies.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import omar.dguez.dacodesmovies.Fragments.RecyclerFragment.RecyclerFragment
import omar.dguez.dacodesmovies.R

class MainActivity : AppCompatActivity() {

    val manager = supportFragmentManager;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        manager.beginTransaction().add(R.id.mainContainer, RecyclerFragment(), "recyclerFragment")
            .commit()
    }


}