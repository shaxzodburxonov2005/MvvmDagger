package com.example.mymvvmnuntium

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.mymvvmnuntium.databinding.FragmentSavedDataBinding
import com.example.mymvvmnuntium.databse.ArticleDB


class SavedDataFragment : Fragment() {
    lateinit var binding:FragmentSavedDataBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_saved_data, container, false)
        binding= FragmentSavedDataBinding.bind(view)

        val m=arguments?.getSerializable("data") as ArticleDB
        Glide.with(requireContext()).load(m.urlToImage).into(binding.img)
        binding.text.text=m.description
        binding.collapsingToolbar.title=m.title

        return view
    }

}