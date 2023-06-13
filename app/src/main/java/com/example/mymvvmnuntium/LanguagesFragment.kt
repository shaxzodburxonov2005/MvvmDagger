package com.example.mymvvmnuntium

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dolatkia.animatedThemeManager.AppTheme
import com.dolatkia.animatedThemeManager.ThemeFragment
import com.example.mymvvmnuntium.databinding.FragmentLanguagesBinding
import com.example.mymvvmnuntium.langua.LocaleHelper
import com.example.mymvvmnuntium.langua.MainView
import com.example.mymvvmnuntium.theme.MyAppTheme


class LanguagesFragment : Fragment() {
    lateinit var binding: FragmentLanguagesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         val view =inflater.inflate(R.layout.fragment_languages, container, false)
        binding= FragmentLanguagesBinding.bind(view)



        return view
    }



}