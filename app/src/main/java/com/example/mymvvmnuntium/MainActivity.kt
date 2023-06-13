package com.example.mymvvmnuntium

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController

import com.example.mymvvmnuntium.databinding.ActivityMainBinding

import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val navController=findNavController(R.id.fragmentContainerView)
        binding.bottomNav.setupWithNavController(navController)

        navController.addOnDestinationChangedListener{controller,destination,argument->
            when(destination.id){
                R.id.homeFragment,R.id.accountFragment,R.id.savedFragment,R.id.categorysFragment->binding.bottomNav.visibility=
                    View.VISIBLE
                else->binding.bottomNav.visibility= View.GONE
            }
        }

    }


    }

