package com.storesoko.entertainmentapp.News.Ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.storesoko.entertainmentapp.News.Models.Article
import com.storesoko.entertainmentapp.News.adapter.allNewsAdapter
import com.storesoko.entertainmentapp.News.viewModel.searchNewsViewModel
import com.storesoko.entertainmentapp.R
import kotlinx.android.synthetic.main.fragment_search_news.*
import kotlinx.android.synthetic.main.fragment_search_news.view.*


class SearchNewsFragment : Fragment() {

    lateinit var searchRecyclerAdapter : allNewsAdapter

    private lateinit var searchButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?



    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_search_news, container, false)

        searchButton = view.findViewById(R.id.btnSearch)
        initViewModel(view)

        searchViewModel()


        return view
    }

    private fun initViewModel(view: View) {
        val recyclerView = view.RecyclerViewSearch

        recyclerView.layoutManager = LinearLayoutManager(activity)

        val decoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(decoration)

        searchRecyclerAdapter = allNewsAdapter()

        recyclerView.adapter = searchRecyclerAdapter



    }

    private fun searchViewModel(){
        val viewModel = ViewModelProvider(this).get(searchNewsViewModel::class.java)

        viewModel.getSearchRecyclerListDataObserver().observe(viewLifecycleOwner, Observer {
            if(it != null){
                searchRecyclerAdapter.everythingSetListData(it.articles as ArrayList<Article>)
                searchRecyclerAdapter.notifyDataSetChanged()
            }else{
                Toast.makeText(activity, "Error in getting data", Toast.LENGTH_SHORT).show()
            }
        })


        searchButton.setOnClickListener {
            viewModel.makeSearchApiCall(et_Search.text.toString())
            et_Search.setText("")
        }
    }


}