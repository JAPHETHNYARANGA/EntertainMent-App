package com.storesoko.entertainmentapp.News.Ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.storesoko.entertainmentapp.News.Models.Article
import com.storesoko.entertainmentapp.News.adapter.allNewsAdapter
import com.storesoko.entertainmentapp.News.viewModel.allNewsViewModel
import com.storesoko.entertainmentapp.R
import kotlinx.android.synthetic.main.fragment_all_news.view.*

class AllNewsFragment : Fragment() {

   private lateinit var newsRecyclerAdapter :   allNewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_all_news, container, false)

        initViewModel(view)

        getViewModel()

        return view
    }

    private fun initViewModel(view: View) {
        val recyclerView = view.allNewsRecycler

        recyclerView.layoutManager = LinearLayoutManager(activity)
      //  recyclerView.layoutManager= GridLayoutManager(activity)


        val decoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(decoration)

        newsRecyclerAdapter = allNewsAdapter()

        recyclerView.adapter = newsRecyclerAdapter

    }



    private fun getViewModel(){
        val viewModel = ViewModelProvider(this).get(allNewsViewModel::class.java)
        viewModel.getAllNewsDataObserver().observe(viewLifecycleOwner, Observer {
            if(it != null){
                newsRecyclerAdapter.everythingSetListData(it.articles as ArrayList<Article>)
                newsRecyclerAdapter.notifyDataSetChanged()
            }else{
                Toast.makeText(activity, "Error in getting data", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.makeAllNewsApiCall()
    }


}