package com.storesoko.entertainmentapp.Movies.Ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.storesoko.entertainmentapp.Age.Ui.AgeActivity
import com.storesoko.entertainmentapp.News.Ui.MainActivity
import com.storesoko.entertainmentapp.R
import kotlinx.android.synthetic.main.activity_movies.*

class MoviesActivity : AppCompatActivity() {
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)


        val drawerLayout: DrawerLayout = findViewById(R.id.moviewsDrawerLayout)
        val navView : NavigationView = findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this, drawerLayout,R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.get_news-> startActivity(Intent(applicationContext, MainActivity::class.java))
                R.id.get_movies -> startActivity(Intent(applicationContext, MoviesActivity::class.java))
                R.id.get_age -> startActivity(Intent(applicationContext, AgeActivity::class.java))
            }

            true
        }

        //bottom Navigation

        moviesbottonNavigationView.setupWithNavController(moviesNavHostFragment.findNavController())

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}