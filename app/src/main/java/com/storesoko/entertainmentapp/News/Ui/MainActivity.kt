package com.storesoko.entertainmentapp.News.Ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.storesoko.entertainmentapp.Age.Ui.AgeActivity
import com.storesoko.entertainmentapp.LoginActivity
import com.storesoko.entertainmentapp.Movies.Ui.MoviesActivity
import com.storesoko.entertainmentapp.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var toggle:ActionBarDrawerToggle
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawerLayout)
        val navView : NavigationView = findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        auth = Firebase.auth

        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.get_news -> startActivity(Intent(applicationContext, MainActivity::class.java))
                R.id.get_movies -> startActivity(Intent(applicationContext, MoviesActivity::class.java))
                R.id.get_age -> startActivity(Intent(applicationContext, AgeActivity::class.java))
                R.id.nav_share -> sharereApp()
                R.id.logout -> logout()
            }

            true
        }

        //bottom navigation
        bottonNavigationView.setupWithNavController(newsNavHostFragment.findNavController())

    }

    private fun logout() {
        Firebase.auth.signOut()
        startActivity(Intent(applicationContext, LoginActivity::class.java))
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}