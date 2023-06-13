package com.example.mymvvmnuntium.access1

import android.os.Bundle
import android.os.CountDownTimer
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
import com.example.mymvvmnuntium.sharedPr.MySharedPreference

class SplashFragment : Fragment() {
    lateinit var binding: FragmentSplashBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        MySharedPreference.init(requireContext())



        object : CountDownTimer(3_000,1_000){
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                if (MySharedPreference.editSplash==true){
                    findNavController().navigate(R.id.homeFragment)
                }else{
                    findNavController().navigate(R.id.firstFragment)
                }
            }
        }.start()

        val view= inflater.inflate(R.layout.fragment_splash, container, false)
        binding= FragmentSplashBinding.bind(view)

        val animTop= AnimationUtils.loadAnimation(view.context, R.anim.from_top)
        val animBottom= AnimationUtils.loadAnimation(view.context, R.anim.from_bottom)

        binding.logoImg.animation=animTop
        binding.textLogo.animation=animBottom
        return view

    }


}