package com.example.austin_androidadvance.views.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.austin_androidadvance.data.response.ResultsItem
import com.example.austin_androidadvance.databinding.CardFilmBinding
import com.example.austin_androidadvance.utils.DataMapper.mapGenre
import com.example.austin_androidadvance.utils.Extensions.showImage

class HomeAdapter : ListAdapter<ResultsItem, HomeAdapter.HomeViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = CardFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class HomeViewHolder(private val binding: CardFilmBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(film: ResultsItem) {
            val categoryNames = mapGenre(film.genreIds)

            binding.apply {
                film.posterPath?.let { imgPoster.showImage(itemView.context, it) }
                tvTitle.text = film.title
                tvGenre.text = categoryNames
            }
        }
    }

    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<ResultsItem>() {
            override fun areItemsTheSame(oldItem: ResultsItem, newItem: ResultsItem) =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: ResultsItem, newItem: ResultsItem) =
                oldItem == newItem
        }
    }
}