package com.example.mymvvmnuntium.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mymvvmnuntium.R
import com.example.mymvvmnuntium.databinding.ItemPostBinding
import com.example.mymvvmnuntium.databinding.ItemSaveBinding
import com.example.mymvvmnuntium.modul.newss.Article

class SearchAdapter(val list:List<Article>,val context: Context):RecyclerView.Adapter<SearchAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        fun onBind(article: Article){
            val bind=ItemPostBinding.bind(itemView)
            Glide.with(context).load(article.urlToImage).into(bind.imgAll)
            bind.textAll.text=article.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_post,parent,false))
    }

    override fun getItemCount(): Int=list.size


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(list[position])
    }
}