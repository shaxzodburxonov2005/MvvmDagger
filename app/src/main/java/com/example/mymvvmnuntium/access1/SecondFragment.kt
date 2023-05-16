package com.example.mymvvmnuntium.access1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mymvvmnuntium.R
import com.example.mymvvmnuntium.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {
    lateinit var binding: FragmentSecondBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_second, container, false)
        binding= FragmentSecondBinding.bind(view)

        binding.getbtn.setOnClickListener {
            findNavController().navigate(R.id.action_secondFragment_to_threeFragment)
        }

        return view


    }


}