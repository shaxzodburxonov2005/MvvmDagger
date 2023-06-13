package com.example.mymvvmnuntium.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mymvvmnuntium.R
import com.example.mymvvmnuntium.databinding.ItemPostBinding
import com.example.mymvvmnuntium.databse.ArticleDB
import com.example.mymvvmnuntium.modul.SelectedItem
import com.example.mymvvmnuntium.modul.newss.Article

class PostAdapter(
    var context: Context,
    val listDb: List<String>,
    var clickItem:itemClick
) :
    ListAdapter<Article, PostAdapter.PostViewHolder>(
        DiffCallBack()
    ) {
    val listSelected = ArrayList<String>()

    init {
        listSelected.addAll(listDb)
    }

    class DiffCallBack : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
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

    inner class PostViewHolder(val binding: ItemPostBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(article: Article, position: Int) {
            Log.d("AAAA", "bind:$position $article ")
            binding.let {

                if (listDb.contains(article.title))
                    it.save.setImageResource(R.drawable.backstop_icon)
                else
                    it.save.setImageResource(R.drawable.save_icon)

                Glide.with(context).load(article.urlToImage).into(it.imgAll)
                it.textAll.text = article.title

                it.save.setOnClickListener { view ->
                    if (listSelected.contains(article.title)) {
                        it.save.setImageResource(R.drawable.save_icon)
                        listSelected.remove(article.title)
                    } else {
                        it.save.setImageResource(R.drawable.backstop_icon)
                        listSelected.add(article.title ?: "")
                    }
                    clickItem.save(article,position)

                }
                it.root.setOnClickListener {
                    clickItem.rootI(article,position)
                }

            }
        }
    }
    interface itemClick{
        fun rootI(article: Article,position: Int)
        fun save(article: Article,position: Int)
    }
}