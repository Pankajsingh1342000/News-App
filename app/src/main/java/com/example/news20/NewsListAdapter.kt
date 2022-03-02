package com.example.news20

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsListAdapter(private val listner: NewsItemClicked) : RecyclerView.Adapter<NewsViewholder>() {

    // CREATING ARRAYLIST OF TYPE News
    private val items: ArrayList<News> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        val viewholder = NewsViewholder(view)
        view.setOnClickListener {
            listner.onItemclicked(items[viewholder.adapterPosition])
        }
        return viewholder
    }

    override fun onBindViewHolder(holder: NewsViewholder, position: Int) {
        val currentItem = items[position]
        holder.titleview.text = currentItem.title
        Glide.with(holder.itemView.context).load(currentItem.imageurl).into(holder.image)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateNews(updatedNews: ArrayList<News>){
        items.clear()
        items.addAll(updatedNews)

        notifyDataSetChanged()
    }
}

class NewsViewholder(itemView: View) : RecyclerView.ViewHolder(itemView){

    // THIS IS HOW WE CAN ACCESS VIEWS OF item_news.xml BY USING item.View.findViewById()
    val titleview : TextView = itemView.findViewById(R.id.title)
    val image :ImageView = itemView.findViewById(R.id.image)
}

interface NewsItemClicked {
    fun onItemclicked(item : News)
}