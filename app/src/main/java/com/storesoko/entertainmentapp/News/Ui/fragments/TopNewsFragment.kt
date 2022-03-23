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
import androidx.recyclerview.widget.LinearLayoutManager
import com.storesoko.entertainmentapp.News.Models.Article
import com.storesoko.entertainmentapp.News.adapter.allNewsAdapter
import com.storesoko.entertainmentapp.News.viewModel.topNewsViewModel
import com.storesoko.entertainmentapp.R
import kotlinx.android.synthetic.main.fragment_top_news.view.*


class TopNewsFragment : Fragment() {

    lateinit var topNewsAdapter : allNewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_top_news, container, false)

        initViewModel(view)

        initTopNewsViewModel()

        return view
    }
    private fun initViewModel(view: View){
        val topNewsRecyclerView = view.topNewsRecyclerView

        topNewsRecyclerView.layoutManager = LinearLayoutManager(activity)

        val decoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        topNewsRecyclerView.addItemDecoration(decoration)

        topNewsAdapter = allNewsAdapter()

        topNewsRecyclerView.adapter = topNewsAdapter
    }

    private fun initTopNewsViewModel(){
        val topNewsViewModel = ViewModelProvider(this).get(topNewsViewModel::class.java)
        topNewsViewModel.getTopNewsDataObserver().observe(viewLifecycleOwner, Observer {
            if(it !=null){
                topNewsAdapter.everythingSetListData(it.articles as ArrayList<Article>)
                topNewsAdapter.notifyDataSetChanged()

            }else{
                Toast.makeText(activity,"Error in getting Data",Toast.LENGTH_SHORT).show()
            }
        })

        topNewsViewModel.maketopNewsApiCall()
    }


}