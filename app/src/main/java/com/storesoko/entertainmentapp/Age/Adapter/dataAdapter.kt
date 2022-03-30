package com.storesoko.entertainmentapp.Age.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.storesoko.entertainmentapp.Age.models.Data
import com.storesoko.entertainmentapp.R
import kotlinx.android.synthetic.main.individual_age_items.view.*

class dataAdapter : RecyclerView.Adapter<dataAdapter.ViewHolder>() {


    private var items = ArrayList<Data>()

    fun setListData(data: ArrayList<Data>){
        this.items = data
    }

    class ViewHolder(itemView : View):RecyclerView.ViewHolder(itemView) {
        private var Nation = itemView.nation
        private var Population = itemView.population
        private var Year = itemView.year

        fun bind(data: Data){
            Nation.text = data.Nation
            Population.text = data.Population.toString()
            Year.text = data.Year



        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.individual_age_items, parent, false)
        return ViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}