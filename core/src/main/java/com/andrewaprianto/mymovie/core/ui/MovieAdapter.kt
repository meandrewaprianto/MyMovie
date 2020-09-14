package com.andrewaprianto.mymovie.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andrewaprianto.mymovie.core.R
import com.andrewaprianto.mymovie.core.domain.model.Movie
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_list.view.*

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(){

    private var listData = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null

    fun setData(newListData: List<Movie>?){
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder
            = MovieViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false))

    override fun getItemCount(): Int = listData.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class MovieViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(data: Movie){
            with(itemView){
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500/" + data.posterPath)
                    .into(imgPoster)
                txtTitle.text = data.title
            }
        }

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }

}