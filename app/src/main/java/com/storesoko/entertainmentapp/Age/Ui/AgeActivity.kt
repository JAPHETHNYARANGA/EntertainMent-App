package com.storesoko.entertainmentapp.Age.Ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.storesoko.entertainmentapp.Age.Adapter.dataAdapter
import com.storesoko.entertainmentapp.Age.viewModel.dataViewModel
import com.storesoko.entertainmentapp.Movies.Ui.MoviesActivity
import com.storesoko.entertainmentapp.News.Ui.MainActivity
import com.storesoko.entertainmentapp.R
import kotlinx.android.synthetic.main.activity_age.*

class AgeActivity : AppCompatActivity() {

    private lateinit var toggle: ActionBarDrawerToggle

    lateinit var recyclerViewAdapter : dataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_age)



        val drawerLayout: DrawerLayout = findViewById(R.id.ageDrawerLayout)
        val navView : NavigationView = findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.get_news -> startActivity(Intent(applicationContext, MainActivity::class.java))
                R.id.get_movies -> startActivity(Intent(applicationContext, MoviesActivity::class.java))
                R.id.get_age -> startActivity(Intent(applicationContext, AgeActivity::class.java))
            }

            true
        }

        initRecyclerView()
        createData()

    }

    private fun initRecyclerView() {
        RecyclerViewAge.apply {
            layoutManager = LinearLayoutManager(this@AgeActivity)
            recyclerViewAdapter = dataAdapter()
            adapter = recyclerViewAdapter

            val decoration = DividerItemDecoration(applicationContext, VERTICAL)
            addItemDecoration(decoration)
        }
    }

    private fun createData(){
       val viewModel = ViewModelProviders.of(this).get(dataViewModel::class.java)
       viewModel.getRecyclerListDataObserver().observe(this, Observer{
           if(it != null){
               recyclerViewAdapter.setListData(it.data)
               recyclerViewAdapter.notifyDataSetChanged()
           }else{
               Toast.makeText(applicationContext,"Error getting data", Toast.LENGTH_SHORT).show()
           }
       })
        viewModel.makeApiCall()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}