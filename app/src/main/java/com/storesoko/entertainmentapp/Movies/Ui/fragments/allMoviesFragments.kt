package com.storesoko.entertainmentapp.Movies.Ui.fragments

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
import com.storesoko.entertainmentapp.Movies.adapter.characterAdapter
import com.storesoko.entertainmentapp.Movies.viewModels.characterViewModel
import com.storesoko.entertainmentapp.R
import kotlinx.android.synthetic.main.fragment_all_movies_fragments.view.*

class allMoviesFragments : Fragment() {

    lateinit var characterAdapter : characterAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_all_movies_fragments, container, false)

        initViewModel(view)

        initView()

        return view
    }

    private fun initViewModel(view: View) {
        val recyclerView = view.RecyclerViewCharacter
        recyclerView.layoutManager = LinearLayoutManager(activity)

        val decoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(decoration)

        characterAdapter = characterAdapter()

        recyclerView.adapter = characterAdapter

    }

    private fun initView(){
        val viewModel = ViewModelProvider(this).get(characterViewModel::class.java)
        viewModel.getCharacterDataObserver().observe(viewLifecycleOwner, Observer {
            if(it != null){
                characterAdapter.everythingSetListData(it.characterModelItem)
                characterAdapter.notifyDataSetChanged()
            }else{
                Toast.makeText(activity, "Error in getting data", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.makeCharacterApiCall()
    }


}


