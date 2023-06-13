package com.example.mymvvmnuntium.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mymvvmnuntium.R
import com.example.mymvvmnuntium.databinding.ItemPostBinding
import com.example.mymvvmnuntium.databinding.ItemSeletectedBinding
import com.example.mymvvmnuntium.modul.ItemDB
import com.example.mymvvmnuntium.modul.SelectedItem


class SelectedAdapter(
    var context: Context,
    var list:ArrayList<SelectedItem>,
    var onItemClicked: (SelectedItem, Int) -> Unit
) :
    ListAdapter<SelectedItem, SelectedAdapter.PostViewHolder>(
        DiffCallback
    ) {
    var listSelected = ArrayList<ItemDB>()



    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<SelectedItem>() {
            override fun areItemsTheSame(oldItem: SelectedItem, newItem: SelectedItem): Boolean {
                return oldItem.nameSd == newItem.nameSd
            }

            override fun areContentsTheSame(oldItem: SelectedItem, newItem: SelectedItem): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            ItemSeletectedBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }

    inner class PostViewHolder(val binding: ItemSeletectedBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(selectedItem: SelectedItem, position: Int) {

            binding.let {
//                if (listDB.contains(selectedItem.nameSd)) {
//                    it.selectedCard.setBackgroundColor(Color.BLUE)
//                    it.selectedTv.setTextColor(Color.BLACK)
//                } else {
//                    it.selectedCard.setBackgroundColor(Color.parseColor("#E4E4E4"))
//                }
                it.selectedTv.text=selectedItem.nameSd
                it.imgSD.setImageResource(selectedItem.img)
//                it.selectedTv.text = selectedItem.nameSd

                it.root.setOnClickListener { view ->
                    if (listSelected.contains(selectedItem.getSelected())) {
                        it.selectedCard.setBackgroundColor(Color.parseColor("#E4E4E4"))
                        listSelected.remove(selectedItem.getSelected())

                    } else {
                        it.selectedCard.setBackgroundColor(Color.BLUE)
                        listSelected.add(selectedItem.getSelected())
                    }

                    onItemClicked(selectedItem, position)
                }
            }
        }

    }

}