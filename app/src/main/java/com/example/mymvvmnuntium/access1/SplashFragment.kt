package com.example.mymvvmnuntium.access1

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mymvvmnuntium.R
import com.example.mymvvmnuntium.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {
    lateinit var binding: FragmentSplashBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(R.id.homeFragment)
        },3000)

        val view= inflater.inflate(R.layout.fragment_splash, container, false)
        binding= FragmentSplashBinding.bind(view)

        val animTop= AnimationUtils.loadAnimation(view.context, R.anim.from_top)
        val animBottom= AnimationUtils.loadAnimation(view.context, R.anim.from_bottom)

        binding.logoImg.animation=animTop
        binding.textLogo.animation=animBottom
        return view

    }


}