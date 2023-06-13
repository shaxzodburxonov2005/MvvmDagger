package com.example.mymvvmnuntium.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mymvvmnuntium.databinding.ItemPostBinding
import com.example.mymvvmnuntium.modul.newss.Article

class LastAdapter(val list: List<Article>) :
    RecyclerView.Adapter<LastAdapter.LastViewHolder>() {

    inner class LastViewHolder(val binding: ItemPostBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(article: Article, position: Int) {
            Log.d("AAAA", "bind:$position $article ")
            binding.let {
//                if (article.title == listDb[position].title)
//                    it?.save?.setImageResource(R.drawable.backstop_icon)
//                else
//                    it?.save?.setImageResource(R.drawable.save_icon)

                Glide.with(itemView.context).load(article.urlToImage).into(it.imgAll)
                it.textAll.text = article.title
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LastViewHolder {
        return LastViewHolder(
            ItemPostBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: LastViewHolder, position: Int) {
        holder.bind(list[position], position)
    }
}