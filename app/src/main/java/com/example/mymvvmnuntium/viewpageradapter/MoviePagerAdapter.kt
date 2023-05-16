package com.example.mymvvmnuntium.viewpageradapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mymvvmnuntium.viewpagerlist.PagerData



class MoviePagerAdapter(fragmentActivity: FragmentActivity, var listSearch: List<PagerData>) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return listSearch.size
    }

    override fun createFragment(position: Int): Fragment = ViewPagerMovieFragment().apply {
        arguments = Bundle().apply {
            putSerializable("pager",listSearch[position])
        }
    }

}