package com.example.mymvvmnuntium.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

import com.example.mymvvmnuntium.databasemodul.Post
import com.example.mymvvmnuntium.databinding.ItemPostBinding
import com.example.mymvvmnuntium.modul.Article
import com.example.mymvvmnuntium.modul.News

class PostAdapter(var onItemClicked: (News, Int) -> Unit) :
    ListAdapter<News, PostAdapter.PostViewHolder>(
        DiffCallback
    ) {

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<News>() {
            override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
                return oldItem.articles == newItem.articles
            }

            override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            ItemPostBinding.inflate(
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
        private var binding: ItemPostBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(post: News,position: Int) {
            binding.apply {
                tvName.text = post.articles[position].author
                tvDescription.text=post.articles[position].title
            }
            binding.btnSave.setOnClickListener {
                onItemClicked(post,position)
            }

        }
    }
}