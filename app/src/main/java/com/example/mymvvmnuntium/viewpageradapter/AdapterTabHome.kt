package com.example.mymvvmnuntium.viewpageradapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mymvvmnuntium.modul.ItemDB
import com.example.mymvvmnuntium.modul.SelectedItem
import com.example.mymvvmnuntium.viewpagerlist.PagerData

class AdapterTabHome (fragmentActivity: FragmentActivity, var listSearch: List<ItemDB>) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return listSearch.size
    }

    override fun createFragment(position: Int): Fragment = ViewpagerHomeTabFragment().apply {
        arguments = Bundle().apply {
            putSerializable("viewTab", listSearch[position])
        }
    }
}