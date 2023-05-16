package com.example.mymvvmnuntium.viewpageradapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mymvvmnuntium.R
import com.example.mymvvmnuntium.databinding.FragmentViewPagerMovieBinding
import com.example.mymvvmnuntium.viewpagerlist.PagerData


class ViewPagerMovieFragment : Fragment() {
    lateinit var binding:FragmentViewPagerMovieBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_view_pager_movie, container, false)
        binding= FragmentViewPagerMovieBinding.bind(view)
        val movie=arguments?.getSerializable("pager")as PagerData

        binding.iv.setImageResource(movie.img)

        return view
    }

}