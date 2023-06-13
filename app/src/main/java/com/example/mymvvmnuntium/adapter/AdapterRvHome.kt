package com.example.mymvvmnuntium.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mymvvmnuntium.databinding.ItemRvBinding
import com.example.mymvvmnuntium.databinding.ItemSeletectedBinding
import com.example.mymvvmnuntium.modul.SelectedItem
import com.example.mymvvmnuntium.modul.newss.Article

class AdapterRvHome (var context: Context,var onItemClicked: (Article, Int) -> Unit) :
    ListAdapter<Article, AdapterRvHome.PostViewHolder>(
        DiffCallback
    ) {


    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            ItemRvBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }

    inner class PostViewHolder(
        var binding: ItemRvBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(article: Article, position: Int) {
            binding.apply {
                binding.textDecs.text=article.title

            }
            binding.imgMain.setOnClickListener {
                onItemClicked(article, position)
            }

        }
    }
}