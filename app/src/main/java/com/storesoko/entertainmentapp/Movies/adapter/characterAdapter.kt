package com.storesoko.entertainmentapp.Movies.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.storesoko.entertainmentapp.Movies.Models.characters.characterModelItem
import com.storesoko.entertainmentapp.R
import kotlinx.android.synthetic.main.individual_character_items.view.*

class characterAdapter : RecyclerView.Adapter<characterAdapter.ViewHolder>() {
    private var characterItems = ArrayList<characterModelItem>()

    fun everythingSetListData(data: ArrayList<characterModelItem>){
        this.characterItems = data
    }

    class ViewHolder(view : View):RecyclerView.ViewHolder(view) {

        private var Name = view.Name
        private var image = view.image
        private var actor = view.Actor
        private var sex = view.gender
        private var dateOfBirth = view.dob

        fun bind(data : characterModelItem){
            Name.text = data.name
            actor.text = data.actor
            sex.text = data.gender
            dateOfBirth.text = data.dateOfBirth

            val imageUrl = data.image

            Glide.with(image)
                .load(imageUrl)
                .into(image)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val everythingInflater = LayoutInflater.from(parent.context).inflate(R.layout.individual_character_items, parent, false)
        return ViewHolder(everythingInflater)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(characterItems[position])
    }

    override fun getItemCount(): Int {
        return characterItems.size
    }
}