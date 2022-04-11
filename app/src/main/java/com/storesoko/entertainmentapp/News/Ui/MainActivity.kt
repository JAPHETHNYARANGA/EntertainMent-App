package com.storesoko.entertainmentapp.News.Ui

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.google.android.play.core.review.ReviewManagerFactory
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
                R.id.nav_rate ->rateApplication()
                R.id.logout -> logout()
            }

            true
        }

        //bottom navigation
        bottonNavigationView.setupWithNavController(newsNavHostFragment.findNavController())

    }

    private fun rateApplication() {
         val manager = ReviewManagerFactory.create(applicationContext)
        val request = manager.requestReviewFlow()
        request.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // We got the ReviewInfo object
                val reviewInfo = task.result
                val flow = manager.launchReviewFlow(this, reviewInfo)
                flow.addOnCompleteListener { _ ->
                    // The flow has finished. The API does not indicate whether the user
                    // reviewed or not, or even whether the review dialog was shown. Thus, no
                    // matter the result, we continue our app flow.
                    Toast.makeText(applicationContext, "Already Rated", Toast.LENGTH_SHORT).show()
                }
            } else {
                // There was some problem, log or handle the error code.
               Toast.makeText(this,"Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }

//            val uri : Uri = Uri.parse("market://details?id=com.storesoko.entertainmentapp")
//            val goToMarket = Intent(Intent.ACTION_VIEW, uri)
//
//            goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY or
//                        Intent.FLAG_ACTIVITY_NEW_DOCUMENT or
//                        Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
//        try {
//            startActivity(goToMarket)
//        }catch (e: ActivityNotFoundException){
//            startActivity(Intent(Intent.ACTION_VIEW,
//            Uri.parse("https://play.google.com/store/apps/details?id=com.storesoko.entertainmentapp")))
//        }
    }



    private fun sharereApp() {
        val share = Intent.createChooser(Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.storesoko.entertainmentapp")
            // (Optional) Here we're setting the title of the content
            putExtra(Intent.EXTRA_TITLE, "Share Application")

            // (Optional) Here we're passing a content URI to an image to be displayed
            flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
        }, null)
        startActivity(share)

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