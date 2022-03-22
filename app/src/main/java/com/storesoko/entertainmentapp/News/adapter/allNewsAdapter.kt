package com.storesoko.entertainmentapp.News.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.storesoko.entertainmentapp.News.Models.Article
import com.storesoko.entertainmentapp.R
import kotlinx.android.synthetic.main.individual_item_news.view.*

class allNewsAdapter :RecyclerView.Adapter<allNewsAdapter.allNewsViewHolder>(){


    private var allNewsItems = ArrayList<Article>()

    fun everythingSetListData(data:ArrayList<Article>){
        this.allNewsItems = data
    }

    class allNewsViewHolder(view:View):RecyclerView.ViewHolder(view) {

        private var everythingTitle = view.news_title
        private var image = view.news_image
        private var description = view.news_description
        private var author = view.news_author


        fun bind(data: Article){
            everythingTitle.text = data.title
            description.text = data.description
            author.text = data.author

            val newsImage = data.urlToImage
            Glide.with(image)
                .load(newsImage)
                .circleCrop()
                .into(image)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): allNewsViewHolder {
        val allNewsInflater = LayoutInflater.from(parent.context).inflate(R.layout.individual_item_news,parent, false)
        return allNewsViewHolder(allNewsInflater)
    }

    override fun onBindViewHolder(holder: allNewsViewHolder, position: Int) {
        holder.bind(allNewsItems[position])
    }

    override fun getItemCount(): Int {
        return allNewsItems.size
    }
}