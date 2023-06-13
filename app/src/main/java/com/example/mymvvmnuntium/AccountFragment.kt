package com.example.mymvvmnuntium

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.findNavController
import com.example.mymvvmnuntium.databinding.FragmentAccountBinding
import com.example.mymvvmnuntium.sharedPr.MySharedPreference
import com.example.mymvvmnuntium.sharedPr.MySharedPreference.darkMode


class AccountFragment : Fragment() {
   lateinit var binding:FragmentAccountBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_account, container, false)
        binding= FragmentAccountBinding.bind(view)
       MySharedPreference.init(requireActivity())

        if (darkMode!!){
            binding.switchBtn.isChecked=true
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
        binding.switchBtn.setOnCheckedChangeListener { view, isChecked ->
            if (!isChecked){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                darkMode=false



            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                darkMode=true

            }
        }
        binding.cardLanguage.setOnClickListener {
            findNavController().navigate(R.id.languagesFragment)
        }



        return view
    }

}