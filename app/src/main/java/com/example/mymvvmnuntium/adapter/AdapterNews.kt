package com.example.mymvvmnuntium.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mymvvmnuntium.databinding.ItemPostBinding
import com.example.mymvvmnuntium.databinding.ItemRvBinding
import com.example.mymvvmnuntium.databinding.ItemSaveBinding
import com.example.mymvvmnuntium.databse.ArticleDB


class AdapterNews (var context: Context, val listDb: List<String>,var savedData:SavedData) :
    ListAdapter<ArticleDB, AdapterNews.PostViewHolder>(
        DiffCallback
    ) {
    val listSelected = ArrayList<String>()

    init {
        listSelected.addAll(listDb)
    }


    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<ArticleDB>() {
            override fun areItemsTheSame(oldItem: ArticleDB, newItem: ArticleDB): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ArticleDB, newItem: ArticleDB): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            ItemSaveBinding.inflate(
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
        var binding: ItemSaveBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(article: ArticleDB, position: Int) {
            Glide.with(context).load(article.urlToImage).into(binding.imgCard)
            binding.descTv.text=article.title


            binding.rvSave.setOnClickListener {
                listSelected.remove(article.title)
                savedData.savedData(article,position)
            }
            binding.root.setOnClickListener {
                savedData.nextData(article,position)
            }

        }
    }
    interface SavedData{
        fun savedData(articleDB: ArticleDB,position: Int)
        fun nextData(article: ArticleDB,position: Int)
    }
}